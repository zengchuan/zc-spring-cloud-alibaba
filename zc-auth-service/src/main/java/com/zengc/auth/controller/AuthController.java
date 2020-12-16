package com.zengc.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zengchuan on 2020/06/27.
 */
@RestController
@RequestMapping("/zc-auth")
public class AuthController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/jwks.json")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @GetMapping("/me")
    public Object getCurrentUserInfo(@AuthenticationPrincipal Object principal) {
        return Collections.singletonMap("user", principal);
    }
}
