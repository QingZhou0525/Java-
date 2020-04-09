import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.css.Rect;

public class GamePanel extends JPanel implements Runnable, MouseMotionListener, KeyListener, MouseListener {

	/**
	 * Create the panel.
	 */
	private Image bg0, bg1;
	private int bg0y, bg1y;
	private int screenw, screenh;
	public Thread tr;
	private MyPlane myPlane;
	private Enemy[] enemys;
	private int enemynum = 5;
	private int enemy_pos_off = 65;
	private int myPlanelife = 3;
	private int initmyplane_x = 160,initmyplane_y = 380;

	private AudioClip bulletclip,bombclip;
	public AudioClip backclip;
	private JLabel lbscore;
	private int score=0;
	
	public GamePanel(JLabel lbscore) {
		setBounds(new Rectangle(0, 0, 320, 480));
		
		this.lbscore=lbscore;
		
		bg0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("map_0.png"));
		bg1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("map_1.png"));
		bulletclip = Applet.newAudioClip(getClass().getResource("laser.wav"));
		bombclip = Applet.newAudioClip(getClass().getResource("explosion.wav"));
		backclip = Applet.newAudioClip(getClass().getResource("backaudio.wav"));

		screenw = 320;
		screenh = 480;

		bg0y = 0;
		bg1y = -screenh;

		myPlane = new MyPlane(160, 380, myPlanelife);
		enemys = new Enemy[enemynum];
		for (int i = 0; i < enemynum; i++) {
			enemys[i] = new Enemy(i * enemy_pos_off, i * enemy_pos_off - 300);
		}

		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setFocusable(true);

		tr = new Thread(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(bg0, 0, bg0y, this);
		g.drawImage(bg1, 0, bg1y, this);
		myPlane.DrawMyPlane(g, this);
		for (int i = 0; i < enemys.length; i++) {
			enemys[i].DrawEnemy(g, this);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (bg1y >= 0 && bg1y < screenh) {
				bg1y += 10;
				bg0y = bg1y - screenh;
			} else {
				bg0y += 10;
				bg1y = bg0y - screenh;
			}

			if(myPlane.getdied()) {
				myPlane.setlife();
				if(myPlane.isOver()) {
					this.repaint();
					//JOptionPane.showMessageDialog(this, "游戏结束了", "提示", JOptionPane.WARNING_MESSAGE);
					//this.tr.stop();
					int n=JOptionPane.showConfirmDialog(this, "游戏结束了,你的得分是:"+score+"分!是否重新开始？","确认提示框",JOptionPane.YES_NO_CANCEL_OPTION);
					if(n==JOptionPane.YES_OPTION) {
						score=0;
						this.lbscore.setText("你的得分是:0分");
						myPlane.life=myPlanelife;
						
					}else if(n==JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				}
				myPlane.initMyplane(initmyplane_x, initmyplane_y);
			}else {
				myPlane.UpdateMyplane();
			}
			

			for (int i = 0; i < enemys.length; i++) {
				enemys[i].UpdateEnemy_y();
				if (enemys[i].gety() > screenh || enemys[i].getdied()) {
					enemys[i].initEnemy((int) (Math.random() * (enemynum - 1) * enemy_pos_off), 0);
				} else {
					if (enemys[i].getx() < 0 || enemys[i].getx() > 260) {
						enemys[i].changeflag();
					}
					enemys[i].UpdateEnemy_x();
				}
			}

			CollisionDetect();

			this.repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void CollisionDetect() {
		Iterator<Bullet> iter = myPlane.bullets.iterator();
		while (iter.hasNext()) {
			while (myPlane.redy) {
				myPlane.redy = false;
				Bullet bullet = iter.next();
				Rectangle bulletrect = bullet.getBounds();
				for (int i = 0; i < enemys.length; i++) {
					Rectangle enemyrect = enemys[i].getBounds();
					if (bulletrect.intersects(enemyrect)) {
						enemys[i].setDied();
						bullet.setDied();
						bombclip.play();
						this.lbscore.setText("你的得分是:"+(++score)+"分");
						break;
					}
				}
			}
			myPlane.redy = true;
		}

		for (int i = 0; i < enemys.length; i++) {
			Rectangle enemyrect = enemys[i].getBounds();
			Rectangle myplanerect = myPlane.getBounds();
			if (enemyrect.intersects(myplanerect)) {
				myPlane.setDied();
				enemys[i].setDied();
				bombclip.play();
				break;
			}
		}
		
		
		for (int i = 0; i < enemys.length; i++) {
			Iterator<EnemyBullet> iter2 = enemys[i].enemybullets.iterator();
			while (iter2.hasNext()) {
				EnemyBullet enemybullet=iter2.next();
				Rectangle enemybulletrect = enemybullet.getBounds();
				Rectangle myplanerect = myPlane.getBounds();
				if(enemybulletrect.intersects(myplanerect)) {
					myPlane.setDied();
					enemybullet.setDied();
					bombclip.play();
					break;
				}
		}
		}
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		if(x>0&&x<298&&y>0&&y<444)
			 myPlane.setPosition(x,y);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			myPlane.goup();
		}
		if (key == KeyEvent.VK_DOWN) {
			myPlane.godown();
		}
		if (key == KeyEvent.VK_LEFT) {
			myPlane.goleft();
		}
		if (key == KeyEvent.VK_RIGHT) {
			myPlane.goright();
		}
		if (key == KeyEvent.VK_SPACE) {
			myPlane.bullets.add(new Bullet(myPlane.getx() - 5, myPlane.gety() - 40));
			bulletclip.play();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// myPlane.bullets.add(new Bullet(myPlane.getx() - 5, myPlane.gety() - 40));
		myPlane.shot();
		bulletclip.play();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
