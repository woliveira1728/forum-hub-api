package com.woliveira.forum_hub_challenger.controller;


import com.woliveira.forum_hub_challenger.dtos.UserDetailsDto;
import com.woliveira.forum_hub_challenger.dtos.UserDto;
import com.woliveira.forum_hub_challenger.dtos.UserIdDto;
import com.woliveira.forum_hub_challenger.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @Transactional
    public ResponseEntity<String> createUser(
            @RequestBody @Valid UserDto userDto, UriComponentsBuilder uriComponentsBuilder
    ) {
        UUID userId = userService.saveUser(userDto);
        var uri = uriComponentsBuilder.path("/users/{id}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(uri)
                .body("User created successfully");
    }

    @GetMapping
    public ResponseEntity<Page<UserIdDto>> userList(Pageable pageable) {
        Page<UserIdDto> pageUsers = userService.getAllUsers(pageable);

        return ResponseEntity.ok(pageUsers);
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<String> updateUser(
            @PathVariable UUID userId, @RequestBody UserIdDto userIdDto
    ) {
        userService.updateUser(userId, userIdDto);

        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> userDetail(@PathVariable UUID userId) {
        Optional<UserDetailsDto> optionalUserDetailsDto = userService.userDetail(userId);

        return optionalUserDetailsDto
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
