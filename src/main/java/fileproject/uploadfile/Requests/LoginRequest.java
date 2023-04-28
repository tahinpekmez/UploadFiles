package fileproject.uploadfile.Requests;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class LoginRequest {

    private String username;

    private String password;
}
