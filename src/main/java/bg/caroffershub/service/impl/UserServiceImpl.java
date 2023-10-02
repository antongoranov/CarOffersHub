package bg.caroffershub.service.impl;

import bg.caroffershub.model.entity.UserRoleEntity;
import bg.caroffershub.model.enums.UserRoleEnum;
import bg.caroffershub.model.mapper.UserMapper;
import bg.caroffershub.model.entity.UserEntity;
import bg.caroffershub.model.dtos.UserRegisterDTO;
import bg.caroffershub.repository.RoleRepository;
import bg.caroffershub.repository.UserRepository;
import bg.caroffershub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final UserDetailsService appUserDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           UserDetailsService appUserDetailsService, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.appUserDetailsService = appUserDetailsService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserRoleEntity userRoleEntity = this.roleRepository.findByUserRole(UserRoleEnum.USER);

        UserEntity newUser = this.userMapper.userRegisterDTOtoUserEntity(userRegisterDTO);
        newUser.setUserRoles(List.of(userRoleEntity));

        newUser.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);
    }

    private void login(UserEntity userEntity) {

        UserDetails userDetails = this.appUserDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }


}
