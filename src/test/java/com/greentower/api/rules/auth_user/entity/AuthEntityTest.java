package com.greentower.api.rules.auth_user.entity;

import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class AuthEntityTest {
    @Test
    public void testAuthEntity() {
        AuthUser person = new AuthUser();
        UUID personId = UUID.randomUUID();

        person.setId(personId);
        person.setName("GreenTower");
        person.setEmail("greentower@greentower.com");
        person.setPassword("123456");

        Assert.assertEquals(person.getId(), personId);
        Assert.assertEquals(person.getName(), "GreenTower");
        Assert.assertEquals(person.getEmail(), "greentower@greentower.com");
        Assert.assertEquals(person.getPassword(), "123456");
    }
}
