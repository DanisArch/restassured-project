package dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
//@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
}
