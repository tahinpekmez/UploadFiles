package fileproject.uploadfile.Service;

import fileproject.uploadfile.Exception.TypeExceptionResponse;
import fileproject.uploadfile.Models.File;
import fileproject.uploadfile.Models.FileUser;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {

    File store(MultipartFile file) throws IOException, TypeExceptionResponse;
}
