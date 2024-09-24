package dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Profile {
    private String company;
    private String location;
    private int year;
    private String status;
    private String skills;
}
