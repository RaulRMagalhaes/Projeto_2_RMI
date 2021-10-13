package RMI;

import java.rmi.RemoteException;

public class Partida implements Runnable{
	
	ClienteIF player1 = null;
	ClienteIF player2 = null;

	public Partida(ServidorIF servidor, ClienteIF player1, ClienteIF player2) throws RemoteException {
		this.player1 = player1;
		this.player2 = player2;
		
		player1.setOponente(player2);
		player2.setOponente(player1);
		
		//player1.setTipoDePlayer("p1");
		//player2.setTipoDePlayer("P2");
		
		player1.setMinhaVez(true);
		player2.setMinhaVez(false);
		
		player1.setPartidaOcorrendo(true);
		player2.setPartidaOcorrendo(true);
		
		//new Thread(this).start();
		

	}
	
	@Override
	public void run(){
		while(true) {
      		try {Thread.sleep(500);} catch (InterruptedException e1) {}
      		System.out.println("partida rodando");
			try {
				player1.recebeMsg(player2.getMsgEnviada());
				
			} catch (RemoteException e) {
				try {player2.setPartidaOcorrendo(false);} catch (RemoteException e1) {e1.printStackTrace();}

				System.err.println("Partida - Erro de comunicacao com clientes 1\n");
				e.printStackTrace();
				break;
			}
			
			try {
				player2.recebeMsg(player1.getMsgEnviada());
				
			} catch (RemoteException e) {
				try {player1.setPartidaOcorrendo(false);} catch (RemoteException e1) {e1.printStackTrace();}
				
				System.err.println("Partida - Erro de comunicacao com clientes 2\n");
				e.printStackTrace();
				break;
			}
		}		
	}
	

}
