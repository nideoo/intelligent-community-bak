package com.enc.token;

import org.springframework.stereotype.Component;

/**
 * token相关
 */
@Component
public interface TokenGenerator {
    public String generate(String... strings);
}
