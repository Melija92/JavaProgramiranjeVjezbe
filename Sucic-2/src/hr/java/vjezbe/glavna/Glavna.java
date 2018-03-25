package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

import hr.java.vjezbe.entitet.*;

public class Glavna {
	private static final int BROJ_MAKSIMALNIH_UNOSA = 3;
	
	public static void main(String[] args) {
		Scanner unos = new Scanner(System.in);
		MjernaPostaja[] arrayMjernihPostaja = new MjernaPostaja[BROJ_MAKSIMALNIH_UNOSA];
		
		for (int i = 0; i < BROJ_MAKSIMALNIH_UNOSA; i++) {
			System.out.println("Unesite " + odrediRedniBroj(i) + " mjernu postaju");

			Drzava drzava = unesiDrzavu(unos);

			Zupanija zupanija = unesiZupaniju(unos, drzava);

			Mjesto mjesto = unesiMjesto(unos, zupanija);

			GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
			
			MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, mjesto, geografskaTocka);

			arrayMjernihPostaja[i] = mjernaPostaja;
		}

		ispisiPodatke(arrayMjernihPostaja);
		
		unos.close();
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
			
			System.out.println("-------------------------------");
		}
	}

	private static Drzava unesiDrzavu(Scanner unosDrzave) {
		System.out.println("Unesite naziv države");
		String naziv = unosDrzave.nextLine();
		
		System.out.println("Unesite površinu države");		
		BigDecimal povrsina = unosDrzave.nextBigDecimal();
		
		unosDrzave.nextLine();
		
		return new Drzava(naziv, povrsina);
	}
	
	private static Zupanija unesiZupaniju(Scanner unosZupanije, Drzava drzava) {
		System.out.println("Unesite naziv županije");
		String naziv = unosZupanije.nextLine();
		
		return new Zupanija(naziv, drzava);
	}
	
	private static Mjesto unesiMjesto(Scanner unosMjesta, Zupanija zupanija) {
		System.out.println("Unesite naziv mjesta");
		String naziv = unosMjesta.nextLine();
		
		return new Mjesto(naziv, zupanija);
	}
	
	private static GeografskaTocka unesiGeografskuTocku(Scanner unosGeoTocke) {
		System.out.println("Unesite Geo koordinatu X");
		BigDecimal x = unosGeoTocke.nextBigDecimal();
		
		unosGeoTocke.nextLine();
		
		System.out.println("Unesite Geo koordinatu Y");
		BigDecimal y = unosGeoTocke.nextBigDecimal();
		
		unosGeoTocke.nextLine();
		
		return new GeografskaTocka(x, y);
	}
	
	private static MjernaPostaja unesiMjernuPostaju(Scanner unosMjernePostaje,
													Mjesto mjesto, GeografskaTocka geografskaTocka) {
		System.out.println("Unesite naziv mjerne postaje");
		String naziv = unosMjernePostaje.nextLine();
		
		return new MjernaPostaja(naziv, mjesto, geografskaTocka, null);
	}

	private static String odrediRedniBroj(int broj) {
		String redniBroj;
		
		switch (broj) {
		case 0:
			redniBroj = "prvu";
			break;
		case 1:
			redniBroj = "drugu";
			break;
		case 2:
			redniBroj = "trecu";
			break;
		case 3:
			redniBroj = "cetvrtu";
			break;

		default:
			redniBroj = "nepoznat redni broj";
			break;
		}
		
		return redniBroj;
	}
}
