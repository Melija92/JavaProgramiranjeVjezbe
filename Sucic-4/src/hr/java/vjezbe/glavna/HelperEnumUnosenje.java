package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.VrstaMjesta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HelperEnumUnosenje {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static VrstaMjesta unosVrsteMjesta(Scanner unos){
        VrstaMjesta vrstaMjesta;

        for (int i = 0; i < VrstaMjesta.values().length - 1 ; i++) {
            System.out.println((i + 1) +  ". " + VrstaMjesta.values()[i]);
        }
        Integer redniBrojvrsteMjesta = null;
        while (true){
            System.out.println("Odaberi vrstu mjesta");
            try{
                redniBrojvrsteMjesta = unos.nextInt();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Neispravan unos vrste mjesta");
                unos.nextLine();
                logger.error("Neispravan unos mjesta", ex);
            }
        }
        if(redniBrojvrsteMjesta >= 1 && redniBrojvrsteMjesta < VrstaMjesta.values().length){
            vrstaMjesta = VrstaMjesta.values()[redniBrojvrsteMjesta - 1];
        } else{
            vrstaMjesta = VrstaMjesta.OSTALO;
        }

        return vrstaMjesta;
    }

    public static RadSenzora unosRadaSenzora(Scanner unos){
        RadSenzora radSenzora;

        for (int i = 0; i < RadSenzora.values().length - 1 ; i++) {
            System.out.println((i + 1) +  ". " + RadSenzora.values()[i]);
        }
        Integer redniBrojSenzora = null;
        while (true){
            System.out.println("Odaberi rad senzora");
            try{
                redniBrojSenzora = unos.nextInt();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Neispravan unos rada senzora");
                unos.nextLine();
                logger.error("Neispravan unos senzora", ex);
            }
        }
        if(redniBrojSenzora >= 1 && redniBrojSenzora < RadSenzora.values().length){
            radSenzora = RadSenzora.values()[redniBrojSenzora - 1];
        } else{
            radSenzora = RadSenzora.OSTALO;
        }

        return radSenzora;
    }
}
