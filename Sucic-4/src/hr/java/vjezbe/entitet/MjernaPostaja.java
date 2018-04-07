package hr.java.vjezbe.entitet;

import java.util.Arrays;
import java.util.List;

/**
 * predstavlja entitet mjernepostaje s atributima nazivom, referencom na mjesto, graficku tocku te polje senzora
 */
public class MjernaPostaja {
	private String naziv;
	
	private Mjesto mjesto;
	
	private GeografskaTocka geografskaTocka;

	private List<Senzor> senzori;

	/**
	 * prima parametre naziva, mjesta, geografske tocke i polje senzora
	 * @param naziv je naziv mjerne postaje
	 * @param mjesto je referenca na mjesto
	 * @param geografskaTocka je referenca na geografsku točku
	 * @param senzori je polje senzora
	 */
	public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
		this.naziv = naziv;
		this.mjesto = mjesto;
		this.geografskaTocka = geografskaTocka;
		this.senzori = senzori;
	}

	/**
	 * sortira senzore u objektu po nazivu mjerne jedinice senzora abecedeno
	 * @return
	 */
	public List<Senzor> dohvatiSenzore(){
		Arrays.sort(senzori, (p1, p2) -> p1.getMjernaJedinica().compareTo(p2.getMjernaJedinica()));
		return senzori;
	}


	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Mjesto getMjesto() {
		return mjesto;
	}

	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

	public GeografskaTocka getGeografskaTocka() {
		return geografskaTocka;
	}

	public void setGeografskaTocka(GeografskaTocka geografskaTocka) {
		this.geografskaTocka = geografskaTocka;
	}

	public Senzor[] getSenzori() {
		return senzori;
	}

	public void setSenzori(Senzor[] senzori) {
		this.senzori = senzori;
	}
}
