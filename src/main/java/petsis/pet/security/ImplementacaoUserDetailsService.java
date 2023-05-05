package petsis.pet.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petsis.pet.model.Usuario;
import petsis.pet.repository.UsuarioRepository;

@Service
@Transactional 
public class ImplementacaoUserDetailsService implements UserDetailsService {
    @Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		} 
		
		// return new User(ulogar.getLogin(), ulogar.getSenha(), 
		// ulogar.isEnabled(), true,
		//  true, true,
		// ulogar.getAuthorities());

		return new User(usuario.getLogin(), usuario.getPassword(), 
		usuario.isEnabled(), true, 
		true, true, usuario.getAuthorities());
	}
}
