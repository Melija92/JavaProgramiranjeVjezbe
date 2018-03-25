package hr.java.vjezbe.entitet;

public class SenzorVjetra extends Senzor {
    public SenzorVjetra(String mjernaJedinica, Byte preciznost) {
        super(mjernaJedinica, preciznost);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return getMjernaJedinica() + getPreciznost();
    }
}
