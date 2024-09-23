package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private boolean success;
    private String message;
    private Object data;
}