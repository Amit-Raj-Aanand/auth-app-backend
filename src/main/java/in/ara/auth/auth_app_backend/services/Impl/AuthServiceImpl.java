package in.ara.auth.auth_app_backend.services.Impl;

import in.ara.auth.auth_app_backend.dto.UserDto;
import in.ara.auth.auth_app_backend.services.AuthService;
import in.ara.auth.auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    public final UserService userService;


    @Override
    public UserDto registerUser(UserDto userDto) {
        //logic
        //varifyEmail
        //varifyPassword
        //default role
        UserDto userDto1 = userService.createUser(userDto);
        return null;
    }
}
