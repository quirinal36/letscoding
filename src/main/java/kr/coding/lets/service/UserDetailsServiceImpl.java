package kr.coding.lets.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.coding.lets.model.MyUserDetails;
import kr.coding.lets.model.User;
import kr.coding.lets.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhone(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("could not found user");
        }
        return new MyUserDetails(user.get());
    }
    
}
