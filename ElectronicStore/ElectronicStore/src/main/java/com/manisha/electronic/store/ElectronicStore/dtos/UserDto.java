package com.manisha.electronic.store.ElectronicStore.dtos;

import com.manisha.electronic.store.ElectronicStore.validate.ImageNameValid;
import lombok.*;

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

    private String userid;
    @Size(min=3,max =20,message = "Invalid Name !!!")
    private String name;
    @NotBlank(message = "Invalid User Email !!!")
    @Pattern(regexp = "^[a-z0-9][a-z0-9._]+[a-z]{2,5}$",message = "Invalid User Email !!!!!")

    private String email;
    @NotBlank(message = "password is required !!!1")
    private String password;

    @Size(min = 4,max=6,message = "Invalid gender")
    private String gender;
    @NotBlank(message = "write somthing about youself !!")
    private  String about;
    @ImageNameValid
    private String imageName;
}
