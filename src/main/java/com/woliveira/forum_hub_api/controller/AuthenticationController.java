package com.woliveira.forum_hub_api.controller;

import com.woliveira.forum_hub_api.dtos.AuthenticationDto;
import com.woliveira.forum_hub_api.infra.security.TokenJWTDto;
import com.woliveira.forum_hub_api.infra.security.TokenService;
import com.woliveira.forum_hub_api.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var tokenJwt = tokenService.tokenGenerator((User) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWTDto(tokenJwt));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
