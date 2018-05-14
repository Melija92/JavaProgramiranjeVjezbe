package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.SenzorTemperature;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import hr.java.vjezbe.entitet.*;

public class Main extends Application {
    private static BorderPane root;
    private Stage primaryStage;
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        try { root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("D:\\Lokalno programiranje - Java\\JavaProgramiranjeVjezbe\\Sucic-7\\src\\hr\\java\\vjezbe\\javafx\\PocetniEkran.fxml"));
            Scene scene = new Scene(root,600,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void setCenterPane(BorderPane centerPane) {
        root.setCenter(centerPane);
    }

    public static void main(String[] args) {
        launch(args);
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

    public static List<Mjesto> dohvatiMjesta(){
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
                    new GeografskaTocka(new BigDecimal(20), new BigDecimal(15)), dohvatiSenzore(), id );
            mjesto.get().getListaMjernihPostaja().add(mjernaPostaja);

            lista.add(mjernaPostaja);
        }

        return lista;
    }
}