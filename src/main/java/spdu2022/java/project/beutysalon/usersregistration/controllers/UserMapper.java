package spdu2022.java.project.beutysalon.usersregistration.controllers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.usersregistration.controllers.dto.UserDto;

@Component
public class UserMapper {
    protected User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setCity(userDto.getCity());
        return user;
    }

    protected UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setCity(user.getCity());
        return userDto;
    }
}
