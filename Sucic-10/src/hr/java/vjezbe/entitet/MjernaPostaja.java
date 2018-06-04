package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Predstavlja entitet mjerna postaja opisan atributima naziv, mjesto, geografska točka,
 * i senzori
 */
public class MjernaPostaja extends BazniEntitet {

	private String naziv;
	private Mjesto mjesto;
	private GeografskaTocka geografskaTocka;
	private List<Senzor> senzori;

    /**
     * Inicijalizira podatke mjerne postaje
     * @param naziv podatak koji predstavlja naziv mjerne postaje
     * @param mjesto podatak koji predstavlja mjesto mjerne postaje
     * @param geografskaTocka podatak koji predstavlja geografsku točku mjerne postaje
     * @param senzori podatak koji predstavlja senzore mjerne postaje
     */
	public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
		super();
		this.naziv = naziv;
		this.mjesto = mjesto;
		this.geografskaTocka = geografskaTocka;
		this.senzori = senzori;
	}

	public MjernaPostaja(Integer id, String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
		super(id);
		this.naziv = naziv;
		this.mjesto = mjesto;
		this.geografskaTocka = geografskaTocka;
		this.senzori = senzori;
	}

    public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka) {
        super();
        this.naziv = naziv;
        this.mjesto = mjesto;
        this.geografskaTocka = geografskaTocka;
    }

    public MjernaPostaja(Integer id, String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka) {
        super(id);
        this.naziv = naziv;
        this.mjesto = mjesto;
        this.geografskaTocka = geografskaTocka;
    }

	public MjernaPostaja(Integer id) {
		super(id);
	}

	/**
     * Dohvaća naziv mjerne postaje
     */
	public String getNaziv() {
		return naziv;
	}

    /**
     * Postavlja naziv u lokalnu varijablu
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

    /**
     * Dohvaća mjesto mjerne postaje
     */
	public Mjesto getMjesto() {
		return mjesto;
	}

    /**
     * Postavlja vrijednost mjesta u lokalnu varijablu
     */
	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

    /**
     * Dohvaća geografsku točku mjerne postaje
     */
	public GeografskaTocka getGeografskaTocka() {
		return geografskaTocka;
	}

    /**
     * Postavlja vrijednost geografske točke u lokalnu varijablu
     */
	public void setGeografskaTocka(GeografskaTocka geografskaTocka) {
		this.geografskaTocka = geografskaTocka;
	}

    /**
     * Dohvaća sve senzore mjerne postaje i izvršava sortiranje prema mjernoj jedinici
	 */
	public List<Senzor> dohvatiSenzore(){
		senzori.sort((o1, o2) -> o1.getMjernaJedinica().compareTo(o2.getMjernaJedinica()));

		return senzori;
	}

	public Optional<Senzor> dohvatiSenzor(Integer id){
	Optional<Senzor> senzor = senzori.stream().filter(p -> p.getId() == id).findFirst();
	return  senzor;
	}

	@Override
    public String toString(){return getNaziv();}
}
