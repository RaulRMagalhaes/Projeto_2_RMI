package RMI;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import player.Pino;

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
	String [][] matrizPinos = null;
	Pino[][] matrizPinosTabuleiro = null;
	boolean conectado = false;
	
	public Cliente(String nomeCliente) throws RemoteException {
		
		this.nomeCliente = nomeCliente;   //"Cliente_" + new Random().nextInt(1000);
		
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
		
		if(oponente != null && this.minhaVez == false) {
			oponente.setMinhaVez(true);
		}
	}
	
	public int getPontos() throws RemoteException{
		return pontos;
	}

	public void setPontos(int pontos){
		this.pontos = pontos;
		
		try {
			System.out.println("Meus pontos: " + getPontos());
			
			if(getPontos() == 12) {
				
				System.out.println("VOCE GANHOU!!!");	
			}
		} catch (RemoteException e) {}
	}

	public String getTipoDePlayer() throws RemoteException{
		return tipoDePlayer;
	}

	public void setTipoDePlayer(String tipoDePlayer) throws RemoteException{
		this.tipoDePlayer = tipoDePlayer;
	}

	public void enviaMsg(String mensagem) throws RemoteException{
		if(!mensagem.equals("")){
			try {
				this.msgEnviada = mensagem;
				oponente.recebeMsg(nomeCliente + ": " + this.msgEnviada);
			} catch (RemoteException e) {
				System.out.println("Chat - Erro de comunicacao. Oponente desconectado\n");
				e.printStackTrace();
			}
		}
	}

	public String getMsgEnviada() throws RemoteException{
		return this.msgEnviada;
	}
	
	public void recebeMsg(String mensagem) throws RemoteException{		
		this.msgRecebida = mensagem;
		System.out.println(this.msgRecebida);
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
	
	public String[][] getMatrizPinos() throws RemoteException{
		return this.matrizPinos;
	}

	public void setMatrizPinos(String[][] matrizPinos) throws RemoteException{
		this.matrizPinos = matrizPinos;
	}
	
	
	public void setMatrizPinosTabuleiro(Pino[][] matrizPinosTabuleiro) throws RemoteException{
		this.matrizPinosTabuleiro = matrizPinosTabuleiro;
	}

	public void removePino(int i, int j) throws RemoteException{
		this.matrizPinosTabuleiro[i][j] = null;
	}
	
	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	@Override
	public void run() {
		while(true) {
      		try {Thread.sleep(50);} catch (InterruptedException e1) {}
			try {
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); 
				enviaMsg(r.readLine());
			}catch (Exception e) {System.err.println("Cliente - Erro ao enviar uma mensagem"); }
		}
	}

	private void registraCliente() {
		try{	
			urlCliente = "//localhost/" + nomeCliente;
			
			Naming.rebind(urlCliente, this);
			
			servidor.registraCliente(urlCliente);
			
			System.out.println("Cliente " + nomeCliente + " Registrado!");
			
			setConectado(true);
			
			new Thread(this).start();

		} catch (Exception e){System.err.println("Erro ao registrar Cliente " + nomeCliente);}
	}
	
	private void conectaCliente() {
		try {   
			servidor = (ServidorIF) Naming.lookup("//localhost/Servidor");
			System.out.println("Servidor Localizado!");
		} catch(Exception e){System.err.println("Erro ao conectar cliente - Servidor não encontrado no servidor de nomes ou Servidor de nomes fora do ar");}
	}
		
}