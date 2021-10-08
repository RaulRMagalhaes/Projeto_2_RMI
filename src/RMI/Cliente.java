package RMI;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Cliente extends UnicastRemoteObject implements InterfacePlayer1, Runnable{
	String nomeCliente = "";
	String urlCliente = null;
	InterfaceServidor servidor = null;
	String msgEnviada = "";
	String msgRecebida = "";
	
	public Cliente(String nome) throws RemoteException {
		
		nomeCliente = "Cliente" + new Random().nextInt(1000);
		
		conectaCliente();
		
		registraCliente();
	
	}
	
	
	public String getUrlCliente() {
		return urlCliente;
	}

	private void setMsdEnviada(String mensagem) throws RemoteException{
		this.msgEnviada = nomeCliente + ": " + mensagem;
	}

	public String getMsgEnviada() throws RemoteException{
		return msgEnviada;
	}
	
	public void recebeMsg(String mensagem) throws RemoteException{
		this.msgRecebida = mensagem;
		if(!this.msgRecebida.equals("")) {
			System.out.println(this.msgRecebida);
		}
		this.msgRecebida = "";
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); 
				setMsdEnviada(r.readLine());				
				servidor.transmiteMsg(urlCliente, getMsgEnviada());
				 
			} catch (Exception e) {System.err.println("Erro ao enviar uam mensagem"); }
		}
	}

	private void registraCliente() {
		try{	
			urlCliente = "//localhost/" + nomeCliente;
			
			Naming.rebind(urlCliente, this);
			
			servidor.registraCliente(urlCliente);
			
			System.out.println("Cliente " + nomeCliente + " Registrado!");
			
			new Thread(this).start();

		} catch (Exception e){System.err.println("Erro ao registrar Cliente " + nomeCliente);}
	}
	
	private void conectaCliente() {
		try {   
			servidor = (InterfaceServidor) Naming.lookup("//localhost/Servidor");
			System.out.println("Servidor Localizado!");
		} catch(Exception e){System.err.println("Erro ao conectar cliente - Servidor não encontrado no servidor de nomes ou Servidor de nomes fora do ar");}
		//System.exit(0);
	}
	
	public static void main(String args[])  { 
		try {
			new Cliente("And");
		} catch (RemoteException e) { e.printStackTrace();}
	}
	
}



