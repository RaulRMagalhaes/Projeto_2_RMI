package Interface;

import javax.swing.JFrame;

import fase.PainelTabuleiro;
import player.Pino;

public class JanelaJogo extends JFrame {

	public JanelaJogo() {
		
		PainelTabuleiro tabuleiro = new PainelTabuleiro();
		
		add(tabuleiro);
		
		setTitle("Jogo");
		setSize(tabuleiro.getLargura(), tabuleiro.getAltura());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
}
