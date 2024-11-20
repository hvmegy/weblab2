package Service;

import entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private List<GrantedAuthority> authorityList;

    public UserInfoUserDetails(UserInfo userInfo ) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        authorityList = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect((Collector.toList()));
    }

    public Collection <? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }
    public String getPassword() {
        return password;
    }
}
