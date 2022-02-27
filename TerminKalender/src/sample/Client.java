package sample;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/*
 * Main Methode
 * @param args
 * Zugang zur Registry des Servers feststellen,
 * um die Kommunikation und den Datenaustausch mit dem Server durchfuehren.
 * mit ausnahmenbehandlung
 */
public class Client {
    public static void main(String[] args){
        
        try{
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            Facade server = (Facade) reg.lookup("rmi://localhost/service");
            Scanner scan = new Scanner(System.in);
        }catch(RemoteException | NotBoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
