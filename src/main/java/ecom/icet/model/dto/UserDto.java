package ecom.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private String id;
    private String name;
    private String password;
    private String email;
    private String role;
}
