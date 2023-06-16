package com.Icwd.electronic.store.dtos;

import com.Icwd.electronic.store.validate.ImageNameValid;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private String userId;

    @Size(min = 3,max = 20,message = "Invalid name!!!")
    private String name;

   // @Email(message = "Invalid email!!!")
   @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid user email!")
    @NotBlank(message = "email is required!!!")
    private String email;

    @NotBlank(message = "Password is Required!!!")
    private String password;

    @Size(min = 4,max = 6,message = "Invalid Gender!!!")
    private String gender;

    @NotBlank(message = "Write something about yourself!")
    private String about;

    //@Pattern
    //custom validator

    @ImageNameValid
    private String imageName;
}
