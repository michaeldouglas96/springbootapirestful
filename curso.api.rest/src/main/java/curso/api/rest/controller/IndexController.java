package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
	public ResponseEntity <Usuario> init(@PathVariable(value = "id") Long id, @PathVariable(value = "venda") Long venda){
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return new ResponseEntity(usuario, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity <Usuario> init(@PathVariable(value = "id") Long id){
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return new ResponseEntity(usuario, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuarios (){
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar (@RequestBody Usuario usuario){
		
		for(int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario){
		
		for(int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity atualizarVenda (@PathVariable Long iduser, @PathVariable Long idvenda){
		
		/* Seria uma rotina de venda */
		//Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity("Venda atualizada", HttpStatus.OK);
	}
	
	@PostMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity cadastrarvenda (@PathVariable Long iduser, @PathVariable Long idvenda){
		
		/* Seria uma rotina de venda */
		//Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity("id user: " + iduser + " id venda: " + idvenda, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public String deletar (@PathVariable(value = "id") Long id) {
		
		usuarioRepository.deleteById(id);
		
		return "Usuário deletado com sucesso!";
	}


}
