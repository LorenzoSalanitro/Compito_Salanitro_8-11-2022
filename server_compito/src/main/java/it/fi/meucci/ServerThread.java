package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread
{
    ServerSocket server = null;
    Socket client = null;
    String stringRecived = null;
    String stringModified = null;
    BufferedReader inputFromClient;
    DataOutputStream outputToClient;

    public ServerThread (Socket socket)
    {
        this.client = socket;
    }

    public void run()
    {
        try
        {
            comunicate();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void comunicate() throws Exception
    {
        inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputToClient = new DataOutputStream(client.getOutputStream());
        
        ObjectMapper mapper = new ObjectMapper();

        while(true)
        {
            stringRecived = inputFromClient.readLine();
            Messaggio mess = mapper.readValue(stringRecived, Messaggio.class);
            
            if (mess.getListaBiglietti().size() == 0) 
            {
                Messaggio nuovo = new Messaggio(MultiServer.LS_biglietti);
                outputToClient.writeBytes(mapper.writeValueAsString(nuovo) + '\n');
            }
            else
            {
                ArrayList <Biglietti> biglietti_presi = new ArrayList<>();

                for (int i = 0; i < mess.getListaBiglietti().size(); i++) 
                {
                    for (int j = 0; j < MultiServer.LS_biglietti.size(); j++) 
                    {
                        if (mess.getListaBiglietti().get(i).getID() == MultiServer.LS_biglietti.get(j).getID()) 
                        {
                            biglietti_presi.add(mess.getListaBiglietti().get(i));
                            MultiServer.LS_biglietti.remove(j);
                            j--;
                        }
                    }
                }
                Messaggio manda = new Messaggio(biglietti_presi);
                outputToClient.writeBytes(mapper.writeValueAsString(manda) + '\n');
            }

           
        }
    }
}
