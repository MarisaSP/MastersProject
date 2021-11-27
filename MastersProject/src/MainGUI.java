import java.awt.EventQueue;
import java.util.ArrayList;

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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainGUI {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=-34,21
	 */
	private final JScrollPane scrollPane = new JScrollPane();

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
		
		//String result = generateArrays.LCPArray("","anab");
		//System.out.println(result);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 230, 255));
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground( new Color(255, 255, 255));
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleLbl = new JLabel("FC[REG]-CQ");
		titleLbl.setForeground(new Color(102, 0, 102));
		titleLbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		
		JLabel createtableLbl = new JLabel("Create Table");
		createtableLbl.setForeground(new Color(102, 0, 102));
		createtableLbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		
		JLabel querytablesLbl = new JLabel("Query Tables");
		querytablesLbl.setForeground(new Color(102, 0, 102));
		querytablesLbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		
		JLabel displaytableLbl = new JLabel("Display Table");
		displaytableLbl.setForeground(new Color(102, 0, 102));
		displaytableLbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		displaytableLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnNewButton = new JButton("Input Word Document");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 102, 0));
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(240, 248, 255));
		
		JLabel strLbl = new JLabel("String:");
		strLbl.setForeground(new Color(0, 102, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(131)
					.addComponent(createtableLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
					.addComponent(querytablesLbl)
					.addGap(131))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(94)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(667, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(strLbl)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(608, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(396)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(displaytableLbl)
						.addComponent(titleLbl))
					.addContainerGap(413, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGap(79)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createtableLbl)
						.addComponent(querytablesLbl))
					.addGap(8)
					.addComponent(strLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(73)
					.addComponent(displaytableLbl)
					.addGap(238))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
