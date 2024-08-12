package com.petcarehub.application.service;

import com.petcarehub.application.dto.UserDTO;
import com.petcarehub.application.mapper.UserMapper;
import com.petcarehub.core.domain.User;
import com.petcarehub.core.ports.in.UserServicePort;
import com.petcarehub.infrastructure.repository.ArchivedUserRepository;
import com.petcarehub.infrastructure.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserServicePort {

  private static final String EMAIL_ALREADY_IN_USE = "Email already in use";
  private static final String USER_NOT_FOUND = "User not found";

  private final UserRepository userRepository;
  private final ArchivedUserRepository archivedUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDTO registerUser(UserDTO userDTO) {
    if (userRepository.existsByEmail(userDTO.email())) {
      throw new IllegalArgumentException(EMAIL_ALREADY_IN_USE);
    }

    User user = UserMapper.INSTANCE.toEntity(userDTO);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);

    return UserMapper.INSTANCE.toDTO(savedUser);
  }

  @Override
  public UserDTO getUserByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
    return UserMapper.INSTANCE.toDTO(user);
  }

  @Override
  public UserDTO updateUser(UUID userId, UserDTO userDTO) {
    User existingUser = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));

    User updatedUser = existingUser.toBuilder()
        .firstName(userDTO.firstName() != null ? userDTO.firstName() : existingUser.getFirstName())
        .lastName(userDTO.lastName() != null ? userDTO.lastName() : existingUser.getLastName())
        .organizationName(userDTO.organizationName() != null ? userDTO.organizationName() : existingUser.getOrganizationName())
        .address(userDTO.address() != null ? userDTO.address() : existingUser.getAddress())
        .phoneNumber(userDTO.phoneNumber() != null ? userDTO.phoneNumber() : existingUser.getPhoneNumber())
        .status(userDTO.status() != null ? userDTO.status() : existingUser.getStatus())
        .role(userDTO.role() != null ? userDTO.role() : existingUser.getRole())
        .password(userDTO.password() != null && !userDTO.password().isEmpty() ? passwordEncoder.encode(userDTO.password()) : existingUser.getPassword())
        .build();

    userRepository.save(updatedUser);
    return UserMapper.INSTANCE.toDTO(updatedUser);
  }

  @Override
  public void deleteUser(UUID userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));

    archiveUser(user);
    userRepository.delete(user);
  }

  private void archiveUser(User user) {
    var archivedUser = UserMapper.INSTANCE.toArchivedUser(user);
    archivedUser.setDeletedAt(LocalDateTime.now());
    archivedUserRepository.save(archivedUser);
  }
}
