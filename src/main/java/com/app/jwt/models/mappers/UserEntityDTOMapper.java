package com.app.jwt.models.mappers;

import com.app.jwt.models.dto.UserResponseDTO;
import com.app.jwt.models.entity.User;
import org.springframework.beans.BeanUtils;

public class UserEntityDTOMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }
}
