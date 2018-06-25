package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class HelperUnosenje {
    public static Drzava unesiDrzavu(Scanner unosDrzave) {
        System.out.println("Unesite naziv države");
        String naziv = unosDrzave.nextLine();

        System.out.println("Unesite površinu države");
        BigDecimal povrsina = unosDrzave.nextBigDecimal();

        unosDrzave.nextLine();

        return new Drzava(naziv, povrsina);
    }

    public static Zupanija unesiZupaniju(Scanner unosZupanije, Drzava drzava) {
        System.out.println("Unesite naziv županije");
        String naziv = unosZupanije.nextLine();

        return new Zupanija(naziv, drzava);
    }

    public static Mjesto unesiMjesto(Scanner unosMjesta, Zupanija zupanija) {
        System.out.println("Unesite naziv mjesta");
        String naziv = unosMjesta.nextLine();

        return new Mjesto(naziv, zupanija);
    }

    public static GeografskaTocka unesiGeografskuTocku(Scanner unosGeoTocke) {
        System.out.println("Unesite Geo koordinatu X");
        BigDecimal x = unosGeoTocke.nextBigDecimal();

        unosGeoTocke.nextLine();

        System.out.println("Unesite Geo koordinatu Y");
        BigDecimal y = unosGeoTocke.nextBigDecimal();

        unosGeoTocke.nextLine();

        return new GeografskaTocka(x, y);
    }

    public static MjernaPostaja unesiMjernuPostaju(Scanner unosMjernePostaje,
                                                    Mjesto mjesto, GeografskaTocka geografskaTocka) {
        System.out.println("Unesite naziv mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();

        return new MjernaPostaja(naziv, mjesto, geografskaTocka);
    }
}
