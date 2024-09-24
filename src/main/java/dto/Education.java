package dto;

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
    private String school;
    private String degree;
    private String fieldOfStudy;
}
