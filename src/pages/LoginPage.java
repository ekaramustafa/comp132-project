package pages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import dialog.SignupDialog;

/**
 * Concrete subclass of Page for Login Page
 * @author Ebubekir Karamustafa
 *
 */
public class LoginPage extends Page implements ActionListener{
	/**
	 * sign up button for creating new users
	 */
	private Button signUpButton;
	/**
	 * login Button to proceed with Home Page
	 */
	private Button loginButton;
	/*
	 * name label
	 */
	private JLabel nameLabel;
	/**
	 * password label
	 */
	private JLabel passwordLabel;
	/**
	 * password field
	 */
	private JPasswordField passwordField;
	/**
	 * field where the user inputs nickname or the name of the account
	 */
	private JTextArea nameField;
	/**
	 * Welcome the e-net text
	 */
	private JLabel welcomeText;
	/**
	 * User that has been logged in
	 */
	private User loggedUser;
	
	
	/**
	 * Initilaze the Login Page with certain size and components
	 */
	public LoginPage() {
		//INITS
		super("Login");
		setSize(400,600);
		//Login Button Init
			loginButton = new Button("Login");
			loginButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			loginButton.addActionListener(this);
		//Signup Button Init
			signUpButton = new Button("Sign Up");
			signUpButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
			signUpButton.addActionListener(this);
		
		//PasswordField init
			passwordField = new JPasswordField();
		//NameField init 
			nameField = new JTextArea();
		
		//Namelabel init
			nameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
		//Passwordlabel init
			passwordLabel = new JLabel("Password: ", SwingConstants.RIGHT);
		
		//WelcomeText init
			welcomeText = new JLabel("Welcome to the e-net", SwingConstants.CENTER);
			welcomeText.setFont(new Font("Serif", Font.ITALIC, 20));

		
		//contentFont is from the superclass
		nameLabel.setFont(contentFont);
		passwordLabel.setFont(contentFont);
		
		
		
		
		///Necassary panels for inner layout settings
		JPanel welcomePanel = new JPanel();//contains logo and welcome text
		
		JPanel loginPart = new JPanel();//contains innerlogin panel
		/*-->*/JPanel innerLoginPart = new JPanel();//contains namelabel, namefield, passwordlabel, passwordfield
		/*-->*/JPanel ButtonPanel = new JPanel();//contains sign up and login button

		
		//LAYOUT SETTINGS
		setLayout(new BorderLayout(20,40));
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));		
		loginPart.setLayout(new BoxLayout(loginPart, BoxLayout.Y_AXIS));
		innerLoginPart.setLayout(new GridLayout(2,2,5,10));
		ButtonPanel.setLayout(new BorderLayout());

		
		//BORDERS
		Border welcomeBorder = BorderFactory.createEmptyBorder(120,85,0,10);//creates a margin
		
		Border loginBorder = BorderFactory.createEmptyBorder(40,48,80,50);//creates a margin
		
		Border innerBorder = BorderFactory.createEmptyBorder(0,10,0,50);//creates a margin
		
		Border imageBorder = BorderFactory.createEmptyBorder(0,80,0,10);//creates a margin
		
		Border fieldBorder = BorderFactory.createLoweredBevelBorder();//creating a extra border for password and name fields
		
		//Settings borders
			nameField.setBorder(fieldBorder);
			passwordField.setBorder(fieldBorder);
			welcomePanel.setBorder(welcomeBorder);
			innerLoginPart.setBorder(innerBorder);
			ButtonPanel.setBorder(loginBorder);

		
				//Logo inits
				JLabel logoImg = new JLabel();
				logoImg.setBorder(imageBorder);
				logoImg.setIcon(logo);
		
		welcomePanel.add(logoImg);
		welcomePanel.add(welcomeText);
		///
		
		//Login Part
		loginPart.setBorder(loginBorder);
				//Inner Login Part
				innerLoginPart.add(nameLabel);
				innerLoginPart.add(nameField);
				innerLoginPart.add(passwordLabel);
				innerLoginPart.add(passwordField);

				//Button Panel
				ButtonPanel.add(loginButton, BorderLayout.CENTER);
				ButtonPanel.add(signUpButton, BorderLayout.SOUTH);
		
		
		loginPart.add(innerLoginPart);
		loginPart.add(ButtonPanel);
		//
		
		//ADDING TWO MAIN PANEL
		add(welcomePanel, BorderLayout.NORTH);
		add(loginPart, BorderLayout.CENTER);
		
		
		setVisible(true);
		
	}
	/**
	 * returns the logged in User after verification
	 * @return loggedUser
	 */
	public User getLoggedUser() {
		return loggedUser;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == loginButton) {
			boolean userFound = false;
			for(User currentUser : User.allUsers) {
				
				if(currentUser.getName().equals( nameField.getText().trim()) || currentUser.getNickname().equals(nameField.getText().trim())) {
					//proceed with the next page
					if(currentUser.getPassword().equals(String.valueOf(passwordField.getPassword()))) 
					{
						loggedUser = currentUser;
						userFound = true;
						setVisible(false);
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"Please try again","Password Wrong", JOptionPane.WARNING_MESSAGE);
						userFound = true;
					}
					
			
				}
		
			}
		
		if(!userFound) {
		JOptionPane.showMessageDialog(null,"No such user found, please check the username","Login Failed", JOptionPane.ERROR_MESSAGE);
		}
		}
		else if (arg0.getSource() == signUpButton) {
			new SignupDialog(this);
		}
		
		
	}
	@Override
	/**
	 * 	implemented to refresh the content if any changes are made. 
	 */
	public void refreshContent() {
		// TODO Auto-generated method stub
		
	}
	
}
	
	
	

