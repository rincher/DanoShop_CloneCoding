package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    //여기에 .addFilterBefore()로 token 검증을 해줬어야 함
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.headers().frameOptions().disable();
        http.authorizeRequests().anyRequest().permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().disable();
    }

    //허용할 CORS header
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //모든 해더
        configuration.addAllowedHeader("*");
        //모든 요청에
        configuration.addAllowedMethod("*");
        //모든 다른 오리진으로 부터의 요청을 다 허용
        configuration.addAllowedOrigin("*");
//        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        //어떤 주소를 허용 할 것인지
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //모든 url을 허용 하는 패턴
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
