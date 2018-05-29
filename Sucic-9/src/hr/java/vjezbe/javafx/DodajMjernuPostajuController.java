package hr.java.vjezbe.javafx;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class DodajMjernuPostajuController {

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField tockaXTextField;
    @FXML
    private TextField tockaYTextField;
    @FXML
    private ComboBox<Mjesto> mjestoComboBox;
    @FXML
    private Button spremiButton;


    public int getZadnjiId() {
        return dohvatiMjernePostaje().size();
    }

    public void initialize(){
        mjestoComboBox.setValue(dohvatiMjesta().get(0));
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
    private List<MjernaPostaja> dohvatiMjernePostaje(){
        List<MjernaPostaja> lista = null;
        try{
            lista = BazaPodataka.dohvatiMjernePostaje();
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
    public void prikaziMjestoComboBox() {
        ObservableList<Mjesto> listaMjestaCombo = FXCollections.observableArrayList(dohvatiMjesta());
        mjestoComboBox.setItems(listaMjestaCombo);
    }

    public void dodajMjernuPostaju(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String naziv = nazivTextField.getText();
        String tockaX = tockaXTextField.getText();
        String tockaY = tockaYTextField.getText();
        Mjesto mjesto = mjestoComboBox.getValue();
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(naziv)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli naziv mjerne postaje!\n";
        }

        if(isStringEmpty(tockaX)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli X tocku mjerne postaje!\n";
        }

        if(isStringEmpty(tockaY)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli Y tocku mjerne postaje!\n";
        }

        if(ispravniPodaci){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje mjerne postaje!");
            alert.setHeaderText("Uspješno spremanje mjerne postaje!");
            alert.setContentText("Uneseni podaci za mjernu postaju su uspješno spremljeni.");
            alert.showAndWait();
            Stage stage = (Stage) spremiButton.getScene().getWindow();
            stage.close();
            GeografskaTocka geografskaTocka = new GeografskaTocka(new BigDecimal(tockaX), new BigDecimal(tockaY));
            MjernaPostaja mjernaPostaja = new MjernaPostaja(noviId, naziv, mjesto, geografskaTocka, null);

            try{
                BazaPodataka.spremiMjernePostaje(mjernaPostaja);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje mjerne postaje");
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
