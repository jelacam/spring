package com.example.codingchallenge.securityconfig.permisson_evaluator;

import com.example.codingchallenge.securityconfig.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SharingStatementPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission) {
        return false;
    }
}
