package br.com.stconsultoria.stexemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.stconsultoria.stexemplo.models.entities.User;
import br.com.stconsultoria.stexemplo.repositories.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userRepository.findByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		return user;


	}

}
