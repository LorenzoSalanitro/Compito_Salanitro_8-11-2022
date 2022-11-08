package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio
{
    ArrayList <Biglietti> listaBiglietti = new ArrayList<>();

    public Messaggio(ArrayList<Biglietti> lista_biglietti) 
    {
        this.listaBiglietti = lista_biglietti;
    }

    public Messaggio() {}

    public ArrayList<Biglietti> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(ArrayList<Biglietti> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    @Override
    public String toString() {
        return "Messaggio [listaBiglietti=" + listaBiglietti + "]";
    }



}
