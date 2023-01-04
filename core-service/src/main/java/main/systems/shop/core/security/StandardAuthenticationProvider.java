package main.systems.shop.core.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StandardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatches = bcrypt.matches(authentication.getCredentials().toString(), userDetails.getPassword());

        if (!isPasswordMatches) {
//        if (!Objects.equals(userDetails.getPassword(), authentication.getCredentials())) {
            log.info("Bad credentials for {}", userDetails.getUsername());
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(username);
    }
}
