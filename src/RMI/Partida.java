package RMI;

import java.rmi.RemoteException;

public class Partida {
	
	ClienteIF player1 = null;
	ClienteIF player2 = null;

	public Partida(ServidorIF servidor, ClienteIF player1, ClienteIF player2) throws RemoteException {
		this.player1 = player1;
		this.player2 = player2;
		
		player1.setOponente(player2);
		player2.setOponente(player1);
		
		player1.setMinhaVez(true);
		player2.setMinhaVez(false);
		
		player1.setPartidaOcorrendo(true);
		player2.setPartidaOcorrendo(true);

	}
}
