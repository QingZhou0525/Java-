import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PlaneGame extends JFrame {

	private JPanel contentPane;
	GamePanel gamePanel;
	private JButton btnstop;
	private JButton btnstart;
	private JButton btnpause;
	private JButton btnresume;
	private JLabel lbscore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneGame frame = new PlaneGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlaneGame() {
		setTitle("\u96F7\u9706\u6218\u673A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		
		
		JButton btnplay = new JButton("\u64AD\u653E\u80CC\u666F\u97F3\u4E50");
		btnplay.setFocusable(false);
		btnplay.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
		btnplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneGame.this.gamePanel.backclip.play();
			}
		});
		btnplay.setBounds(340, 61, 95, 23);
		contentPane.add(btnplay);
		
		btnstop = new JButton("\u6682\u505C\u80CC\u666F\u97F3\u4E50");
		btnstop.setFocusable(false);
		btnstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneGame.this.gamePanel.backclip.stop();
			}
		});
		btnstop.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
		btnstop.setBounds(340, 103, 94, 23);
		contentPane.add(btnstop);
		
		btnstart = new JButton("\u5F00\u59CB\u6E38\u620F");
		btnstart.setFocusable(false);
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneGame.this.gamePanel.tr.start();
			}
		});
		btnstart.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
		btnstart.setBounds(340, 363, 94, 23);
		contentPane.add(btnstart);
		
		btnpause = new JButton("\u6682\u505C\u6E38\u620F");
		btnpause.setFocusable(false);
		btnpause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneGame.this.gamePanel.tr.suspend();
			}
		});
		btnpause.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
		btnpause.setBounds(341, 396, 94, 23);
		contentPane.add(btnpause);
		
		btnresume = new JButton("\u7EE7\u7EED\u6E38\u620F");
		btnresume.setFocusable(false);
		btnresume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneGame.this.gamePanel.tr.resume();
			}
		});
		btnresume.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
		btnresume.setBounds(340, 429, 94, 23);
		contentPane.add(btnresume);
		
		lbscore = new JLabel("\u4F60\u7684\u5F97\u5206\u662F0\u5206\uFF01");
		lbscore.setBounds(340, 26, 101, 15);
		contentPane.add(lbscore);
		
		gamePanel = new GamePanel(lbscore);
		gamePanel.setBounds(10, 10, 320, 480);
		contentPane.add(gamePanel);
	}
}
