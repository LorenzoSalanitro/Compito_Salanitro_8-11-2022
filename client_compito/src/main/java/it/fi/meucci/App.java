package it.fi.meucci;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Client cli = new Client();

        cli.connect();
        cli.comunicate();
        
    }
}
