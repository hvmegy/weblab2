package Service;

import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import repo.UserInfoRepository;

import java.util.Optional;

public class UserInfoService  implements UserDetailsService {
    @Autowired
    UserInfoRepository userInfoRepository;

    public userInfoService(UserInfoRepository userInfoRepository)
    {
        this.userInfoRepository = userInfoRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        return userInfo.map(UserInfoUserDetails::new).orElseThrow() -> new UsernameNotFoundException("user not found");
    }
}
