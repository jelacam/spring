package com.example.codingchallenge.securityconfig;

import com.example.codingchallenge.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {

    @Autowired
    @Qualifier(value = "entityPermissionEvaluator")
    private PermissionEvaluator entityPermissionEvaluator;

    @Autowired
    @Qualifier(value = "productPermissionEvaluator")
    private PermissionEvaluator productPermissionEvaluator;

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if (auth == null || targetDomainObject == null || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPrivilege(auth, targetType, null, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if (auth == null || targetType == null || !(permission instanceof  String)){
            return  false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), targetId.toString(), permission.toString().toUpperCase());
    }


    private boolean hasPrivilege(Authentication auth, String targetType,  String targetId, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    if (targetId != null) {
                        return SharingPermissionEvaluatorFactory(targetType).SharingPermission(auth, targetId, targetType, permission);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private PermissionEvaluator SharingPermissionEvaluatorFactory(String targetType) {
        Entity entity = Entity.valueOf(targetType);
        switch (entity) {
            case PRODUCT: {
                return productPermissionEvaluator;
            }
            default: {
                return entityPermissionEvaluator;
            }
        }
    }
}
