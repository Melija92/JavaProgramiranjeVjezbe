package hr.java.vjezbe.ispisivanje;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hr.java.vjezbe.entitet.RadSenzora.OSTALO;
import static hr.java.vjezbe.entitet.RadSenzora.STREAMING;
import static hr.java.vjezbe.entitet.VrstaMjesta.GRAD;

public class HelperTestniPodaci {


    public static List<MjernaPostaja> napraviTestnePodatke(){
        List<MjernaPostaja> mjernePostaje = new ArrayList<>();
        List<Senzor> senzori = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            BigDecimal testBigDecimal = new BigDecimal("23");

            Drzava drzava = new Drzava("drzava" + i, testBigDecimal);
            Zupanija zupanija = new Zupanija("zupanija" + i, drzava);
            drzava.getListaZupanije().add(zupanija);
            Mjesto mjesto = new Mjesto("mjesto" + i, zupanija, GRAD);
            zupanija.getListaMjesta().add(mjesto);
            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);

            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
            senzorVlage.setVrijednost(testBigDecimal);
            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
            senzorTemperature.setVrijednost(testBigDecimal);
            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
            senzorVjetra.setVrijednost(testBigDecimal);
            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);

            MjernaPostaja mjernaPostaja = new MjernaPostaja("mjernaPostaja" + i, mjesto,
                    geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(mjernaPostaja);

            mjernePostaje.add(mjernaPostaja);
        };

        for(int i = 0; i < 5; i++) {
            BigDecimal testBigDecimal = new BigDecimal("23");

            Drzava drzava = new Drzava("drzava", testBigDecimal);
            Zupanija zupanija = new Zupanija("zupanija", drzava);
            drzava.getListaZupanije().add(zupanija);
            Mjesto mjesto = new Mjesto("mjesto", zupanija, GRAD);
            zupanija.getListaMjesta().add(mjesto);
            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);

            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
            senzorVlage.setVrijednost(testBigDecimal);
            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
            senzorTemperature.setVrijednost(testBigDecimal);
            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
            senzorVjetra.setVrijednost(testBigDecimal);
            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);

            MjernaPostaja mjernaPostaja = new MjernaPostaja("mjernaPostaja", mjesto,
                    geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(mjernaPostaja);

            mjernePostaje.add(mjernaPostaja);
        };
        return mjernePostaje;
    }
}
