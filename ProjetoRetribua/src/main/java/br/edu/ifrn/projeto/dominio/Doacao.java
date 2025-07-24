package br.edu.ifrn.projeto.dominio;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Doacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nomeDoDoador;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String tipo;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne(optional = false)
	private Usuario usuarioD;
	
	
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
		Doacao other = (Doacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNomeDoDoador() {
		return nomeDoDoador;
	}
	public void setNomeDoDoador(String nomeDoDoador) {
		this.nomeDoDoador = nomeDoDoador;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuarioD() {
		return usuarioD;
	}
	public void setUsuarioD(Usuario usuarioD) {
		this.usuarioD = usuarioD;
	}	
	
}
