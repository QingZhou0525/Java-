import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Enemy {
	private int x, y;
	private int xstep, ystep;
	private int flagx;
	private int picid;
	private Image enemypic;
	private Image[] bombpic;
	private boolean died;
	List<EnemyBullet> enemybullets;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		ystep = 5;
		xstep = -2;
		flagx = 1;
		picid = 0;
		died = false;
		enemypic = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("e1_0.png"));

		bombpic = new Image[6];
		for (int i = 0; i < bombpic.length; i++) {
			bombpic[i] = Toolkit.getDefaultToolkit()
					.getImage(this.getClass().getResource("bomb_enemy_" + i + ".png"));
		}
		
		enemybullets=new LinkedList<EnemyBullet>();
	}

	void DrawEnemy(Graphics g, GamePanel p) {
		if (died) {
			g.drawImage(bombpic[(++picid) % 6], x, y, p);
		} else {
			g.drawImage(enemypic, x, y, p);
		}
		
		Iterator<EnemyBullet> iter = enemybullets.iterator();
		while (iter.hasNext()) {
			iter.next().DrawEnemyBullet(g,p);
		}
	}
	
	void fire() {
		int x=this.x+56/2-10/2;
		int y=this.y+75;
		EnemyBullet eb=new EnemyBullet(x,y);
		enemybullets.add(eb);
	}

	void UpdateEnemy_y() {
		y = y + ystep;
		
		Iterator<EnemyBullet> iter = enemybullets.iterator();
		while (iter.hasNext()) {
			EnemyBullet enemybullet=iter.next();
			enemybullet.UpdateEnemyBullet();
			if(enemybullet.y>480||enemybullet.isDied()) {
				iter.remove();
			}
			
			}
		if(Math.random()*40>38)
			fire();
	}

	void UpdateEnemy_x() {
		x = x + xstep * flagx;
	}

	void initEnemy(int x, int y) {
		this.x = x;
		this.y = y;
		died = false;
		picid = 0;
	}

	int getx() {
		return x;
	}

	int gety() {
		return y;
	}

	void setDied() {
		died = true;
	}

	boolean getdied() {
		return died;
	}

	void changeflag() {
		flagx = flagx * (-1);
	}

	Rectangle getBounds() {
		return new Rectangle(x, y, 56, 75);
	}
}
