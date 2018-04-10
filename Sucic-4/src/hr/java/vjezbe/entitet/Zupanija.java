package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * predstavlja entitet županije
 */
public class Zupanija {
	private String naziv;
	private Drzava drzava;
	private List<Mjesto> listaMjesta;

	/**
	 * prima parametre naziva županije i referencu na državu
	 * @param naziv je naziv županije
	 * @param drzava je referenca na državu
	 */
	public Zupanija(String naziv, Drzava drzava) {
		this.naziv = naziv;
		this.drzava = drzava;
		listaMjesta = new ArrayList<Mjesto>();
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public List<Mjesto> getListaMjesta() {
		return listaMjesta;
	}
}
