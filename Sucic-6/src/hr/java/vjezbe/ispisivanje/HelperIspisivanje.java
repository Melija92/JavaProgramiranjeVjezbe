package hr.java.vjezbe.ispisivanje;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;
import hr.java.vjezbe.sortiranje.ZupanijaSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HelperIspisivanje {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void ispisiZupanijeBezDuplikataAbecedno(MjernePostaje<MjernaPostaja> listaMjernihPostaja){
        List<Zupanija> listaZupanijaZaIspis = new ArrayList<>();
        for (MjernaPostaja mjernaPostaja: listaMjernihPostaja.getSortedList()) {
            Zupanija zupanija = mjernaPostaja.getMjesto().getZupanija();
            if(!listaZupanijaZaIspis.contains(zupanija))
                listaZupanijaZaIspis.add(zupanija);
        }
        Collections.sort(listaZupanijaZaIspis, new ZupanijaSorter());

        System.out.println("\nIspis svih županija abecedeno sortirano: ");
        for (Zupanija zupanija : listaZupanijaZaIspis){
            System.out.println(zupanija.getNaziv());
        }
        System.out.println("------------------------------------------------------------------");
    }


    public static void ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde(MjernePostaje<MjernaPostaja> listaMjernihPostaja){
        while (true){
            for (MjernaPostaja mjernaPostaja: listaMjernihPostaja.getSortedList()) {
                for (Senzor senzor: mjernaPostaja.dohvatiSenzore()) {
                    if(senzor instanceof SenzorTemperature){
                        try{
                            ((SenzorTemperature)senzor).generirajVrijednost();
                            System.out.println();
                            Thread.sleep(1000);
                        }
                        catch (NiskaTemperaturaException | VisokaTemperaturaException | InterruptedException ex){
                            logger.info("Pogreška na postaji - " + mjernaPostaja.getNaziv() + " " + ex.getMessage(), ex);
                        }
                    }
                }
            }
        }
    }

    public static void ispisiSenzoreKojiPostojeUJednomMjestu(MjernePostaje<MjernaPostaja> listaMjernihPostaja){
        Map<Mjesto, List<Senzor>> mapaMjestaISenzora = new HashMap<>();

        for (MjernaPostaja mjernaPostaja : listaMjernihPostaja.getSortedList()){
            mapaMjestaISenzora.put(mjernaPostaja.getMjesto(), mjernaPostaja.dohvatiSenzore());
        }

        for (Mjesto mjestoKey : mapaMjestaISenzora.keySet()){
            System.out.println("Senzori u mjestu " + mjestoKey.getNaziv() + " su:");
            for(Senzor senzor: mapaMjestaISenzora.get(mjestoKey)){
                if(senzor instanceof SenzorTemperature)
                    System.out.println("Senzor tempeature");
                else if(senzor instanceof SenzorVjetra)
                    System.out.println("Senzor vjetra");
                else if(senzor instanceof  SenzorVlage)
                    System.out.println("Senzor vlage");
            }
        }
    }
}
