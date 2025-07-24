package br.edu.ifrn.projeto.controladores;

/**
 * Objetivo: Este controlador, é o controlador para os usuários
 * seu objetivo é dar acesso as diferentes páginas, para buscar, editar e 
 * remover as informações armazenadas sobre os usuários.
 * 
 * @author Ariane Selli Melo de Souza (arianneselli2013@gmail.com)
 * @author Luiz Felipe de Souza Silva (lf06092004@gmail.com)
 * 
 * Data de criação: 13/01/2022
 * ###########################
 * 
 * @version 1.0
 * 
 * */

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.projeto.dominio.Doacao;
import br.edu.ifrn.projeto.dominio.Usuario;
import br.edu.ifrn.projeto.repository.UsuarioRepository;

@Controller
@RequestMapping("/cruds")
public class crudColaboradoresController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/crudCollabe")
	public String entrarCrudCollabe(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("doacao", new Doacao());
		
		List<Usuario> usuariosEncontrados = usuarioRepository.findAll();
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		return "crudColaboradores";
	}
	
	/**
	 * Objetivo: Busca o usuario pelo nome e retorna uma lista com os nomes encontrados
	 * 
	 * @param nome Recebe o nome do usuário que deve ser buscado no banco de dados
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return crudColaboradores Nome da página na qual deve retornar após realizar a busca
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@GetMapping("/buscar")
	public String buscar(@RequestParam(name = "nome", required = false) String nome, 
			 ModelMap model) {
		
		List<Usuario> usuariosEncontrados = usuarioRepository.findByNome(nome);
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
				
		return "crudColaboradores";
	}
	
	/**
	 * Objetivo: Edita o usuário através do id, modificando as informações anteriores
	 * e adicionando as novas no mesmo id 
	 * 
	 * @param IdUser Pegando o ID do usuário que foi clicado para pegar seus dados.
	 * @param model  Tem a função de enviar atributos para as páginas HTML
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@GetMapping("/edit/{id}")
	public String iniciarEdicao(
			@PathVariable("id") Integer IdUser,
			ModelMap model
			) { 
		
			Usuario usuario = usuarioRepository.findById(IdUser).get();
			
			model.addAttribute("usuario", usuario);
			
			return "Admin/editColaboradores";
		
	}
	
	/**
	 * Objetivo: remove o usuário de acordo com o id dele e retorna uma mensagem de sucesso 
	 * informando que o usuário foi removido
	 * 
	 * @param IdUser Pegando o ID do usuário que foi clicado para pegar seus dados e remover.
	 * @param attr Tem a função de redirecionar mensagens para página redirecionada no return
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@GetMapping("/remover/{id}")
	public String remover(
			@PathVariable("id") Integer idUser, RedirectAttributes attr
			) {
		
		usuarioRepository.deleteById(idUser);
		attr.addFlashAttribute("msgSucess", "Usuario removido com sucesso");
		
		return "redirect:/cruds/crudCollabe";
	}
	
}
