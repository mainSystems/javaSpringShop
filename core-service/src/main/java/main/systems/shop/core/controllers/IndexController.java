package main.systems.shop.core.controllers;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.entity.dto.AuthRequestDto;
import main.systems.shop.core.persistence.entity.dto.AuthResponseDto;
import main.systems.shop.core.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    private String index() {
        return "index";
    }

    @GetMapping("/resources")
    @ResponseBody
    private String resources() {
        return "Content";
    }

    @PostMapping("/auth")
    @ResponseBody
    public AuthResponseDto authorize(@RequestBody AuthRequestDto request) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//        log.info("Auth request: [{}, {}]", request.getUsername(), request.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String jwtToken = jwtService.generateJwtToken(user);

            return new AuthResponseDto(jwtToken);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }
}
