package RMI;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Servidor extends UnicastRemoteObject implements ServidorIF {
	
	boolean conectado = false;
	String msgServidor = "";
	private ArrayList<ClienteIF> listaClientes = new ArrayList<ClienteIF>();
	private ArrayList<String> listaUrlClientes = new ArrayList<String>();

	public Servidor() throws RemoteException{
		super();
      	this.msgServidor += "Servidor criado!\n";
      	System.out.println("Servidor criado!");
      	registraServidor();
      	
      	criaPartidas();
	}
	
	private void criaPartidas() {
		int minimoParaNovaPartida = 2;
      	while(true){
      		try {Thread.sleep(100);} catch (InterruptedException e1) {}
      		
      		if(listaClientes.size() == minimoParaNovaPartida) {
      			int i = minimoParaNovaPartida - 2;
      			try {
					new Partida(this, listaClientes.get(i), listaClientes.get(i+1));
					minimoParaNovaPartida += 2;
					this.msgServidor += "\n   Nova partida iniciada: " + listaClientes.get(i).getNomeCliente() + " X " + listaClientes.get(i+1).getNomeCliente() + "\n";
					System.out.println("\n   Nova partida iniciada: " + listaClientes.get(i).getNomeCliente() + " X " + listaClientes.get(i+1).getNomeCliente() + "\n");
				} catch (Exception e) {
					this.msgServidor += "Erro ao criar partidas no servidor\n";
					System.out.println("Erro ao criar partidas no servidor");
					e.printStackTrace();
					break;
				}
      		}
      	}
	}
	
	public synchronized void registraCliente(String urlCliente) throws RemoteException, MalformedURLException, NotBoundException {
		listaUrlClientes.add(urlCliente);
		
		ClienteIF player = (ClienteIF) Naming.lookup(urlCliente);
		listaClientes.add(player);
		
		if(listaClientes.size() % 2 != 0) {	
			player.setTipoDePlayer("p1");
		} else {
			player.setTipoDePlayer("p2");
		}
		this.msgServidor += player.getNomeCliente() + " se conectou\n";
		System.out.println(player.getNomeCliente() + " se conectou"); 
	}
	
	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	
	public String getMsgServidor() throws RemoteException{
		return this.msgServidor;
	}

        public ArrayList<String> getListaUrlClientes() throws RemoteException{
            return listaUrlClientes;
        }
      
	public void registraServidor() {
		try{
			LocateRegistry.createRegistry(1099);
	
			Naming.rebind("//localhost/Servidor", this);
			
			this.msgServidor += "Servidor Registrado!\n";
			System.out.println("Servidor Registrado!");
			
			setConectado(true);

		} catch (Exception e){System.err.println("Erro ao registrar servidor");}
	                        
	}
	
	/*
	public static void main(String args[]) {
		try {
			new Servidor ();
		} catch (RemoteException e) {}
	}
	//*/
}
