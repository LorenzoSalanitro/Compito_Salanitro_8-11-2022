package it.fi.meucci;

public class Biglietti 
{
    int ID;
    String nome_biglietto;

    public Biglietti(int iD, String nome_biglietto) 
    {
        ID = iD;
        this.nome_biglietto = nome_biglietto;
    }

    public Biglietti() {}

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getNome_biglietto() {
        return nome_biglietto;
    }

    public void setNome_biglietto(String nome_biglietto) {
        this.nome_biglietto = nome_biglietto;
    }

    @Override
    public String toString() {
        return "Biglietti [ID=" + ID + ", nome_biglietto=" + nome_biglietto + "]";
    }
}
