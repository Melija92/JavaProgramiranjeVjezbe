package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static hr.java.vjezbe.unosenje.UnosenjeLokacija.*;
import static hr.java.vjezbe.unosenje.UnosenjeMjernihPostaja.unesiMjernuPostaju;
import static hr.java.vjezbe.unosenje.UnosenjeMjernihPostaja.unesiRadioSondaznuMjernuPostaju;
import static hr.java.vjezbe.unosenje.UnosenjeSenzora.unesiSenzorTemperature;
import static hr.java.vjezbe.unosenje.UnosenjeSenzora.unesiSenzorVjetra;
import static hr.java.vjezbe.unosenje.UnosenjeSenzora.unesiSenzorVlage;

/**
 * predstavlja entitet za pomoćnu klasu za postavljanje
 */
public class HelperPostavljanje {
    public static MjernePostaje<MjernaPostaja> postaviMjernePostaje
            (int BROJ_MJERNIH_POSTAJA, int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, MjernePostaje<MjernaPostaja> mjernePostaje){

        Scanner unos = new Scanner(System.in);
        Integer ukupanBrojPostaja = BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;

        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(i) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos, mjernePostaje);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            List<Senzor> senzori = pomocnaMetodaZapostavljanjeSenzora(unos);
            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, mjesto, geografskaTocka, senzori, mjernePostaje);

            mjernePostaje.add(mjernaPostaja);
        }

        for (int n = BROJ_MJERNIH_POSTAJA; n < ukupanBrojPostaja; n++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(n) + " mjernu postaju");

            Mjesto mjesto = pomocnaMetodaZapostavljanjeMjesta(unos, mjernePostaje);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            List<Senzor> senzori = pomocnaMetodaZapostavljanjeSenzora(unos);
            RadioSondaznaMjernaPostaja radioSondaznamjernaPostaja = unesiRadioSondaznuMjernuPostaju
                                                            (unos, mjesto, geografskaTocka, senzori, mjernePostaje);

            mjernePostaje.add(radioSondaznamjernaPostaja);
        }
        unos.close();

        return mjernePostaje;
    }

    /**
     * pomoćna statična klasa za postavljanje podataka mjesta
     */
    private static Mjesto pomocnaMetodaZapostavljanjeMjesta(Scanner unos, MjernePostaje<MjernaPostaja> mjernePostaje){
        Drzava drzava = unesiDrzavu(unos);
        Zupanija zupanija = unesiZupaniju(unos, drzava, mjernePostaje);
        Mjesto mjesto = unesiMjesto(unos, zupanija, mjernePostaje);

        return mjesto;
    }

    /**
     * pomoćna statična metoda za postavljanje polje senzora
     * @param unos
     * @return
     */
    private static List<Senzor> pomocnaMetodaZapostavljanjeSenzora(Scanner unos){
        SenzorTemperature senzorTemperature = unesiSenzorTemperature(unos);
        SenzorVjetra senzorVjetra = unesiSenzorVjetra(unos);
        SenzorVlage senzorVlage = unesiSenzorVlage(unos);

        List<Senzor> senzori = new ArrayList<Senzor>();

        senzori.add(senzorTemperature);
        senzori.add(senzorVjetra);
        senzori.add(senzorVlage);

        return senzori;
    }
}
