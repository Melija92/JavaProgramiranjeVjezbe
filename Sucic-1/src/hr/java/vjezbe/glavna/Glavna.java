package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

public class Glavna {
	private static final int BROJ_MJERNIH_POSTAJA = 2;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;

	public static void main(String[] args) {
		MjernaPostaja[] mjernePostaje = new MjernaPostaja[BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA];

		mjernePostaje = HelperPostavljanje.postaviMjernePostaje
				(BROJ_MJERNIH_POSTAJA, BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, mjernePostaje);

		ispisiPodatke(mjernePostaje);
	}
	
	private static void ispisiPodatke(MjernaPostaja[] mjernePostaje) {
		for (MjernaPostaja mjernaPostaja : mjernePostaje) {
			System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());
			
			System.out.println("Postaja se nalazi u mjestu " 
			+ mjernaPostaja.getMjesto().getNaziv() + ", "
			+ "županiji " + mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", "
			+ "državi " + mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());
			
			System.out.println("Točne koorinate postaje su x:" 
			+ mjernaPostaja.getGeografskaTocka().getX()
			+ " " + "y:" 
			+ mjernaPostaja.getGeografskaTocka().getY());

			System.out.println("---------------------------------------------------");
		}
	}
}
