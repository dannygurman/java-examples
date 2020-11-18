package mockmvcexample.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    public MyUserDetails(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @JsonProperty("isAdmin")
    private Boolean isAdmin;

    private String username;

    private String displayName;

    /* Spring Security fields */
    private String password = "";
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

}
