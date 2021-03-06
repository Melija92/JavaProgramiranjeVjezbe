package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MjernePostaje<T extends MjernaPostaja> {
    private List<T> listaMjernihPostaja;

    public MjernePostaje() {
        listaMjernihPostaja = new ArrayList<T>();
    }

    public T get(Integer i ){
        for (int j = 0; j < listaMjernihPostaja.size(); j++) {
          if(i == j){
              return listaMjernihPostaja.get(i);
          }
        }
        return null;
    }

    public void add(T mjernaPostaja){
        listaMjernihPostaja.add(mjernaPostaja);
    }

    public List<T> getSortedList(){
        Collections.sort(this.listaMjernihPostaja, (T m1, T m2) ->
        m1.getNaziv().compareTo(m2.getNaziv()));

        return listaMjernihPostaja;
    }
}
