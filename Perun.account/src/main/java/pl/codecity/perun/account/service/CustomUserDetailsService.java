package pl.codecity.perun.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.account.model.User;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        /*
        for(UserRole userRole : user.getUserRoles()){
            logger.info("UserProfile : {}", userRole);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }
        */

        user.getUserRoles().forEach(userRole -> {
            logger.info("UserProfile : {}", userRole);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        });
        logger.info("authorities : {}", authorities);
        return authorities;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userService.findByNickName(nickname);
        logger.info("User : {}", user);
        if(user == null){
            logger.info("User not found.");
            throw new UsernameNotFoundException("Username not found.");
        }
        return new org.springframework.security.core.userdetails.User(user.getNickName(),
                user.getPassword(), true, true, true, true, getGrantedAuthorities(user));
    }
}
