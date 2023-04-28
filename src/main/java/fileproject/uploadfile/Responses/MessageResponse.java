package fileproject.uploadfile.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class MessageResponse {

    private String message;

    public MessageResponse(String message){
        this.message = message;
    }
}
