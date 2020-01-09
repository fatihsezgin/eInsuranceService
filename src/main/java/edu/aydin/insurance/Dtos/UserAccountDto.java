package edu.aydin.insurance.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
