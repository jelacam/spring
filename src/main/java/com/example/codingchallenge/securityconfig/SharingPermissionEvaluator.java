package com.example.codingchallenge.securityconfig;

import org.springframework.security.core.Authentication;

public interface SharingPermissionEvaluator {
    boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission);
}
