package RMI;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Cliente extends UnicastRemoteObject implements ClienteIF, Runnable{
	String nomeCliente = "";
	String urlCliente = null;
	ServidorIF servidor = null;
	ClienteIF oponente = null;
	boolean partidaOcorrendo = false;
	boolean minhaVez = false;
	int pontos = 0;
	String tipoDePlayer = null;
	String msgEnviada = "";
	String msgRecebida = "";
	String msgStatusPartida = "Aguardando oponente";
	String msgStatusTurnoPartida = "";
	
	public Cliente(String nome) throws RemoteException {
		
		nomeCliente = "Cliente" + new Random().nextInt(1000);
		
		conectaCliente();
		
		registraCliente();
	
	}
	
	public String getNomeCliente() throws RemoteException{
		return nomeCliente;
	}

	public String getUrlCliente() throws RemoteException{
		return urlCliente;
	}
	


	public ClienteIF getOponente() throws RemoteException{
		return oponente;
	}


	
	public void setOponente(ClienteIF oponente) throws RemoteException{
		this.oponente = oponente;
	}
	
	public boolean isPartidaOcorrendo() throws RemoteException{
		return partidaOcorrendo;
	}

	public void setPartidaOcorrendo(boolean partidaEstaOcorrendo) throws RemoteException{
		this.partidaOcorrendo = partidaEstaOcorrendo;
		
		if(partidaEstaOcorrendo) {
			setMsgStatusPartida("Partina iniciada");
		} else {
			setMsgStatusPartida("Fim da partida");
		}
	}

	public boolean isMinhaVez() throws RemoteException{
		return minhaVez;
	}

	public void setMinhaVez(boolean minhaVez) throws RemoteException{
		this.minhaVez = minhaVez;
		
		if(minhaVez) {
			setMsgStatusTurnoPartida( nomeCliente + " - Sua vez de jogar!");
		} else {
			setMsgStatusTurnoPartida("Vez do oponente!");
		}
	}
	
	public int getPontos() throws RemoteException{
		return pontos;
	}

	private void setPontos(int pontos){
		this.pontos = pontos;
	}

	public String getTipoDePlayer() throws RemoteException{
		return tipoDePlayer;
	}

	public void setTipoDePlayer(String tipoDePlayer) throws RemoteException{
		this.tipoDePlayer = tipoDePlayer;
	}

	private void enviaMsg(String mensagem) {
		this.msgEnviada = mensagem;
		
		try {
			oponente.recebeMsg(this.msgEnviada);
		} catch (RemoteException e) {
			System.err.println("Chat - Erro de comunicacao \n");
			e.printStackTrace();
		}
	}

	public String getMsgEnviada() throws RemoteException{
		return this.msgEnviada;
	}
	
	public void recebeMsg(String mensagem) throws RemoteException{
		if(!mensagem.equals("") && mensagem != null) {
			this.msgRecebida = mensagem;
			System.out.println(this.msgRecebida);
		}
	}
	
	public String getMsgRecebida() throws RemoteException{
		return msgRecebida;
	}
	
	


	public String getMsgStatusPartida() throws RemoteException{
		return msgStatusPartida;
	}

	private void setMsgStatusPartida(String msgStatusPartida) {
		this.msgStatusPartida = msgStatusPartida;
	}

	public String getMsgStatusTurnoPartida() throws RemoteException{
		return msgStatusTurnoPartida;
	}

	private void setMsgStatusTurnoPartida(String msgStatusTurnoPartida) {
		this.msgStatusTurnoPartida = msgStatusTurnoPartida;
	}

	@Override
	public void run() {
		while(true) {
      		try {Thread.sleep(50);} catch (InterruptedException e1) {}
			try {
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); 
				enviaMsg(nomeCliente + ": " + r.readLine());
			}catch (Exception e) {System.err.println("Cliente - Erro ao enviar uma mensagem"); }
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
			servidor = (ServidorIF) Naming.lookup("//localhost/Servidor");
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