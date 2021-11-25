package kr.coding.lets.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.coding.lets.model.Roles;
import kr.coding.lets.model.SessionUser;
import kr.coding.lets.model.User;
import kr.coding.lets.model.enums.UserRole;
import kr.coding.lets.model.oauth.OAuthAttributes;
import kr.coding.lets.repository.UserRepository;
import kr.coding.lets.service.RolesService;

import javax.servlet.http.HttpSession;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    @Autowired
    private final RolesService rolesService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);
        final String GUEST_ROLE = new StringBuilder("ROLE_").append(UserRole.GUEST.name().toUpperCase()).toString();

        grantedAuthorities.add(new SimpleGrantedAuthority(GUEST_ROLE));
        return new DefaultOAuth2User(grantedAuthorities,
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<Roles> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }

    // 유저 생성 및 수정 서비스 로직
    private User saveOrUpdate(OAuthAttributes attributes){
        String phone = attributes.getPhone();
        if(! (phone.indexOf("+82")<0)){
            phone = phone.substring(3, phone.length()).trim();
            phone = "0" + phone;
        }
        
        User user = userRepository.findByPhone(phone)
                .map(entity -> entity.update(attributes.getEmail(), attributes.getPicture()))
                .orElse(attributes.toEntity(phone));
        
        return userRepository.save(user);
    }
}
