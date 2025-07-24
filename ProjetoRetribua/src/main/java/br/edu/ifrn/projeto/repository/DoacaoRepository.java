package br.edu.ifrn.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.projeto.dominio.Doacao;
import br.edu.ifrn.projeto.dominio.Usuario;


public interface DoacaoRepository extends JpaRepository<Doacao, Integer>{

	@Query("select u from Doacao u where u.nomeDoDoador like %:nomeDoDoador%")
	List<Doacao> findByNome(@Param("nomeDoDoador") String nome);
	
	@Query("select u from Doacao u where u.usuarioD.id = :usuarioD ")
	List<Doacao> findDoacao(@Param("usuarioD") int usuarioD);

}
