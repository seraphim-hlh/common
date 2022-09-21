package com.linhao.configuration;

import com.linhao.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MD5Util.getSaltverifyMD5(rawPassword.toString(),encodedPassword);
    }
}
