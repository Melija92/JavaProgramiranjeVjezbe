package hr.java.vjezbe.ispisivanje;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static hr.java.vjezbe.entitet.RadSenzora.OSTALO;
import static hr.java.vjezbe.entitet.RadSenzora.STREAMING;
import static hr.java.vjezbe.entitet.VrstaMjesta.GRAD;

public class HelperTestniPodaci {


    public static MjernePostaje<MjernaPostaja> napraviTestnePodatke(){
        MjernePostaje<MjernaPostaja> objektMjernihPostaja = new MjernePostaje<>();
        List<Senzor> senzori = new ArrayList<>();
        BigDecimal testBigDecimal = new BigDecimal("23");
        String[] drzave = {"Hrvatska", "Slovenija", "Argentina"};
        String[] zupanije = {"Karlovacka", "Nova Gora zup", "Zagrebacka", "Manchaster zup", "Zadarska", "Brodsko-posavska", "Osjecko-baranjska",
        "Varazdinska", "Sibensko-kninska", "Argeno-buenos-zupanija", "Licko-Senjska", "Primorsko-Goranska", "Krapinsko-Zagorksa", "Medimurska",
        "Dolenjska", "Prekmurje", "Strajerska", "Gorenjska", "Primorska", "Dolenjska"};
        String[] mjesta = {"Zagreb", "Split", "Lubljanja", "Osijek", "Rijeka", "Zadar", "Maribor", "Buenos Aires", "Cordobia", "Mehiko",
        "Varazdin", "Bjelovar", "Sisak", "Porec", "Krizevci", "Cakovec", "Vukovar", "Velika Gorica", "Umag", "Makarska", "Crikvenica"};
        String[] mjernePostaje = {"Siget postaja", "Pescenica postaja", "Klarina postaja", "Velika argentinska postaja", "Bosnaska postaja u Ljubljani",
        "Velika postaja", "Mala postaja", "Najveca postaja", "Slatka postajica Ljubuski"};

        Random random = new Random();

        //ISPIS REALNIH
        for (int i = 0; i < 9; i++) {
            String imeDrzave = drzave[random.nextInt(((drzave.length - 1) - 1) + 1)];
            String imeZupanije = zupanije[random.nextInt(((zupanije.length -1) - 1) + 1)];
            String imeMjesta = mjesta[random.nextInt(((mjesta.length - 1) - 1) + 1) +1];
            String imeMjernePostaje = mjernePostaje[random.nextInt((mjernePostaje.length - 1) + 1)];

            Drzava drzava = new Drzava(imeDrzave, testBigDecimal);
            Zupanija zupanija = new Zupanija(imeZupanije, drzava);
            drzava.getListaZupanije().add(zupanija);
            Mjesto mjesto = new Mjesto(imeMjesta, zupanija, GRAD);
            zupanija.getListaMjesta().add(mjesto);
            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);

            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
            senzorVlage.setVrijednost(testBigDecimal);
            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
            senzorTemperature.setVrijednost(testBigDecimal);
            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
            senzorVjetra.setVrijednost(testBigDecimal);
            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);

            if(i < 5 ){
                MjernaPostaja mjernaPostaja = new MjernaPostaja(imeMjernePostaje, mjesto,
                        geografskaTocka, senzori);
                mjesto.getListaMjernihPostaja().add(mjernaPostaja);
                objektMjernihPostaja.add(mjernaPostaja);
            }
            else{
                RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(1500, imeMjernePostaje, mjesto
                ,geografskaTocka, senzori);
                mjesto.getListaMjernihPostaja().add(radioSondaznaMjernaPostaja);
                objektMjernihPostaja.add(radioSondaznaMjernaPostaja);
            }
        }
//        //ISPIS ISTIH
//        for(int i = 0; i < 2; i++) {
//            Drzava drzava = new Drzava("drzava" + i, testBigDecimal);
//            Zupanija zupanija = new Zupanija("zupanija" + i, drzava);
//            drzava.getListaZupanije().add(zupanija);
//            Mjesto mjesto = new Mjesto("mjesto" + i, zupanija, GRAD);
//            zupanija.getListaMjesta().add(mjesto);
//            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);
//
//            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
//            senzorVlage.setVrijednost(testBigDecimal);
//            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
//            senzorTemperature.setVrijednost(testBigDecimal);
//            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
//            senzorVjetra.setVrijednost(testBigDecimal);
//            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);
//
//            MjernaPostaja mjernaPostaja = new MjernaPostaja("mjernaPostaja" + i, mjesto,
//                    geografskaTocka, senzori);
//            mjesto.getListaMjernihPostaja().add(mjernaPostaja);
//
//            objektMjernihPostaja.add(mjernaPostaja);
//        };
//
//        //ISPIS RAZLICITIH
//        for(int i = 0; i < 2; i++) {
//            Drzava drzava = new Drzava("drzava", testBigDecimal);
//            Zupanija zupanija = new Zupanija("zupanija", drzava);
//            drzava.getListaZupanije().add(zupanija);
//            Mjesto mjesto = new Mjesto("mjesto", zupanija, GRAD);
//            zupanija.getListaMjesta().add(mjesto);
//            GeografskaTocka geografskaTocka = new GeografskaTocka(testBigDecimal, testBigDecimal);
//
//            SenzorVlage senzorVlage = new SenzorVlage("%", testBigDecimal, STREAMING);
//            senzorVlage.setVrijednost(testBigDecimal);
//            SenzorTemperature senzorTemperature = new SenzorTemperature("MK23","C", testBigDecimal, OSTALO);
//            senzorTemperature.setVrijednost(testBigDecimal);
//            SenzorVjetra senzorVjetra = new SenzorVjetra("kmh", testBigDecimal, "velik", STREAMING);
//            senzorVjetra.setVrijednost(testBigDecimal);
//            senzori = Arrays.asList(senzorTemperature, senzorVjetra, senzorVlage);
//
//            MjernaPostaja mjernaPostaja = new MjernaPostaja("mjernaPostaja", mjesto,
//                    geografskaTocka, senzori);
//            mjesto.getListaMjernihPostaja().add(mjernaPostaja);
//
//            objektMjernihPostaja.add(mjernaPostaja);
//        };

        return objektMjernihPostaja;
    }
}
