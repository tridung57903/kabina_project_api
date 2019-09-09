package Kabina.Service;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import Kabina.Repository.UserRepository;
import Kabina.DTO.SecurityUser;
import Kabina.Model.User;

@RunWith(SpringRunner.class)
public class JwtUserDetailsServiceTest {
	
	@TestConfiguration
    static class JwtUserDetailsServiceTestContextConfiguration {
  
        @Bean
        public JwtUserDetailsService jwtUserDetailsService() {
            return new JwtUserDetailsService();
        }
    }
	
	@Autowired
    private JwtUserDetailsService jwtUserDetailsService;
 
    @MockBean
	private UserRepository userRepository;
    
    @MockBean
	private PasswordEncoder bcryptEncoder;
    
    @Test
    public void successfullyLoadUserByUsername() {
    	User user = new User(1,"mquan","123","","","","","",false,"User",null);
        Mockito.when(userRepository.findByUsername("mquan")).thenReturn(user);
        String username = "mquan";
        SecurityUser suser = (SecurityUser) jwtUserDetailsService.loadUserByUsername(username);  
        assertEquals(1,suser.getId());
        assertEquals("mquan",suser.getUsername());
        assertEquals("User",suser.getRole());
     }
    
    @Test(expected = UsernameNotFoundException.class)
    public void failLoadUserByUsername() {
        Mockito.when(userRepository.findByUsername("mquan")).thenReturn(null);
        String username = "mquan";
        SecurityUser suser = (SecurityUser) jwtUserDetailsService.loadUserByUsername(username);  
     }
}
