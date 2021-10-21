import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleLbl = new JLabel("FC[REG]-CQ");
		
		JLabel createtableLbl = new JLabel("Create Table");
		
		JLabel querytablesLbl = new JLabel("Query Tables");
		
		JLabel displaytableLbl = new JLabel("Display Table");
		displaytableLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(createtableLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
					.addComponent(querytablesLbl)
					.addGap(53))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(188)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(displaytableLbl)
						.addComponent(titleLbl))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createtableLbl)
						.addComponent(querytablesLbl))
					.addGap(67)
					.addComponent(displaytableLbl)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
