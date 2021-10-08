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
	private String player = "";
	private int altura;
	private int largura;
	private Point posicaoTabuleiro[][] = new Point[6][6];
	private int pinosTabuleiro[][] = new int[6][6];
	private Pino matrizPinos[][] = new Pino[6][6];

	ArrayList<Pino> listaPlayer = new ArrayList<>();

	private Timer timer;
	
	public PainelTabuleiro(String player) {
		this.player = player;
		setFocusable(true);
		setDoubleBuffered(true);
		
		inicializaImagens();
		
		inicializaTabuleiro(player);
		
		addMouseListener(new ControleMouseAdapter());
		
		new movePinosTabuleiro().start();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(fundo, 0, 0, null);
		grafico.drawImage(tabuleiro, 0, 0, null);
		
		for(Pino[] i : matrizPinos){										//
			for(Pino pino: i) {
				if(pino != null) {
					grafico.drawImage(pino.getImagem(), pino.getX(), pino.getY(), this);
				}
			}
		}
		
		for(Pino[] i : matrizPinos){										//
			for(Pino pino: i) {
				if(pino != null) {
					if(pino.mouseClicou()) {
						grafico.drawImage(pino.getImagem(), pino.getX(), pino.getY(), this);
					}
				}
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Pino[] i : matrizPinos){										//
			for(Pino pino: i) {
				if(pino != null) {
					pino.update();
				}
			}
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
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						pino.mouseReleased(e);
					}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						pino.mousePressed(e);
					}
				}
			}
		}
						
		@Override
		public void mouseEntered(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						pino.mouseEntered(e);
					}
				}
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						pino.mouseExited(e);
					}
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						pino.mouseClicked(e);
					}
				}
			}
			System.out.println("\nClicou: " + e.getX() + "," + e.getY() + "\n");  //apagar
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
		// posicoes dos pinos no tabuleiro
		int dist = 37, linhaA = 0, linhaB = 0;
		
		for(int y=0; y<posicaoTabuleiro.length; y++){
			for(int x=0; x<posicaoTabuleiro[y].length; x++) {
				posicaoTabuleiro[y][x] = new Point(108+x*dist,108+y*dist);
			}
		}

		for(Point[] point : posicaoTabuleiro){                          //
			for(Point pos: point) { 									//
				System.out.print("{" + pos.x + "," + pos.y + "}, ");    // apagar 
			}															//
			System.out.println("");										//
		}																//
		
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
		
		for(int i=0; i<matrizPinos.length; i++){
			for(int j=0; j<matrizPinos[i].length; j++) {
				
				int x = posicaoTabuleiro[i][j].x;
				int y = posicaoTabuleiro[i][j].y;
					
				if(i == linhaA || i == linhaB) {
					matrizPinos[i][j] = new Pino(this, x, y, player);
				}else {
					matrizPinos[i][j] = null;
				}

			}
		}
		
		for(Pino[] i : matrizPinos){										//
			for(Pino posPino: i) {									//
				if(posPino != null) {								//
					System.out.print("{" + 1 + "}, ");				//	
				} else if(posPino == null){							// Apagar
					System.out.print("{" + 0 + "}, ");				//
				}													//
			}														//
			System.out.println("");									//
		}															//
		
		/*  
		 posicoes iniciais p1
		{{0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {1}, {1}, {1}, {1}, {1}, {1}, 
		 {1}, {1}, {1}, {1}, {1}, {1}}
		 
		  posicoes iniciais p2
		{{1}, {1}, {1}, {1}, {1}, {1}, 
		 {1}, {1}, {1}, {1}, {1}, {1}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}, 
		 {0}, {0}, {0}, {0}, {0}, {0}}
		 */
	}
		
	public void atualizaPinosTabuleiro() {		
		for(Pino[] i : matrizPinos){
			for(Pino posPino: i) {
				if(posPino !=null) {
					System.out.print("{" + 1 + "}, ");
				} else {
					System.out.print("{" + 0 + "}, ");
				}
			}
			System.out.println("");
		}
		System.out.println("");

	}
	
	public void movePinos (Thread t) {
		//
	}
	
	public class movePinosTabuleiro extends Thread {	
		public void run() {
			while(true) {
				try {
					sleep(30);
				
					int raio = 15;
					int dist = 37;
					for(int i=0; i<matrizPinos.length; i++){
						for(int j=0; j<matrizPinos[i].length; j++) {
	
							if(matrizPinos[i][j] != null) {
								if(matrizPinos[i][j].mouseClicou()) {
															
									int xInicialPino = posicaoTabuleiro[i][j].x;
									int yInicialPino = posicaoTabuleiro[i][j].y;
										
									while(matrizPinos[i][j].mouseClicou()) {
										System.out.println("esperando soltar pino");
									}
									
									System.out.print("  SOLTOU");
	
									int x = matrizPinos[i][j].getMousePos().x;
									int y = matrizPinos[i][j].getMousePos().y;
	
									System.out.println("     " + x + "," + y);
									
									boolean pinoMudouParaPosicaoValida = false;
									for(Point[] point : posicaoTabuleiro){                          
										for(Point pos: point) { 					
											int distCima = pos.y - raio; 
							 				int distBaixo = pos.y + raio;
											int distEsquerda = pos.x - raio; 
											int distDireita = pos.x + raio;
												
											if(x >= distEsquerda && x <= distDireita && y >= distCima && y<= distBaixo) {
												int novaLinha = (pos.x - 108)/dist;
												int novaColuna = (pos.y - 108)/dist;

												System.out.println("soltou na posição [" + novaLinha + "," + novaColuna + "]"); //apagar
												
												if(matrizPinos[novaLinha][novaColuna] == null) {
													System.out.println("posiçao vazia");
													pinoMudouParaPosicaoValida = true;
													matrizPinos[i][j].movePino(pos.x, pos.y);
													matrizPinos[novaLinha][novaColuna] = matrizPinos[i][j];
													matrizPinos[i][j] = null;
												} else if (matrizPinos[novaLinha][novaColuna] != null) {
													System.out.print("posiçao ocupada");

													if(matrizPinos[i][j].getPlayer().toLowerCase().equals(player)) {
														System.out.println(" por um ALIADO");

														matrizPinos[i][j].movePino(xInicialPino, yInicialPino);
													} else if (!matrizPinos[i][j].getPlayer().toLowerCase().equals(player)) {
														System.out.println(" por um ADVERSÁRIO");

														pinoMudouParaPosicaoValida = true;
														matrizPinos[i][j].movePino(pos.x, pos.y);
														matrizPinos[novaLinha][novaColuna] = matrizPinos[i][j];
														matrizPinos[i][j] = null;
													}
												}
												
													
											} 
										}															
									}
									if (!pinoMudouParaPosicaoValida) {
										matrizPinos[i][j].movePino(xInicialPino, yInicialPino);
									}
								}				
							}
						}
					}
				
				 } catch (InterruptedException e) {}
				
				
				//atualizaPinosTabuleiro();
			}	
		}
	}

}


