package fileproject.uploadfile.Service;

import fileproject.uploadfile.Exception.TypeExceptionResponse;
import fileproject.uploadfile.Models.File;
import fileproject.uploadfile.Repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UploadService implements IUploadService {

    @Autowired private FileRepository fileRepository;

    @Value(value = "${data.exception.message}")
    private String invalidMessage;


    private boolean isSupportedExtension(String extension) {
        return extension != null && (
                extension.equals("png") || extension.equals("jpg")
                        || extension.equals("jpeg") || extension.equals("docx")
                        || extension.equals("xlsx") || extension.equals("pdf"));
    }


    public File store(MultipartFile file) throws IOException, TypeExceptionResponse {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        String extension = FilenameUtils.getExtension(
                file.getOriginalFilename());
        if (!isSupportedExtension(extension))
            throw new TypeExceptionResponse(new Date(), invalidMessage);
        return fileRepository.save(FileDB);

    }

    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}
