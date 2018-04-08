package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperEnumUnosenje.unosRadaSenzora;
import static hr.java.vjezbe.glavna.HelperEnumUnosenje.unosVrsteMjesta;

/**
 * predstavlja entitet klase HelperUnosenje sa statičnim metodama za unošenje podataka
 */
public class HelperUnosenje {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
    /**
     * pomoćna statična metoda za unos države
     */
    public static Drzava unesiDrzavu(Scanner unosDrzave) {
        System.out.println("Unesite naziv države");
        BigDecimal povrsina;
        String naziv = unosDrzave.nextLine();

        boolean nastaviPetlju;
        do {
            try {
                System.out.println("Unesite površinu države");
                povrsina = unosDrzave.nextBigDecimal();
                unosDrzave.nextLine();
                nastaviPetlju = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosDrzave.nextLine();
                nastaviPetlju = true;
                povrsina = null;
            }
        }while (nastaviPetlju);




        return new Drzava(naziv, povrsina);
    }

    /**
     * pomoćna statična metoda za unos županije
     */
    public static Zupanija unesiZupaniju(Scanner unosZupanije, Drzava drzava, List<MjernaPostaja> postaje) {
            System.out.println("Unesite naziv županije");
            String naziv = unosZupanije.nextLine();
            Zupanija zupanija;

            Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getZupanija().getDrzava().getNaziv().equals(
                    drzava.getNaziv())).findFirst();

            if(postaja.isPresent()){
                Drzava postjecaDrzavaIzListe = postaja.get().getMjesto().getZupanija().getDrzava();
                zupanija = new Zupanija(naziv, postjecaDrzavaIzListe);
                postjecaDrzavaIzListe.zupanije.add(zupanija);
            }
            else {
            zupanija = new Zupanija(naziv, drzava);
            drzava.zupanije.add(zupanija);
        }

