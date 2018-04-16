package hr.java.vjezbe.unosenje;

import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.SenzorTemperature;
import hr.java.vjezbe.entitet.SenzorVjetra;
import hr.java.vjezbe.entitet.SenzorVlage;
import hr.java.vjezbe.glavna.HelperOperacije;

import java.math.BigDecimal;
import java.util.Scanner;

import static hr.java.vjezbe.glavna.HelperEnumUnosenje.unosRadaSenzora;
import static hr.java.vjezbe.glavna.HelperOperacije.vratiBigDecimal;

public class UnosenjeSenzora {
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

        System.out.println("Unesite vrijednost senzora temperature");
        BigDecimal vrijednost = vratiBigDecimal(unosSenzora);

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
        Byte mjernaJedinica = unosSenzora.nextByte();

        System.out.println("Unesite vrijednost senzora vlage");
        BigDecimal vrijednost = vratiBigDecimal(unosSenzora);

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

        System.out.println("Unesite vrijednost senzora vjetra");
        BigDecimal vrijednost = vratiBigDecimal(unosSenzora);

        RadSenzora radSenzora = unosRadaSenzora(unosSenzora);
        SenzorVjetra senzorVjetra = new SenzorVjetra
                (HelperOperacije.odrediMjernuJedinicuVjertra(mjernaJedinica), HelperOperacije.odrediPreciznostVjetra(mjernaJedinica),
                        velicinaSenzora,
                        radSenzora);
        senzorVjetra.setVrijednost(vrijednost);

        return senzorVjetra;
    }
}
