package RMI;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Servidor extends UnicastRemoteObject implements ServidorIF {
	
	private ArrayList<ClienteIF> listaClientes = new ArrayList<ClienteIF>();
	private ArrayList<String> listaUrlClientes = new ArrayList<String>();

	public Servidor() throws RemoteException{
		super();
      	System.out.println("Servidor criado!");
      	registraServidor();
	}
	
	public synchronized void transmiteMsg(String urlClienteOrigem, String mensagem) throws RemoteException {
		
		System.out.println("servidor: " + urlClienteOrigem + "/ " + mensagem);
		
		int i = 0;
		if(listaClientes.size() >= 2) {			
			ClienteIF p1 = listaClientes.get(i);
			ClienteIF p2 = listaClientes.get(i+1);
			
			//System.out.println("1");
			
			if(urlClienteOrigem.equals(p1.getUrlCliente())) {
				System.out.println("quem enviou foi o p1");
				p2.recebeMsg(mensagem);
			} else if(urlClienteOrigem.equals(p2.getUrlCliente())) {
				System.out.println("quem enviou foi o p2");
				p1.recebeMsg(mensagem);
			} else {
				System.out.println("URL_CLIENTE não serve pra comparar com o objeto remoto");
			}
		}
		
		
	}
	
	public synchronized void registraCliente(String urlCliente) throws RemoteException, MalformedURLException, NotBoundException {
		listaUrlClientes.add(urlCliente);
		
		ClienteIF player = (ClienteIF) Naming.lookup(urlCliente);
		listaClientes.add(player);
	}
	
	public void registraServidor() {
		try{
			LocateRegistry.createRegistry(1099);
	
			Naming.rebind("//localhost/Servidor", this);

			System.out.println("Servidor Registrado!");

		} catch (Exception e){System.err.println("Erro ao registrar servidor");}
	                        
	}
	

	public static void main(String args[]) {
		try {
			new Servidor ();
		} catch (RemoteException e) {}
	}

}
