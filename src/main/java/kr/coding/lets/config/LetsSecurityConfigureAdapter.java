package kr.coding.lets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.coding.lets.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class LetsSecurityConfigureAdapter extends WebSecurityConfigurerAdapter{
    private static final String[] IGNORED_RESOURCE_LIST = new String[] {"/v2/api-docs","/swagger-resources/**",
            "/swagger-ui/*", "/api/v1/authenticate", 
            "/api/v1/member/add", "/api/v1/get_access_token",
            "/resources/**", "/robots.txt", "/sitemap.xml"};
    @Autowired
    private LetsFilter letsFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST).permitAll()
			.antMatchers("/auth/**", "/oauth2/**").permitAll()            
            .and().logout().logoutSuccessUrl("/")
            .and()
            .formLogin().loginPage("/signin")
            .and()
            .oauth2Login().successHandler(new LoginSuccessHandler())
                .userInfoEndpoint().userService(customOAuth2UserService);
		http.addFilterBefore(letsFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(IGNORED_RESOURCE_LIST);
	}
    
}
