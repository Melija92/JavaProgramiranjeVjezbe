package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * predstavlja entitet radio sondažne postaje
 */
public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

    private Integer visinaPostaje;

    /**
     * prima parametre visine postaje, naziva, referencu na mjesto, geografsku točku i senzore
     * @param visinaPostaje je visina radio sondažne postaje
     * @param naziv je naziv radio sondažne postaje
     * @param mjesto je referenca na jesto
     * @param geografskaTocka je referenca na geografku točku
     * @param senzori je referenca na polje senzora
     */
    public RadioSondaznaMjernaPostaja(Integer visinaPostaje,
                                      String naziv, Mjesto mjesto,
                                      GeografskaTocka geografskaTocka, List<Senzor> senzori) {
        super(naziv, mjesto, geografskaTocka, senzori);
        this.visinaPostaje = visinaPostaje;
    }

    @Override
    public void podesiVisinuPostaje(Integer visina) {
        this.visinaPostaje = visina;
    }

    @Override
    public Integer dohvatiVisinuPostaje() {
        return visinaPostaje;
    }
}