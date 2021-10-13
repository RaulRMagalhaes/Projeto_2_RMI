package Interface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.BreakIterator;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import RMI.Cliente;
import RMI.ClienteIF;
import player.Pino;

public class PainelTabuleiro extends JPanel implements ActionListener {
	
	private Image fundo, tabuleiro;
	private Cliente player = null;
	private String tipoDePlayer = "";
	private int altura;
	private int largura;
	private Point posicaoTabuleiro[][] = new Point[6][6];
	private String pinosTabuleiro[][] = new String[6][6];
	private Pino matrizPinos[][] = new Pino[6][6];

	ArrayList<Pino> listaPlayer = new ArrayList<>();

	private Timer timer;
	
	public PainelTabuleiro(Cliente player) throws RemoteException {
		this.player = player;
		
		this.tipoDePlayer = this.player.getTipoDePlayer();
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		inicializaImagens();
				
		inicializaTabuleiro(this.tipoDePlayer);
		
		addMouseListener(new ControleMouseAdapter());
		
		new movePinosTabuleiro().start();
		new movePinosOponente(this).start();
		//new exibindoMatrizDePinos().start();    //APAGAR
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(fundo, 0, 0, null);
		grafico.drawImage(tabuleiro, 0, 0, null);
		
		desenhaPinos(grafico, matrizPinos);
				
		g.dispose();
	}
	
