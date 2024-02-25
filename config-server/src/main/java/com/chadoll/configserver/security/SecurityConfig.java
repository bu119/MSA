package com.chadoll.configserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// 시큐리티 설정
@EnableWebSecurity
public class SecurityConfig {

    // 특정 경로 요정 및 내부 아이디, 비밀번호 설정

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // 시큐리티 필터
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF(Cross-Site Request Forgery) 보호 기능을 비활성화
        // CSRF 공격으로부터 애플리케이션을 보호
        http.csrf((auth) -> auth.disable());
        // 모든 HTTP 요청에 대해 인증 필요를 설정함
        // 인증된 사용자만이 모든 요청에 접근할 수 있다.
        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated());
        // HTTP Basic 인증을 설정 (간단한 인증 방법 중 하나)
        // 사용자 이름과 비밀번호를 기반으로 클라이언트를 인증
        http.httpBasic(Customizer.withDefaults());
        // Spring 애플리케이션의 보안을 적용
        return http.build();
    }

    // 내부에 접근할 수 있는 아이디, 비밀번호 설정 (시큐리티 영속성 이용)
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("1234a"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }

}
