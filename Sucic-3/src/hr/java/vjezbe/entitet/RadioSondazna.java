package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public interface RadioSondazna {
    void podesiVisinuPostaje(Integer visina);

    Integer dohvatiVisinuPostaje();

    private Boolean provjeriVisinu(Integer visina){
        if(visina > 1000)
            return true;
        return false;
    }

    default void povecajVisinu(Integer visina){
        if (provjeriVisinu(visina))
            podesiVisinuPostaje(1000);
        else
            podesiVisinuPostaje(visina + 1);
    }
}
