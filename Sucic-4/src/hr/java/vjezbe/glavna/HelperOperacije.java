package hr.java.vjezbe.glavna;

import java.math.BigDecimal;

/**
 * predstavlja entitet za pomoće izračune kao pomoćna klasa u aplikaciji
 */
public class HelperOperacije {
    /**
     * određuje redni broj na osnovu dobivenog broja
     * @param broj
     * @return
     */
    public static String odrediRedniBroj(int broj) {
        String redniBroj;

        switch (broj) {
            case 0:
                redniBroj = "prvu";
                break;
            case 1:
                redniBroj = "drugu";
                break;
            case 2:
                redniBroj = "trecu";
                break;
            case 3:
                redniBroj = "cetvrtu";
                break;

            default:
                redniBroj = "nepoznat redni broj";
                break;
        }

        return redniBroj;
    }

    /**
     * određuje mjernu jedinicu za temperaturu
     * @param oznaka
     * @return
     */
    public static String odrediMjernuJedinicuTemperature(int oznaka) {
        String mjernaJedinica;

        switch (oznaka) {
            case 1:
                mjernaJedinica = "°C";
                break;
            case 2:
                mjernaJedinica = "K";
                break;
            case 3:
                mjernaJedinica = "°F";
                break;
            case 4:
                mjernaJedinica = "°Ra";
                break;

            default:
                mjernaJedinica = null;
                break;
        }

        return mjernaJedinica;
    }

    /**
     * određuje preciznost mjerne jedinice za temperaturu
     * @param oznaka
     * @return
     */
    public static BigDecimal odrediPreciznostTemperature(int oznaka) {
        BigDecimal preciznost;

        switch (oznaka) {
            case 1:
                preciznost = new BigDecimal(2);
                break;
            case 2:
                preciznost = new BigDecimal(275.15);
                break;
            case 3:
                preciznost = new BigDecimal(35.6);
                break;
            case 4:
                preciznost = new BigDecimal(495.27);
                break;

            default:
                preciznost = null;
                break;
        }

        return preciznost;
    }

    /**
     * određuje mjernu jedinic za vlagu na osnovu dobivenog broja
     * @param oznaka
     * @return
     */
    public static String odrediMjernuJedinicuVlage(int oznaka) {
        String mjernaJedinica;

        switch (oznaka) {
            case 1:
                mjernaJedinica = "%";
                break;
            default:
                mjernaJedinica = "nepoznato";
                break;
        }

        return mjernaJedinica;
    }

    /**
     * određuje preciznost za mjernu jedinicu vlage po dobivenom broju
     * @param oznaka
     * @return
     */
    public static BigDecimal odrediPreciznostVlage(Byte oznaka) {
        BigDecimal preciznost;

        switch (oznaka) {
            case 1:
                preciznost = new BigDecimal(2);
                break;
            default:
                preciznost = null;
                break;
        }

        return preciznost;
    }

    /**
     *određuje mjernu jedinicu vjetra na osnovu dobivenog broja
     * @param oznaka
     * @return
     */
    public static String odrediMjernuJedinicuVjertra(int oznaka) {
        String mjernaJedinica;

        switch (oznaka) {
            case 1:
                mjernaJedinica = "m/s";
                break;
            case 2:
                mjernaJedinica = "kt";
                break;
            case 3:
                mjernaJedinica = "km/h";
                break;
            default:
                mjernaJedinica = "nepoznato";
                break;
        }

        return mjernaJedinica;
    }

    /**
     * određuje preciznost mjerne jedinice vjetra na osnovu dobivenog broja
     * @param oznaka
     * @return
     */
    public static BigDecimal odrediPreciznostVjetra(Byte oznaka) {
        BigDecimal preciznost;

            switch (oznaka) {
                case 1:
                    preciznost = new BigDecimal(0.3);
                    break;
                case 2:
                    preciznost = new BigDecimal(0.5);
                    break;
                case 3:
                    preciznost = new BigDecimal(1.08);
                    break;
                default:
                    preciznost = null;
                    break;
            }
        return preciznost;
    }
}
