package fileproject.uploadfile.Exception;

import fileproject.uploadfile.Responses.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TypeException extends ResponseEntityExceptionHandler {

    @Value(value = "${data.exception.message}")
    private String invalidMessage;

    @ExceptionHandler(TypeExceptionResponse.class)
    public ResponseEntity<MessageResponse> handleMaxSizeException() {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(invalidMessage));
    }
}
