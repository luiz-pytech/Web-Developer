package br.edu.ifrn.projeto.controladores;

/**
 * Objetivo: Este controlador tem o intuito de salvar e fazer download de arquivos
 * guardados no banco de dados.
 * 
 * @author Ariane Selli Melo de Souza (arianneselli2013@gmail.com)
 * @author Luiz Felipe de Souza Silva (lf06092004@gmail.com)
 * 
 * Data de criação: 07/03/2022
 * ###########################
 * 
 * @version 1.0
 * 
 * */
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.projeto.dominio.Arquivo;
import br.edu.ifrn.projeto.repository.ArquivoRepository;


@Controller
public class DownloadArquivoController {

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	/**
	 * Objetivo: Definindo url e construindo o metodo para realizar downloads e salvar arquivos
	 * 
	 * @param idArquivo guardar o ID do arquivo
	 * @param salvar indicar se o método irá salvar no banco de dados ou realizar download para página.
	 * 
	 * @return retorna o arquivo.
	 * */
	@GetMapping("/download/{idArquivo}")
	public ResponseEntity<?> downloadFile(@PathVariable Long idArquivo, @PathParam("salvar") String salvar){
		
		
		Arquivo arquivoBD = arquivoRepository.findById(idArquivo).get();
		
		String texto = (salvar == null || salvar.equals("true")) ? "attachment; filename=\""+arquivoBD.getNomeArquivo()+ "\""
				: "inline; filename=\""+arquivoBD.getNomeArquivo()+ "\"";
		
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo())).
				header(HttpHeaders.CONTENT_DISPOSITION, texto).body(new ByteArrayResource(arquivoBD.getDados()));
	}
}
