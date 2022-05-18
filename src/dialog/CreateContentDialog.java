package dialog;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import content.Content;
import pages.GroupPage;
import pages.HomePage;
import pages.Page;
import pages.User;

/**
 * JDialog for creating content
 * @author Ebubekir Karamustafa
 *
 */
public class CreateContentDialog extends JDialog implements ActionListener{
	/**
	 * title of the content label
	 */
	private JLabel title;
	/**
	 * where to share label
	 */
	private JLabel locationLabel;
	/**
	 * for text of the content
	 */
	private JTextArea content;
	/**
	 * for typing title
	 */
	private JTextField titleField;
	
	/**
	 * topPanel
	 */
	private JPanel topPanel;
	/**
	 * centerPanel
	 */
	private JPanel centerPanel;
	/**
	 * bottomPanel
	 */
	private JPanel bottomPanel;
	
	/**
	 * for openning file chooser
	 */
	private JButton chooseFileButton;
	/**
	 * for sharing
	 */
	private JButton shareButton;
	
	/**
	 * for the JDialogs
	 */
	private Page parent;
	/**
	 * user who is creating the content 
	 */
	private User currentUser;
	
	/**
	 * for choice files
	 */
	private JFileChooser fileChooser;
	/**
	 * for selecting where to share
	 */
	private JComboBox<String> location;
	
	/**
	 * selected file by file chooser
	 */
	private File selectedFile;
	
	/**
	 * options where to share
	 */
	
	private String[] options;
	/**
	 * Initializes the Dialog with field variables
	 * @param parent class that instansiated the this dialog
	 * @param currentUser User that is creating the content 
	 */
	public CreateContentDialog(Page parent, User currentUser) {
		setSize(500,500);
		this.currentUser = currentUser;
		this.parent = parent;
		setTitle("Create Content");
		setModal(true);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		//init of title of the content
		title = new JLabel("Title");
		title.setAlignmentX(RIGHT_ALIGNMENT);
		
		//init of the locationLabel
		locationLabel = new JLabel("Where to share  ");
		
		
		//init of the location options
		options = new String[] {"My Profil Page", "Group Page", "Homepage"};
		
		//init of the location comboBox
		location = new JComboBox<>(options);
		locationLabel.setAlignmentX(LEFT_ALIGNMENT);

		
		//init of the text area for the content
		content = new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray));
		content.setLineWrap(true);
		
		//init of the title Field
		titleField = new JTextField();
		
		//init of the file chooser button
		chooseFileButton = new JButton("Choose");
		chooseFileButton.addActionListener(this);
		
		//init of the share button
		shareButton = new JButton("Share");
		shareButton.addActionListener(this);
		
		//init of the topPanel for design purposes
		topPanel = new JPanel();
		Border topPanelMargin = BorderFactory.createEmptyBorder(50, 40, 10, 40);
		topPanel.setBorder(topPanelMargin);
		topPanel.setLayout(new GridLayout(0,2,0,0));
			
		topPanel.add(title);
		topPanel.add(titleField);
		
		//init of the centerPanel for design purposes
		centerPanel = new JPanel();
		Border centerPanelMargin = BorderFactory.createEmptyBorder(10,40,10,40);
		centerPanel.setBorder(centerPanelMargin);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
			
			//two extra panel for design purposes
					JPanel topCenterPanel = new JPanel();
					JPanel bottomCenterPanel = new JPanel();
					topCenterPanel.setLayout(new BoxLayout(topCenterPanel, BoxLayout.X_AXIS));
					topCenterPanel.add(content);
			
					Border innerBorder = BorderFactory.createEmptyBorder(20,0,0,0);
		
					bottomCenterPanel.setBorder(innerBorder);
					bottomCenterPanel.setLayout(new BoxLayout(bottomCenterPanel, BoxLayout.X_AXIS));
					
					bottomCenterPanel.add(locationLabel);
					bottomCenterPanel.add(location);
					//creating glue for design purposes
					bottomCenterPanel.add(Box.createHorizontalGlue());
					bottomCenterPanel.add(chooseFileButton);
					
		centerPanel.add(topCenterPanel);
		centerPanel.add(bottomCenterPanel);
				
		//init of bottomPanel for design purposes
		bottomPanel = new JPanel();
		Border bottomPanelMargin = BorderFactory.createEmptyBorder(10,40,10,40);
		
		bottomPanel.setBorder(bottomPanelMargin);
		bottomPanel.add(shareButton);
		
		
		
		
		//adds three panel to main frame
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		
		setVisible(true);

	}
	/**
	 * actionPerformed is overriden to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//share buttons creates content then refresh the content of the currentUser Profile Page, closes the dialog
		if(arg0.getSource() == shareButton) {
			createContent();
			currentUser.refreshContent();
			dispose();
		}
		//chooseFileButton initialize the filechooser with a filter then initialize the selected file if the user selected right type of document
		if(arg0.getSource() == chooseFileButton) {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG & PNG Images", "jpeg", "png");
			fileChooser.setFileFilter(filter);
			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
			}
		}
		
	}

	/**
	 * creates the content
	 */
	public void createContent() {
		//init of exact time
		Timestamp time = new Timestamp(new Date().getTime());
		
		//creating reference for newContent
		Content newContent;
		
		//if no image selected then initialize a content with no image
		if(selectedFile != null) {
		//init of new Content
		newContent = new Content(titleField.getText(),time,currentUser,content.getText(),selectedFile.getName());
		}
		else {
		//init of new Content
		newContent = new Content(titleField.getText(),time,currentUser,content.getText(),null);
		}
		
		//if user select to share the content on Home Page then it adds newContent to Home page contents list
		if(location.getSelectedItem().equals(options[2])) {
			HomePage.contents.add(newContent);
			dispose();
		}
		
		//if user select to share the content on a group page it instansiate dialog to ask user in which group to share.
		else if(location.getSelectedItem().equals(options[1])) {
			
					//creating string array of groups name
					ArrayList<String> groupsName = new ArrayList<>();
					
					//takes the group names of the user that is creating the content
					for(GroupPage current: currentUser.getGroups()) {
						groupsName.add(current.getGroupName());
					}
					
					//creating a jcombobox to with groupsname
					JComboBox optionsCombo = new JComboBox(groupsName.toArray());
					optionsCombo.setEditable(true);
					
					//option array for optionPane initialization
					String[] options = new String[] {};
					
					//init of the optionPane
					JOptionPane optionPane = new JOptionPane("Please select",JOptionPane.QUESTION_MESSAGE,JOptionPane.DEFAULT_OPTION,null,options,null);
					
					//button for sharing after the group selected
					JButton share = new JButton("Share");
					
					//adding the comboBox and share button to optionPane
					optionPane.add(optionsCombo);
					optionPane.add(share);
					
					//creating a temporary JDialog for optionPane
					JDialog temp = new JDialog();
					temp.getContentPane().add(optionPane);
					temp.setLocationRelativeTo(this);
					temp.setResizable(false);
					temp.pack();
					
					//this anonymous inner class enables user to add new content to selected group page
					share.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							int index = optionsCombo.getSelectedIndex();
							currentUser.getGroups().get(index).getAllContents().add(newContent);
							temp.dispose();
						}
					});
					temp.setVisible(true);
		}
		
		//if the user select to share the created content it adds created content to users's content list
		else if(location.getSelectedItem().equals(options[0])) {
			HomePage.contents.add(newContent);
			currentUser.getContents().add(newContent);
			dispose();

		}
	}
	
}
