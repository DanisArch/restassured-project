package dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Profile {
    private int userId;
    private String company;
    private String location;
    private Date year;
    private String status;
    private String skills;

    @JsonSetter("userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
