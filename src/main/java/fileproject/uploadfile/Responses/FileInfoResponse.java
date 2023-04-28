package fileproject.uploadfile.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfoResponse {

    private String name;
    private String url;
    private String type;
    private long size;
}
