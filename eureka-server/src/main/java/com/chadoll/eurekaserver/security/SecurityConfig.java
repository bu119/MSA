package com.chadoll.eurekaserver.security;

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

// 시큐리티 커스텀 config를 작성하여 특정 경로에 대해 접근을 막는다.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 암호화 시키기
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // 특정 경로에 대해서 보안 설정하기
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 무시
        http.csrf((auth) -> auth.disable());

        // 모든 경로에 대해 권한이 필요하게 한다.
        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated());

        // 로그인 방식은 http 베이직 방식
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 서버에 접속할 수 있는 비밀번호와 아이디 등록하기 (인 메모리 타입)
    @Bean
    public UserDetailsService userDetailsService() {
        // 시용자1 생성
        UserDetails user1 = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("1234a"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }
}
