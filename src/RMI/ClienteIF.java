package RMI;


import java.rmi.Remote; 
import java.rmi.RemoteException;

import player.Pino;

public interface ClienteIF extends Remote {

	String getNomeCliente() throws RemoteException;

	String getUrlCliente() throws RemoteException;
		
	ClienteIF getOponente() throws RemoteException;

	void setOponente(ClienteIF oponente) throws RemoteException;
	
	boolean isPartidaOcorrendo() throws RemoteException;
	
	void setPartidaOcorrendo(boolean partidaComecou) throws RemoteException;	
	
	boolean isMinhaVez() throws RemoteException;
	
	void setMinhaVez(boolean minhaVez) throws RemoteException;
	
	int getPontos() throws RemoteException;
	
	void setPontos(int pontos) throws RemoteException;
		
	String getTipoDePlayer() throws RemoteException;
	
	void setTipoDePlayer(String tipoDePlayer) throws RemoteException;
	
	void enviaMsg(String mensagem) throws RemoteException;
	
	String getMsgEnviada() throws RemoteException;
	
	void recebeMsg(String mensagem) throws RemoteException;
	
	String getMsgRecebida() throws RemoteException;
	
	String getMsgStatusPartida() throws RemoteException;
	
	String getMsgStatusTurnoPartida() throws RemoteException;
	
	String[][] getMatrizPinos() throws RemoteException;

	void setMatrizPinos(String[][] matrizPinos) throws RemoteException;
	
	void setMatrizPinosTabuleiro(Pino[][] matrizPinosTabuleiro) throws RemoteException;
	
	void removePino(int i, int j) throws RemoteException;
}


