package net.datasa.trade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] PUBLIC_URLS = {
            "/"
            , "/css/**" 
            , "/js/**"
            , "/member/login"
            , "/member/registerForm"
            , "/member/register"
  
    };

    @Bean 
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
 
            .authorizeHttpRequests(author -> author
                .requestMatchers(PUBLIC_URLS).permitAll()   
                .anyRequest().authenticated()              
            )
            .httpBasic(Customizer.withDefaults())

            .formLogin(formLogin -> formLogin
                    .loginPage("/member/loginForm")               
                    .usernameParameter("id")              
                    .passwordParameter("password")          
                    .loginProcessingUrl("/member/login")           
                    .defaultSuccessUrl("/")                 
                    .failureUrl("/member/login?error=true") 
                    .permitAll()                           
            )

            //로그아웃 설정
            .logout(logout -> logout
                    .logoutUrl("/logout")                 
                    .logoutSuccessUrl("/")                 
            );

        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
    
    
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
