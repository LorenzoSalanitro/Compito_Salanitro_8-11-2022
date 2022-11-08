package it.fi.meucci;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer 
{
    private ArrayList<ServerThread> lista_thread = new ArrayList<>(); 
    public static ArrayList<Biglietti> LS_biglietti = new ArrayList<>();

    static ServerSocket serverSocket;
    
    public MultiServer() 
    {
        Biglietti uno = new Biglietti(1,"tribuna est");
        Biglietti due = new Biglietti(2,"tribuna centrale");
        Biglietti tre = new Biglietti(3,"tribuna ovest");
        Biglietti quattro = new Biglietti(4,"tribuna sud");

        LS_biglietti.add(uno);
        LS_biglietti.add(due);
        LS_biglietti.add(tre);
        LS_biglietti.add(quattro);
    }

    public void parti()
    {
        try
        {
            serverSocket = new ServerSocket(7777);
            
            for(;;)
            {
                System.out.println("server still waiting");
                Socket socket = serverSocket.accept();
                System.out.println("server socket" + socket);
                ServerThread serverThread = new ServerThread(socket);
                lista_thread.add(serverThread);
                serverThread.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("error during instance");
        }
    }
}
