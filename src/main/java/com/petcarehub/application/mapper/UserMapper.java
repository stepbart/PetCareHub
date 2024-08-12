package com.petcarehub.application.mapper;

import com.petcarehub.application.dto.UserDTO;
import com.petcarehub.core.domain.ArchivedUser;
import com.petcarehub.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toEntity(UserDTO userDTO);
  UserDTO toDTO(User user);
  ArchivedUser toArchivedUser(User user);
}

