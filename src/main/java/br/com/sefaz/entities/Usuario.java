package br.com.sefaz.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	@Embedded
	private Telefone telefone;

	public Usuario() {
	}

	public Usuario(String nome, String email, String senha, int ddd, String numero, String tipo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = new Telefone(ddd, numero, tipo);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		return String.format("Nome: %s | Email: %s | %s", this.nome, this.email, this.telefone.toString());
	}
}
