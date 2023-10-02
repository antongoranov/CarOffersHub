package bg.caroffershub.service.impl;

import bg.caroffershub.model.security.CarOffersHubUserDetails;
import bg.caroffershub.model.entity.UserEntity;
import bg.caroffershub.model.entity.UserRoleEntity;
import bg.caroffershub.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// NOTE: This is not annotated as @Service, because we will return it as a bean
public class CarOffersHubUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CarOffersHubUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(entity -> map(entity))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity){

       return new CarOffersHubUserDetails(
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.
                        getUserRoles().
                        stream().
                        map(this::mapToGrantedAuthority).
                        toList());
    }

    private GrantedAuthority mapToGrantedAuthority(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}
