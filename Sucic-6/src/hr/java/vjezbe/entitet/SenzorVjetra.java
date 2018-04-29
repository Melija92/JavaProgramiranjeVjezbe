package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja entitet senzora vjetra
 */
public class SenzorVjetra extends Senzor {
    private String velicinaSenzora;

    /**
     * prima parametre mjerne jedinice, njene preciznost i veličine senzora
     * @param mjernaJedinica je mjerne jedinica senzora
     * @param preciznost je preciznost mjerne jedinice
     * @param velicinaSenzora je veličina senzora vjetra
     */
    public SenzorVjetra(String mjernaJedinica, BigDecimal preciznost, String velicinaSenzora, RadSenzora radSenzora) {
        super(mjernaJedinica, preciznost, radSenzora);
        this.velicinaSenzora = velicinaSenzora;
    }
    public SenzorVjetra(String mjernaJedinica, BigDecimal preciznost, String velicinaSenzora, RadSenzora radSenzora, Integer id) {
        super(mjernaJedinica, preciznost, radSenzora, id);
        this.velicinaSenzora = velicinaSenzora;
    }

    /**
     * vraća string s tekstom veličinom senzora, njenom vrijednošću s mjernom jedinicom
     * @return string teksta
     */
    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor vjetra\n Veličina senzora: " + getVelicinaSenzora() + ", " + getVrijednost() + getMjernaJedinica()
                + "\nRad senzora je: " + getRadSenzora();
    }

    public String getVelicinaSenzora() {
        return velicinaSenzora;
    }

    public void setVelicinaSenzora(String velicinaSenzora) {
        this.velicinaSenzora = velicinaSenzora;
    }
}
