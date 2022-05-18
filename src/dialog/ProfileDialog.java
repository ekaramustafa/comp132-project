package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import pages.Page;
import pages.User;

/**
 * JDialog for profile editing
 * @author Ebubekir Karamustafa
 *
 */
public class ProfileDialog extends JDialog implements ActionListener{
	/**
	 * parent for JDialog
	 */
	private Page parent;
	/**
	 * User that is going to be edited
	 */
	private User selected;
	
	/**
	 * apply button
	 */
	private JButton apply;
	/**
	 * choose button
	 */
	private JButton choose;
	
	/**
	 * name label
	 */
	private JLabel nameLabel;
	
	/**
	 * surname label
	 */
	private JLabel surnameLabel;
	
	/**
	 * email label
	 */
	
	private JLabel emailLabel;
	/**
	 * password label
	 */
	private JLabel passwordLabel;
	/**
	 * hobbies label
	 */
	private JLabel hobbiesLabel;
	/**
	 * photo label
	 */
	private JLabel photoLabel;
	
	/**
	 * Field for typing name
	 */
	private JTextField nameField;
	/**
	 * Field for typing surname;
	 */
	private JTextField surnameField;
	/**
	 * Field for typing email
	 */
	private JTextField emailField;
	/**
	 * Field for typing password
	 */
	private JTextField passwordField;
	/**
	 * Area for typing hobbies
	 */
	private JTextArea hobbies;
	/**
	 * File chooser for the image
	 */
	private JFileChooser chooser;
	
	/**
	 * Top panel
	 */
	private JPanel topPanel;
	/**
	 * Bottom Panel
	 */
	private JPanel bottomPanel;
	
	/**
	 * File that is selected by file chooser
	 */
	private File selectedFile;
	
	/**
	 * Initializes the Dialog with field variables
	 * @param parent parent for JDialogs
	 * @param selected User that is going to be edited
	 */
	public ProfileDialog(Page parent, User selected) {
	
	super(parent);
	setSize(600,600);
	setTitle("Edit Profile");
	setModal(true);
	setLocationRelativeTo(getParent());
	setLayout(new BorderLayout());
	
	//init of selecte User
	this.selected = selected;
	//init of the parent 
	this.parent = parent;
	
	//init of name label
	nameLabel = new JLabel("Change name to: ");
	//init of the surname label
	surnameLabel = new JLabel("Change surname to: ");
	//init of email label
	emailLabel = new JLabel("Change email to: ");
	//init of password label
	passwordLabel = new JLabel("Change password to: ");
	//init of hobbies label
	hobbiesLabel = new JLabel("Change hobbies to: ");
	//init of photo label
	photoLabel = new JLabel("Change image to: ");
	
	//init of buttons
	apply = new JButton("Apply");
	apply.addActionListener(this);
	choose = new JButton("Choose");
	choose.addActionListener(this);
	
	//init of name field
	nameField = new JTextField();
	//init of surname field
	surnameField = new JTextField();
	//init of the email field
	emailField = new JTextField();
	//init of the password field
	passwordField = new JTextField();
	//init of the hobbies area
	hobbies = new JTextArea();
	hobbies.setBorder(BorderFactory.createLineBorder(Color.gray));
	
	//Border for general margin
	Border generalMargin = BorderFactory.createEmptyBorder(20,15,20,15);
	
	
	//init of Top Panel
	topPanel = new JPanel();
	topPanel.setBorder(generalMargin);
	topPanel.setLayout(new GridLayout(6,6,5,5));
	
	topPanel.add(nameLabel);
	topPanel.add(nameField);
	topPanel.add(surnameLabel);
	topPanel.add(surnameField);
	topPanel.add(emailLabel);
	topPanel.add(emailField);
	topPanel.add(passwordLabel);
	topPanel.add(passwordField);
	topPanel.add(hobbiesLabel);
	topPanel.add(hobbies);
	topPanel.add(photoLabel);
	topPanel.add(choose);
	
	
	//init of Bottom Panel
	bottomPanel = new JPanel();
	
	bottomPanel.setBorder(generalMargin);
	bottomPanel.setLayout(new BorderLayout());
	bottomPanel.add(apply);
	
	//adds all panels into the main frame
	
	add(topPanel, BorderLayout.CENTER);
	add(bottomPanel, BorderLayout.SOUTH);
	
	
	
	setVisible(true);
	}
	/**
	 * actionPerformed is overriden to assign tasks to buttons
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//calls applyContent() and closes the window
		if(arg0.getSource() == apply) {
			applyContent();
			dispose();
		}
		//initializes the filechooser with a filter then initialize the selected file if the user selected right type of document
		else if (arg0.getSource() == choose) {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG & PNG Images", "jpeg", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = chooser.getSelectedFile();
			}
		}
		
		
	}

	/**
	 * apply the changes on the profile
	 */
	private void applyContent() {
		// TODO Auto-generated method stub
		//check whether any changes are made by the user if not then the values of the selected user's variable should no be changed
		if(!nameField.getText().isEmpty()) {
			selected.setName(nameField.getText());
		}
		if(!surnameField.getText().isEmpty()) {
			selected.setSurname(surnameField.getText());
		}
		if(!emailField.getText().isEmpty()) {
			selected.setEmail(emailField.getText());
		}
		if(!passwordField.getText().isEmpty()) {
			selected.setPassword(passwordField.getText());
		}
		if(!hobbies.getText().isEmpty()) {
			selected.setHobbies(hobbies.getText());
		}
		if(selectedFile != null) {
			selected.setIconByPath(selectedFile.getAbsolutePath());
		}
		
	}
	
}

