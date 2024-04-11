package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.LoginUserRequest;
import africa.semicolon.dtos.requests.LogoutUserRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.response.LoginUserResponse;
import africa.semicolon.dtos.response.LogoutUserResponse;
import africa.semicolon.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    LoginUserResponse login(LoginUserRequest loginRequest);
    LogoutUserResponse logout(LogoutUserRequest logoutRequest);

    User findUserBy(String username);
    boolean isUserRegistered(String username);
    boolean isUserLoggedIn(String username);

}
