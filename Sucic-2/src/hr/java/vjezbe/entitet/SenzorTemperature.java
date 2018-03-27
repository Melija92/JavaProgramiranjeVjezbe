package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class SenzorTemperature extends Senzor {

//    private static final string CELSIJUS = "C";
//    private static final string FAHRENHEIT = "F";
//    private static final string KELVIN = "K";
//    private static final string RANKINE = "R";

    private String elektronickaKomponenta;

    public SenzorTemperature(String elektronickaKomponenta, String mjernaJedinica, BigDecimal preciznost) {
        super(mjernaJedinica, preciznost);
        this.elektronickaKomponenta = elektronickaKomponenta;
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor temperature\n Komponenta: " +  getElektronickaKomponenta() + ", " + "vrijednost: " + getVrijednost() + getMjernaJedinica();
    }

    public String getElektronickaKomponenta() {

        return elektronickaKomponenta;
    }

    public void setElektronickaKomponenta(String elektronickaKomponenta) {
        this.elektronickaKomponenta = elektronickaKomponenta;
    }
}
