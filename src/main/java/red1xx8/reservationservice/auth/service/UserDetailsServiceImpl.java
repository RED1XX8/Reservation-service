package red1xx8.reservationservice.auth.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import red1xx8.reservationservice.auth.model.UserPrincipal;
import red1xx8.reservationservice.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  repository.findByUserNameOrNumberPhone(username , username)
                .orElseThrow(()-> new UsernameNotFoundException("User is not found"));



        return new UserPrincipal(user);
    }


}
