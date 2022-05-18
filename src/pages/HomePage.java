package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import content.Content;
import dialog.CreateContentDialog;
import dialog.EditContentDialog;
import dialog.GroupDialog;

import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.DropMode;

/**
 * Concrete subclass of Page for Home page
 * @author Ebubekir Karamustafa
 *
 */

public class HomePage extends Page implements MouseListener,ActionListener{
	/**
	 * search button
	 */
	private JButton searchButton;
	/**
	 * field for typing to search
	 */
	private JTextField searchText;
	/**
	 * label to contains profile photo
	 */
	private JLabel profilPhotoLabel;
	/**
	 * logged in user
	 */
	private User currentUser;
	/**
	 * button to go own profil page
	 */
	private JButton profilPageLink;
	/**
	 * sign out link
	 */
	private JLabel signOutLink;
	/**
	 * following label
	 */
	private JLabel followingsLabel;
	/**
	 * followers label
	 */
	private JLabel followersLabel;
	/**
	 * for creating contents
	 */
	private JButton createContentButton;
	/**
	 * for refreshing the contents
	 */
	private JButton refreshButton;
	/**
	 * for managing the groups (exclusive to premium users)
	 */
	private JButton manageGroupButton;
	/**
	 * contains the content, is static because content is the same for logged in user
	 */
	private static JScrollPane contentPane;
	
	/**
	 * parent for JDialogs
	 */
	private Page parent = this;
	/**
	 * controls if user wants to go login page
	 */
	public static boolean returnLoginPage = false;
	/**
	 * controls if user wants to go any profil page
	 */
	public static boolean returnProfilPage = false;
	/**
	 * controls if user wants to go any group page
	 */
	public static boolean returnGroupPage = false;
	/**
	 * controls if user wants to return home page
	 */
	public static boolean returnHomePage = false;
	
	/**
	 * list of the contents that will be display on homepage
	 */
	public static ArrayList<Content> contents = new ArrayList<Content>();
	
