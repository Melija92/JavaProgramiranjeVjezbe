package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class SenzorVjetra extends Senzor {
    private String velicinaSenzora;

    public SenzorVjetra(String mjernaJedinica, BigDecimal preciznost, String velicinaSenzora) {
        super(mjernaJedinica, preciznost);
        this.velicinaSenzora = velicinaSenzora;
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor vjetra\n Veličina senzora: " + getVelicinaSenzora() + ", " + getVrijednost() + getMjernaJedinica();
    }

    public String getVelicinaSenzora() {
        return velicinaSenzora;
    }

    public void setVelicinaSenzora(String velicinaSenzora) {
        this.velicinaSenzora = velicinaSenzora;
    }
}
