package peaksoft.gadgetstoresession.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SimpleResponse {

    private String message;
    private HttpStatus httpStatus;
}
