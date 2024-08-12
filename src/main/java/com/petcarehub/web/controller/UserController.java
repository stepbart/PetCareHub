package com.petcarehub.web.controller;

import com.petcarehub.application.dto.UserDTO;
import com.petcarehub.core.ports.in.UserServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserServicePort userServicePort;

  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
    log.info(String.valueOf(userDTO));
    UserDTO registeredUser = userServicePort.registerUser(userDTO);
    return ResponseEntity.ok(registeredUser);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
    UserDTO userDTO = userServicePort.getUserByEmail(email);
    return ResponseEntity.ok(userDTO);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
    userServicePort.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId, @Valid @RequestBody UserDTO userDTO) {
    UserDTO updatedUser = userServicePort.updateUser(userId, userDTO);
    return ResponseEntity.ok(updatedUser);
  }
}
