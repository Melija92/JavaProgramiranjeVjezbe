package hr.java.vjezbe.javafx;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DodajMjestoController {

    @FXML
    private TextField nazivTextField;
    @FXML
    private ComboBox<Zupanija> zupanijaComboBox;
    @FXML
    private ComboBox<VrstaMjesta> vrstaMjestaComboBox;
    @FXML
    private Button spremiButton;

    public void initialize(){
        zupanijaComboBox.setValue(dohvatiZupanije().get(0));
        vrstaMjestaComboBox.setValue(VrstaMjesta.GRAD);
    }

    public int getZadnjiId() {
        return dohvatiMjesta().size();
    }

    private List<Mjesto> dohvatiMjesta(){
        List<Mjesto> lista = null;
        try{
            lista = BazaPodataka.dohvatiMjesta();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return lista;
    }
    private List<Zupanija> dohvatiZupanije(){
        List<Zupanija> lista = null;
        try{
            lista = BazaPodataka.dohvatiZupanije();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return lista;
    }

    @FXML
    public void prikaziZupanijeComboBox() {
        ObservableList<Zupanija> listaZupanijaCombo = FXCollections.observableArrayList(dohvatiZupanije());
        zupanijaComboBox.setItems(listaZupanijaCombo);
    }

    @FXML
    public void prikaziVrstaMjestaComboBox() {
        ObservableList<VrstaMjesta> listaVrstaMjesta = FXCollections.observableArrayList(VrstaMjesta.values());
        vrstaMjestaComboBox.setItems(listaVrstaMjesta);
    }

    public void dodajMjesto(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        Zupanija zupanija = zupanijaComboBox.getValue();
        VrstaMjesta vrstaMjesta = vrstaMjestaComboBox.getValue();
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv mjesta!";
        }

        if(ispravniPodaci){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje mjesta!");
            alert.setHeaderText("Uspješno spremanje mjesta!");
            alert.setContentText("Uneseni podaci za mjesto su uspješno spremljeni.");
            alert.showAndWait();
            Stage stage = (Stage) spremiButton.getScene().getWindow();
            stage.close();
            Mjesto mjesto = new Mjesto(noviId, naziv, zupanija);
            mjesto.setVrstaMjesta(vrstaMjesta);

            try{
                BazaPodataka.spremiMjesto(mjesto);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje mjesta");
            alert.setHeaderText("Pogreške u podacima");
            alert.setContentText(porukaKorisniku);

            alert.showAndWait();
        }


    }

    private boolean isStringEmpty(String tekst){
        Boolean isEmpty = false;

        if (tekst == null || "".equals(tekst)) {
            isEmpty = true;
        }

        return isEmpty;
    }
}
