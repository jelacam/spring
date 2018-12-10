package com.example.codingchallenge.securityconfig;

import org.springframework.security.core.Authentication;

public interface PermissionEvaluator {
    boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission);
}
