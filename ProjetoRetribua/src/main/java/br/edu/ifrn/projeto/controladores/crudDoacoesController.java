package br.edu.ifrn.projeto.controladores;


/**
 * Objetivo: Este controlador, é o controlador para as doações
 * seu objetivo é dar acesso as diferentes páginas, para buscar, editar e 
 * remover as informações armazenadas sobre as doações.
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
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import br.edu.ifrn.projeto.repository.DoacaoRepository;
import br.edu.ifrn.projeto.repository.UsuarioRepository;


@Controller
@RequestMapping("/cruds")
public class crudDoacoesController {
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	/**
	 * Objetivo: retornar as doações cadastradas 
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return crudDoacoes Nome da página em que a url definida irá retornar
	 */
	@GetMapping("/crudDoacoes")
	public String entrarCrudCollabe(ModelMap model) {
		model.addAttribute("doacao", new Doacao());
		
		List<Doacao> doacoesEncontradas = doacaoRepository.findAll();
		
		model.addAttribute("doacoesEncontradas", doacoesEncontradas);
		return "crudDoacoes";
	}
	
	

	/**
	 * Objetivo: Busca as doações pelo nome e retorna uma lista com as doações encontradas
	 * 
	 * @param nome Recebe o nome Doador
	 * @param nomeDoacao Recebe o nome da Doacao
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return perfil ou crudDoacoes Nome da página em que a url definida irá retornar
	 */

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@GetMapping("/buscarDoacoes")
	public String buscar(@RequestParam(name = "nomeDoDoador", required = false) String nome,
			@RequestParam(name = "nomeDoacao", required = false) String nomeDoacao,
			 ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Usuario usuarioLogado = usuarioRepository.findByEmail(email).get();
		
		if(nome == null) {
			
			List<Doacao> doacoesEncontradas = doacaoRepository.findDoacao(usuarioLogado.getId());
			
			model.addAttribute("doacoesEncontradas", doacoesEncontradas);
			model.addAttribute("doacao", new Doacao());
			
			
			model.addAttribute("usuarioLogado", usuarioLogado);
			
			return "perfil";
		}
		List<Doacao> doacoesEncontradas = doacaoRepository.findByNome(nome);
	
		model.addAttribute("doacoesEncontradas", doacoesEncontradas);
		model.addAttribute("doacao", new Doacao());
		
		
		model.addAttribute("usuarioLogado", usuarioLogado);
		
		return "crudDoacoes";
	}
	
	/**
	 * Objetivo: edita as doações através do id, modificando pelas novas e adicionando no mesmo id
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return editDoacoes Nome da página em que a url definida irá retornar, dentro da pasta ADMIN
	 */

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@GetMapping("/editDoacao/{id}")
	public String iniciarEdicao(
			@PathVariable("id") Integer IdDoacao,
			ModelMap model,
			HttpSession sessao
			) { 
		
			Doacao doacao = doacaoRepository.findById(IdDoacao).get();
		
			model.addAttribute("doacao", doacao);
			
			return "Admin/editDoacoes";
		
	}
	
	/**
	 * Objetivo: remove as doações pelo id, e retorna uma mensagem de sucesso
	 * 
	 * @param idDoacao recebe o ID da doação para retornar para página.
	 * @param attr Tem a função de redirecionar mensagens para página redirecionada no return
	 * 
	 * @return retorna a url dos dados
	 * */
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@GetMapping("/removerDoacao/{id}")
	public String remover(
			@PathVariable("id") Integer idDoacao, RedirectAttributes attr
			) {
		
		doacaoRepository.deleteById(idDoacao);
		attr.addFlashAttribute("msgSucess", "Usuario removido com sucesso");
		
		
		return "redirect:/cruds/buscarDoacoes";
	}
	
	/*Pega os tipos de doações*/
	@ModelAttribute("tipos")
    public List<String> getTipo(){
        return Arrays.asList("Livros", "Móveis", "Roupas", "Comida", "Tempo", "Outra");
        
    }
	
}
