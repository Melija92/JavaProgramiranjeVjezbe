package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class SenzorTemperature extends Senzor {

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
