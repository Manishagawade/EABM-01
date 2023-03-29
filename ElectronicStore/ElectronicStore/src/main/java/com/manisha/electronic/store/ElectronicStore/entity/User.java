package com.manisha.electronic.store.ElectronicStore.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity     ///represent User namesTable in database
@Table(name = "Users")    ///table name Users se table create hoga
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userid;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email", unique = true)
    private String email;
    @Column(name = "user_password", length = 10)
    private String password;
    private String gender;
    @Column(length = 1000)
    private String about;
    @Column(name = "user_image_name")
    private String imageName;

}