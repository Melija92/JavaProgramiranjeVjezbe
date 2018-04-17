package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * predstavlja entitet mjesta s atributima naziva i reference na županiju
 */
public class Mjesto {
	private String naziv;
	private Zupanija zupanija;
	private VrstaMjesta vrstaMjesta;

	public VelicinaMjesta getVelicinaMjesta() {
		return velicinaMjesta;
	}

	public void setVelicinaMjesta(VelicinaMjesta velicinaMjesta) {
		this.velicinaMjesta = velicinaMjesta;
	}

	private VelicinaMjesta velicinaMjesta;
	private List<MjernaPostaja> listaMjernihPostaja;

	/**
	 * prima parametre naziva i reference na županiju
	 * @param naziv je naziv mjesta
	 * @param zupanija je referenca na županiju
	 */
	public Mjesto(String naziv, Zupanija zupanija, VrstaMjesta vrstaMjesta, VelicinaMjesta velicinaMjesta) {
		this.naziv = naziv;
		this.zupanija = zupanija;
		this.vrstaMjesta = vrstaMjesta;
		this.velicinaMjesta = velicinaMjesta;
		listaMjernihPostaja = new ArrayList<MjernaPostaja>();
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Zupanija getZupanija() {
		return zupanija;
	}

	public void setZupanija(Zupanija zupanija) {
		this.zupanija = zupanija;
	}
	public VrstaMjesta getVrstaMjesta() {
		return vrstaMjesta;
	}

	public void setVrstaMjesta(VrstaMjesta vrstaMjesta) {
		this.vrstaMjesta = vrstaMjesta;
	}

	public List<MjernaPostaja> getListaMjernihPostaja() {
		return listaMjernihPostaja;
	}
}
