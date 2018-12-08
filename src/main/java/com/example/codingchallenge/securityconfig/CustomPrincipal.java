package com.example.codingchallenge.securityconfig;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.model.Role;
import org.glassfish.jersey.internal.inject.Custom;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomPrincipal  implements UserDetails {

    private Admin user;

    public CustomPrincipal(Admin user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Permission permission : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(String.format("%s_%s",permission.getEntity(), permission.getOperation().toString())));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