	/**
	 * list of the content that are shared by the followed user
	 */
	private ArrayList<Content> followedContents = new ArrayList<Content>();
	/**
	 * initialize the Home Page and variables
	 * @param currentUser logged in user from Login Page
	 */
	public HomePage(User currentUser) {
		super("Home Page");
		setSize(1280,1024);
		setLocationRelativeTo(null); 
		//currentUser init
		this.currentUser = currentUser;
		
		//SearchText init
		searchText = new JTextField("");
		searchText.setBorder(BorderFactory.createEtchedBorder());
		searchText.setPreferredSize(new Dimension(100,1));

		//init of followedContents
		for(Content current:contents) {
			if(currentUser.getFollowing().contains(current.getCreator())) {
				followedContents.add(current);
			}
			else if(current.getCreator().getName() == currentUser.getName()) {
				followedContents.add(current);
			}
		}
		
		//SearchButton init
		searchButton = new JButton("Search");
		
		//Profile Photo init
		profilPhotoLabel = new JLabel();
		profilPhotoLabel.setIcon(currentUser.getProfilPhoto());
		profilPhotoLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		//Profile Page link init
		profilPageLink = new JButton("Go my profile");
		profilPageLink.setAlignmentX(Component.CENTER_ALIGNMENT);
		profilPageLink.addActionListener(this);
		
		//Sign out link init
		signOutLink = new JLabel("Sign out");
		signOutLink.addMouseListener(this);
		signOutLink.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//createContent Button init
		createContentButton = new JButton("Create content");
		createContentButton.addActionListener(this);
		
		//refresh button init
		refreshButton = new JButton("Refresh");
		
		//followings and followers label inits
		followingsLabel = new JLabel("Followings",SwingConstants.CENTER);
		followersLabel = new JLabel("Followers", SwingConstants.CENTER);
		
		//manage group button init
		manageGroupButton = new JButton("Manage Groups");
		manageGroupButton.setAlignmentX(CENTER_ALIGNMENT);
		manageGroupButton.addActionListener(this);

		
		//if the user is not premium then disable the manage group button
		if(!currentUser.isPremium()){
			manageGroupButton.setEnabled(false);
		}
	
		//General Layout Setting
		getContentPane().setLayout(new BorderLayout());

		//Main border for the JFrame
		Border mainBorder = BorderFactory.createRaisedSoftBevelBorder();
		
		
		
		JPanel centerPanel = new JPanel();
		
		
		
		//LEFT PANEL
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		//LEFT PANEL BORDER
		Border leftPanelMargin = BorderFactory.createEmptyBorder(40,20,20,0);
		
		Border leftPanelBorder = BorderFactory.createCompoundBorder(leftPanelMargin, mainBorder);//creates softbevel border with margin
		
		leftPanel.setBorder(leftPanelBorder);
		

		
				//UPPER LEFT PANEL
				JPanel upperLeftPanel = new JPanel();
				Border upperLeftMargin = BorderFactory.createEmptyBorder(10,10,10,10);
				upperLeftPanel.setBorder(upperLeftMargin);
				

				
				upperLeftPanel.setLayout(new BoxLayout(upperLeftPanel, BoxLayout.X_AXIS));
				upperLeftPanel.add(searchText);
				upperLeftPanel.add(searchButton);
				
	
				//CENTER LEFT PANEL
				JPanel centerLeftPanel = new JPanel();
				
				Border centerLeftMargin = BorderFactory.createEmptyBorder(10,10,10,10);
				centerLeftPanel.setBorder(centerLeftMargin);
				
				centerLeftPanel.setLayout(new BoxLayout(centerLeftPanel, BoxLayout.Y_AXIS));
				
				centerLeftPanel.add(profilPageLink);
				// vertical glue for design purposes
				centerLeftPanel.add(Box.createVerticalGlue());
				centerLeftPanel.add(profilPhotoLabel);
				
					//creating full name with name and surname of the logged in user
					JLabel fullName = new JLabel(currentUser.getName() + " " + currentUser.getSurname());
					fullName.setAlignmentX(CENTER_ALIGNMENT);
				
					//nickname init
					JLabel nickName = new JLabel("("+currentUser.getNickname()+")");
					nickName.setAlignmentX(CENTER_ALIGNMENT);
					
				centerLeftPanel.add(fullName);
				centerLeftPanel.add(nickName);
				
				//vertical glue for design purposes 
				centerLeftPanel.add(Box.createVerticalGlue());
				
				//Follower data panel contains following followers info
				JPanel followerDataPanel = new JPanel();
					followerDataPanel.setLayout(new GridLayout(2,2));
					followerDataPanel.add(followersLabel);
					followerDataPanel.add(new JLabel(String.valueOf((currentUser.getFollowers().size()))));
					followerDataPanel.add(followingsLabel);
					followerDataPanel.add(new JLabel(String.valueOf((currentUser.getFollowing().size()))));
				
				
				centerLeftPanel.add(followerDataPanel);
				//vertical glue for design purposes 
				centerLeftPanel.add(Box.createVerticalGlue());
				centerLeftPanel.add(manageGroupButton);
				

			
				
				//BOTTOM LEFT PANEL
				JPanel bottomLeftPanel = new JPanel();
				bottomLeftPanel.add(signOutLink);
				
				
		
		
				
				leftPanel.add(upperLeftPanel,BorderLayout.NORTH);
				leftPanel.add(centerLeftPanel,BorderLayout.CENTER);
				leftPanel.add(bottomLeftPanel, BorderLayout.SOUTH);
		
		
		
		//CENTER_PANEL
		centerPanel.setLayout(new BorderLayout());
		Border centerPanelMargin = BorderFactory.createEmptyBorder(40, 0, 20, 20);
		
		Border centerPanelBorder = BorderFactory.createCompoundBorder(centerPanelMargin, mainBorder);
		centerPanel.setBorder(centerPanelBorder);
		
				//TOP CENTER PANEL
				
				JPanel topCenterPanel = new JPanel();
				Border topCenterPanelMargin = BorderFactory.createEmptyBorder(10,10,10,10);
				topCenterPanel.setBorder(topCenterPanelMargin);
				topCenterPanel.setLayout(new BoxLayout(topCenterPanel, BoxLayout.X_AXIS));
				
				topCenterPanel.add(createContentButton);
				
				//CONTENT CENTER PANEL
				JPanel bottomCenterPanel = new JPanel();
				bottomCenterPanel.setLayout(new BorderLayout());
				
				Border bottomCenterPanelMargin = BorderFactory.createEmptyBorder(10,10,10,10);
				bottomCenterPanel.setBorder(bottomCenterPanelMargin);
				
				//ContentPane init
				contentPane = new JScrollPane();
				contentPane.setBorder(BorderFactory.createLineBorder(Color.gray));
				contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				

				bottomCenterPanel.add(refreshButton,BorderLayout.SOUTH);
				bottomCenterPanel.add(contentPane,BorderLayout.CENTER);
				

				
		
		centerPanel.add(topCenterPanel,BorderLayout.NORTH);
		centerPanel.add(bottomCenterPanel, BorderLayout.CENTER);

		
		getContentPane().add(leftPanel,BorderLayout.WEST);
		getContentPane().add(centerPanel,BorderLayout.CENTER);
		
		//refreshContent when the Home Page initialized 
		refreshContent();
		
		setVisible(true);
		
	}
	
