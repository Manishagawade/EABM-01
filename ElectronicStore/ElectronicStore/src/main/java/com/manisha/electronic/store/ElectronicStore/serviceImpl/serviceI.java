package com.manisha.electronic.store.ElectronicStore.serviceImpl;

import com.manisha.electronic.store.ElectronicStore.dtos.PagebleResponse;
import com.manisha.electronic.store.ElectronicStore.dtos.UserDto;
import com.manisha.electronic.store.ElectronicStore.entity.User;
import com.manisha.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.manisha.electronic.store.ElectronicStore.helper.Helper;
import com.manisha.electronic.store.ElectronicStore.repository.UserRepository;
import com.manisha.electronic.store.ElectronicStore.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class serviceI implements UserService {
    Logger logger = LoggerFactory.getLogger(serviceI.class);

@Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("initiation dao call for save user");
        //generation unique id in spring format
        String userId= UUID.randomUUID().toString();
        userDto.setUserid(userId);
        //dto->entity
        User user= dtoToEnity(userDto);
        User saveUser=userRepository.save(user);
        //entity-->dto
        UserDto newDto=entityToDto(saveUser);
        logger.info("Completed dao call for save user"+newDto);
        return newDto;
    }

    @Override
    public UserDto updateUser
    (UserDto userDto, String userid) {
        logger.info("initiation dao call for Update user");
        User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        User updateUser=userRepository.save(user);
        UserDto updateDto=entityToDto(updateUser);
        logger.info("Completed dao call for Update user");
        return updateDto;
    }

    @Override
    public void delete(String userid) {
        logger.info("initiation dao call for delete user");
        User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
        logger.info("Completed dao call for delete user");
         userRepository.delete(user);
    }

    @Override
    public PagebleResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String  sortDir) {
        logger.info("initiation dao call for getAll user");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).ascending() ):(Sort.by(sortBy).descending() );
        //pageNumber default start from 0
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page=userRepository.findAll(pageable);
        PagebleResponse<UserDto> response=Helper.getPagebleResponse(page,UserDto.class);
        logger.info("Completed dao call for getAllUser ");
           return response;

    }

    @Override
    public UserDto getUserById(String userid) {
        logger.info("initiation dao call for getUserById");

        User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
        logger.info("Completed dao call for getUserById ");
        return entityToDto(user);
    }

    @Override
    public UserDto getuUserByEmail(String email) {
        logger.info("initiation dao call for getuUserByEmail");
        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with given email and password!!"));
        logger.info("Completed dao call for getuUserByEmail ");

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        logger.info("initiation dao call for searchUser");

        List<User> users= userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList= users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        logger.info("Completed dao call for searchUser ");

        return dtoList;
    }
    private UserDto entityToDto(User saveUser) {
//        UserDto userDto=UserDto.builder()
//                .userid(saveUser.getUserid())
//                .name(saveUser.getName())
//                .email(saveUser.getEmail())
//                .password(saveUser.getPassword())
//                .about(saveUser.getAbout())
//                .gender(saveUser.getGender())
//                .imageName(saveUser.getImageName())
//                .build();
//        return userDto;
        return mapper.map(saveUser, UserDto.class);
    }

    private User dtoToEnity(UserDto userDto) {
//        User user=User.builder().userid(userDto .getUserid())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
//        return user;
        return mapper.map(userDto,User.class);
    }

}