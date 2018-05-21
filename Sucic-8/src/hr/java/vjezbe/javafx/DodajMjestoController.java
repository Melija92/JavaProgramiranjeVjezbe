package hr.java.vjezbe.javafx;

import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DodajMjestoController {
    @FXML
    private TextField nazivTextfield;
    @FXML
    private ComboBox zupanijaCombobox;
    @FXML
    private ComboBox vrstaCombobox;
    @FXML
    private Button spremiButton;

    public void prikaziEkranZaNovoMjesto() {
        try{
            BorderPane novoMjestoPane = FXMLLoader.load(Main.class
                                    .getResource("DodajMjestoEkran.fxml"));
            Scene scene = new Scene(novoMjestoPane,600,400);
            scene.getStylesheets().add(getClass().getResource(
                    "application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch
                (IOException
                e
                ) {
            e
                    .printStackTrace();
        }
    }

    public void spremanjeUDatoteku(){
        String naziv = nazivTextfield.getText();
        Zupanija zupanija = zupanijaCombobox.getValue();
        VrstaMjesta vrstaMjesta = vrstaCombobox.getValue();
        Mjesto mjesto = new Mjesto(naziv, zupanija, vrstaMjesta);
        File mjestaFile = new File("dat/mjesta.txt");
        int noviId = getZadnjiId() + 1;
        try (FileWriter writer = new FileWriter(mjestaFile, true);) {
            writer.write(noviId+ "\n");
            writer.write(naziv + "\n");
            writer.write(vrstaMjesta.toString() + "\n");
            writer.write(zupanija.getId() + "\n");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uspješno spremanje mjesta!");
        alert.setHeaderText("Uspješno spremanje mjesta!");
        alert.setContentText("Uneseni podaci za mjesto su uspješno spremljeni.");
        Stage stage = (Stage) spremiButton.getScene().getWindow();
        stage.close();
        PocetniEkranController.dodajNovoMjesto(mjesto);
    } catch (IOException e) {
        logger.error("Pogreška kod spremanja podataka!", e);
        e.printStackTrace();
    }
    }
}
