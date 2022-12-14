package main.systems.configs;

import lombok.extern.slf4j.Slf4j;
import main.systems.persistence.services.ServiceUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class securityConfig {
    private ServiceUser serviceUser;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Auth provider");
       return  httpSecurity.authorizeHttpRequests()
               .antMatchers("/**").authenticated()
               .antMatchers("/**", "/app/api/v1/shop/**").hasAnyRole("USER", "ADMIN")
               .antMatchers("/api/v1/shop/admin/**").hasAnyAuthority("RIGHT_ADMIN")
               .anyRequest().permitAll()
               .and()
               .formLogin()
               .and()
               .logout()
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID")
               .and()
               .csrf().disable()
               .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(serviceUser);
        return authenticationProvider;
    }

}
