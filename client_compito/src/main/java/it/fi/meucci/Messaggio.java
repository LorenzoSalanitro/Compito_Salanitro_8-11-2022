package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio
{
    ArrayList <Biglietti> Lista_biglietti = new ArrayList<>();

    public Messaggio(ArrayList<Biglietti> lista_biglietti) 
    {
        Lista_biglietti = lista_biglietti;
    }

    public Messaggio() {}

    public ArrayList<Biglietti> getLista_biglietti() {
        return Lista_biglietti;
    }

    public void setLista_biglietti(ArrayList<Biglietti> lista_biglietti) {
        Lista_biglietti = lista_biglietti;
    }

    @Override
    public String toString() {
        return "Messaggio [Lista_biglietti=" + Lista_biglietti + "]";
    }
}
