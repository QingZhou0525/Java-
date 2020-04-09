import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mChoose extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			mChoose dialog = new mChoose();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public mChoose(JFrame owner) {
		super(owner);
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("\u9009\u62E9\u80CC\u666F\u97F3\u4E50");
		setBounds(100, 100, 256, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox ±≥æ∞“Ù¿÷ = new JComboBox();
		±≥æ∞“Ù¿÷.setModel(new DefaultComboBoxModel(new String[] {"\u80CC\u666F\u97F3\u4E50", "rock"}));
		±≥æ∞“Ù¿÷.setBounds(63, 52, 130, 21);
		contentPanel.add(±≥æ∞“Ù¿÷);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dds1 dds=(dds1)mChoose.this.getOwner();
						dds.a2.stop();
						dds.a2=Applet.newAudioClip(this.getClass().getResource(±≥æ∞“Ù¿÷.getSelectedItem()+".wav"));
					    dds.a2.loop();
						mChoose.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mChoose.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
