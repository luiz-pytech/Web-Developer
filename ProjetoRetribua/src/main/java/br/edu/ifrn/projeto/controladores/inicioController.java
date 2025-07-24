package br.edu.ifrn.projeto.controladores;

/**
 * Objetivo: Este controlador, é o controlador principal
 * seu objetivo é dar acesso as diferentes páginas, salvar 
 * entidades no banco de dados e validar as informações de cadastro.
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.edu.ifrn.projeto.dominio.Usuario;
import br.edu.ifrn.projeto.repository.ArquivoRepository;
import br.edu.ifrn.projeto.repository.DoacaoRepository;
import br.edu.ifrn.projeto.repository.UsuarioRepository;
import br.edu.ifrn.projeto.dominio.Arquivo;
import br.edu.ifrn.projeto.dominio.Doacao;

@Controller
public class inicioController {
	
	//Criação dos repositorios
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	
	//Método de entrada
	/**
	 * Objetivo: Definindo url da página principal
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * @return inicio Nome da página em que a url definida irá retornar
	 * 
	 * */
	@GetMapping("/")
	public String entrar(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("doacao", new Doacao());
		return "inicio";
	}
	
	/**
	 * Objetivo: Definindo url da página colaboradores
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * @return colaborados Nome da página em que a url definida irá retornar
	 * 
	 * */
	@GetMapping("/colaboradores")
	public String entrarColaboradores(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("doacao", new Doacao());
		
		List<Usuario> usuariosEncontrados = usuarioRepository.findAll();
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		return "colaboradores";
	}
	
	/**
	 * Objetivo: Definindo url da página perfil
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * @return perfil Nome da página em que a url definida irá retornar
	 * 
	 * */
	@GetMapping("/perfil")
	public String entrarPerfil(ModelMap model) {
		model.addAttribute("doacao", new Doacao());
		model.addAttribute("usuario", new Usuario());
		
		Usuario usuarioLogado = GetUsuarioLogado();
		List<Doacao> doacoesUsuario = usuarioLogado.getDoacoes(); 
		
		model.addAttribute("usuarioLogado", usuarioLogado);
		model.addAttribute("doacoesEncontradas", doacoesUsuario);
		return "perfil";
	}
	
	
	/**
	 * Objetivo: Definindo url da página login
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * @return login/login Nome da página em que a url definida irá retornar, dentro da pasta login.
	 * 
	 * */
	@GetMapping("/login")
	public String login(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "login/login";
	}
	
	
	/**
	 * Objetivo: Definindo url da página login-error (caso o usuário faça login com dados errados)
	 * 
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * @return login/login Nome da página em que a url definida irá retornar, dentro da pasta login.
	 * 
	 *  */
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("msgErroLogin", "login ou senha incorretos");
		return "login/login";
	}
	
	
	/**
	 * Objetivo: Salvar usuários no banco de dados
	 * 
	 * @param user Recebe o usuário enviado pelo formulário de cadastro
	 * @param attr Tem a função de redirecionar mensagens para página redirecionada no return
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return redirecionando para páginas com as mensagens de feedback
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario user,RedirectAttributes attr,ModelMap model) {
		
		
		List<String> msgValidacao = validarDados(user);
		
		
		if (!msgValidacao.isEmpty()) {
			attr.addFlashAttribute("msgsErro", msgValidacao);
			
			return "redirect:/login";
		}	
		String senhaCriptograda = new BCryptPasswordEncoder().encode(user.getSenha());
		user.setSenha(senhaCriptograda);
		
		usuarioRepository.save(user);
		attr.addFlashAttribute("msgSucess", "Cadastro realizado");
		
		
		return "redirect:/login";
	}
	
	
	/**
	 * Objetivo: Salvar Usuários no banco de dados, porém essa função está inclusiva apenas para admin
	 * 
	 * @param user Recebe o usuário enviado pelo formulário de cadastro
	 * @param attr Tem a função de redirecionar mensagens para página redirecionar no return
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return redirecionando para páginas com as mensagens de feedback
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@PostMapping("/salvarAdmin")
	public String salvarAdmin(@Valid Usuario user,RedirectAttributes attr,ModelMap model) {
		
		
		List<String> msgValidacao = validarDados(user);
		
		
		if (!msgValidacao.isEmpty()) {
			attr.addFlashAttribute("msgsErro", msgValidacao);
			
			
			return "redirect:/";
		}	
		String senhaCriptograda = new BCryptPasswordEncoder().encode(user.getSenha());
		user.setSenha(senhaCriptograda);
		
		usuarioRepository.save(user);
		attr.addFlashAttribute("msgSucess", "Cadastro realizado");
		
		
		return "redirect:/";
	}
	
	/**
	 * Objetivo: Salvar fotos no banco de dados
	 * 
	 * @param user Recebe o usuário enviado pelo formulário de cadastro
	 * @param attr Tem a função de redirecionar mensagens para página redirecionar no return
	 * @param arquivo Recebe a foto dada pelo o usuário para salvar no banco de dados
	 * @param model Tem a função de enviar atributos para as páginas HTML
	 * 
	 * @return redirecionando para páginas com as mensagens de feedback
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@PostMapping("/salvarFotos")
	public String salvarFotos(@Valid Usuario user,RedirectAttributes attr, 
			@RequestParam("file") MultipartFile arquivo,
			 ModelMap model) {
		
		
        Usuario usuario = GetUsuarioLogado();
		
		try {
			if(arquivo != null && !arquivo.isEmpty()) {
				String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());
			
				arquivoRepository.save(arquivoBD);
				
				if(usuario.getFoto() != null && usuario.getFoto().getId() != null && usuario.getFoto().getId() > 0) {
					arquivoRepository.delete(usuario.getFoto());
				}
				
				usuario.setFoto(arquivoBD);
			}else {
				usuario.setFoto(null);
			}
			
			usuarioRepository.save(usuario);
			attr.addFlashAttribute("msgSucess", "fotxinha adicionada");
			
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return "redirect:/perfil";
	}
	
	
	/**
	 * Objetivo: Validar dados do usuarios
	 * 
	 * @param user Recebe o usuário enviado pelo formulário de cadastro
	 * 
	 * @return msgs redirecionando as mensagens de feedback de erro
	 * 
	 * */
		private List<String> validarDados(Usuario usuario){
		
		List<String> msgs = new ArrayList<>();
		
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			msgs.add("O campo nome é obrigatório.");
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			msgs.add("O campo email é obrigatório.");
			
		}
		
		Usuario usuario2 = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);
		
		if(usuario2 != null) {
			msgs.add("Esse email já é cadastrado no nosso sistema");
		}
		
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty() || usuario.getSenha().length() < 6) {
			msgs.add("O campo senha é obrigatório ou não tem 6 caracteres.");
		}
		if(!usuario.getSenha().equals(usuario.getConfirmSenha())) {
			msgs.add("As senhas não são iguais");
		}
		
		if (usuario.getCidade() == null || usuario.getCidade().isEmpty()) {
			msgs.add("O campo cidade é obrigatório.");
		}
		
		if (usuario.getFuncao() == null || usuario.getFuncao().isEmpty()) {
			msgs.add("O campo função é obrigatório.");
		}
		
		if (usuario.getEstado() == null || usuario.getEstado().isEmpty()) {
			msgs.add("O campo estado é obrigatório.");
		}
		return msgs;
	}
	
		/**
		 * Objetivo: Validar dados doação
		 * 
		 * @param doacao Recebe a doacao enviado pelo formulário de cadastro
		 * 
		 * @return msgs redirecionando as mensagens de feedback de erro
		 * 
		 * */
		private List<String> validarDoaçoes(Doacao doacao){
			
			List<String> msgs = new ArrayList<>();
			
			if (doacao.getNomeDoDoador() == null || doacao.getNomeDoDoador().isEmpty()) {
				msgs.add("O campo nome é obrigatório.");
			}
			
			if (doacao.getCidade() == null || doacao.getCidade().isEmpty()) {
				msgs.add("O campo cidade é obrigatório.");
			}
			
			if ( doacao.getTelefone() == null || doacao.getTelefone().isEmpty()) {
				msgs.add("O campo telefone é obrigatório.");
			}
			
			if (doacao.getTipo() == null || doacao.getTipo().isEmpty()) {
				msgs.add("O campo tipo é obrigatório.");
			}
			
			if (doacao.getDescricao() == null || doacao.getDescricao().isEmpty()) {
				msgs.add("O campo descrição é obrigatório.");
			}
			return msgs;
		}
	
		/*Pegar estados e construir os selects*/
		@ModelAttribute("estados")
	    public List<String> getEstados(){
	        return Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", 
	        		"PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF");
		}
		
		/**
		 * Objetivo: Salvar doações no banco de dados
		 * 
		 * @param doacao Recebe a doacao enviado pelo formulário de cadastro
		 * @param attr Tem a função de redirecionar mensagens para página redirecionar no return
		 * @param model Tem a função de enviar atributos para as páginas HTML
		 * 
		 * @return redirecionando para páginas com as mensagens de feedback
		 * 
		 * */
		@SuppressWarnings("unchecked")
		@Transactional(readOnly = false)
		@PostMapping("/salvarDoacoes")
		public String salvarDoacoes(Doacao doacao,RedirectAttributes attr, ModelMap model) {
			
			
			/*Usuário logado*/
			Usuario usuarioLogado = GetUsuarioLogado();
			
			List<String> msgValidacaoDoar = validarDoaçoes(doacao);
			
			if (!msgValidacaoDoar.isEmpty()) {
				model.addAttribute("msgsErro", msgValidacaoDoar);	
				return "inicio";
			}
			
			doacao.setUsuarioD(usuarioLogado);
			
			doacaoRepository.save(doacao);
			attr.addFlashAttribute("msgSucess", "Operação realizada com sucesso");
			
			return "redirect:/";
		}
		
		// Pegar tipos de doações para construir selects
		@ModelAttribute("tipos")
	    public List<String> getTipo(){
	        return Arrays.asList("Livros", "Móveis", "Roupas", "Comida", "Tempo", "Outra");
	        
	    }

		// Pegar funções para construir selects
		@ModelAttribute("funcao")
	    public List<String> getFuncao(){
	        return Arrays.asList("Colaborador", "Usuário");
	    }
		
	
		/**
		 * Objetivo: Entrar na página idealizadores
		 * 
		 * @param model Tem a função de enviar atributos para as páginas HTML
		 * @return idealizadores Nome da página em que a url definida irá retornar
		 * 
		 * */
		@GetMapping("/idealizadores")
		public String entrarIdealizadores(ModelMap model) {

			model.addAttribute("usuario", new Usuario());
			model.addAttribute("doacao", new Doacao());
			return "idealizadores";
		}
		
		
		/**
		 * Objetivo: Entrar na página projeto
		 * 
		 * @param model Tem a função de enviar atributos para as páginas HTML
		 * @return projeto Nome da página em que a url definida irá retornar
		 * 
		 * */
		@GetMapping("/projeto")
		public String entrarProjeto(ModelMap model) {

			model.addAttribute("usuario", new Usuario());
			model.addAttribute("doacao", new Doacao());
			return "projeto";
		}
		
		/**
		 * Objetivo: Função para pegar o usuario logado em algumas funções e não repetir código.
		 * 
		 * @return usuarioLogado usuarioLogado na sessão
		 * 
		 * */
		public Usuario GetUsuarioLogado() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	        Usuario usuarioLogado = usuarioRepository.findByEmail(email).get();
	        
	        return usuarioLogado;
		}
}
