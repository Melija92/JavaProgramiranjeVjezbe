package hr.java.vjezbe.ispisivanje;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static hr.java.vjezbe.entitet.RadSenzora.OSTALO;
import static hr.java.vjezbe.entitet.RadSenzora.PING;
import static hr.java.vjezbe.entitet.RadSenzora.STREAMING;
import static hr.java.vjezbe.entitet.VelicinaMjesta.VELIKO;
import static hr.java.vjezbe.entitet.VrstaMjesta.GRAD;

public class HelperTestniPodaci {


    public static List<MjernaPostaja> napraviTestnePodatke(){
        List<MjernaPostaja> mjernePostaje = new ArrayList<>();
        List<Senzor> senzori = new ArrayList<>();
        BigDecimal testBigDecimal = new BigDecimal("23");

        for(int i = 0; i < 5; i++) {

            Drzava drzava = new Drzava("drzava" + i, testBigDecimal);
            Zupanija zupanija = new Zupanija("zupanija" + i, drzava);
            drzava.getListaZupanije().add(zupanija);
            Mjesto mjesto = new Mjesto("mjesto" + i, zupanija, GRAD, VELIKO);
            zupanija.getListaMjesta().add(mjesto);
            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);

            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
            senzorVlage.setVrijednost(testBigDecimal);
            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
            senzorTemperature.setVrijednost(testBigDecimal);
            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", PING);
            senzorVjetra.setVrijednost(testBigDecimal);
            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);

            MjernaPostaja mjernaPostaja = new MjernaPostaja("mjernaPostaja" + i, mjesto,
                    geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(mjernaPostaja);

            mjernePostaje.add(mjernaPostaja);
        };

        for(int i = 0; i < 5; i++) {

            Drzava drzava = new Drzava("drzava", testBigDecimal);
            Zupanija zupanija = provjeriIVratiZupaniju(drzava, mjernePostaje);
            Mjesto mjesto = provjeriIVratiMjesto(zupanija, mjernePostaje);
            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);

            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
            senzorVlage.setVrijednost(testBigDecimal);
            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
            senzorTemperature.setVrijednost(testBigDecimal);
            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
            senzorVjetra.setVrijednost(testBigDecimal);
            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);

            MjernaPostaja mjernaPostaja = provjeriIVratiMjernuPostaju(mjesto, senzori, geografskaTocka, mjernePostaje);

            mjernePostaje.add(mjernaPostaja);
        };
        return mjernePostaje;
    }

    private static Zupanija provjeriIVratiZupaniju(Drzava drzava, List<MjernaPostaja> postaje){
        Zupanija zupanija;
        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getZupanija()
                .getDrzava().getNaziv().equals(drzava.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Drzava postjecaDrzavaIzListe = postaja.get().getMjesto().getZupanija().getDrzava();
            zupanija = new Zupanija("zupanija", postjecaDrzavaIzListe);
            postjecaDrzavaIzListe.getListaZupanije().add(zupanija);
        }
        else {
            zupanija = new Zupanija("zupanija", drzava);
            drzava.getListaZupanije().add(zupanija);
        }

        return zupanija;
    }

    private static Mjesto provjeriIVratiMjesto(Zupanija zupanija, List<MjernaPostaja> postaje){
        Mjesto mjesto;

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getZupanija()
                .getNaziv().equals(zupanija.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Zupanija postjecaZupanijaIzListe = postaja.get().getMjesto().getZupanija();
            mjesto = new Mjesto("mjesto", postjecaZupanijaIzListe, GRAD, VELIKO);
            postjecaZupanijaIzListe.getListaMjesta().add(mjesto);
        }
        else {
            mjesto = new Mjesto("mjesto", zupanija, GRAD, VELIKO);
            zupanija.getListaMjesta().add(mjesto);
        }

        return mjesto;
    }

    private static MjernaPostaja provjeriIVratiMjernuPostaju(Mjesto mjesto, List<Senzor> senzori, GeografskaTocka geografskaTocka, List<MjernaPostaja> postaje){
        MjernaPostaja mjernaPostaja;

        Optional<MjernaPostaja> postaja = postaje.stream().filter(p -> p.getMjesto().getNaziv()
                .equals(mjesto.getNaziv())).findFirst();

        if(postaja.isPresent()){
            Mjesto postjeceMjestoIzListe = postaja.get().getMjesto();
            mjernaPostaja = new MjernaPostaja("mjesto", postjeceMjestoIzListe, geografskaTocka, senzori);
            postjeceMjestoIzListe.getListaMjernihPostaja().add(mjernaPostaja);
        }
        else {
            mjernaPostaja = new MjernaPostaja("mjesto", mjesto, geografskaTocka, senzori);
            mjesto.getListaMjernihPostaja().add(mjernaPostaja);
        }

        return mjernaPostaja;
    }
}
