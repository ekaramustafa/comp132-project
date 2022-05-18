package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.ByteOrder;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import pages.Page;
import pages.User;


/**
 *  JDialog for sign up 
 * @author Ebubekir Karamustafa
 *
 */
public class SignupDialog extends JDialog implements ActionListener{
	/**
	 * for JDialogs
	 */
	private Page parent;
	/**
	 * nickname label
	 */
	private JLabel nicknameLabel;
	/**
	 * password label
	 */
	private JLabel passwordLabel;
	/**
	 * name label
	 */
	private JLabel nameLabel;
	/**
	 * surname label
	 */
	private JLabel surnameLabel;
	/**
	 * age label
	 */
	private JLabel ageLabel;
	/**
	 * email label
	 */
	private JLabel emailLabel;
	/**
	 * isPremium label
	 */
	private JLabel isPremiumLabel;
	/**
	 * hobbies label
	 */
	private JLabel hobbiesLabel;
	/**
	 * photo label
	 */
	private JLabel photoLabel;
	
	/**
	 * field for typing nickname
	 */
	private JTextField nicknameField;
	/**
	 * field for typing password
	 */
	private JPasswordField passwordField;
	/**
	 * field for typing name
	 */
	private JTextField nameField;
	/**
	 * field for typing surname
	 */
	private JTextField surnameField;
	/**
	 * field for typing age
	 */
	private JTextField ageField;
	/**
	 * field for typing email
	 */
	private JTextField emailField;
	/**
	 * buttongroup for JRadiobuttons
	 */
	private ButtonGroup buttonGroup;
	/**
	 * for checking if the user is premium
	 */
	private JRadioButton yesOption;
	/**
	 * for checking if the user is premium
	 */
	private JRadioButton noOption;
	/**
	 * Area for typing hobbies
	 */
	private JTextArea hobbiesArea;
	
	/**
	 * for creating
	 */
	private JButton create;
	
	/**
	 * for choosing image
	 */
	private JButton choose;
	
	/**
	 * file chooser for image
	 */
	private JFileChooser chooser;
	
	/**
	 * topPanel
	 */
	private JPanel topPanel;
	/**
	 * bottomPanel
	 */
	private JPanel bottomPanel;
	/**
	 * radioPanel for JRadioButtons
	 */
	private JPanel radioPanel;
	
	/**
	 * selected file from file chooser
	 */
	private File selectedFile;
	
	
	/**
	 * Initializes the Dialog with field variables
	 * @param parent class that has instansiated the dialog
	 */
	public SignupDialog(Page parent) {
		super(parent);
		this.parent = parent;
		
		setSize(600,600);
		setTitle("Sign Up");
		setModal(true);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		
		//init of nickname label
		nicknameLabel = new JLabel("Nickname:*");
		//init of password label
		passwordLabel = new JLabel("Password:*");
		//init of name label
		nameLabel = new JLabel("Name:*");
		//init of surname label
		surnameLabel = new JLabel("Surname:*");
		//init of age label
		ageLabel = new JLabel("Age:*");
		//init of email label
		emailLabel = new JLabel("Email address:*");
		//init of isPremium label
		isPremiumLabel= new JLabel("Is user premium:*");
		//init of hobbies label
		hobbiesLabel = new JLabel("Hobbies");
		//init of photo label
		photoLabel = new JLabel("Upload a profil photo?");
		
		//init of nickname field
		nicknameField = new JTextField();
		//init of password field
		passwordField = new JPasswordField();
		//init of name field
		nameField = new JTextField();
		//init of surname field
		surnameField = new JTextField();
		//init of age field
		ageField = new JTextField();
		//init of email field
		emailField = new JTextField();
		//init of yes JRadioButton
		yesOption = new JRadioButton("Yes");
		//init of no JRadioButton
		noOption = new JRadioButton("No");
		//init of button group
		buttonGroup = new ButtonGroup();
		buttonGroup.add(yesOption);
		buttonGroup.add(noOption);

		//init of hobbies area
		hobbiesArea = new JTextArea();
		hobbiesArea.setLineWrap(true);
		hobbiesArea.setBorder(BorderFactory.createLineBorder(Color.gray));

		//init of create and choose buttons
		create = new JButton("Create");
		create.addActionListener(this);
		choose = new JButton("Choose");
		choose.addActionListener(this);
		
		//init of panels for design purposes
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		
		//radioPanel
		radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
		radioPanel.add(yesOption);
		radioPanel.add(Box.createHorizontalGlue());
		radioPanel.add(noOption);
		
		//TopPanel
		
		topPanel.setLayout(new GridLayout(9,2,5,5));
		Border generalMargin = BorderFactory.createEmptyBorder(20,20,20,20);
		topPanel.setBorder(generalMargin);
		
		topPanel.add(nicknameLabel);
		topPanel.add(nicknameField);
		topPanel.add(nameLabel);
		topPanel.add(nameField);
		topPanel.add(surnameLabel);
		topPanel.add(surnameField);
		topPanel.add(ageLabel);
		topPanel.add(ageField);
		topPanel.add(emailLabel);
		topPanel.add(emailField);
		topPanel.add(passwordLabel);
		topPanel.add(passwordField);
		topPanel.add(isPremiumLabel);
		topPanel.add(radioPanel);
		topPanel.add(hobbiesLabel);
		topPanel.add(hobbiesArea);
		topPanel.add(photoLabel);
		topPanel.add(choose);
		
		//BottomPanel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(generalMargin);
		bottomPanel.add(create, BorderLayout.CENTER);
		
		
		//adds two panel into the main frame
		add(topPanel, BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		
		
		
		
		
		setVisible(true);
		
		
	}
	
	
	/**
	 * implemented to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//calls createUSer methods and closes the jdialog
		if(arg0.getSource() == create) {
			createUser();
			dispose();
		}
		//chooseFileButton initialize the filechooser with a filter then initialize the selected file if the user selected right type of document
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
	 * creates a new user 
	 */
	public void createUser() {
		//checks whether user is premium or not
		boolean isPremium = yesOption.isSelected() ? true : false;
		
		//checks whether any file selected
		String path = selectedFile == null ? null : selectedFile.getPath();
		
		//checks whether hobbies area is empty
		String hobbies = hobbiesArea.getText().isEmpty() ? " " : hobbiesArea.getText();
		
		//takes the age field text converts into integer
		int age = Integer.parseInt(ageField.getText());
		
		//checks whether necessary blanks are filled
		if(!nameField.getText().isEmpty() && !nicknameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !emailField.getText().isEmpty() &&
				String.valueOf(passwordField.getPassword()) != null && !ageField.getText().isEmpty() && (yesOption.isSelected() || noOption.isSelected())){
			User newUser = new User(nicknameField.getText(),String.valueOf(passwordField.getPassword()),nameField.getText(),surnameField.getText(),age,
					emailField.getText(), isPremium, path, hobbies);
		}
		
		//if not shows a message dialog to warn user
		else {
			
			JOptionPane.showMessageDialog(null,"Please check the necessary parts","Signup Failed", JOptionPane.ERROR_MESSAGE);
			
		}
	}

}
