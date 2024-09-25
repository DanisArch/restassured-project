package dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Education {
    private int id;
    private int userId;
    private String school;
    private String degree;
    private String fieldOfStudy;

    @JsonSetter("userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
