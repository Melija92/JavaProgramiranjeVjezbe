package hr.java.vjezbe.entitet;

public abstract class BazniEntitet {
    private int id;

    BazniEntitet() {};

    BazniEntitet(int id)
    {
        setId(id);
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void nesto(){
        System.out.println("");
    }
}
