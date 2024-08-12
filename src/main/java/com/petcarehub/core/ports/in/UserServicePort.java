package com.petcarehub.core.ports.in;

import com.petcarehub.application.dto.UserDTO;
import java.util.UUID;

public interface UserServicePort {
  UserDTO registerUser(UserDTO userDTO);
  UserDTO getUserByEmail(String email);
  UserDTO updateUser(UUID userId, UserDTO userDTO);
  void deleteUser(UUID userId);
}
