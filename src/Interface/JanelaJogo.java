package Interface;

import java.rmi.RemoteException;
import java.util.Random;

import javax.swing.JFrame;

import RMI.Cliente;

public class JanelaJogo extends JFrame {
	
	Cliente player = null;
	PainelTabuleiro tabuleiro = null;
	
	public JanelaJogo() {	
		inicializaJanelaPrincipal();
		inicializaPlayer();
		inicializaPainelJogo();		
	}
	
	private void inicializaJanelaPrincipal() {
		this.setTitle("Jogo");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void inicializaPlayer() {
		try {
			player = new Cliente("Cliente_" + new Random().nextInt(1000));
		} catch (RemoteException e) { e.printStackTrace();}
	}

	private void inicializaPainelJogo() {
		try {
			tabuleiro = new PainelTabuleiro(player);
			this.add(tabuleiro);
			this.setTitle(player.getNomeCliente());
			this.setSize(tabuleiro.getLargura(), tabuleiro.getAltura());

		} catch (RemoteException e) {e.printStackTrace();}
	
	}
}
