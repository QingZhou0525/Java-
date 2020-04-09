import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class myAbout extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			myAbout dialog = new myAbout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public myAbout() {
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 324, 267);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u7248\u672C\uFF1A1.1.0");
		label.setBounds(10, 30, 134, 15);
		contentPanel.add(label);
		{
			JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A\u66F9\u5578\u52C7");
			label_1.setBounds(10, 68, 94, 15);
			contentPanel.add(label_1);
		}
		{
			JLabel label_1 = new JLabel("\u5B66\u53F7\uFF1A16013208");
			label_1.setBounds(10, 105, 107, 15);
			contentPanel.add(label_1);
		}
		{
			JLabel label_1 = new JLabel("\u73ED\u7EA7\uFF1A\u8F6F\u4EF6\u5DE5\u7A0B\u4E00\u73ED");
			label_1.setBounds(10, 142, 117, 15);
			contentPanel.add(label_1);
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("about1.jpg")));
			lblNewLabel.setBounds(136, 10, 161, 175);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myAbout.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
