package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

import hr.java.vjezbe.sortiranje.ZupanijaSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisiSenzoreKojiPostojeUJednomMjestu;
import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisiZupanijeBezDuplikataAbecedno;
import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde;

public class Glavna {
	private static final int BROJ_MJERNIH_POSTAJA = 5;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 0;
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		List<MjernaPostaja> listaMjernihPostaja = new ArrayList<MjernaPostaja>();

		listaMjernihPostaja = HelperPostavljanje.postaviMjernePostaje
				(BROJ_MJERNIH_POSTAJA, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, listaMjernihPostaja);

		ispisiPodatkeSvihMjernihPostaja(listaMjernihPostaja);

		ispisiZupanijeBezDuplikataAbecedno(listaMjernihPostaja);

		ispisiSenzoreKojiPostojeUJednomMjestu(listaMjernihPostaja);

		//ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde(listaMjernihPostaja);
	}

	/**
	 * ispisuje podatke mjernih postaja
	 * @param mjernePostaje
	 */
	private static void ispisiPodatkeSvihMjernihPostaja(List<MjernaPostaja> mjernePostaje) {
		for (MjernaPostaja mjernaPostaja : mjernePostaje) {
			System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());

			mjernaPostaja = provjeriIIspisiJeLiRadioSondazna(mjernaPostaja);
			
			System.out.println("Postaja se nalazi u mjestu " 
			+ mjernaPostaja.getMjesto().getNaziv() + " (" +  mjernaPostaja.getMjesto().getVrstaMjesta() + ") " + ", "
			+ "županiji " + mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", "
			+ "državi " + mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());
			
			System.out.println("Točne koorinate postaje su x:" 
			+ mjernaPostaja.getGeografskaTocka().getX()
			+ " " + "y:" 
			+ mjernaPostaja.getGeografskaTocka().getY());

			List<Senzor> sortiraniSenzori = mjernaPostaja.dohvatiSenzore();
			System.out.println("Vrijednosti senzora postaje su:");
			for (Senzor senzor : sortiraniSenzori) {
				System.out.println(senzor.dohvatiPodatkeSenzora());
			}
			System.out.println("---------------------------------------------------");
		}
	}

	/**
	 * pomoćna metoda za provjeru je li mjerna postaja radio sondažna te ispis njenih podataka
	 * @param mjernaPostaja
	 * @return
	 */
	private static MjernaPostaja provjeriIIspisiJeLiRadioSondazna(MjernaPostaja mjernaPostaja){
		if(mjernaPostaja instanceof RadioSondaznaMjernaPostaja){
			RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = (RadioSondaznaMjernaPostaja)mjernaPostaja;
			Integer visinaPostaje = radioSondaznaMjernaPostaja.dohvatiVisinuPostaje();

			System.out.println("Postaja je radio sondažna");
			System.out.println("Visina radio sondažne postaje: " + visinaPostaje);

			radioSondaznaMjernaPostaja.povecajVisinu(visinaPostaje);
			System.out.println("Nova visina radio sondažne mjerne postaje je: "
					+ radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());

			return radioSondaznaMjernaPostaja;
		}

		return mjernaPostaja;
	}
}
