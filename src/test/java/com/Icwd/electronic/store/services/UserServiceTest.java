package com.Icwd.electronic.store.services;

import ch.qos.logback.core.CoreConstants;
import com.Icwd.electronic.store.dtos.UserDto;
import com.Icwd.electronic.store.entities.User;
import com.Icwd.electronic.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    User user;

    @BeforeEach
    public void init() {

        user = User.builder()
                .name("ashutosh")
                .email("ashudongare07@gmail.com")
                .about("this is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("lcwd")
                .build();
    }

    //create user test
    @Test
    public void createUserTest() {

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserDto user1 = userService.createUser(mapper.map(user, UserDto.class));

        System.out.println(user1.getName());

        Assertions.assertNotNull(user1);

    }

    //update user test
    @Test
    public  void updateUserTest(){
        String userId ="";
        UserDto userDto= UserDto.builder()
                .name("ashutosh dongare")
                .about("this is updated testing method")
                .gender("Male")
                .imageName("xyz.png")
                .build();

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto, userId);
        System.out.println(updatedUser.getName());
        Assertions.assertNotNull(userDto);

    }
}





