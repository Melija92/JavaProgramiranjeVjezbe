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
import static hr.java.vjezbe.ispisivanje.HelperTestniPodaci.napraviTestnePodatke;

public class Glavna {
	private static final int BROJ_MJERNIH_POSTAJA = 5;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 0;
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		MjernePostaje<MjernaPostaja> listaMjernihPostaja = new MjernePostaje<>();
		//		List<MjernaPostaja> listaMjernihPostaja = new ArrayList<MjernaPostaja>();

//		listaMjernihPostaja = HelperPostavljanje.postaviMjernePostaje
//				(BROJ_MJERNIH_POSTAJA, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, listaMjernihPostaja);

		listaMjernihPostaja = napraviTestnePodatke();

		ispisiPodatkeSvihMjernihPostaja(listaMjernihPostaja);

		ispisiZupanijeBezDuplikataAbecedno(listaMjernihPostaja);

		ispisiSenzoreKojiPostojeUJednomMjestu(listaMjernihPostaja);

//		ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde(listaMjernihPostaja);
	}

	/**
	 * ispisuje podatke mjernih postaja
	 *
	 * @param mjernePostaje
	 */
	private static void ispisiPodatkeSvihMjernihPostaja(MjernePostaje<MjernaPostaja> mjernePostaje) {

		mjernePostaje.getSortedList().forEach(a -> {
			if (a instanceof RadioSondaznaMjernaPostaja) {
				RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = (RadioSondaznaMjernaPostaja) a;
				Integer visinaPostaje = radioSondaznaMjernaPostaja.dohvatiVisinuPostaje();
				System.out.println("Postaja je radio sondažna!!!");
				System.out.println("Visina radio sondažne postaje: " + visinaPostaje);
				radioSondaznaMjernaPostaja.povecajVisinu(visinaPostaje);
				System.out.println("Nova visina radio sondažne mjerne postaje je: "
						+ radioSondaznaMjernaPostaja.dohvatiVisinuPostaje());
				System.out.println("Naziv mjerne postaje: " + a.getNaziv());
			}
			System.out.println("Postaja se nalazi u mjestu " + a.getMjesto().getNaziv()
					+ " (" + a.getMjesto().getVrstaMjesta() + ") " + ", "
					+ "županiji " + a.getMjesto().getZupanija().getNaziv() + ", "
					+ "državi " + a.getMjesto().getZupanija().getDrzava().getNaziv());
			System.out.println("Točne koorinate postaje su x:"
					+ a.getGeografskaTocka().getX()
					+ " " + "y:"
					+ a.getGeografskaTocka().getY());

			List<Senzor> sortiraniSenzori = a.dohvatiSenzore();
			System.out.println("Vrijednosti senzora postaje su:");
			sortiraniSenzori.forEach(b -> System.out.println(b.dohvatiPodatkeSenzora()));
			System.out.println("---------------------------------------------------");

		});
	}
}