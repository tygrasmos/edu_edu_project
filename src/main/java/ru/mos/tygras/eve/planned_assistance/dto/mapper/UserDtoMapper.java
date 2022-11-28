package ru.mos.tygras.eve.planned_assistance.dto.mapper;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.dto.UserDto;
import ru.mos.tygras.eve.planned_assistance.model.entity.User;
import ru.mos.tygras.eve.planned_assistance.service.repository.UserService;

@Service
public class UserDtoMapper {

    private final UserService service;

    public UserDtoMapper(UserService service){
        this.service = service;
    }


    public User dtoToEntity(UserDto dto){
        if(dto.getId() == null){
            return getNewUser(dto);
        }
        return service.findUserById(dto.getId());
    }

    public UserDto entityToDto(User entity){
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    private User getNewUser(UserDto userDto){
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return user;
    }

}
