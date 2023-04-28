package fileproject.uploadfile.Controller;

import fileproject.uploadfile.Exception.TypeExceptionResponse;
import fileproject.uploadfile.Repositories.FileRepository;
import fileproject.uploadfile.Responses.FileInfoResponse;
import fileproject.uploadfile.Responses.MessageResponse;
import fileproject.uploadfile.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    @Autowired private FileRepository fileRepository;
    @Autowired private UploadService uploadService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, MaxUploadSizeExceededException, TypeExceptionResponse {
        uploadService.store(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Uploaded The File Successfully"));

    }


    @GetMapping("/files")
    public ResponseEntity<List<FileInfoResponse>> getListFiles() {
        fileRepository.findAll();

        List<FileInfoResponse> files = uploadService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new FileInfoResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}
