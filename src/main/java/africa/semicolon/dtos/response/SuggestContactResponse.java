package africa.semicolon.dtos.response;

import africa.semicolon.dtos.response.ContactResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SuggestContactResponse{
    private List<ContactResponse> contactResponseList = new ArrayList<>();
}
