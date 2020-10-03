package com.greentower.api.rules.auth_user.service;

import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.service.impl.AuthUserServiceImpl;
import com.greentower.api.rules.auth_user.util.exception.AuthUserNotFoundException;
import com.greentower.api.rules.person.domain.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthUserServiceTest {

    @InjectMocks private AuthUserServiceImpl authUserService;

    @Mock private AuthUserRepository authUserRepository;

    @Test
    public void testFindAllIsEmpty(){
        List<AuthUser> authUserList = new ArrayList<>();
        assertEquals(authUserList, authUserService.findAll());
    }

    @Test(expected = AuthUserNotFoundException.class)
    public void testFindByIdNotFound(){
        authUserService.findByEmail("");
    }
}