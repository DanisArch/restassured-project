package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private boolean success;
    private String message;
    private Object data;
}