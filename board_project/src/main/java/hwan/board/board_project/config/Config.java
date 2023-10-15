package hwan.board.board_project.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableWebSecurity
@Configuration
public class Config {
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                    authorize -> authorize
                    .requestMatchers(new AntPathRequestMatcher("/h2/**")
                                    ,new AntPathRequestMatcher("/signin")
                                    ,new AntPathRequestMatcher("/signup")
                                    ,new AntPathRequestMatcher("/checkUsername.do")
                                    ,new AntPathRequestMatcher("/checkNickname.do")
                                    ,new AntPathRequestMatcher("/")).permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/signin")
                    .passwordParameter("password")
                    .loginProcessingUrl("/signin")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/signin")
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/signout.do")
                    .logoutSuccessUrl("/")
                )
                .csrf(CsrfConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .build();                
    }
}
