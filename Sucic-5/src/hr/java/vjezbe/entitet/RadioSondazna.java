package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja sučelje koje opisuje mjerne postaje koje su radio sondažne
 */
public interface RadioSondazna {
    /**
     * predstavlja metodu koje sve klase moraju nasljediti koje implementiraju
     * @param visina predstavlja visinu koju će se stavit u varijablu visine
     */
    void podesiVisinuPostaje(Integer visina);

    /**
     * dohvaćanje visine iz varijable
     * @return vraća visinu
     */
    Integer dohvatiVisinuPostaje();

    /**
     * privatna metoda za provjeru visine
     * @param visina je parametar metode koji prima za provjeru
     * @return vraća true ili false, zavisno o tome je li visina manje ili veća od 1000
     */
    private Boolean provjeriVisinu(Integer visina){
        if (visina > 1000)
            return true;
        return false;
    }

    /**
     * povećava visinu za 1 ili na 1000
     * @param visina koje se dobiva i povećava za 1 ili je vraćena na 1000 ako je veća od 1000
     */
    default void povecajVisinu(Integer visina){
        if (provjeriVisinu(visina))
            podesiVisinuPostaje(1000);
        else
            podesiVisinuPostaje(visina + 1);
    }
}
