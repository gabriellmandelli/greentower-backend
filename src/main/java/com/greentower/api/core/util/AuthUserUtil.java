package com.greentower.api.core.util;

import com.greentower.api.core.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthUserUtil {
    public static CustomUserDetails getCurrentAuthUser(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
