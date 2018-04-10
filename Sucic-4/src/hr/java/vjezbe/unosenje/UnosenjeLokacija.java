package hr.java.vjezbe.unosenje;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.glavna.Glavna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperEnumUnosenje.unosVrsteMjesta;
import static hr.java.vjezbe.glavna.HelperOperacije.vratiBigDecimal;

public class UnosenjeLokacija {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
    /**
     * pomoćna statična metoda za unos države
     */
    public static Drzava unesiDrzavu(Scanner unosDrzave) {
        System.out.println("Unesite naziv države");
        BigDecimal povrsina;
        String naziv = unosDrzave.nextLine();

        System.out.println("Unesite površinu države");
        povrsina = vratiBigDecimal(unosDrzave);


        return new Drzava(naziv, povrsina);
    }

    /**
     * pomoćna statična metoda za unos županije
     */
    public static Zupanija unesiZupaniju(Scanner unosZupanije, Drzava drzava, List<MjernaPostaja> postaje) {
        System.out.println("Unesite naziv županije");
        String naziv = unosZupanije.nextLine();
        Zupanija zupanija;

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getZupanija()
                .getDrzava().getNaziv().equals(drzava.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Drzava postjecaDrzavaIzListe = postaja.get().getMjesto().getZupanija().getDrzava();
            zupanija = new Zupanija(naziv, postjecaDrzavaIzListe);
            postjecaDrzavaIzListe.getListaZupanije().add(zupanija);
        }
        else {
            zupanija = new Zupanija(naziv, drzava);
            drzava.getListaZupanije().add(zupanija);
        }

        return zupanija;
    }

    /**
     * pomoćna statična metoda za unos mjesta
     */
    public static Mjesto unesiMjesto(Scanner unosMjesta, Zupanija zupanija, List<MjernaPostaja> postaje) {
        System.out.println("Unesite naziv mjesta");
        String naziv = unosMjesta.nextLine();
        VrstaMjesta vrstaMjesta = unosVrsteMjesta(unosMjesta);
        Mjesto mjesto;

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getZupanija()
                .getNaziv().equals(zupanija.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Zupanija postjecaZupanijaIzListe = postaja.get().getMjesto().getZupanija();
            mjesto = new Mjesto(naziv, postjecaZupanijaIzListe, vrstaMjesta);
            postjecaZupanijaIzListe.getListaMjesta().add(mjesto);
        }
        else {
            mjesto = new Mjesto(naziv, zupanija, vrstaMjesta);
            zupanija.getListaMjesta().add(mjesto);
        }

        return mjesto;
    }

    /**
     * pomoćna statična metoda za unos geografske točke
     */
    public static GeografskaTocka unesiGeografskuTocku(Scanner unosGeoTocke) {

        BigDecimal x;
        BigDecimal y;

        System.out.println("Unesite Geo koordinatu X");
        x = vratiBigDecimal(unosGeoTocke);
        System.out.println("Unesite Geo koordinatu Y");
        y = vratiBigDecimal(unosGeoTocke);

        return new GeografskaTocka(x, y);
    }
}
