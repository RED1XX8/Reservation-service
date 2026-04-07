package red1xx8.reservationsystem.auth.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import red1xx8.reservationsystem.auth.repository.UserEntity;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final UserEntity user;

    public UserPrincipal(UserEntity user){
        this.user = user;
    }

    public Long getId(){return user.getId();}
    public UserStatus getStatus(){return user.getStatus();}
    public Roles getRole() {return user.getRole();}
    public String getNumberPhone() {return user.getNumberPhone();}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(() -> "ROLE_" + user.getRole().name());
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override public boolean isAccountNonExpired() { return true; }


    @Override public boolean isAccountNonLocked() {
        return user.getStatus() == UserStatus.ACTIVE; }

    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
