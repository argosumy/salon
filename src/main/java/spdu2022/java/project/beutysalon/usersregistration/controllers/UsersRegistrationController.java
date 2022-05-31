package spdu2022.java.project.beutysalon.usersregistration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.usersregistration.controllers.dto.UserDto;
import spdu2022.java.project.beutysalon.usersregistration.services.PersistenceUsersModificationService;
import spdu2022.java.project.beutysalon.usersregistration.services.UsersModificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UsersRegistrationController {
    private final UserMapper userMapper;
    private final UsersModificationService persistenceUsersModificationService;

    public UsersRegistrationController(UserMapper userMapper, PersistenceUsersModificationService persistenceUsersModificationService) {
        this.userMapper = userMapper;
        this.persistenceUsersModificationService = persistenceUsersModificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto insertNewUser(@Valid @RequestBody UserDto userDto) {
        User user = persistenceUsersModificationService.createNewUser(userMapper.convertUserDtoToUser(userDto));
        return userMapper.convertUserToUserDto(user);
    }
}