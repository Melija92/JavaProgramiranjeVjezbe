package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * predstavlja entitet države s atributima naziva i povrsine
 */
public class Drzava extends BazniEntitet{
	private String naziv;
	private BigDecimal povrsina;
	private List<Zupanija> listaZupanije;

	/**
	 * prima parametre naziva i površine koje sprema u pripadajuće varijable
	 * @param naziv služi za naziv države
	 * @param povrsina služi za definiranje površine države
	 */
	public Drzava(String naziv, BigDecimal povrsina) {
		super();
		this.naziv = naziv;
		this.povrsina = povrsina;
		listaZupanije = new ArrayList<Zupanija>();
	}
	public Drzava(String naziv, BigDecimal povrsina, Integer id){
		super(id);
		this.naziv = naziv;
		this.povrsina = povrsina;
		listaZupanije = new ArrayList<Zupanija>();
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

	public List<Zupanija> getListaZupanije() {
		return listaZupanije;
	}
}
