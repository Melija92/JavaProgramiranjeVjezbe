package hr.java.vjezbe.entitet;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

    private Integer visinaPostaje;

    public RadioSondaznaMjernaPostaja(Integer visinaPostaje,
                                      String naziv, Mjesto mjesto,
                                      GeografskaTocka geografskaTocka, Senzor[] senzori) {
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