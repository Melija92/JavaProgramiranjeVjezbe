package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.javafx.Main;
import hr.java.vjezbe.javafx.SenzoriController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SenzoriNit implements Runnable {
    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    private Integer broj;
    public void run(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    broj = BazaPodataka.dohvatiSenzoreNegativneVrijednosti();

                    if(broj > 0 && SenzoriController.jeLiGumbUkljucen){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Senzor ispod nule");
                        alert.show();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
