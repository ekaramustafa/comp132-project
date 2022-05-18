package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import pages.GroupPage;
import pages.Page;
import pages.User;

/**
 * JDialog for creating a group
 * @author Ebubekir Karamustafa
 *
 */
public class CreateGroupDialog extends JDialog implements ActionListener{
	/**
	 * country label
	 */
	private JLabel country;
	/**
	 * name label
	 */
	private JLabel name;
	/**
	 * hobbies label
	 */
	private JLabel hobbies;
	
	/**
	 * upperPanel
	 */
	private JPanel upperPanel;
	/**
	 * bottomPanel
	 */
	private JPanel bottomPanel;
	/**
	 * User that is creating the group
	 */
	private User currentUser;

	/**
	 * field for typing country
	 */
	private JTextField countryField;
	/**
	 * field for typing name
	 */
	private JTextField nameField;
	/**
	 * area for typing hobbies
	 */
	private JTextArea hobbiesField;
	
	/**
	 * for creating button
	 */
	private JButton createButton;
	
	/**
	 * 
	 * @param parent parent for JDialog
	 * @param currentUser user that is creating group
	 */
	public CreateGroupDialog(Page parent, User currentUser) {
		super(parent);
		setSize(500,500);
		setTitle("Create Group");
		setModal(true);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		
		//init of current user
		this.currentUser = currentUser;
		//init of country label
		country = new JLabel("Country: ");
		//init of name label
		name = new JLabel("Name: ");
		//init of hobbies label
		hobbies = new JLabel("Hobbies: ");
		//init of country field
		countryField = new JTextField();
		countryField.setBorder(BorderFactory.createLineBorder(Color.black));
		//init of name field
		nameField = new JTextField();
		nameField.setBorder(BorderFactory.createLineBorder(Color.black));
		//init of hobbies area
		hobbiesField = new JTextArea();
		hobbiesField.setLineWrap(true);
		hobbiesField.setBorder(BorderFactory.createLineBorder(Color.black));
		//init of create button
		createButton = new JButton("Create");
		createButton.addActionListener(this);
		
		//Border for margin
		Border generalMargin = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		//init of upper panel 
		upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(2,2,0,5));
		upperPanel.setBorder(generalMargin);
		
		upperPanel.add(name);
		upperPanel.add(nameField);
		upperPanel.add(country);
		upperPanel.add(countryField);
		
		//init of bottom panel
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(generalMargin);
		
		bottomPanel.add(hobbies, BorderLayout.WEST);
		bottomPanel.add(hobbiesField,BorderLayout.CENTER);
		
		
		//adds all panels into the main frame
		add(upperPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
		add(createButton, BorderLayout.SOUTH);
		
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//calls creategroup method then closes the JDialog
		if(arg0.getSource() == createButton) {
			createGroup();
			dispose();
		}
	}

	/**
	 * creates a new group with the values typed in fields
	 */
	private void createGroup() {
		// TODO Auto-generated method stub
		//gets necessary information from the fields
		String name = nameField.getText();
		String country = countryField.getText();
		String hobbies = hobbiesField.getText();
		
		//init of newGroup
		GroupPage newGroup = new GroupPage(name,country,hobbies,currentUser);
		
		//currentUser is the creator of the group
		currentUser.joinGroup(newGroup);
		newGroup.addMember(currentUser);
		
	}
	
}