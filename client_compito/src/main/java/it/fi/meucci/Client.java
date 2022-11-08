package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Client 
{
    String name_server = "localhost";
    int serverport = 7777;
    Socket mysocket;
    BufferedReader keyboard;
    String user;
    String received;
    String risposta;
    String disponibili;
    DataOutputStream output;
    BufferedReader input;

    public Socket connect()
    {
        ObjectMapper mapper = new ObjectMapper();

        try 
        {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(name_server, serverport);
            output = new DataOutputStream(mysocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));

            ArrayList <Biglietti> L_vuota = new ArrayList<>();

            Messaggio mess = new Messaggio(L_vuota);

            output.writeBytes(mapper.writeValueAsString(mess) + '\n');

            disponibili = input.readLine();

            Messaggio ms = mapper.readValue(disponibili, Messaggio.class);

            System.out.println("i biglietti disponibili sono: ");

            for (int i = 0; i < ms.getListaBiglietti().size(); i++) 
            {
                System.out.println("id: " + ms.getListaBiglietti().get(i).ID +" "+"posto: " + ms.getListaBiglietti().get(i).nome_biglietto);
            }

        } catch (UnknownHostException e) 
        {
            System.err.println("Unknowed host");
        }
        catch(Exception e)
        {
            System.out.println("Error during connection");
            System.exit(1);
        }
        return mysocket;
        
    }

    public void comunicate()
    {   
        while(true)
        {
            try
            {
                ArrayList <Biglietti> da_comprare = new ArrayList<>();

                System.out.println("scegli i biglietti da comprare: ");

                received = keyboard.readLine();
                String compra[] = received.split(",");

                for (int i = 0; i < compra.length; i++) 
                {
                    Biglietti a = new Biglietti(Integer.parseInt(compra[i]), "");
                    da_comprare.add(a);
                }

                ObjectMapper mapper = new ObjectMapper();

                Messaggio ms = new Messaggio(da_comprare);

                output.writeBytes(mapper.writeValueAsString(ms) + '\n');

                risposta = input.readLine();

                Messaggio ricevuto = mapper.readValue(risposta, Messaggio.class);

                System.out.println("i biglietti comprati sono i seguenti: ");
                for (int i = 0; i < ricevuto.getListaBiglietti().size(); i++) 
                {
                    System.out.println("id: " + ricevuto.getListaBiglietti().get(i).getID());
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("error");
            }
        }
    

    } 
}
