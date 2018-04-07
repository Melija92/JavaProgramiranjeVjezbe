package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Random;

/**
 * predstavlja entitet senzora temperature
 */
public class SenzorTemperature extends Senzor {

    private String elektronickaKomponenta;

    /**
     * prima parametre naziva elektroničke komponente, mjerne jedinice i preciznost
     * @param elektronickaKomponenta je nazive elektroničke komponente
     * @param mjernaJedinica je vrsta mjerne jedinice
     * @param preciznost predstavlja precinost mjerne jedinice
     */
    public SenzorTemperature(String elektronickaKomponenta, String mjernaJedinica, BigDecimal preciznost, RadSenzora radSenzora) {
        super(mjernaJedinica, preciznost, radSenzora);
        this.elektronickaKomponenta = elektronickaKomponenta;
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor temperature\n Komponenta: " +  getElektronickaKomponenta() + ", " + "vrijednost: " + getVrijednost() + getMjernaJedinica();
    }

    /**
     * generira random temperaturu
     * @throws VisokaTemperaturaException
     */
    public void generirajVrijednost() throws VisokaTemperaturaException {
        final int minVrijedost = -50;
        final int maxVrijedost = 50;
        final BigDecimal minNedopustenaVrijedost = new BigDecimal("-10");
        final BigDecimal maxnedopustenaVrijedost = new BigDecimal("40");

        Random random = new Random();
        BigDecimal generiranaVrijednost;
        generiranaVrijednost = new BigDecimal(random.nextInt(maxVrijedost - minVrijedost + 1) + minVrijedost);
        setVrijednost(generiranaVrijednost);
        System.out.println(generiranaVrijednost);

        if(getVrijednost().compareTo(maxnedopustenaVrijedost) > 0){
            throw new VisokaTemperaturaException("Temperatura " + getVrijednost() + " je previsoka");
        }
        if(getVrijednost().compareTo(minNedopustenaVrijedost) < 0){
            throw new NiskaTemperaturaException("Preniska temperatura " + getVrijednost() + " je preniska");
        }

    }

    public String getElektronickaKomponenta() {

        return elektronickaKomponenta;
    }

    public void setElektronickaKomponenta(String elektronickaKomponenta) {
        this.elektronickaKomponenta = elektronickaKomponenta;
    }
}
