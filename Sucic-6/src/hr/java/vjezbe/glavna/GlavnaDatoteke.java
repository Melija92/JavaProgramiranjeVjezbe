package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static hr.java.vjezbe.glavna.Glavna.ispisiPodatkeSvihMjernihPostaja;
import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisiSenzoreKojiPostojeUJednomMjestu;
import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisiZupanijeBezDuplikataAbecedno;
import static hr.java.vjezbe.ispisivanje.HelperIspisivanje.ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde;

public class GlavnaDatoteke {

    public static void main(String[] args) {
        MjernePostaje<MjernaPostaja> listaMjernihPostaja = new MjernePostaje<>();
        for (MjernaPostaja mj: dohvatiPostaje()) {
            listaMjernihPostaja.add(mj);
        }
//        ispisiPodatkeSvihMjernihPostaja(listaMjernihPostaja);
//        ispisiZupanijeBezDuplikataAbecedno(listaMjernihPostaja);
//        ispisiSenzoreKojiPostojeUJednomMjestu(listaMjernihPostaja);
//		ispisujtrajnoRadnomTemperatureSenzoraSvakeSekunde(listaMjernihPostaja);

        //PRVI ZADATAK
        serijaliziaj(listaMjernihPostaja);
        deserijaliziraj();

        //DRUGI ZADATAK
        String mjestoSNaduljimNazivom = listaMjernihPostaja.getSortedList()
                            .stream()
                            .max(Comparator.comparing(a -> a.getMjesto().getNaziv()))
                            .get().getNaziv();
        Integer mjestoSNaduljimNazivomBrojZnakova = listaMjernihPostaja.getSortedList()
                .stream()
                .max(Comparator.comparing(a -> a.getMjesto().getNaziv()))
                .get().getNaziv().length();

        String mjestoSNajkracimNazivom = listaMjernihPostaja.getSortedList()
                            .stream()
                            .min(Comparator.comparing(a -> a.getMjesto().getNaziv()))
                            .get().getNaziv();
        Integer mjestoSNajkracimNazivomBrojZnakova = listaMjernihPostaja.getSortedList()
                .stream()
                .min(Comparator.comparing(a -> a.getMjesto().getNaziv()))
                .get().getNaziv().length();

        try (PrintWriter out = new PrintWriter(
                new FileWriter(new File("dat/ispisivanjeMjestaDuzinaINaziva")))) {
                    out.println(mjestoSNaduljimNazivom + " broj znakova najduze - " + mjestoSNaduljimNazivomBrojZnakova);
                    out.println(mjestoSNajkracimNazivom + " broj znakova najkrace - " + mjestoSNajkracimNazivomBrojZnakova);
        } catch(IOException ex){
            System.err.println(ex);
        }

        List<String> listaZaIspis = procitajDatoteku("ispisivanjeMjestaDuzinaINaziva.txt");
        listaZaIspis.forEach(a -> System.out.println(a));


    }

