package com.manisha.electronic.store.ElectronicStore.service;

import com.manisha.electronic.store.ElectronicStore.dtos.PagebleResponse;
import com.manisha.electronic.store.ElectronicStore.dtos.UserDto;
import com.manisha.electronic.store.ElectronicStore.entity.User;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userid);

    //delete
    void  delete(String userid);

    //get all users
    PagebleResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String  sortDir);

    //get single user by id
    UserDto getUserById(String userid);

    //get single user by email
    UserDto getuUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);

}


