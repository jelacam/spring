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
        // get user organization id - accessingOrgId for ProductSharingStatement object
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        String accessingOrgId = principal.getUser().getOrganizationId();

        switch (Entity.valueOf(targetType)){
            case ADMIN: {
                Admin admin = userService.findById(objectId);
                if (admin == null) {
                    return false;
                }
                return admin.getOrganizationId().equals(accessingOrgId);
            }
            case ORGANIZATION: {
                Organization organization = organizationService.OrganizationByID(objectId);
                if (organization == null) {
                    return false;
                }
                return organization.getId().equals(accessingOrgId);
            }
            case ROLE: {
                Role role = roleService.FindById(objectId);
                if (role == null) {
                    return false;
                }
                return role.getOrganizationId().equals(accessingOrgId);
            }
            case SHARING_STATEMENT: {
                ProductSharingStatement productSharingStatement = productSharingService.FindById(objectId);
                if (productSharingStatement == null) {
                    return false;
                }
                return productSharingStatement.getSharingOrgId().equals(accessingOrgId);
            }
            default: {
                return false;
            }
        }
    }
}
