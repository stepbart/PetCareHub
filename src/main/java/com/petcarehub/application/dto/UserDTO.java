package com.petcarehub.application.dto;

import static com.petcarehub.core.domain.UserStatus.ACTIVE;

import com.petcarehub.core.domain.Role;
import com.petcarehub.core.domain.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    String email,
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password,
    @NotBlank(message = "Role is mandatory")
    Role role,
    @Size(max = 100)
    String firstName,
    @Size(max = 100)
    String lastName,
    @Size(max = 255)
    String organizationName,
    @Size(max = 255)
    String address,
    @Size(max = 20)
    String phoneNumber,
    UserStatus status
) {

  public UserDTO {
    if (status == null) {
      status = ACTIVE;
    }
  }
}
