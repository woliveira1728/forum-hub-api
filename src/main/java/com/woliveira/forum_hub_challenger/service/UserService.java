package com.woliveira.forum_hub_challenger.service;

import com.woliveira.forum_hub_challenger.dtos.UserDetailsDto;
import com.woliveira.forum_hub_challenger.dtos.UserDto;
import com.woliveira.forum_hub_challenger.dtos.UserIdDto;
import com.woliveira.forum_hub_challenger.model.User;
import com.woliveira.forum_hub_challenger.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UUID saveUser(UserDto userDto) {

        if (userDto.email() == null || userDto.password() == null) {
            throw new IllegalArgumentException("E-mail and password fields are required");
        }
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalStateException("E-mail already exists");
        }

        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        User newUser = userRepository.save(user);

        return newUser.getId();

    }

    public Page<UserIdDto> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(user -> new UserIdDto(user.getId(), user.getName(), user.getEmail(), user.isStatus()));
    }

    @Transactional
    public void updateUser(UUID userId, UserIdDto userIdDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User isUser = optionalUser.get();
        isUser.setName(userIdDto.name());
        isUser.setEmail(userIdDto.email());
        isUser.setStatus(userIdDto.status() ? userIdDto.status() : isUser.isStatus());
        userRepository.save(isUser);

    }

    @Transactional
    public void deleteUser(UUID userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User isUser = optionalUser.get();
        isUser.setStatus(false);
        userRepository.save(isUser);
    }

    public User findByEmail(String email) {

        User user = (User) userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        return user;

    }

    public Optional<UserDetailsDto> userDetail(UUID id) {

        Optional<User> optionalUser = userRepository.findById(id)
                .filter(User::isStatus);

        return optionalUser.map(this::mapToDetailsDto);

    }

    private UserDetailsDto mapToDetailsDto(User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isStatus()
        );
    }
}
