package com.company.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean verified;
    private boolean admin;
    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled  = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

}
