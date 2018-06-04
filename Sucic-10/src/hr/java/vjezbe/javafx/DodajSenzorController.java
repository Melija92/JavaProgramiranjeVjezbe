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

public class DodajSenzorController {

    @FXML
    private TextField jedinicaTextField;
    @FXML
    private TextField preciznostTextField;
    @FXML
    private TextField vrijednostTextField;
    @FXML
    private ComboBox<RadSenzora> radComboBox;
    @FXML
    private TextField komponentaTextField;
    @FXML
    private ComboBox<MjernaPostaja> mjernaPostajaComboBox;
    @FXML
    private Button spremiButton;


    public void initialize(){
        radComboBox.setValue(RadSenzora.PING);
        mjernaPostajaComboBox.setValue(dohvatiMjernePostaje().get(0));
    }

    public int getZadnjiId() {
        return dohvatiSenzoreTemperature().size();
    }

    private List<SenzorTemperature> dohvatiSenzoreTemperature(){
        List<SenzorTemperature> lista = null;
        try{
            lista = BazaPodataka.dohvatiSenzore();
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
    public void prikaziRadComboBox() {
        ObservableList<RadSenzora> vrstaRadaSenzora = FXCollections.observableArrayList(RadSenzora.values());
        radComboBox.setItems(vrstaRadaSenzora);
    }

    @FXML
    public void prikaziMjernePostajeComboBox() {
        ObservableList<MjernaPostaja> mjernePostaje = FXCollections.observableArrayList(dohvatiMjernePostaje());
        mjernaPostajaComboBox.setItems(mjernePostaje);
    }

    public void dodajSenzor(){
        Boolean ispravniPodaci = true;
        String porukaKorisniku = "";

        String jedinica = jedinicaTextField.getText();
        String preciznost = preciznostTextField.getText();
        String vrijednost = vrijednostTextField.getText();
        RadSenzora radSenzora = radComboBox.getValue();
        String elektronickaKomponenta = komponentaTextField.getText();
        MjernaPostaja mjernaPostaja = mjernaPostajaComboBox.getValue();
        int noviId = getZadnjiId() + 1;

        if(isStringEmpty(jedinica)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli jedinicu mjerenja!";
        }

        if(isStringEmpty(preciznost)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli preciznost mjerenja!";
        }

        if(isStringEmpty(vrijednost)) {
            ispravniPodaci = false;
            porukaKorisniku += "Niste unijeli vrijednost senzora!";
        }

        if(isDigitsOnly(preciznost)){
            ispravniPodaci = false;
            porukaKorisniku += "Smiju se unijeti samo brojčane vrijednosti za preciznost!\n";
        }

        if(isDigitsOnly(vrijednost)){
            ispravniPodaci = false;
            porukaKorisniku += "Smiju se unijeti samo brojčane vrijednosti za vrijednost!";
        }

        if(ispravniPodaci){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje senzora temperature!");
            alert.setHeaderText("Uspješno spremanje senzora!");
            alert.setContentText("Uneseni podaci za senzor su uspješno spremljeni.");
            alert.showAndWait();
            Stage stage = (Stage) spremiButton.getScene().getWindow();
            stage.close();
            SenzorTemperature senzor = new SenzorTemperature(noviId, elektronickaKomponenta, jedinica,
                    Double.parseDouble(preciznost), new BigDecimal(vrijednost), radSenzora, mjernaPostaja);

            try{
                BazaPodataka.spremiSenzor(senzor);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje senzora");
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

    private boolean isDigitsOnly(String tekst){
        Boolean isDigit = false;

        if(tekst.matches("[^0-9]+")){
            isDigit = true;
        }
        return isDigit;
    }
}