    private static void deserijaliziraj(){
        List<Mjesto> mjesta = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("dat/serijaliziranaMjesta.txt"))){
                mjesta = (ArrayList<Mjesto>)in.readObject();
        } catch (IOException ex){
            System.err.println(ex);
        } catch(ClassNotFoundException ex){
            System.err.println(ex);
        }

        mjesta.forEach(a -> System.out.println(a.getNaziv()));
    }
    private static void serijaliziaj(MjernePostaje<MjernaPostaja> listaMjernihPostaja){
        List<Mjesto> mjestaNaSvjernojPolutci = listaMjernihPostaja.getSortedList().stream()
                .filter(a -> a.getGeografskaTocka().getY().compareTo(new BigDecimal(90)) > 0)
                .map(p -> p.getMjesto())
                .collect(Collectors.toList());

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dat/serijaliziranaMjesta.txt"))){
            out.writeObject(mjestaNaSvjernojPolutci);
        }catch(IOException ex){
            System.err.println();
        }
    }


    private static List<String> procitajDatoteku(String nazivFajla){
        List<String> listaStringova = null;
        try(Stream<String> stream = Files.lines(new File("dat/" + nazivFajla).toPath())){
            listaStringova = stream.collect(Collectors.toList());
    }
    catch (IOException e) {
        e.printStackTrace();
    }
        return listaStringova;
}
    private static List<Drzava> dohvatiDrzave(){
        List<Drzava> lista = new ArrayList<>();
        Drzava drzava = null;
        int id = 0;
        String naziv = null;
        BigDecimal povrsina = null;
        List<String> procitajDatoteku = procitajDatoteku("drzave.txt");

        for(int i = 0; i <procitajDatoteku.size(); i += 3) {
            String red = procitajDatoteku.get(i);
            id = Integer.parseInt(procitajDatoteku.get(i));
            naziv = procitajDatoteku.get(i + 1);
            povrsina = new BigDecimal(procitajDatoteku.get(i + 2));

            drzava = new Drzava(naziv, povrsina, id);

            lista.add(drzava);
        }

        return lista;
    }

    private static List<Zupanija> dohvatiZupanije(){
        List<Zupanija> lista = new ArrayList<>();
        List<Drzava> listaDrzava = dohvatiDrzave();
        List<String> procitajDatoteku = procitajDatoteku("zupanije.txt");
        Optional<Drzava> drzava = null;

        for(int i= 0; i <procitajDatoteku.size(); i += 3) {
            int id = Integer.parseInt(procitajDatoteku.get(i));
            String naziv = procitajDatoteku.get(i + 1);
            Integer drzavaId = Integer.parseInt(procitajDatoteku.get(i + 2));
            drzava = listaDrzava.stream()
                    .filter(a -> a.getId() == drzavaId)
                    .findFirst();

            Zupanija zupanija = new Zupanija(naziv, drzava.get(), id);
            drzava.get().getListaZupanije().add(zupanija);
            lista.add(zupanija);
        }

        return lista;
    }

    private static List<Mjesto> dohvatiMjesta(){
        List<Mjesto> lista = new ArrayList<>();
        List<Zupanija> listaZupanija = dohvatiZupanije();
        List<String> procitajDatoteku = procitajDatoteku("mjesta.txt");
        Optional<Zupanija> zupanija = null;

        for(int i= 0; i <procitajDatoteku.size(); i += 4) {
            int id = Integer.parseInt(procitajDatoteku.get(i));
            String naziv = procitajDatoteku.get(i + 1);
            VrstaMjesta vrstaMjesta = VrstaMjesta.valueOf(procitajDatoteku.get(i + 2));
            Integer zupanijaId = Integer.parseInt(procitajDatoteku.get(i + 3));
            zupanija = listaZupanija.stream()
                    .filter(a -> a.getId() == zupanijaId)
                    .findFirst();

            Mjesto mjesto = new Mjesto(naziv, zupanija.get(), vrstaMjesta, id);
            zupanija.get().getListaMjesta().add(mjesto);

            lista.add(mjesto);
        }

        return lista;
    }

    private static SenzorVlage dohvatiSenzoreVlage(){
//        List<SenzorVlage> lista = new ArrayList<>();
        List<String> procitajDatoteku = procitajDatoteku("senzoriVlage.txt");
        SenzorVlage senzor = null;
        for(int i= 0; i <procitajDatoteku.size(); i += 4) {
            int id = Integer.parseInt(procitajDatoteku.get(i));
            String mjernaJedinica = procitajDatoteku.get(i + 1);
            BigDecimal vrijednost = new BigDecimal(procitajDatoteku.get(i + 2));
            RadSenzora radSenzora = RadSenzora.valueOf(procitajDatoteku.get(i + 3));

            senzor = new SenzorVlage(mjernaJedinica, new BigDecimal(15), radSenzora, id);
            senzor.setVrijednost(vrijednost);
//            lista.add(senzor);
        }
        return senzor;
    }
    private static SenzorTemperature dohvatiSenzoreTemperature(){
//        List<SenzorTemperature> lista = new ArrayList<>();
        List<String> procitajDatoteku = procitajDatoteku("senzoriTemperature.txt");
        SenzorTemperature senzor = null;
        for(int i= 0; i <procitajDatoteku.size(); i += 5) {
            Integer id = Integer.parseInt(procitajDatoteku.get(i));
            String komponenta = procitajDatoteku.get(i + 1);
            String mjernaJedinica = procitajDatoteku.get(i + 2);
            BigDecimal vrijednost = new BigDecimal(procitajDatoteku.get(i + 3));
            RadSenzora radSenzora = RadSenzora.valueOf(procitajDatoteku.get(i + 4));

            senzor = new SenzorTemperature(komponenta, mjernaJedinica, new BigDecimal(23), radSenzora, id);
            senzor.setVrijednost(vrijednost);
//            lista.add(senzor);
        }
        return senzor;
    }
    
    private static SenzorVjetra dohvatiSenzoreVjetra(){
//        List<SenzorVjetra> lista = new ArrayList<>();
        List<String> procitajDatoteku = procitajDatoteku("senzoriVjetra.txt");
        SenzorVjetra senzor = null;
        for(int i= 0; i <procitajDatoteku.size(); i += 5) {
            Integer id = Integer.parseInt(procitajDatoteku.get(i));
            String mjernaJedinica = procitajDatoteku.get(i + 1);
            BigDecimal vrijednost = new BigDecimal(procitajDatoteku.get(i + 2));
            String velicina = procitajDatoteku.get(i + 3);
            RadSenzora radSenzora = RadSenzora.valueOf(procitajDatoteku.get(i + 4));

            senzor = new SenzorVjetra(mjernaJedinica, new BigDecimal(23),velicina, radSenzora, id );
            senzor.setVrijednost(vrijednost);
//            lista.add(senzor);
        }
        return senzor;
    }
    private static List<Senzor> dohvatiSenzore(){
        return Arrays.asList(dohvatiSenzoreTemperature(), dohvatiSenzoreVjetra(), dohvatiSenzoreVlage());
    }

    private static List<MjernaPostaja> dohvatiPostaje(){
        List<MjernaPostaja> lista = new ArrayList<>();
        List<Mjesto> listaMjesta = dohvatiMjesta();
        List<String> procitajDatoteku = procitajDatoteku("mjernePostaje.txt");
        Optional<Mjesto> mjesto = null;

        for(int i= 0; i <procitajDatoteku.size(); i += 3) {
            int id = Integer.parseInt(procitajDatoteku.get(i));
            String naziv = procitajDatoteku.get(i + 1);
            Integer mjestoId = Integer.parseInt(procitajDatoteku.get(i + 2));
            mjesto = listaMjesta.stream()
                    .filter(a -> a.getId() == mjestoId)
                    .findFirst();

            MjernaPostaja mjernaPostaja = new MjernaPostaja(naziv, mjesto.get(),
                    new GeografskaTocka(new BigDecimal(100), new BigDecimal(152)), dohvatiSenzore(), id );
            mjesto.get().getListaMjernihPostaja().add(mjernaPostaja);

            lista.add(mjernaPostaja);
        }

        return lista;
    }
}

