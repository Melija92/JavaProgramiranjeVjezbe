package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class SenzorVlage extends Senzor {
    public SenzorVlage(String mjernaJedinica, BigDecimal preciznost) {

        super(mjernaJedinica, preciznost);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return "Senzor vlage\n Vrijednost: " + getVrijednost() + getMjernaJedinica() + " vlage zraka";
    }
}
