import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import org.omg.CORBA_2_3.portable.OutputStream;

import java.awt.Color;

public class dds1 extends JFrame implements Runnable, MouseListener, ActionListener {

	private JPanel contentPane;
    private Cursor c1,c2;
	private Thread t;
	private JLabel img[];
	private JLabel lsore;
	private int score=0;
	private AudioClip a1;
	public AudioClip a2;
	private JMenuItem mntmNewMenuItem,mntmOff;
	private JMenuItem menuItem;
	private Timer timer;
	private JProgressBar progressBar;
	private boolean threadstarted=false;
	private Score []sc;
	
	private File file;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dds1 frame = new dds1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static class Score implements Serializable{
		String id;
		int score;
	    Score(){
	    	id="匿名";
	    	score=0;
	    	
	    }
	}

	/**
	 * Create the frame.
	 */
	public dds1() {
		setAlwaysOnTop(true);
		setTitle("\u6253\u5730\u9F20\u6E38\u620F");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 300, 500, 538);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u83DC\u5355");
		menuBar.add(mnNewMenu);
		
		menuItem = new JMenuItem("\u5F00\u59CB");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(threadstarted==false) {
					t.start();
					threadstarted=true;
				}
					
				else {
					t.resume();
				}
				timer.start();
				menuItem.setEnabled(false);
				progressBar.setString(null);
			}
		});
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u505C\u6B62");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.suspend();
				timer.stop();
			}
		});
		mnNewMenu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u7EE7\u7EED");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.resume();
				timer.start();
			}
		});
		mnNewMenu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u9000\u51FA");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(menuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("\u97F3\u4E50\u63A7\u5236");
		menuBar.add(mnNewMenu_1);
		
		 mntmNewMenuItem = new JMenuItem("On");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a2.loop();
				mntmNewMenuItem.setEnabled(false);
				mntmOff.setEnabled(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		 mntmOff = new JMenuItem("Off");
		 mntmOff.setEnabled(false);
		mntmOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a2.stop();
				mntmOff.setEnabled(false);
				mntmNewMenuItem.setEnabled(true);
			}
		});
		mnNewMenu_1.add(mntmOff);
		
		JMenuItem menuItem_5 = new JMenuItem("\u9009\u62E9\u80CC\u666F\u97F3\u4E50");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mChoose mc=new mChoose(dds1.this);
				mc.setModal(true);
				mc.setLocation(200,200);
				mc.show();
			}
		});
		mnNewMenu_1.add(menuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("\u5E2E\u52A9");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("\u82F1\u96C4\u699C");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hero heros=new Hero();
				for(int i=0;i<sc.length;i++) {
					heros.taheros.append("英雄大名："+sc[i].id+"  成绩"+sc[i].score+"\n");
				}
				
				heros.setModal(true);
				heros.show();
				
				
			}
		});
		mnNewMenu_2.add(menuItem_4);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5173\u4E8E");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myAbout myAbout=new myAbout();
				myAbout.setModal(true);
				myAbout.setLocation(200, 200);
				myAbout.show();
			}
		});
		mnNewMenu_2.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img1.setBounds(337, 241, 63, 67);
		contentPane.add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img2.setBounds(99, 323, 63, 67);
		contentPane.add(img2);
		
		JLabel img3 = new JLabel("");
		img3.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img3.setBounds(190, 346, 63, 67);
		contentPane.add(img3);
		
		JLabel img4 = new JLabel("");
		img4.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img4.setBounds(390, 287, 63, 67);
		contentPane.add(img4);
		
		JLabel img5 = new JLabel("");
		img5.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img5.setBounds(359, 358, 63, 67);
		contentPane.add(img5);
		
		JLabel img6 = new JLabel("");
		img6.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img6.setBounds(242, 404, 63, 67);
		contentPane.add(img6);
		
		JLabel img7 = new JLabel("");
		img7.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img7.setBounds(252, 289, 63, 67);
		contentPane.add(img7);
		
		JLabel img8 = new JLabel("");
		img8.setIcon(new ImageIcon(this.getClass().getResource("mouse.png")));
		img8.setBounds(242, 252, 63, 67);
		contentPane.add(img8);
		
		lsore = new JLabel("\u60A8\u7684\u5F97\u5206\u662F0\u5206");
		lsore.setBounds(32, 21, 146, 15);
		contentPane.add(lsore);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(10);
		progressBar.setBorder(new TitledBorder(null, "\u6E38\u620F\u8FDB\u5EA6", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, Color.RED));
		progressBar.setStringPainted(true);
		progressBar.setBounds(32, 49, 146, 35);
		contentPane.add(progressBar);
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("background.jpg")));
		background.setBounds(0, 0, 500, 500);
		contentPane.add(background);
		
		img=new JLabel[8];
		img[0]=img1;
		img[1]=img2;
		img[2]=img3;
		img[3]=img4;
		img[4]=img5;
		img[5]=img6;
		img[6]=img7;
		img[7]=img8;
		
		for(int i=0;i<8;i++)
		{
			img[i].setVisible(false);
			img[i].addMouseListener(this);
		}
        
		c1=Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.png")), new Point(20,20),"c1");
		c2=Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon1.png")), new Point(20,20),"c2");
		
		a1=Applet.newAudioClip(this.getClass().getResource("响声.wav"));
		a2=Applet.newAudioClip(this.getClass().getResource("背景音乐.wav"));
		
		   t=new Thread(this);	
           //t.start();
		   timer=new Timer(1000,this);
		   
		   file=new File(".\\hero.data");
		   if(!file.exists()) {
			   try {
				file.createNewFile();
				sc=new Score[6];
				for(int i=0;i<sc.length;i++)
					sc[i]=new Score();
				FileOutputStream os=new FileOutputStream(file);
				ObjectOutputStream oos=new ObjectOutputStream(os);
				oos.writeObject(sc);
				oos.close();
				os.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		   }
		   else {
			   FileInputStream is;
			try {
				is = new FileInputStream(file);
				ObjectInputStream ois=new ObjectInputStream(is);
				sc=(Score[])(ois.readObject());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   
		   }
	}
        
	@Override
	public void run() {
		// TODO Auto-generated method stub
	  while(true){
		try {
			Thread.sleep(800);
			int index=(int)(Math.random()*8);
			if(!img[index].isShowing()) {
				img[index].setVisible(true);
				Thread.sleep(800);
				img[index].setVisible(false);
			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		a1.play();
		JLabel l=(JLabel)(e.getSource());
		if(l.isShowing()) {
			l.setVisible(false);
			lsore.setText("你的得分是"+(++score)+"分");
         	t.interrupt();	
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(c1);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(c2);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int value=progressBar.getValue();
		if(value<10)
			progressBar.setValue(++value);
		else {
			JOptionPane.showMessageDialog(this,"游戏结束，得分是："+score,"提示",JOptionPane.WARNING_MESSAGE);
			menuItem.setEnabled(true);
			timer.stop();
			t.suspend();
			
			boolean findhero=false;
			int heroid=0;
			
			for(int i=0;i<sc.length;i++) {
				if(score>sc[i].score) {
					findhero=true;
					heroid=i;
					break;
				}
			}
			if(findhero) {
				for(int j=sc.length-1;j<heroid;j--) {
					sc[j].id=sc[j-1].id;
					sc[j].score=sc[j-1].score;
				}
				sc[heroid].id="匿名";
				sc[heroid].score=score;
				
				String heroname=JOptionPane.showInputDialog("请留下您的大名：");
				if(heroname!=null)sc[heroid].id=heroname;
				OutputStream os = null;
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(os);
					oos.writeObject(sc);
					oos.close();
					os.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			score=0;
			progressBar.setValue(0);
			progressBar.setString("游戏结束了");
		}
	}
}

