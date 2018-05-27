package hr.java.vjezbe.javafx;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Zupanija;
import hr.java.vjezbe.javafx.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZupanijeController {


    private static List<Zupanija> listaZupanija;

    @FXML
    private TextField zupanijaFilterTextField;
    @FXML
    private TableView<Zupanija> zupanijeTableView;
    @FXML
    private TableColumn<Zupanija, String> nazivColumn;
    @FXML
    private TableColumn<Zupanija, String> drzavaColumn;
    @FXML
    private Button pretraziButton;


    @FXML
    public void initialize() {

        nazivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Zupanija, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Zupanija, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getNaziv().toString());
            }});

        nazivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Zupanija, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Zupanija, String> param) {
                return new ReadOnlyObjectWrapper<String>
                        (param.getValue().getNaziv().toString());
            }});

        drzavaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Zupanija, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Zupanija, String> param) {
                return new ReadOnlyObjectWrapper<String> (param.getValue().getDrzava().getNaziv());}});
    }

    @FXML
    public void prikaziZupanije() {
        try{
            listaZupanija = BazaPodataka.dohvatiZupanije();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        List<Zupanija> filtriraneZupanije = new ArrayList<Zupanija>();
        if (zupanijaFilterTextField.getText().isEmpty() == false) {
            filtriraneZupanije = listaZupanija.stream().filter(m ->
                    m.getNaziv().contains(zupanijaFilterTextField.getText()))
                    .collect(Collectors.toList());
        } else {
            filtriraneZupanije = listaZupanija;
        }
        ObservableList<Zupanija> listaMjesta = FXCollections.observableArrayList(filtriraneZupanije);
        zupanijeTableView.setItems(listaMjesta);
    }

    @FXML
    public void prikaziEkranZupanije() {
        try {
            BorderPane zupanijaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Zupanije.fxml"));
            Main.setCenterPane(zupanijaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranDrzave() {
        try {
            BorderPane drzavePane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Drzave.fxml"));
            Main.setCenterPane(drzavePane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranMjesta() {
        try {
            BorderPane mjestoPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/PocetniEkran.fxml"));
            Main.setCenterPane(mjestoPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranPostaje() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/MjernePostaje.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void prikaziEkranSenzora() {
        try {
            BorderPane postajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/Senzori.fxml"));
            Main.setCenterPane(postajaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void dodajNovuZupaniju(Zupanija novaZupanija) {
        listaZupanija = Main.dohvatiZupanije();
        listaZupanija.add(novaZupanija);
    }

    public void prikaziEkranZaNovoMjesto() {
        try {
            BorderPane novoMjestoPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajMjesto.fxml"));
            Scene scene = new Scene(novoMjestoPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuZupaniju() {
        try {
            BorderPane novaZupanijaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajZupaniju.fxml"));
            Scene scene = new Scene(novaZupanijaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuDrzavu() {
        try {
            BorderPane novaDrzavaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajDrzavu.fxml"));
            Scene scene = new Scene(novaDrzavaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNovuPostaju() {
        try {
            BorderPane novaPostajaPane = FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajMjernuPostaju.fxml"));
            Scene scene = new Scene(novaPostajaPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziEkranZaNoviSenzor() {
        try {
            BorderPane noviSenzorPane= FXMLLoader.load(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/DodajSenzor.fxml"));
            Scene scene = new Scene(noviSenzorPane,600,400);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("src/hr/java/vjezbe/javafx/application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
