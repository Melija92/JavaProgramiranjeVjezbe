package hr.java.vjezbe.unosenje;

import hr.java.vjezbe.entitet.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperOperacije.vratiInteger;

public class UnosenjeMjernihPostaja {
    /**
     * pomoćna statična metoda za unos mjerne postaje
     */
    public static MjernaPostaja unesiMjernuPostaju(Scanner unosMjernePostaje,
                                                   Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori, List<MjernaPostaja> postaje) {
        System.out.println("Unesite naziv mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();
        MjernaPostaja mjernaPostaja;

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getNaziv()
                .equals(mjesto.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Mjesto postjeceMjestoIzListe = postaja.get().getMjesto();
            mjernaPostaja = new MjernaPostaja(naziv, postjeceMjestoIzListe, geografskaTocka, senzori);
            postjeceMjestoIzListe.getListaMjernihPostaja().add(mjernaPostaja);
        }
        else {
            mjernaPostaja = new MjernaPostaja(naziv, mjesto, geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(mjernaPostaja);
        }

        return mjernaPostaja;
    }

    /**
     * pomoćna statična metoda za unos radio sondažne mjerne postaje
     */
    public static RadioSondaznaMjernaPostaja unesiRadioSondaznuMjernuPostaju(Scanner unosMjernePostaje,
                                                                             Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori, List<MjernaPostaja> postaje) {
        System.out.println("Unesite naziv radio sondažne mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();
        RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja;
        Integer visina = vratiInteger(unosMjernePostaje);

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getNaziv()
                .equals(mjesto.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Mjesto postjeceMjestoIzListe = postaja.get().getMjesto();
            radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(visina, naziv, postjeceMjestoIzListe, geografskaTocka, senzori);
            postjeceMjestoIzListe.getListaMjernihPostaja().add(radioSondaznaMjernaPostaja);
        }
        else {
            radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(visina, naziv, mjesto, geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(radioSondaznaMjernaPostaja);
        }

        return radioSondaznaMjernaPostaja;
    }
}