	/**
	 * 	implemented to refresh the content if any changes are made.
	 */
	@Override
	public void refreshContent() {
		//first removes the all contents in Content Pane
		contentPane.getViewport().removeAll();

		
		//Init of a general panel to add all contens into it
		JPanel Panel = new JPanel();
		Panel.setLayout(new BoxLayout(Panel,BoxLayout.Y_AXIS));
		//Panel has vertical content flow
		
		//BORDERS for design purposes
		Border lines = BorderFactory.createLineBorder(Color.gray);
		Border mainPanelBorder = BorderFactory.createCompoundBorder(lines,BorderFactory.createEmptyBorder(10,10,10,10));
		
		//iterate through the contents of the HomePage
		Collections.reverse(followedContents);
		for(Content content : followedContents) {
			
			//title of the content
			JLabel title = new JLabel(content.getTitle(),SwingConstants.CENTER);
			title.setFont(new Font("Times New Roman",Font.BOLD,18));
			
			//text of the content
			JTextArea contentText = new JTextArea(content.getContent());
			contentText.setFont(new Font("Time New Roman", Font.PLAIN, 13));
			contentText.setEditable(false);
			contentText.setLineWrap(true);
			contentText.setBorder(BorderFactory.createLineBorder(Color.gray));
			
			//edit&delete button init
			JButton edit = new JButton("Edit");
			JButton delete = new JButton("Delete");

			
			//creation time init
			JLabel time = new JLabel(content.getCreationTime().toString(),SwingConstants.CENTER);
			time.setFont(new Font("Times New Roman", Font.PLAIN,14));
			
			//creator init
			User creator = content.getCreator();
			
			//creating full name with name, surname and nickname of the creator
			JLabel creatorFullName = new JLabel(creator.getName()+" "+creator.getSurname() + " (" + creator.getNickname() + ") ",SwingConstants.CENTER);
			creatorFullName.setFont(new Font("Times New Roman",Font.ITALIC,14));
			
				//init of the mainPanel to add Panel
				JPanel mainPanel = new JPanel();
				
				
				mainPanel.setLayout(new BorderLayout());
				mainPanel.setBorder(mainPanelBorder);
			
						//topPanel in main panel.
						JPanel topPanel = new JPanel();
						topPanel.setLayout(new BorderLayout());
						
						topPanel.add(title, BorderLayout.NORTH);
						topPanel.add(time, BorderLayout.CENTER);
						topPanel.add(creatorFullName,BorderLayout.SOUTH);
						
						//midPanel in main panel
						JPanel midPanel = new JPanel();
						midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
						midPanel.add(contentText);
						
						//inits with checking whether the user uploaded a image
						if(content.getIcon() != null) {
						ImageIcon icon_old = content.getIcon();
						ImageIcon icon = scaleImg(icon_old);
						JLabel image = new JLabel(icon);
						midPanel.add(image);
						image.setAlignmentX(CENTER_ALIGNMENT);
						}
					
						//bottomPanel in main panel
						JPanel bottomPanel = new JPanel();
						bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
						bottomPanel.add(edit);
						bottomPanel.add(Box.createHorizontalGlue());
						bottomPanel.add(delete);
			
			
			
				mainPanel.add(topPanel,BorderLayout.NORTH);
				mainPanel.add(midPanel, BorderLayout.CENTER);
				mainPanel.add(bottomPanel, BorderLayout.SOUTH);
				mainPanel.setMinimumSize(new Dimension(900,800));
			
			Panel.add(mainPanel);
			
			//Adding action listener to edit button 
			edit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Content current: contents) {
						//if the edit button is pressed by the creator
						if(current.getTitle().equals(title.getText()) && current.getCreator().getName().equals(creator.getName()) && current.getContent().equals(contentText.getText())) {
							//reference the selected content
							Content selected = current;
							//opens dialog for editing the content
							new EditContentDialog(parent,selected);
							//refresh the content after editing
							refreshContent();
							
						}
					}
					
				}	
			});
			
			//Adding action listener to delete button
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Content current: contents) {
						//checks if delete button is pressed by the creator
						if(current.getTitle().equals(title.getText()) && current.getCreator().getName().equals(creator.getName()) && current.getContent().equals(contentText.getText())) {
							//deletes the content
							contents.remove(contents.indexOf(content));
							//refresh the content after deleting
							refreshContent();
						}
					}
					
				}
				
			});
		}
		contentPane.setViewportView(Panel);
		contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		contentPane.setVisible(true);
				
	}
	

	/**
	 * scales the icon with some standarts and returns scaled icon
	 * @param icon icon that is going to be scaled
	 * @return icon
	 */
	private ImageIcon scaleImg(ImageIcon icon) {
		// TODO Auto-generated method stub
		Image image = icon.getImage();
		Image newImg = image.getScaledInstance(480, 360, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		return icon;
	}
	@Override
	/**
	 * ActionPerformed method for buttons
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == manageGroupButton) {
		new GroupDialog(HomePage.this,currentUser); //instansiate group manager dialog
		}
		if(arg0.getSource() == createContentButton) {
			new CreateContentDialog(this, currentUser); //instansiate content creator dialog
			refreshContent();
		}
		if(arg0.getSource() == refreshButton) {
			refreshContent(); // refresh the content
		}
		if(arg0.getSource() == profilPageLink) {
			returnProfilPage = true; // returns the Profil Page
			setVisible(false);
		}
		
	}

	@Override
	/**
	 * MouseClicked method for labels
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == signOutLink) {
			returnLoginPage = true;
			returnHomePage = false;
			returnProfilPage = false;
			setVisible(false);
			
		}
		
	}

	@Override
	/**
	 * implemented for design purposes
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel label = ((JLabel) arg0.getSource());
		Map<TextAttribute, Object> attributes = new HashMap<>(label.getFont().getAttributes());
		label.setFont(label.getFont().deriveFont(attributes));
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}







