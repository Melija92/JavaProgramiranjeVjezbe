package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja entitet senzora vlage
 */
public class SenzorVlage extends Senzor {
    /**
     * prima parametre mjerne jedinice i njene preciznosti
     * @param mjernaJedinica
     * @param preciznost
     */
    public SenzorVlage(String mjernaJedinica, BigDecimal preciznost) {

        super(mjernaJedinica, preciznost);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor vlage\n Vrijednost: " + getVrijednost() + getMjernaJedinica() + " vlage zraka";
    }
}
