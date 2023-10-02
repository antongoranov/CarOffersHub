package bg.caroffershub.config;

import bg.caroffershub.repository.UserRepository;
import bg.caroffershub.service.impl.CarOffersHubUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("/offers/delete").hasRole("ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin().loginPage("/users/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureForwardUrl("/users/login-error")
        .and()
                .logout().logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new CarOffersHubUserDetailsServiceImpl(userRepository);
    }
}
