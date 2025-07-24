package br.edu.ifrn.projeto.dominio;

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


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

@Entity
public class Usuario {
	
	// admin e comum para utilizar nas restrições de acesso das páginas e funcionalidades
	public static final String ADMIN = "ADMIN";
	public static final String USUARIO_COMUM = "COMUM";
	
	
	// id do usuário
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// relacionamento um para um, uma foto para um usuário
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Arquivo foto;
	
	// nome do usuário
	@Column(nullable = false)
	private String nome;
	
	// email do usuário
	@Column(nullable = false)
	@Email
	private String email;
	
	// senha do usuário
	@Column(nullable = false)
	private String senha;
	
	// para verificar se a senha fornecida anteriormente é igual
	@Transient
	private String confirmSenha;
	
	// cidade 
	@Column(nullable = false)
	private String cidade;
	
	// estado
	@Column(nullable = false)
	private String estado;
	
	// função no projeto retribua 
	@Column(nullable = false)
	private String funcao;
	
	// para a identificação, se o usuário é um administrador ou se o perfil é de usuário comum
	@Column(nullable = false)
	private String perfil = USUARIO_COMUM;
	
	// relacionamento de um para muitos da classe usuário
	@OneToMany(mappedBy = "usuarioD")
	private List<Doacao> doacoes;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	/*Métodos especiais: Getters e Setters, criado automático pelo ambiente de desenvolvimento */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmSenha() {
		return confirmSenha;
	}
	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public java.util.List<Doacao> getDoacoes() {
		return doacoes;
	}
	public void setDoacoes(java.util.List<Doacao> doacoes) {
		this.doacoes = doacoes;
	}
	public Arquivo getFoto() {
		return foto;
	}
	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}
	
	
}
