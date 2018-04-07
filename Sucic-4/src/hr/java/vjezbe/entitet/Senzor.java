package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja entitet klase Senzora kao apstraktnu klasu
 */
public abstract class Senzor {
    private String mjernaJedinica;
    private BigDecimal preciznost;
    private BigDecimal vrijednost;

    /**
     * prima parametre mjerne jedinice i njenu preciznost
     * @param mjernaJedinica označava mjernuJedinicu
     * @param preciznost ozačava preciznost
     */
    public Senzor(String mjernaJedinica, BigDecimal preciznost) {
        this.mjernaJedinica = mjernaJedinica;
        this.preciznost = preciznost;
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
}
