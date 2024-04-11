package africa.semicolon.dtos.response;

import lombok.Data;

@Data
public class UpdateUserResponse {
    private String userId;
    private String username;
    private String dateUpdated;
    private boolean loggedIn;
}
