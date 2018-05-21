package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * predstavlja entitet klase Senzora kao apstraktnu klasu
 */
public abstract class Senzor extends BazniEntitet {
    private String mjernaJedinica;
    private BigDecimal preciznost;
    private BigDecimal vrijednost;
    private RadSenzora radSenzora;
    /**
     * prima parametre mjerne jedinice i njenu preciznost
     * @param mjernaJedinica označava mjernuJedinicu
     * @param preciznost ozačava preciznost
     */
    public Senzor(String mjernaJedinica, BigDecimal preciznost, RadSenzora radSenzora) {
        super();
        this.mjernaJedinica = mjernaJedinica;
        this.preciznost = preciznost;
        this.radSenzora = radSenzora;
    }

    public Senzor(String mjernaJedinica, BigDecimal preciznost, RadSenzora radSenzora, int id) {
        super(id);
        this.mjernaJedinica = mjernaJedinica;
        this.preciznost = preciznost;
        this.radSenzora = radSenzora;
    }

    public abstract String dohvatiPodatkeSenzora();

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public BigDecimal getPreciznost() {
        return preciznost;
    }

    public void setPreciznost(BigDecimal preciznost) {
        this.preciznost = preciznost;
    }

    public BigDecimal getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(BigDecimal vrijednost) {
        this.vrijednost = vrijednost;
    }
    public RadSenzora getRadSenzora() {
        return radSenzora;
    }

    public void setRadSenzora(RadSenzora radSenzora) {
        this.radSenzora = radSenzora;

    }
}
