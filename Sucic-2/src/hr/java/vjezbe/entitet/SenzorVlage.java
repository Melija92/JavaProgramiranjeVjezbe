package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {
    public SenzorVlage(String mjernaJedinica, Byte preciznost) {
        super(mjernaJedinica, preciznost);
    }

    @Override
    public String dohvatiPodatkeSenzora() {
        return getMjernaJedinica() + getPreciznost();
    }
}
