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

//@EnableWebSecurity
//@RequiredArgsConstructor
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class securityConfig {
//public class securityConfig extends WebSecurityConfigurerAdapter {
    private ServiceUser serviceUser;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    log.info("Auth provider");
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .antMatchers("/**", "/app/api/v1/shop/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/api/v1/shop/admin/**").hasAnyAuthority("RIGHT_ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Auth provider");
        httpSecurity.csrf().disable();
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