	private void desenhaPinos(Graphics2D grafico, Pino[][] matrizPinos) {
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
					try {
						if(pino != null && pino.getPlayer().equals(player.getTipoDePlayer())) {
							pino.mouseReleased(e);
						}
					} catch (RemoteException e1) {e1.printStackTrace();}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						try {
							if(pino != null && pino.getPlayer().equals(player.getTipoDePlayer())) {
								pino.mousePressed(e);
							}
						} catch (RemoteException e1) {e1.printStackTrace();}
					}
				}
			}
		}
						
		@Override
		public void mouseEntered(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						try {
							if(pino != null && pino.getPlayer().equals(player.getTipoDePlayer())) {
								pino.mouseEntered(e);
							}
						} catch (RemoteException e1) {e1.printStackTrace();}
					}
				}
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						try {
							if(pino != null && pino.getPlayer().equals(player.getTipoDePlayer())) {
								pino.mouseExited(e);
							}
						} catch (RemoteException e1) {e1.printStackTrace();}
					}
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			for(Pino[] i : matrizPinos){										//
				for(Pino pino: i) {
					if(pino != null) {
						try {
							if(pino != null && pino.getPlayer().equals(player.getTipoDePlayer())) {
								pino.mouseClicked(e);
							}
						} catch (RemoteException e1) {e1.printStackTrace();}
					}
				}
			}
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
	
	public void inicializaTabuleiro(String tipoDePlayer) {
		// posicoes dos pinos no tabuleiro
		int dist = 37, linhaA = 0, linhaB = 0;
		
		for(int y=0; y<posicaoTabuleiro.length; y++){
			for(int x=0; x<posicaoTabuleiro[y].length; x++) {
				posicaoTabuleiro[y][x] = new Point(108+x*dist,108+y*dist);
			}
		}
		
		/*
		for(Point[] point : posicaoTabuleiro){                          //
			for(Point pos: point) { 									//
				System.out.print("{" + pos.x + "," + pos.y + "}, ");    // apagar 
			}															//
			System.out.println("");										//
		}
		*/																//
		
		/* posicaoTabuleiro
		{{108,108}, {145,108}, {182,108}, {219,108}, {256,108}, {293,108}, 
		 {108,145}, {145,145}, {182,145}, {219,145}, {256,145}, {293,145}, 
		 {108,182}, {145,182}, {182,182}, {219,182}, {256,182}, {293,182}, 
		 {108,219}, {145,219}, {182,219}, {219,219}, {256,219}, {293,219}, 
		 {108,256}, {145,256}, {182,256}, {219,256}, {256,256}, {293,256}, 
		 {108,293}, {145,293}, {182,293}, {219,293}, {256,293}, {293,293}} 
		 */
		
		//posicoes iniciais dos pinos do p1	ou p2	
		if(tipoDePlayer.toLowerCase().equals("p1")) {
			linhaA = 4;
			linhaB = 5;
		} else if(tipoDePlayer.toLowerCase().equals("p2")) {
			linhaA = 0;
			linhaB = 1;
		}
		
		for(int i=0; i<matrizPinos.length; i++){
			for(int j=0; j<matrizPinos[i].length; j++) {
				
				int x = posicaoTabuleiro[i][j].x;
				int y = posicaoTabuleiro[i][j].y;
					
				if(i == linhaA || i == linhaB) {
					matrizPinos[i][j] = new Pino(this, x, y, tipoDePlayer);
				}else {
					matrizPinos[i][j] = null;
				}

			}
		}
		
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
		for(int i=0; i<pinosTabuleiro.length; i++){
			for(int j=0; j<pinosTabuleiro[i].length; j++) {
				if(matrizPinos[i][j] != null) {
					if(matrizPinos[i][j].getPlayer().toLowerCase().equals("p1")) {
						pinosTabuleiro[i][j] = "p1";
					} else if (matrizPinos[i][j].getPlayer().toLowerCase().equals("p2")) {
						pinosTabuleiro[i][j] = "p2";
					}
				} else {
					pinosTabuleiro[i][j] = " 0";
				}
			}
		}
		
		try {
			this.player.setMatrizPinos(this.pinosTabuleiro);
			this.player.setMatrizPinosTabuleiro(this.matrizPinos);
		} catch (RemoteException e) {e.printStackTrace();}
	}
	
	public class movePinosTabuleiro extends Thread {	
		public void run() {
			while(true) {
				try {
					sleep(10);
				
					int raio = 15;
					int dist = 37;
					for(int i=0; i<matrizPinos.length; i++){
						for(int j=0; j<matrizPinos[i].length; j++) {
	
							if(matrizPinos[i][j] != null) {
								if(matrizPinos[i][j].mouseClicou()) {
															
									int xInicialPino = posicaoTabuleiro[i][j].x;
									int yInicialPino = posicaoTabuleiro[i][j].y;
										
									while(matrizPinos[i][j].mouseClicou()) {
										sleep(100);
										//System.out.print(" ");
									}
									
									//System.out.print("            ");
	
									int x = matrizPinos[i][j].getMousePos().x;
									int y = matrizPinos[i][j].getMousePos().y;
	
									//System.out.println("     " + x + "," + y);
									
									boolean pinoMudouParaPosicaoValida = false;
									for(Point[] point : posicaoTabuleiro){                          
										for(Point pos: point) { 					
											int distCima = pos.y - raio; 
							 				int distBaixo = pos.y + raio;
											int distEsquerda = pos.x - raio; 
											int distDireita = pos.x + raio;
												
											if(x >= distEsquerda && x <= distDireita && y >= distCima && y<= distBaixo) {
												int novaColuna = (pos.x - 108)/dist;
												int novaLinha = (pos.y - 108)/dist;

												//System.out.println("soltou na posição [" + novaColuna + "," + novaLinha + "]"); //apagar
												
												if(matrizPinos[novaLinha][novaColuna] == null) {
													//System.out.println("posiçao vazia");
													pinoMudouParaPosicaoValida = true;
													matrizPinos[i][j].movePino(pos.x, pos.y);
													matrizPinos[novaLinha][novaColuna] = matrizPinos[i][j];
													matrizPinos[i][j] = null;
												} else if (matrizPinos[novaLinha][novaColuna] != null) {
													//System.out.print("posiçao ocupada");

													if(matrizPinos[novaLinha][novaColuna].getPlayer().toLowerCase().equals(tipoDePlayer)) {
														//System.out.println(" por um ALIADO");

														matrizPinos[i][j].movePino(xInicialPino, yInicialPino);
													} else if (!matrizPinos[novaLinha][novaColuna].getPlayer().toLowerCase().equals(tipoDePlayer)) {
														//System.out.println(" por um ADVERSÁRIO");

														pinoMudouParaPosicaoValida = true;
														matrizPinos[novaLinha][novaColuna] = null;
														player.getOponente().removePino(novaLinha, novaColuna);
														matrizPinos[i][j].movePino(pos.x, pos.y);
														matrizPinos[novaLinha][novaColuna] = matrizPinos[i][j];
														matrizPinos[i][j] = null;
														
														
														player.setPontos(player.getPontos() + 1);
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
				
				 } catch (InterruptedException | RemoteException e) {break;}
				
				atualizaPinosTabuleiro();
			}	
		}
	}
	
	
	public class movePinosOponente extends Thread {	
		
		JPanel painel = null; 
		
		public movePinosOponente(JPanel painel) {
			this.painel = painel;
		}
		
		public void run() {
			while(true) {
				try {
					sleep(10);
					if(player.getOponente() != null) {
						sleep(120);
						
						String[][] matrizPinosOponente = player.getOponente().getMatrizPinos();
						String tipoDePlayerOponente = player.getOponente().getTipoDePlayer();
						
						for(int i=0; i<matrizPinos.length; i++){
							for(int j=0; j<matrizPinos[i].length; j++) {
								if(matrizPinosOponente[i][j].equals(tipoDePlayerOponente) && matrizPinos[i][j] == null) {
									int x = posicaoTabuleiro[i][j].x;
									int y = posicaoTabuleiro[i][j].y;
									matrizPinos[i][j] = new Pino(painel, x, y, tipoDePlayerOponente);
								}else if(matrizPinosOponente[i][j].equals(" 0") && matrizPinos[i][j] != null){
									if(matrizPinos[i][j].getPlayer().equals(tipoDePlayerOponente)) {
										matrizPinos[i][j] = null;
									}
								}
							}
						}
					}
				} catch (RemoteException | InterruptedException e) {e.printStackTrace(); break;}
			}
		}
	}
	
	@Deprecated
	public class exibindoMatrizDePinos extends Thread {	
		public void run() {
			while(true) {
				try {sleep(1000);} catch (InterruptedException e) {}
				
				try {
					for(String[] i : player.getMatrizPinos()){										
						for(String Pino: i) {									
								System.out.print("|" + Pino + "| ");					
						}														
						System.out.println("");									
					}
													
					if(player.getOponente() != null) {
						System.out.println("\n        ---OPONENTE---\n");	
						for(String[] j : player.getOponente().getMatrizPinos()){										
							for(String Pino: j) {									
									System.out.print("|" + Pino + "| ");					
							}														
							System.out.println("");									
						}
					}
					System.out.println("///////////////////////////////");									

					
				} catch (RemoteException e) {e.printStackTrace();}	
			}
		}
	}
}


