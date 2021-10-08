package RMI;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class Registrador {

	public static void main(String args[]) {
		try{
			LocateRegistry.createRegistry(1099);
	
			Servidor obj = new Servidor ();

			Naming.rebind("//localhost/InverterRef",obj);

			System.out.println("Servidor Registrado!");

		} catch (Exception e){System.out.println("Erro");}
                        
	}
}
