package com.example.codingchallenge.securityconfig.permisson_evaluator;

import com.example.codingchallenge.model.*;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import com.example.codingchallenge.securityconfig.PermissionEvaluator;
import com.example.codingchallenge.service.OrganizationService;
import com.example.codingchallenge.service.ProductSharingService;
import com.example.codingchallenge.service.RoleService;
import com.example.codingchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class EntityPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ProductSharingService productSharingService;


    @Override
    public boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission) {

        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        String accessingOrgId = principal.getUser().getOrganizationId();

        switch (Entity.valueOf(targetType)){
            case ADMIN: {
                Admin admin = userService.findById(objectId);
                if (admin == null) {
                    return false;
                }
                if (admin.getOrganizationId() != null) {
                    return admin.getOrganizationId().equals(accessingOrgId);
                }
                return false;
            }
            case ORGANIZATION: {
                Organization organization = organizationService.OrganizationByID(objectId);
                if (organization == null) {
                    return false;
                }
                if (organization.getId() != null) {
                    return organization.getId().equals(accessingOrgId);
                }
                return false;
            }
            case ROLE: {
                Role role = roleService.FindById(objectId);
                if (role == null) {
                    return false;
                }
                if (role.getOrganizationId() != null) {
                    return role.getOrganizationId().equals(accessingOrgId);
                }
                return false;
            }
            case SHARING_STATEMENT: {
                ProductSharingStatement productSharingStatement = productSharingService.FindById(objectId);
                if (productSharingStatement == null) {
                    return false;
                }
                if (productSharingStatement.getSharingOrgId() != null) {
                    return productSharingStatement.getSharingOrgId().equals(accessingOrgId);
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }
}
