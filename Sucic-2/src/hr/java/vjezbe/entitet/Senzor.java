package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public abstract class Senzor {
    private String mjernaJedinica;
    private BigDecimal preciznost;
    private BigDecimal vrijednost;

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
