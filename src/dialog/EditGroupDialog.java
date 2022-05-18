package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import pages.GroupPage;
import pages.Page;
import pages.User;

/**
 * JDialog for editing a group
 * @author Ebubekir Karamustafa
 *
 */
public class EditGroupDialog extends JDialog implements ActionListener, MouseListener{
	/**
	 * name label
	 */
	private JLabel name;
	/**
	 * country label
	 */
	private JLabel country;
	/**
	 * hobbies label
	 */
	private JLabel hobbies;
	/**
	 * members label
	 */
	private JLabel membersLabel;
	/**
	 * nonmembers label
	 */
	private JLabel nonMembersLabel;
	
	/**
	 * field for typing name
	 */
	private JTextField nameField;
	/**
	 * field for typing country
	 */
	private JTextField countryField;
	/**
	 * field for typing hobbies
	 */
	private JTextArea hobbiesField;
	
	/**
	 * pane for memberList
	 */
	private JScrollPane memberListPane;
	/**
	 * pane for nonMembersList
	 */
	private JScrollPane nonMembersListPane;
	/**
	 * list for members
	 */
	private JList members;
	/**
	 * list for nonmembers
	 */
	private JList nonMembers;
	
	/**
	 * center Panel
	 */
	private JPanel centerPanel;
	/**
	 * top Panel
	 */
	private JPanel topPanel;
	/**
	 * bottom Panel
	 */
	private JPanel bottomPanel;
	
	/**
	 * for adding user
	 */
	private JButton addUser;
	/**
	 * for removing User
	 */
	private JButton removeUser;
	/**
	 * for apply the changes
	 */
	private JButton apply;
	/**
	 * selected group from the user
	 */
	private GroupPage selected;
	
	/**
	 * to keep track of the member of the group
	 */
	private ArrayList<User> membersArr;
	/**
	 * to keep track of the nonmember of the group
	 */
	private ArrayList<User> nonMembersArr; 
	
	/**
	 * model for nonmembers list
	 */
	private DefaultListModel<String> model2;
	/**
	 * model for members list
	 */
	private DefaultListModel<String> model1; 
	
