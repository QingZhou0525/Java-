import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ChatClient extends JFrame {

	private JPanel contentPane;
	private JTextField tfip;
	private JButton btnconn;
	private JButton btndisconn;
	private JTextField tfname;
	private JTextField tfcontent;
	private Socket s;
	private DataOutputStream dos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
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
	public ChatClient() {
		setAlwaysOnTop(true);
		setTitle("\u804A\u5929");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP\u5730\u5740\uFF1A");
		lblIp.setBounds(10, 10, 54, 15);
		contentPane.add(lblIp);
		
		tfip = new JTextField();
		tfip.setBounds(57, 7, 155, 21);
		contentPane.add(tfip);
		tfip.setColumns(10);
		
		btnconn = new JButton("\u8FDE\u63A5");
		btnconn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClient.this.connect();
			}
		});
		btnconn.setBounds(238, 6, 93, 23);
		contentPane.add(btnconn);
		
		btndisconn = new JButton("\u65AD\u5F00");
		btndisconn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btndisconn.setBounds(341, 6, 93, 23);
		contentPane.add(btndisconn);
		
		JTextArea ta = new JTextArea();
		ta.setBounds(10, 35, 424, 186);
		contentPane.add(ta);
		
		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setBounds(10, 234, 54, 15);
		contentPane.add(label);
		
		tfname = new JTextField();
		tfname.setBounds(44, 231, 104, 21);
		contentPane.add(tfname);
		tfname.setColumns(10);
		
		JLabel label_1 = new JLabel("\uFF1A");
		label_1.setBounds(158, 234, 54, 15);
		contentPane.add(label_1);
		
		tfcontent = new JTextField();
		tfcontent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=tfname.getText()+":"+tfcontent.getText()+"\n";
				ta.append(str);
				
				try {
					dos.writeUTF(str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tfcontent.setText(null);
			}
		});
		tfcontent.setBounds(168, 231, 266, 21);
		contentPane.add(tfcontent);
		tfcontent.setColumns(10);
	}
	
	void connect() {
		try {
			s=new Socket(tfip.getText(),50000);
			JOptionPane.showMessageDialog(this,"已连接到服务器：");
			dos=new DataOutputStream(s.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
