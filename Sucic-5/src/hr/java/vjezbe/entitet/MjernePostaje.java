package hr.java.vjezbe.entitet;

import java.util.List;

public class MjernePostaje<T extends MjernaPostaja> {
    private List<T> listaMjernihPostaja;

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

    public List<T> getSortedList(T listaMjernihPostaja){
        
    }
}