	/**
	 * user who is editing the group
	 */
	private User currentUser;
	
	
	/**
	 * 
	 * @param parent parent for JDialogs
	 * @param selected group which is to be edited
	 * @param currentUser user that is editing the group
	 */
	public EditGroupDialog(Page parent,GroupPage selected, User currentUser) {
		
		super(parent);
		setSize(500,500);
		setTitle("Edit Group");
		setModal(true);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		//init of selected group
		this.selected = selected;
		//init of the currentUser
		this.currentUser = currentUser;
		
		//init of panels for design purposes
		topPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		
		//init of name label
		name = new JLabel("Change Name to: ");
		//init of country label
		country = new JLabel("Change Country to: ");
		//init of hobbies label
		hobbies = new JLabel("Change Hobbies to: ");
		//init of members label
		membersLabel = new JLabel("Members");
		membersLabel.setAlignmentX(CENTER_ALIGNMENT);
		//init of nonmembers label
		nonMembersLabel = new JLabel("Nonmembers");
		nonMembersLabel.setAlignmentX(CENTER_ALIGNMENT);
		//init of name field
		nameField = new JTextField();
		nameField.setBorder(BorderFactory.createLineBorder(Color.black));
		//init of country field
		countryField = new JTextField();
		countryField.setBorder(BorderFactory.createLineBorder(Color.black));
		//init of hobbies field
		hobbiesField = new JTextArea();
		hobbiesField.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//init of buttons
		addUser = new JButton("Add");
		removeUser = new JButton("Remove");
		apply = new JButton("Apply");
		removeUser.addActionListener(this);
		addUser.addActionListener(this);
		apply.addActionListener(this);

		//Border for margin
		Border generalMargin = BorderFactory.createEmptyBorder(10,10,10,10);
		
		//TOP PANEL
		topPanel.setLayout(new GridLayout(3,3,5,5));
		topPanel.setBorder(generalMargin);
		topPanel.add(name);
		topPanel.add(nameField);
		topPanel.add(country);
		topPanel.add(countryField);
		topPanel.add(hobbies);
		topPanel.add(hobbiesField);
		
		
			//MEMBERS LIST INIT
			model1 = new DefaultListModel<>();
			
			for(User current: selected.getUsers()) {
				model1.addElement(current.getName());
			}
			members = new JList<String>(model1);
			
			//Members List Pane initialization
			memberListPane = new JScrollPane(members, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			members.addMouseListener(this);
			memberListPane.getViewport().add(members);
			memberListPane.setVisible(true);
		
			//NONMEMBERS LIST INIT
			model2 = new DefaultListModel<>();
			membersArr = new ArrayList<>(selected.getUsers());
			nonMembersArr = new ArrayList<>(User.allUsers);
			nonMembersArr.removeAll(membersArr);
			
			for(User current: nonMembersArr) {
				model2.addElement(current.getName());
			}
			
			//Nonmembers List Pane initialization
			nonMembers = new JList<String>(model2);
			nonMembersListPane = new JScrollPane(nonMembers, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			nonMembersListPane.getViewport().add(nonMembers);
			nonMembersListPane.setVisible(true);
			nonMembers.addMouseListener(this);
		
		//CENTER PANEL INIT
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(generalMargin);
		centerPanel.add(membersLabel);
		centerPanel.add(memberListPane);
		centerPanel.add(nonMembersLabel);
		centerPanel.add(nonMembersListPane);
		
		
		//BOTTOM PANEL
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setBorder(generalMargin);
		bottomPanel.add(addUser);
		bottomPanel.add(removeUser);
		bottomPanel.add(Box.createHorizontalGlue());	
		bottomPanel.add(apply);
		
		
		//adds all panels to main panel
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel ,BorderLayout.CENTER);
		add(bottomPanel , BorderLayout.SOUTH);
		
		
		setVisible(true);
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == members || arg0.getSource() == nonMembers) {
			try {
				((JList) arg0.getSource()).setSelectionBackground(Color.decode("#ADD8E6"));
			}catch(Exception e) {
				
			}
		}
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
	
	/**
	 * actionPerformed method is overridden to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//adds user to the group
		if(arg0.getSource() == addUser) {
			if(!nonMembers.isSelectionEmpty()) {
			ArrayList<String> allUsersName = new ArrayList<>();
			for(User current : User.getAllUsers()) {
				allUsersName.add(current.getName());
			}
			int index = allUsersName.indexOf(nonMembers.getSelectedValue());
			User addedUser = User.getAllUsers().get(index);
			addedUser.joinGroup(selected);
			//refresh the content of the panes
			refreshList();
		}
		}
		//removes user from the group
		else if(arg0.getSource() == removeUser) {
			if(!members.isSelectionEmpty()) {
			ArrayList<String> allUsersName = new ArrayList<>();
			for(User current : User.getAllUsers()) {
				allUsersName.add(current.getName());
			}
			int index = allUsersName.indexOf(members.getSelectedValue());
			User removedUser = User.getAllUsers().get(index);
			removedUser.leaveGroup(selected);
			selected.removeMember(removedUser);
			User.getAllUsers().get(index).leaveGroup(selected);
			//refresh the content of the panes
			refreshList();
			}
		}
		//apply the changes and closes the window
		else if (arg0.getSource() == apply) {
			if(!nameField.getText().isEmpty()) {
				selected.setGroupName(nameField.getText());
			}
			if(!countryField.getText().isEmpty()) {
				selected.setCountry(countryField.getText());
			}
			if(!hobbiesField.getText().isEmpty()) {
				selected.setHobbies(hobbiesField.getText());
			}
			
			dispose();
		}
		
	}


	/**
	 * refresh the content of the panes
	 */
	public void refreshList() {
		//removes the content of the panes
		memberListPane.getViewport().removeAll();
		nonMembersListPane.getViewport().removeAll();
		
			//re-init of model1
			model1 = new DefaultListModel<>();
			
			for(User current: selected.getUsers()) {
				model1.addElement(current.getName());
			}
			//re-init of members list
			members = new JList<String>(model1);
		
		//adds the new members to memberListPane
		memberListPane.getViewport().add(members);
		
			//re-init of model2
			model2 = new DefaultListModel<>();
			
			//gets the users of the group
			membersArr = new ArrayList<>(selected.getUsers());
			nonMembersArr = new ArrayList<>(User.allUsers);
			nonMembersArr.removeAll(membersArr);
		
			for(User current: nonMembersArr) {
			model2.addElement(current.getName());
			}
		
			//re-init of nonmembers list
			nonMembers = new JList<String>(model2);
		
		//adds the nonmembers to nonMembersListPane
		nonMembersListPane.getViewport().add(nonMembers);
	
	}
	
	
}
