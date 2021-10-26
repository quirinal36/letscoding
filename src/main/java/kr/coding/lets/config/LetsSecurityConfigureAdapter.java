package kr.coding.lets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class LetsSecurityConfigureAdapter extends WebSecurityConfigurerAdapter{
    @Autowired
    private LetsFilter letsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST).authenticated()
			.antMatchers("/auth/**", "/oauth2/**").permitAll();
		http.addFilterBefore(letsFilter, UsernamePasswordAuthenticationFilter.class);
    }
}