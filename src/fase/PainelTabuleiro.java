package fase;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import player.Pino;

public class PainelTabuleiro extends JPanel implements ActionListener {
	
	private Image fundo, tabuleiro;
	private int altura;
	private int largura;
	private Point posicaoTabuleiro[][] = new Point[6][6];
	private int pinosTabuleiro[][] = new int[6][6];
	ArrayList<Pino> listaPlayer = new ArrayList<>();

	private Timer timer;
	
	public PainelTabuleiro() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		inicializaImagens();
		
		inicializaTabuleiro("p1");
		
		addMouseListener(new ControleMouseAdapter());
		
		new AtualizaTabuleiro().start();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(fundo, 0, 0, null);
		grafico.drawImage(tabuleiro, 0, 0, null);
		
		for(Pino pinos : listaPlayer) {
			grafico.drawImage(pinos.getImagem(), pinos.getX(), pinos.getY(), this);
		}
		
		for(Pino pinos : listaPlayer) {
			if(pinos.mouseClicou()) {
				grafico.drawImage(pinos.getImagem(), pinos.getX(), pinos.getY(), this);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Pino pinos : listaPlayer) {
			pinos.update();
		}
		repaint();
	}
	
	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}

	private class ControleMouseAdapter extends MouseAdapter{
			
		@Override
		public void mouseReleased(MouseEvent e) {
			for(Pino pinos : listaPlayer) {
				pinos.mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(Pino pinos : listaPlayer) {
				pinos.mousePressed(e);
			}
		}
						
		@Override
		public void mouseEntered(MouseEvent e) {
			for(Pino pinos : listaPlayer) {
				pinos.mouseEntered(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			for(Pino pinos : listaPlayer) {
				pinos.mouseExited(e);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			for(Pino pinos : listaPlayer) {
				pinos.mouseClicked(e);
			}
			
			System.out.println("\nClicou: " + e.getX() + "," + e.getY() + "\n");

		}
		
	}
	
	
	public void inicializaImagens() {
		ImageIcon iconeImagemFundo = new ImageIcon("Imagens//fundoMadeira.jpg");
		fundo = iconeImagemFundo.getImage();
		
		ImageIcon iconeImagemTab = new ImageIcon("Imagens//surakartaTabuleiro.png");
		this.altura = iconeImagemTab.getIconHeight() + 39;
		this.largura = iconeImagemTab.getIconWidth() + 16;
		tabuleiro = iconeImagemTab.getImage();
		
		this.setBounds(0, 0, largura, altura);
	}
	
	public void inicializaTabuleiro(String player) {
		//posicoes dos pinos no tabuleiro
		int dist = 37, linhaA = 0, linhaB = 0;
		
		for(int y=0; y<posicaoTabuleiro.length; y++){
			for(int x=0; x<posicaoTabuleiro[y].length; x++) {
				posicaoTabuleiro[y][x] = new Point(108+x*dist,108+y*dist);
			}
		}

		for(Point[] point : posicaoTabuleiro){
			for(Point pos: point) {
				System.out.print("{" + pos.x + "," + pos.y + "}, ");
			}
			System.out.println("");
		}
		
		/* posicaoTabuleiro
		{{108,108}, {145,108}, {182,108}, {219,108}, {256,108}, {293,108}, 
		 {108,145}, {145,145}, {182,145}, {219,145}, {256,145}, {293,145}, 
		 {108,182}, {145,182}, {182,182}, {219,182}, {256,182}, {293,182}, 
		 {108,219}, {145,219}, {182,219}, {219,219}, {256,219}, {293,219}, 
		 {108,256}, {145,256}, {182,256}, {219,256}, {256,256}, {293,256}, 
		 {108,293}, {145,293}, {182,293}, {219,293}, {256,293}, {293,293}} 
		 */
		
		//posicoes iniciais dos pinos do p1	ou p2	
		if(player.toLowerCase().equals("p1")) {
			linhaA = 4;
			linhaB = 5;
		} else if(player.toLowerCase().equals("p2")) {
			linhaA = 0;
			linhaB = 1;
		}
		
		for(int i=0; i<pinosTabuleiro.length; i++){
			for(int j=0; j<pinosTabuleiro[i].length; j++) {
					
				if(i == linhaA || i == linhaB) {
					pinosTabuleiro[i][j] = 1;
				}else {
					pinosTabuleiro[i][j] = 0;
				}

			}
		}
		
		for(int[] i : pinosTabuleiro){
			for(int j: i) {
				System.out.print("{" + j + "}, ");
			}
			System.out.println("");
		}
		
		/* 
		  posicoes iniciais p1
		{{1}, {1}, {1}, {1}, {1}, {1}, 
		 {1}, {1}, {1}, {1}, {1}, {1}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}}
		 
		 * 
		 * posicoes iniciais p2
		{{0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {1}, {1}, {1}, {1}, {1}, {1}, 
		 {1}, {1}, {1}, {1}, {1}, {1}}
		 */
		
		//cria os pinos nas posicoes iniciais de p1 ou p2
		for(int i=0; i<pinosTabuleiro.length; i++){
			for(int j=0; j<pinosTabuleiro[i].length; j++) {
				if(pinosTabuleiro[i][j] == 1) {
					int x = posicaoTabuleiro[i][j].x;
					int y = posicaoTabuleiro[i][j].y;
					
					listaPlayer.add(new Pino(this, x, y, player));
				}
			}
		}
		
	}
		
	public void atualizaPinosTabuleiro() {
		for(int i=0; i<pinosTabuleiro.length; i++){
			for(int j=0; j<pinosTabuleiro[i].length; j++) {
				
				pinosTabuleiro[i][j] = 0;
				int x = posicaoTabuleiro[i][j].x;
				int y = posicaoTabuleiro[i][j].y;
				
				for(Pino pino : listaPlayer) {									
					if(pino.getPinoPos().x == x && pino.getPinoPos().y == y) {
						pinosTabuleiro[i][j] = 1;
						System.out.println("   Achou uma pino na posicao [" + i + "," + j + "]");    //apagar
					}
				}
			}
			System.out.println("");
  		}
		System.out.println("");
		
		
		for(int[] i : pinosTabuleiro){
			for(int j: i) {
				System.out.print("{" + j + "}, ");
			}
			System.out.println("");
		}
		
	}
	
	public class AtualizaTabuleiro extends Thread {	
		public void run() {
			while(true) {
				try { Thread.sleep(500); } catch (InterruptedException e) {}
				atualizaPinosTabuleiro();
			}	
		}
	}

}


