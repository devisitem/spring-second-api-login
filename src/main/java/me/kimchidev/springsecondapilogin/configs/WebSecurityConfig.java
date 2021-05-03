package me.kimchidev.springsecondapilogin.configs;

import lombok.RequiredArgsConstructor;
import me.kimchidev.springsecondapilogin.domain.MemberDetails;
import me.kimchidev.springsecondapilogin.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .antMatchers(HttpMethod.POST,"/user/join").permitAll()
                .antMatchers("/user/**").hasAuthority("CERTIFIED_USER")
                .anyRequest().authenticated()

                .and()
                .formLogin().disable()
                .logout();


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
