package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * predstavlja entitet senzora vlage
 */
public class SenzorVlage extends Senzor  implements Serializable {
    /**
     * prima parametre mjerne jedinice i njene preciznosti
     * @param mjernaJedinica
     * @param preciznost
     */
    public SenzorVlage(String mjernaJedinica, BigDecimal preciznost, RadSenzora radSenzora) {
        super(mjernaJedinica, preciznost, radSenzora);
    }

    public SenzorVlage(String mjernaJedinica, BigDecimal preciznost, RadSenzora radSenzora, Integer id) {
        super(mjernaJedinica, preciznost, radSenzora, id);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor vlage\n Vrijednost: " + getVrijednost() + getMjernaJedinica() + " vlage zraka"
                + "\nRad senzora je: " + getRadSenzora();
    }
}
