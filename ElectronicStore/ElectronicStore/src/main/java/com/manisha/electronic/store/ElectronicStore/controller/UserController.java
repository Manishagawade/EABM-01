package com.manisha.electronic.store.ElectronicStore.controller;

import com.manisha.electronic.store.ElectronicStore.dtos.ApiResponse;
import com.manisha.electronic.store.ElectronicStore.dtos.PagebleResponse;
import com.manisha.electronic.store.ElectronicStore.dtos.UserDto;
import com.manisha.electronic.store.ElectronicStore.entity.User;
import com.manisha.electronic.store.ElectronicStore.service.UserService;
import com.manisha.electronic.store.ElectronicStore.serviceImpl.serviceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(serviceI.class);
    @Autowired
private UserService userService;
    //create
    @PostMapping
public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
        logger.info("initiate req for save user");
    UserDto userDto1=userService.createUser(userDto);
        logger.info("initiate req for save user"+userDto1);
    return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
}

    //update
    @PutMapping("/{userId}")
public ResponseEntity<UserDto>updateUser(

        @PathVariable("userid")String userId,
        @Valid @RequestBody UserDto userDto
        ){
        logger.info("initiate req for Update user");
        UserDto updateUserDto=userService.updateUser(userDto ,userId);
        logger.info("initiate req forUpdate user");
        return new ResponseEntity<>(updateUserDto ,HttpStatus.OK);
}

    //delete
    @DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse>deleteUser(@PathVariable String userId){
        logger.info("initiate req for delete user");
        userService.delete(userId);
        ApiResponse message=ApiResponse.builder()
                .meessage("User is deleted successfully !!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        logger.info("initiate req for delete user");

        return new ResponseEntity<>(message,HttpStatus.OK);

}
    //getAll
    @GetMapping
    public ResponseEntity<PagebleResponse<UserDto>>getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0")int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String  sortDir


    ){
        logger.info("initiate req for getAll user");
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);

    }

    //getsingle
    @GetMapping("/{userId}")
public ResponseEntity<UserDto>getUser(@PathVariable String userId){
        logger.info("initiate req forgetUserById ");
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
}
    //by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email){
        logger.info("initiate req forgetUserByEmail ");
        return new ResponseEntity<>(userService.getuUserByEmail(email),HttpStatus.OK);
    }
    //search
    @GetMapping("/serch/{keywords}")
    public ResponseEntity<List<UserDto>>serchUser(@PathVariable String keywords){
        logger.info("initiate req for getUserByEmail ");
        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
}}
