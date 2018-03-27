package hr.java.vjezbe.glavna;

import com.sun.management.UnixOperatingSystemMXBean;
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
    public static SenzorTemperature unesiSenzorTemperature(Scanner unosSenzora) {
        System.out.println("Unesite elektroničku komponentu za senzor temperature:");
        String elektronickaKomponenta = unosSenzora.nextLine();

        System.out.println("Unesite mjernu jedinicu za temeraturu po oznaci ispod navedenoj");
        System.out.println(" - 1 za Celzijus, 2 za Kelvin, 3 za Fahrenheit ili 4 za Rankine");
        Integer mjernaJedinica = unosSenzora.nextInt();

        System.out.println("Unesite vrijednost senzora temperature");
        BigDecimal vrijednost = unosSenzora.nextBigDecimal();
        unosSenzora.nextLine();

        SenzorTemperature senzorTemperature = new SenzorTemperature(elektronickaKomponenta,
                HelperOperacije.odrediMjernuJedinicuTemperature(mjernaJedinica), HelperOperacije.odrediPreciznostTemperature(mjernaJedinica));
        senzorTemperature.setVrijednost(vrijednost);

        return senzorTemperature;
    }

    public static SenzorVlage unesiSenzorVlage(Scanner unosSenzora) {
        System.out.println("Unesite mjernu jedinicu za vlagu po oznaci ispod navedenoj");
        System.out.println(" - 1 za postotak");
        Byte mjernaJedinica = unosSenzora.nextByte();

        System.out.println("Unesite vrijednost senzora vlage");
        BigDecimal vrijednost = unosSenzora.nextBigDecimal();
        unosSenzora.nextLine();

        SenzorVlage senzorVlage = new SenzorVlage
                (HelperOperacije.odrediMjernuJedinicuVlage(mjernaJedinica), HelperOperacije.odrediPreciznostVlage(mjernaJedinica));
        senzorVlage.setVrijednost(vrijednost);

        return senzorVlage;
    }

    public static SenzorVjetra unesiSenzorVjetra(Scanner unosSenzora) {
        System.out.println("Unesite veličinu senzora vjetra:");
        String velicinaSenzora = unosSenzora.nextLine();

        System.out.println("Unesite mjernu jedinicu za vlagu po oznaci ispod navedenoj");
        System.out.println(" - 1 za metre po sekundi, 2 za čvorove, 3 za kilometre po satu");
        Byte mjernaJedinica = unosSenzora.nextByte();

        System.out.println("Unesite vrijednost senzora vjetra");
        BigDecimal vrijednost = unosSenzora.nextBigDecimal();
        unosSenzora.nextLine();

        SenzorVjetra senzorVjetra = new SenzorVjetra
                (HelperOperacije.odrediMjernuJedinicuVjertra(mjernaJedinica), HelperOperacije.odrediPreciznostVjetra(mjernaJedinica), velicinaSenzora);
        senzorVjetra.setVrijednost(vrijednost);

        return senzorVjetra;
    }

    public static MjernaPostaja unesiMjernuPostaju(Scanner unosMjernePostaje,
                                                    Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
        System.out.println("Unesite naziv mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();

        return new MjernaPostaja(naziv, mjesto, geografskaTocka, senzori);
    }

    public static RadioSondaznaMjernaPostaja unesiRadioSondaznuMjernuPostaju(Scanner unosMjernePostaje,
                                                   Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
        System.out.println("Unesite naziv radio sondažne mjerne postaje");
        String naziv = unosMjernePostaje.nextLine();

        System.out.println("Unesite visinu radio sondažne mjerne postaje");
        Integer visina = unosMjernePostaje.nextInt();

        return new RadioSondaznaMjernaPostaja(visina, naziv, mjesto, geografskaTocka, senzori);
    }

    public static MjernaPostaja[] postaviMjernePostaje
            (int BROJ_MJERNIH_POSTAJA, int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA, MjernaPostaja[] mjernePostaje){

        Scanner unos = new Scanner(System.in);
        Integer ukupanBrojPostaja = BROJ_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;

        for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(i) + " mjernu postaju");

            Drzava drzava = unesiDrzavu(unos);
            Zupanija zupanija = unesiZupaniju(unos, drzava);
            Mjesto mjesto = unesiMjesto(unos, zupanija);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            SenzorTemperature senzorTemperature = unesiSenzorTemperature(unos);
            SenzorVjetra senzorVjetra = unesiSenzorVjetra(unos);
            SenzorVlage senzorVlage = unesiSenzorVlage(unos);

            Senzor[] senzori = {senzorVlage, senzorVjetra, senzorTemperature};
            MjernaPostaja mjernaPostaja = unesiMjernuPostaju(unos, mjesto, geografskaTocka, senzori);

            mjernePostaje[i] = mjernaPostaja;
        }

        for (int n = BROJ_MJERNIH_POSTAJA; n < ukupanBrojPostaja; n++) {
            System.out.println("Unesite " + HelperOperacije.odrediRedniBroj(n) + " mjernu postaju");

            Drzava drzava = unesiDrzavu(unos);
            Zupanija zupanija = unesiZupaniju(unos, drzava);
            Mjesto mjesto = unesiMjesto(unos, zupanija);
            GeografskaTocka geografskaTocka = unesiGeografskuTocku(unos);
            SenzorTemperature senzorTemperature = unesiSenzorTemperature(unos);
            SenzorVjetra senzorVjetra = unesiSenzorVjetra(unos);
            SenzorVlage senzorVlage = unesiSenzorVlage(unos);

            Senzor[] senzori = {senzorVlage, senzorVjetra, senzorTemperature};
            RadioSondaznaMjernaPostaja radioSondaznamjernaPostaja = unesiRadioSondaznuMjernuPostaju
                    (unos, mjesto, geografskaTocka, senzori);

            mjernePostaje[n] = radioSondaznamjernaPostaja;
        }
        unos.close();

        return mjernePostaje;
    }
}
