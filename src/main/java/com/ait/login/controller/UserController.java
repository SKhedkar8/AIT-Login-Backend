package com.ait.login.controller;

import com.ait.login.dto.LoginRequest;
import com.ait.login.dto.RegisterRequest;
import com.ait.login.dto.UserResponse;
import com.ait.login.entity.User;
import com.ait.login.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // USER REGISTRATION API
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());

        User savedUser = userService.registerUser(user);

        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "Registration successful!"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // USER LOGIN API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody LoginRequest request,
            HttpSession session,
            HttpServletRequest httpRequest) {

        try {

            User user = userService.loginUser(
                    request.getEmail(),
                    request.getPassword()
            );

            // Regenerate session ID after successful authentication
            httpRequest.changeSessionId();

            // Store logged-in user information
            session.setAttribute("loggedInUserId", user.getId());
            session.setAttribute("loggedInUserEmail", user.getEmail());

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    "Login successful!"
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException exception) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(exception.getMessage());
        }
    }

    // CHECK LOGIN SESSION AND GET USER DETAILS
    @GetMapping("/session")
    public ResponseEntity<?> checkSession(HttpSession session) {

        Object userId = session.getAttribute("loggedInUserId");

        if (userId == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No active login session!");
        }

        try {

            User user = userService.getUserById((Long) userId);

            return ResponseEntity.ok(
                    Map.of(
                            "loggedIn", true,
                            "userId", user.getId(),
                            "fullName", user.getFullName(),
                            "email", user.getEmail()
                    )
            );

        } catch (IllegalArgumentException exception) {

            session.invalidate();

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found!");
        }
    }

    // USER LOGOUT API
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {

        session.invalidate();

        return ResponseEntity.ok("Logout successful!");
    }

    // HANDLE DUPLICATE EMAIL ERRORS
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleDuplicateEmail(
            IllegalArgumentException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}