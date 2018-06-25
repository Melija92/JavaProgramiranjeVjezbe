package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperUnosenje.*;

public class HelperPostavljanje {
    public static MjernaPostaja[] postaviMjernePostaje
            (int BROJ_MJERNIH_POSTAJA, int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, MjernaPostaja[] mjernePostaje){

        Scanner unos = new Scanner(System.in);
        Integer ukupanBrojPostaja = BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;

        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(i) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, mjesto, geografskaTocka);

            mjernePostaje[i] = mjernaPostaja;
        }

        for (int n = BROJ_MJERNIH_POSTAJA; n < ukupanBrojPostaja; n++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(n) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
        }
        unos.close();

        return mjernePostaje;
    }

    private static Mjesto pomocnaMetodaZapostavljanjeMjesta(Scanner unos){
        Drzava drzava = unesiDrzavu(unos);
        Zupanija zupanija = unesiZupaniju(unos, drzava);
        Mjesto mjesto = unesiMjesto(unos, zupanija);

        return mjesto;
    }
}
