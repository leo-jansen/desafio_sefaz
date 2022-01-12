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

	@Override
	public String toString() {
		return String.format("%s: (%d) %s", this.tipo, this.ddd, this.numero);
	}
}
