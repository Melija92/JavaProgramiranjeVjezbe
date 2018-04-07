package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.util.List;
import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperUnosenje.*;

/**
 * predstavlja entitet za pomoćnu klasu za postavljanje
 */
public class HelperPostavljanje {
    public static List<MjernaPostaja> postaviMjernePostaje
            (int BROJ_MJERNIH_POSTAJA, int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, List<MjernaPostaja> mjernePostaje){

        Scanner unos = new Scanner(System.in);
        Integer ukupanBrojPostaja = BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;

        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(i) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            Senzor[] senzori = pomocnaMetodaZapostavljanjeSenzora(unos);
            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, mjesto, geografskaTocka, senzori);

            mjernePostaje.add(mjernaPostaja);
        }

        for (int n = BROJ_MJERNIH_POSTAJA; n < ukupanBrojPostaja; n++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(n) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            Senzor[] senzori = pomocnaMetodaZapostavljanjeSenzora(unos);
            RadioSondaznaMjernaPostaja radioSondaznamjernaPostaja = unesiRadioSondaznuMjernuPostaju
                                                            (unos, mjesto, geografskaTocka, senzori);

            mjernePostaje.add(radioSondaznamjernaPostaja);
        }
        unos.close();

        return mjernePostaje;
    }

    /**
     * pomoćna statična klasa za postavljanje podataka mjesta
     */
    private static Mjesto pomocnaMetodaZapostavljanjeMjesta(Scanner unos){
        Drzava drzava = unesiDrzavu(unos);
        Zupanija zupanija = unesiZupaniju(unos, drzava);
        Mjesto mjesto = unesiMjesto(unos, zupanija);

        return mjesto;
    }

    /**
     * pomoćna statična metoda za postavljanje polje senzora
     * @param unos
     * @return
     */
    private static Senzor[] pomocnaMetodaZapostavljanjeSenzora(Scanner unos){
        SenzorTemperature senzorTemperature = unesiSenzorTemperature(unos);
        SenzorVjetra senzorVjetra = unesiSenzorVjetra(unos);
        SenzorVlage senzorVlage = unesiSenzorVlage(unos);
        Senzor[] senzori = {senzorVlage, senzorVjetra, senzorTemperature};

        return senzori;
    }
}
