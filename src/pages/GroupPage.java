package pages;

import java.util.ArrayList;
import java.util.LinkedList;

import content.Content;

/**
 * Concrete subclass of Page for Group pages
 * @author Ebubekir Karamustafa
 *
 */

public class GroupPage extends Page{
	
	/**
	 * name of the group
	 */
	private String groupName;
	/**
	 * country of the group
	 */
	private String country;
	/**
	 * hobbies of the group
	 */
	private String hobbies;
	/**
	 * current user who is displaying the Group Page
	 */
	private User currentUser;
	/**
	 * creator of the Page
	 */
	private User creator;
	/**
	 * list of users
	 */
	private ArrayList<User> Users = new ArrayList<User>();
	/**
	 * list of contents of the group
	 */
	private LinkedList<Content> allContents = new LinkedList<Content>();
	
	/**
	 * all groups that have been created so far
	 */
	public static ArrayList<GroupPage> allGroups = new ArrayList<GroupPage>();

	/**
	 * @param groupName name of the group
	 * @param country where group is created
	 * @param hobbies hobbies of the group
	 * @param creator creator of the group
	 */
	public GroupPage(String groupName,String country,String hobbies,User creator) {
		super(groupName);
		this.groupName = groupName;
		allGroups.add(this);
		Users.add(creator);
		this.creator = creator;
		
	}
	/**
	 * returns country of the group
	 * @return country <em>String</em> country of the group
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * sets country of the group to a new String value
	 * @param country new <em>String</em> country value
	 */

	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * returns hobbies of the group
	 * @return hobbies <em>String</em> hobbies of the group
	 */

	public String getHobbies() {
		return hobbies;
	}

	/**
	 * sets hobbies of the group to new String value
	 * @param hobbies new <em>String</em> hobbies of the group
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	/**
	 * returns current user that is displaying the group page
	 * @return currentUser 
	 */

	public User getCurrentUser() {
		return currentUser;
	}
	/**
	 * sets current user to new User value
	 * @param currentUser new <em>User</em> current user of the group
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	/**
	 * returns the creator of the Group
	 * @return creator 
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * sets creator of the group to new User value
	 * @param creator new <em>User</em> creator of the group
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * returns the list of the users
	 * @return Users 
	 */
	public ArrayList<User> getUsers() {
		return Users;
	}

	/**
	 * sets the list of the users of the group to new list.
	 * @param allUsers new <em>ArrayList</em> users of the group
	 */
	public void setUsers(ArrayList<User> allUsers) {
		this.Users = allUsers;
	}
	/**
	 * returns the list of the contents of the group
	 * @return allContents
	 */
	public LinkedList<Content> getAllContents() {
		return allContents;
	}
	/**
	 * sets the list of the contents of the group to new list
	 * @param allContents new <em>LinkedList</em> contents of the group
	 */

	public void setAllContents(LinkedList<Content> allContents) {
		this.allContents = allContents;
	}

	/**
	 * returns the list of the all groups that have been created
	 * @return allGroups 
	 */
	public static ArrayList<GroupPage> getAllGroups() {
		return allGroups;
	}
	/**
	 * sets the name of the group to new String
	 * @param groupName new <em>String</em> group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * returns the name of the group
	 * @return groupName 
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * removes the given member from the list of users and remove its contents from contents list
	 * @param member
	 */
	public void removeMember(User member) {
		Users.remove(member);
		for(Content current: member.getContents()) {
			allContents.remove(current);
		}
	}
	/**
	 * Adds given member to users list and add its contents to the content list
	 * @param newMember new User instance to add group
	 */
	public void addMember(User newMember) {
		Users.add(newMember);
		for(Content current : newMember.getContents()) {
			allContents.add(current);
		}
		
	}
	

	/**
	 * implemented to refresh the content if any changes are made.
	 */
	@Override
	public void refreshContent() {
		// TODO Auto-generated method stub
		
	}
	


}
