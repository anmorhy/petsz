package petsis.pet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;


	@Override // Configura as solicitações de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
        http
        
		.authorizeRequests() // Pertimir restringir acessos
		.antMatchers("/static/**","/css/**","/img/**","/index/**","/js/**","/vendor/**").permitAll()
        .antMatchers("/","/page/login", "/page/registro", "/page/recuperar", "/index", "/page/erro","/logout","/adm/**","/use/**", "/pet/**", "/page/form","/page/**").permitAll() // Qualquer usuário acessa a pagina inicial
		// .antMatchers(HttpMethod.POST, "/adm/**","/use/**").permitAll()
		// .antMatchers(HttpMethod.GET, "/adm/**","/use/**").permitAll()
		// .antMatchers( "/adm/**","/use/**").hasAnyRole("ADMIN")
		// .antMatchers( "/use/**").hasAnyRole("USER")
		.antMatchers("/static/**").permitAll()
		
        .anyRequest().authenticated()
        .and().formLogin().permitAll() // permite qualquer usuário
        .loginPage("/page/login")
        .defaultSuccessUrl("/use/dash",true)
        .failureUrl("/page/erro")
        .and().logout().logoutSuccessUrl("/use/dash") // Mapeia URL de Logout e invalida usuário autenticado
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/page/login")
		.and().csrf().disable();
    
	}
	
	@Override // Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());


		// auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		// .withUser("pet")
		// .password("$2a$10$fm7Ik2YxKPjhLtdO9O79Vem.qO6pYxSJpYpk4YxBe4TMrCeo9lQwO")
		// .roles("ADMIN");
	}
	
	// @Override // Ignora URL especificas
	// public void configure(WebSecurity web) throws Exception {
	// 	web.ignoring().antMatchers("/static/**","/css/**","/img/**","/index/**","/js/**","/vendor/**");
		
	// }
	

	
}
 