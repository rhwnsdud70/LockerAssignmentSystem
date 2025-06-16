package yu22112077.LockerAssignmentSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // h2-console 사용을 위한 설정
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/auth/**", "/api/**"))
                .headers(headers -> headers.frameOptions().sameOrigin())

                // 경로별 접근 권한
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/js/**", "/css/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/upload-excel").authenticated()
                        .requestMatchers("/api/**").authenticated() // 나머지 API는 인증 필요
                        .anyRequest().permitAll()
                )

                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
