package player;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;


import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Pino {
	private int x, y;
	private int dx, dy;
	private String player = ""; 

	
	private JPanel janela;
	
	private ImageIcon iconeImagem;
	private int altura, largura;
	
	private boolean mousePrecionado = false;
	private boolean mouseNatela = false;
	private boolean mouseClicou = false;
	private Point mousePos = null;
	private Point pinoPos = new Point();
	
	public Pino(JPanel janela, int x, int y, String player) {
		this.janela = janela;
		this.dx = x;
		this.dy = y;
		this.player = player;
				
		if(player.toLowerCase().equals("p1")) {
			iconeImagem = new ImageIcon("Imagens//usuarioAzul.png");
			this.altura = iconeImagem.getIconHeight();
			this.largura = iconeImagem.getIconWidth();
		}else if(player.toLowerCase().equals("p2")) {
			iconeImagem = new ImageIcon("Imagens//usuarioVermelho.png");
			this.altura = iconeImagem.getIconHeight();
			this.largura = iconeImagem.getIconWidth();
		}
	

		
		new Mover().start();;
	}
	
	
	public void update() {
		x = dx - largura/2;
		y = dy - altura/2;
		
		pinoPos.setLocation(dx,dy);
	}
		
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void movePino(int x, int y) {
		dx = x;
		dy = y;
	}
	
	public Image getImagem() {
		return iconeImagem.getImage();
	}
	
	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public boolean mouseClicou() {
		return mouseClicou;
	}
	
	public Point getMousePos() {
		return mousePos;
	}
	
	public Point getPinoPos() {
		return pinoPos;
	}

	public void mouseReleased(MouseEvent e) {
		mouseClicou = false;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if ((e.getX() > x) && (e.getX() < x+largura) && (e.getY() > y) && (e.getY() < y+altura)) {
			mouseClicou = true;
		}

	}
	
	public void mouseEntered(MouseEvent e) {
		mouseNatela = true; 
	}
	
	public void mouseExited(MouseEvent e) {
		mouseNatela = false; 
	}
	
	public void mouseClicked(MouseEvent e) {
		//
	}
	
	public class Mover extends Thread {
		public void run() {
			while(true) {
				try { sleep(5); } catch (InterruptedException e) {}
				
				if(mouseClicou && mouseNatela) {
					mousePos =  janela.getMousePosition();
					dx = mousePos.x ;					
					dy = mousePos.y ;
				}	
			}
		}
	}
	
}
