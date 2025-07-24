package br.edu.ifrn.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.projeto.dominio.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
