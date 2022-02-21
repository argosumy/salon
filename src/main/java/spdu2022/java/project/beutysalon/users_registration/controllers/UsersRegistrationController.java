package spdu2022.java.project.beutysalon.users_registration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.users_registration.controllers.dto.UserDto;
import spdu2022.java.project.beutysalon.users_registration.services.PersistenceUsersModificationService;
import spdu2022.java.project.beutysalon.users_registration.services.UsersModificationService;

import javax.validation.Valid;
import java.sql.SQLException;

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
    public UserDto insertNewUser(@Valid @RequestBody UserDto userDto) throws SQLException {
        User user = persistenceUsersModificationService.createNewUser(userMapper.convertUserDtoToUser(userDto));
        return userMapper.convertUserToUserDto(user);
    }




}