        return zupanija;
    }

    /**
     * pomoćna statična metoda za unos mjesta
     */
    public static Mjesto unesiMjesto(Scanner unosMjesta, Zupanija zupanija) {
        System.out.println("Unesite naziv mjesta");
        String naziv = unosMjesta.nextLine();

        VrstaMjesta vrstaMjesta = unosVrsteMjesta(unosMjesta);

        return new Mjesto(naziv, zupanija, vrstaMjesta);
    }

    /**
     * pomoćna statična metoda za unos geografske točke
     */
    public static GeografskaTocka unesiGeografskuTocku(Scanner unosGeoTocke) {

        BigDecimal x;
        BigDecimal y;

        boolean nastaviPetljuX = false;
        do {
            try {
                System.out.println("Unesite Geo koordinatu X");
                x = unosGeoTocke.nextBigDecimal();
                unosGeoTocke.nextLine();
                nastaviPetljuX = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosGeoTocke.nextLine();
                nastaviPetljuX = true;
                x = null;
            }
        }while (nastaviPetljuX);

        boolean nastaviPetljuY = false;
        do {
            try {
                System.out.println("Unesite Geo koordinatu Y");
                y = unosGeoTocke.nextBigDecimal();

                unosGeoTocke.nextLine();
                nastaviPetljuY = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosGeoTocke.nextLine();
                nastaviPetljuY = true;
                y = null;
            }
        }while (nastaviPetljuY);

        return new GeografskaTocka(x, y);
    }

    /**
     * pomoćna metoda za unos senzora temperature
     */
    public static SenzorTemperature unesiSenzorTemperature(Scanner unosSenzora) {
        System.out.println("Unesite elektroničku komponentu za senzor temperature:");
        String elektronickaKomponenta = unosSenzora.nextLine();

        System.out.println("Unesite mjernu jedinicu za temeraturu po oznaci ispod navedenoj");
        System.out.println(" - 1 za Celzijus, 2 za Kelvin, 3 za Fahrenheit ili 4 za Rankine");
        Integer mjernaJedinica = unosSenzora.nextInt();

        RadSenzora radSenzora = unosRadaSenzora(unosSenzora);
        BigDecimal vrijednost;
        boolean nastaviPetlju;
        do {
            try {
                System.out.println("Unesite vrijednost senzora temperature");
                vrijednost = unosSenzora.nextBigDecimal();

                unosSenzora.nextLine();
                nastaviPetlju = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosSenzora.nextLine();
                nastaviPetlju = true;
                vrijednost = null;
            }
        }while (nastaviPetlju);

        SenzorTemperature senzorTemperature = new SenzorTemperature(elektronickaKomponenta,
                HelperOperacije.odrediMjernuJedinicuTemperature(mjernaJedinica), HelperOperacije.odrediPreciznostTemperature(mjernaJedinica),
                radSenzora);
        senzorTemperature.setVrijednost(vrijednost);

        return senzorTemperature;
    }

    /**
     * pomoćna statična metoda za unos senzora vlage
     */
    public static SenzorVlage unesiSenzorVlage(Scanner unosSenzora) {
        System.out.println("Unesite mjernu jedinicu za vlagu po oznaci ispod navedenoj");
        System.out.println(" - 1 za postotak");
        Byte mjernaJedinica = unosSenzora.nextByte( );

        BigDecimal vrijednost;
        boolean nastaviPetlju;
        do {
            try {
                System.out.println("Unesite vrijednost senzora vlage");
                vrijednost = unosSenzora.nextBigDecimal();

                unosSenzora.nextLine();
                nastaviPetlju = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosSenzora.nextLine();
                nastaviPetlju = true;
                vrijednost = null;
            }
        }while (nastaviPetlju);
        RadSenzora radSenzora = unosRadaSenzora(unosSenzora);
        SenzorVlage senzorVlage = new SenzorVlage
                (HelperOperacije.odrediMjernuJedinicuVlage(mjernaJedinica), HelperOperacije.odrediPreciznostVlage(mjernaJedinica), radSenzora);
        senzorVlage.setVrijednost(vrijednost);

        return senzorVlage;
    }

    /**
     * pomoćna statična metoda za unos senzora vjetra
     */
    public static SenzorVjetra unesiSenzorVjetra(Scanner unosSenzora) {
        System.out.println("Unesite veličinu senzora vjetra:");
        String velicinaSenzora = unosSenzora.nextLine();

        System.out.println("Unesite mjernu jedinicu za vjetar po oznaci ispod navedenoj");
        System.out.println(" - 1 za metre po sekundi, 2 za čvorove, 3 za kilometre po satu");
        Byte mjernaJedinica = unosSenzora.nextByte();

        BigDecimal vrijednost;
        boolean nastaviPetlju;

        do {
            try {
                System.out.println("Unesite vrijednost senzora vjetra");
                vrijednost = unosSenzora.nextBigDecimal();

                unosSenzora.nextLine();
                nastaviPetlju = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosSenzora.nextLine();
                nastaviPetlju = true;
                vrijednost = null;
            }
        }while (nastaviPetlju);

        RadSenzora radSenzora = unosRadaSenzora(unosSenzora);
        SenzorVjetra senzorVjetra = new SenzorVjetra
                (HelperOperacije.odrediMjernuJedinicuVjertra(mjernaJedinica), HelperOperacije.odrediPreciznostVjetra(mjernaJedinica),
                        velicinaSenzora,
                        radSenzora);
        senzorVjetra.setVrijednost(vrijednost);

        return senzorVjetra;
    }

    /**
     * pomoćna statična metoda za unos mjerne postaje
     */
    public static MjernaPostaja unesiMjernuPostaju(Scanner unosMjernePostaje,
                                                    Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
        System.out.println("Unesite naziv mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();

        return new MjernaPostaja(naziv, mjesto, geografskaTocka, senzori);
    }

    /**
     * pomoćna statična metoda za unos radio sondažne mjerne postaje
     */
    public static RadioSondaznaMjernaPostaja unesiRadioSondaznuMjernuPostaju(Scanner unosMjernePostaje,
                                                   Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
        System.out.println("Unesite naziv radio sondažne mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();


        Integer visina;
        boolean nastaviPetlju;
        do {
            try {
                System.out.println("Unesite visinu radio sondažne mjerne postaje");
                visina = unosMjernePostaje.nextInt();

                unosMjernePostaje.nextLine();
                nastaviPetlju = false;
            }
            catch (InputMismatchException ex){
                System.out.println("Unijeli ste krivo, ponovite unos");
                unosMjernePostaje.nextLine();
                nastaviPetlju = true;
                visina = null;
            }
        }while (nastaviPetlju);


        return new RadioSondaznaMjernaPostaja(visina, naziv, mjesto, geografskaTocka, senzori);
    }
}
