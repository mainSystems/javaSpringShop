package main.systems.shop.core.security.configs;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.repositories.CustomerRepository;
import main.systems.shop.core.persistence.services.ServiceUser;
import main.systems.shop.core.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class securityConfig {
    private ServiceUser serviceUser;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().antMatchers(
                "/auth",
                "/",
                "/index.js",
                "/css/**",
                "/img/**",
                "/ws/**",
                "/api/v1/shop/**"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Auth provider");
        return httpSecurity.authorizeHttpRequests()
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .antMatchers("/**", "/app/api/v1/shop/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/api/v1/shop/admin/**").hasAnyAuthority("RIGHT_ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .headers().frameOptions().disable()
//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();

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
                .cors().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(serviceUser);
//        return authenticationProvider;
//    }

    @Bean
    UserDetailsService userDetailsService() {
        return new ServiceUser(customerRepository);
//        UserDetails user = User.builder()
//                .username("user")
//                .password("pass")
//                .authorities("RIGHT_ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }

}
