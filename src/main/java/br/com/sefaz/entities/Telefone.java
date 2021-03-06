package br.com.sefaz.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone {
	private int ddd;
	private String numero;
	private String tipo;

	public Telefone() {
	}

	public Telefone(int ddd, String numero, String tipo) {
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
	}

	public int getDdd() {
		return ddd;
	}

	public String getNumero() {
		return numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return String.format("%s: (%d) %s", this.tipo, this.ddd, this.numero);
	}
}
