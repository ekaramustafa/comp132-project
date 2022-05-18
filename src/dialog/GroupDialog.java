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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import pages.GroupPage;
import pages.Page;
import pages.User;
/**
 * JDialog for managament of groups
 * @author Ebubekir Karamustafa
 *
 */
public class GroupDialog extends JDialog implements MouseListener,ActionListener{
	/**
	 * creating the group
	 */
	private JButton createGroup;
	/**
	 * deleting the group
	 */
	private JButton deleteGroup;
	/**
	 * editing group
	 */
	private JButton editGroup;
	/**
	 * group list for the groupListPane
	 */
	private JList<String> groupList;
	/**
	 * parent for the JDialogs
	 */
	private Page parent;
	/**
	 * contains the groupList
	 */
	private JScrollPane groupListPane;
	/**
	 * User managing the groups
	 */
	private User currentUser;
	
	/** 
	 * Initializes the Dialog with field variables
	 * @param parent Page that is instansiated this Dialog
	 * @param currentUser User that is managing the groups
	 */
	public GroupDialog(Page parent, User currentUser){
		super(parent);
		setSize(500,500);
		setTitle("Group Management");
		setModal(true);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		
		//init of currentUser
		this.currentUser = currentUser;
		
		//init of parent
		this.parent = parent;
		
		//init of createGroup button
		createGroup = new JButton("Create");
		createGroup.addActionListener(this);

		//init of deleteGroup button
		deleteGroup = new JButton("Delete");
		deleteGroup.setEnabled(false);
		deleteGroup.addActionListener(this);
		
		//init of editGroup button
		editGroup = new JButton("Edit");
		editGroup.setEnabled(false);
		editGroup.addActionListener(this);

		
			Border panelMargin = BorderFactory.createEmptyBorder(10,10,10,10);
		
			//topPanel for design purposes 
			JPanel topPanel = new JPanel();
			topPanel.setBorder(panelMargin);
			topPanel.setLayout(new BorderLayout());
			
			topPanel.add(createGroup,BorderLayout.CENTER);
			topPanel.add(editGroup, BorderLayout.WEST);
			topPanel.add(deleteGroup, BorderLayout.EAST);
		
		
			//centerPanel for design purposes 		
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			
			Border centerPanelBorder = BorderFactory.createCompoundBorder(panelMargin, BorderFactory.createRaisedSoftBevelBorder());
			centerPanel.setBorder(centerPanelBorder);
			
			
	
			//model for groupList
			DefaultListModel<String> model = new DefaultListModel<>();
			
			//adding all name of the groups
			for(GroupPage current: GroupPage.allGroups) {
				model.addElement(current.getGroupName());
			}
			
			//init of the groupList with modified model
			groupList = new JList<String>(model);		
			groupList.addMouseListener(this);
	
			//init of the groupListPane with modified groupList
			groupListPane = new JScrollPane(groupList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			groupListPane.getViewport().add(groupList);
			groupListPane.setVisible(true);
			
			
			centerPanel.add(groupListPane,BorderLayout.CENTER);
		
		
		//adds two panel to main frame
		add(topPanel,BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		setVisible(true);

		
	}
	/**
	 * mouseClicked is overriden to assign task to groupList
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == groupList) {
			try {
				editGroup.setEnabled(true);
				deleteGroup.setEnabled(true);
			
				
			}catch(Exception e) {
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == groupList) {
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
	 * actionPerformed is overriden to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//editGroup button instansiates EditGroupDialog with parent, selected page and editing user
		//verify the authority
		//then refreshes the list
		if(arg0.getSource() == editGroup) {
			int index = groupList.getSelectedIndex();
			GroupPage selected = GroupPage.allGroups.get(index);
			if(selected.getCreator().getName().equals(currentUser.getName())) {
			new EditGroupDialog(parent,selected,currentUser);
			refreshTheList();
			}
			else {
				JOptionPane.showMessageDialog(this, "You are not the creator of this group", "NOT ALLOWED", JOptionPane.ERROR_MESSAGE);
			}
		}
		//createGroup button instansiates CreateGroupDialog
		//then refreshes the list
		else if(arg0.getSource() == createGroup) {
			new CreateGroupDialog(parent, currentUser);
			refreshTheList();
			editGroup.setEnabled(true);
			deleteGroup.setEnabled(true);

		}
		//deleteGroup button deletes the seleceted group
		//then refreshes the list
		else if(arg0.getSource() == deleteGroup) {
			int index = groupList.getSelectedIndex();
			GroupPage selected = GroupPage.allGroups.get(index);
			if(selected.getCreator().getName().equals(currentUser.getName())) {
			currentUser.getGroups().remove(GroupPage.allGroups.get(index));
			GroupPage.allGroups.remove(index);
			refreshTheList();
			}
			else {
				JOptionPane.showMessageDialog(this, "You are not the creator of this group", "NOT ALLOWED", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	/**
	 * refreshes the list with new model
	 */
	public void refreshTheList() {
		//clear the all content from the groupListPane
		groupListPane.getViewport().removeAll();
		
		//init of new model with modified grouplist
		DefaultListModel<String> model = new DefaultListModel<>();
		for(GroupPage current: GroupPage.allGroups) {
			model.addElement(current.getGroupName());
		}
		
		//init of groupList with modofied model
		groupList = new JList<String>(model);		

		//adds new list to groupListPane
		groupListPane.getViewport().add(groupList);
		
	}
	
}




