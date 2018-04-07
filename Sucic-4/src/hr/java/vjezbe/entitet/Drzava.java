package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja entitet države s atributima naziva i povrsine
 */
public class Drzava {
	private String naziv;

	private BigDecimal povrsina;

	/**
	 * prima parametre naziva i površine koje sprema u pripadajuće varijable
	 * @param naziv služi za naziv države
	 * @param povrsina služi za definiranje površine države
	 */
	public Drzava(String naziv, BigDecimal povrsina) {
		this.naziv = naziv;
		this.povrsina = povrsina;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public BigDecimal getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(BigDecimal povrsina) {
		this.povrsina = povrsina;
	}
}
