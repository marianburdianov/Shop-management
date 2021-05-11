package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.UserDao;
import com.ms3.demo.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    private static final long ID = 1L;
    private User user;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Mock
    private UserDao userDao;

    @BeforeEach
    void serUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsService = new CustomUserDetailsService(userDao);

        user = new User();

        user.setUserId(ID);
        user.setUserName("admin");
        user.setPassword("admin");
        user.setEmail("admin@ms3-inc.com");

    }

    @Test
    void loadUserByUsername() {

        when(userDao.findByUserName(user.getUserName())).thenReturn(Optional.of(user));

        UserDetails userForVerify = userDetailsService.loadUserByUsername(user.getUserName());

        assertEquals(user.getUserName(), userForVerify.getUsername());

        verify(userDao, times(1)).findByUserName(user.getUserName());
    }
}