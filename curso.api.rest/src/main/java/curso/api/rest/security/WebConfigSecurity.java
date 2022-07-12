package curso.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import curso.api.rest.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	/*Configura as solicitações de acesso por Http*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*Ativando a proteção contra usuário que não estão validados por TOKEN*/
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		/*Ativando a permissão para acesso a página incial do sistema EX: sistema.com.br/index*/
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		
		/*URL de Logout - Redireciona após o user deslogar do sistema*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/*Maperia URL de Logout e insvalida o usuário*/
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		/*Filtra requisições de login para autenticação*/
		
		
		/*Filtra demais requisições paa verificar a presenção do TOKEN JWT no HEADER HTTP*/
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/* Service irá consultar usuario no banco de dados */
		auth.userDetailsService(implementacaoUserDetailsService)
		
		/* Padrão de codificação de senha*/
		.passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	
}
