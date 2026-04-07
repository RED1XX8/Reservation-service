package red1xx8.reservationsystem.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import red1xx8.reservationsystem.auth.model.UserPrincipal;
import red1xx8.reservationsystem.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user =  repository.findByUserNameOrNumberPhone(login , login)
                .orElseThrow(()-> new UsernameNotFoundException("User is not found"));



        return new UserPrincipal(user);
    }


}
