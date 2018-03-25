package hr.java.vjezbe.entitet;

public class SenzorTemperature extends Senzor {

//    private static final string CELSIJUS = "C";
//    private static final string FAHRENHEIT = "F";
//    private static final string KELVIN = "K";
//    private static final string RANKINE = "R";

    private String elektronickaKomponenta;

    public SenzorTemperature(String elektronickaKomponenta, String mjernaJedinica, Byte preciznost) {
        super(mjernaJedinica, preciznost);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return getElektronickaKomponenta() + getMjernaJedinica() + getPreciznost();
    }

    public String getElektronickaKomponenta() {
        return elektronickaKomponenta;
    }

    public void setElektronickaKomponenta(String elektronickaKomponenta) {
        this.elektronickaKomponenta = elektronickaKomponenta;
    }
}
