/* Pledge of Honor *************************************************************************************
I hereby certify that I have completed this programming project on my own without any help from
anyone else. The effort in the project thus belongs completely to me. I did not search for a
solution, or I did not consult to any program written by others or did not copy any program from
other sources. I read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Ebubekir Karamustaf, 0076289>

*/

package main;

import java.sql.Timestamp;
import java.util.Date;

import content.Content;
import pages.GroupPage;
import pages.HomePage;
import pages.LoginPage;
import pages.Page;
import pages.User;

/**
 * The class main is a base class where the transition between pages are controlled
 * @author Ebubekir Karamustafa
 *
 */



public class main {
	
	
	//FOR DEMO PURPOSES

	
	public static boolean isHomePageContentsAdded;
	
	static User chandler = new User("chandler", "bingbing", "Chandler","Bing",29,"chandlerbing@friends.com",true,"chandler.jpeg","Joking");
	static User joey = new User("joey","girlsandfood","Joey","Tribbiani", 29, "joeytribbiani@friends.com",true,"joey.jpeg","girls and food no janice");
	static User phoebe = new User("phoebe","smellycat","Phoebe","Buffay",29,"phoebebuffay@friends.com",true,"pheobe.jpeg","smelly cat and mike");
	static User ross = new User("ross","dinosaur","Ross","Geller",29,"rossgeller@friends.com",true,"ross.jpeg","we were on a break, dinosaurs, holiday armadillo, Ben, maybe Rachel");
	static User rachel = new User("rachel","ralphlauren","Rachel","Green", 29,"rachelgreen@friends.com",true,"rachel.jpeg","Drums, Weekend At Bernie's");
	static User monica = new User("monica","cleaning","Monica","Geller",29,"monicageller@friends.com",true,"monica.jpeg","Cleaning Cleaning cooking, and 7!!");
	static User morty = new User("morty","jessica","Morty","Smith",14,"mortysmith@rickandmorty.com",false,"morty.jpeg","Pickle Rick and Jessica");
	static User rick = new User("rick","pickle","Rick","Sanchez",70,"ricksanchez@rickandmorty.com",false,"rick.jpeg","Birdperson, Science");
	static User peter = new User("peter","television","Peter","Griffin",44,"petergriffin@familyguy.com",false,"peter.jpeg","star trek, watching tv with guys");
	static User stewie = new User("stewie","stewie","Stewie","Griffin",1,"stewiegriffing@familyguy.com",false,"stewie.jpeg","science, violence, world domination");
	
	
	static User ebu = new User("ebu", "5353","Ebubekir","Karamustafa",20,"ekaramustafa20@ku.edu.tr",true,null,"Playing piano and Comp132 (especially when gets high score from the project)");
	static User neo = new User("neo","12345","Neo","Drake",29,"neo_drake@gmail.com",true,null,"");
	static User marge = new User("marge", "12345", "Margaret", "Camacho", 25, "margecamacho@gmail.com",true,null,"");
	
	static String contentText= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, "
			+ "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
			+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
			+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	static Content content1 = new Content("I'VE MADE A CUSTARD!!",new Timestamp(new Date().getTime()),rachel,contentText,"custard.jpeg");
	static Content content2 = new Content("WHAT A STORY HUH!",new Timestamp(new Date().getTime()),peter,"","story.jpeg");
	static Content content3 = new Content("A STORY ON DINASOURS", new Timestamp(new Date().getTime()), ross, contentText,"office.jpeg");
	static Content content4 = new Content("CHECK THIS OUT !!!", new Timestamp(new Date().getTime()), chandler, contentText,"centralperk.jpeg");
	static Content content5 = new Content("JAZZ LEGENDS: MILES DAVIS", new Timestamp(new Date().getTime()), ebu, contentText,"milesdavis.jpeg");
	static Content content6 = new Content("JAZZ LEGENDS: CHICK COREA",new Timestamp(new Date().getTime()), ebu, contentText,"chickcorea.jpeg");
	static Content content7 = new Content("AnOtHeR AdVaNtUrE", new Timestamp(new Date().getTime()), rick,"WABBA WABBA DUB DUB","rickmorty.jpeg");
	///

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		///FOR DEMO PURPOSES
		ebu.follow(rick);
		ebu.follow(monica);
		ebu.follow(joey);
		ebu.follow(peter);
		
		monica.follow(ross);
		monica.follow(chandler);
		monica.follow(phoebe);
		monica.follow(rachel);
		monica.follow(joey);
		
		ross.follow(ross);
		ross.follow(chandler);
		ross.follow(phoebe);
		ross.follow(rachel);
		ross.follow(joey);
		
		chandler.follow(ross);
		chandler.follow(chandler);
		chandler.follow(phoebe);
		chandler.follow(rachel);
		chandler.follow(joey);
		
		phoebe.follow(ross);
		phoebe.follow(chandler);
		phoebe.follow(phoebe);
		phoebe.follow(rachel);
		phoebe.follow(joey);
		
		rachel.follow(ross);
		rachel.follow(chandler);
		rachel.follow(phoebe);
		rachel.follow(rachel);
		rachel.follow(joey);
		
		
		joey.follow(ross);
		joey.follow(chandler);
		joey.follow(phoebe);
		joey.follow(rachel);
		joey.follow(joey);
		
		peter.follow(stewie);
		peter.follow(rick);
		peter.follow(morty);
		
		morty.follow(rick);
		morty.follow(ebu);
		morty.follow(marge);
		
		rick.follow(ebu);
		rick.follow(neo);
		rick.follow(stewie);
		
		stewie.follow(rick);
		stewie.follow(peter);
		stewie.follow(morty);
		
		neo.follow(rick);
		neo.follow(ebu);
		neo.follow(marge);
		
		marge.follow(morty);
		marge.follow(ebu);
		marge.follow(neo);
		
		GroupPage jazz = new GroupPage("Jazz Listeners","Turkey", "Playing jazz, dancing a lot", ebu);
		neo.joinGroup(jazz);
		marge.joinGroup(jazz);
		rick.joinGroup(jazz);
		
		GroupPage centralPerk = new GroupPage("CentralPerk","USA","Enjoying with friends at central perk", monica);
		chandler.joinGroup(centralPerk);
		ross.joinGroup(centralPerk);
		joey.joinGroup(centralPerk);
		phoebe.joinGroup(centralPerk);
		rachel.joinGroup(centralPerk);
		
		
		GroupPage familyGuy = new GroupPage("Family Guy", "Nigeria","Being members of family guy",peter);
		stewie.joinGroup(familyGuy);
		ebu.joinGroup(familyGuy);
		
		GroupPage rickAdventures = new GroupPage("Rick's Journey","China","ENJOY RICK'S ADVANTURE, Wubba Lubba Dub Dub",rick);
		morty.joinGroup(rickAdventures);
		ebu.joinGroup(rickAdventures);
		stewie.joinGroup(rickAdventures);

		GroupPage paleontology = new GroupPage("Paleontology","USA","Dinasours!!",ross);
		
		////
		

		launchApp();
		
		
		
		
		
	
		
		
		
		

	}
	/**
	 * Controls the application flow.
	 * First initialize Login Page and assign isLoginPageOpen to true
	 * <p> 
	 * <b> Local boolean control variables </b>
	 * <p> <em> isHomePageOpen </em> checks whether any Home Page is initialized, initially false</p>
	 * <p> <em> isLoginPageOpen </em> checks whether any Login Page is initialized, initially true</p>
	 * <p> <em> isGroupPageOpen </em> checks whether any Group page is initialized, initially false</p>
	 * <p> <em> isProfilPageOpen </em> checks whether any Profile page is initialized, initially false</p>
	 * <p></p>
	 * <p> <b>HomePage's static public boolean variables are used here as well </b> </p>
	 * <p> <em> returnLoginPage, returnHomePage, returnProfilPage, returnGroupPage </em> are used to check if the user make a transition between pages </p>
	 * </p>
	 */
	
	public static void launchApp() {
		LoginPage loginpage = new LoginPage();
		boolean isLoginPageOpen = true;
		boolean isHomePageOpen = false;
		boolean isGroupPageOpen = false;
		boolean isProfilPageOpen = false;
		
		/**
		 * enter a infinite loop to check whether any type of page is initialized.
		 */
		while(true) {
		/**
		 * If Login Page is opened then make it visible and check whether it is opened every frame with isLoginPageOpen
		 */
		while(isLoginPageOpen || (HomePage.returnLoginPage && !HomePage.returnProfilPage && !HomePage.returnHomePage)) {
			if(HomePage.returnLoginPage) {
				/**
				 * if HomePage.returnLoginPage is true then that means the user wanted to returned Profil Page
				 * Assign it to false since user does not request to return Profil Page in every frame. 
				 */
				HomePage.returnHomePage = false;
				HomePage.returnProfilPage = false;
				HomePage.returnGroupPage = false;
				loginpage.setVisible(true);
				HomePage.returnLoginPage = false;
			}
			isLoginPageOpen = loginpage.isVisible(); 
			System.out.println("");
		}
		/**
		 * After Login Page is closed, initialize a Home Page with logged in user. 
		 */
		HomePage homepage = new HomePage(loginpage.getLoggedUser());
		//FOR DEMO PURPOSES
		if(isHomePageContentsAdded == false) {
			addContent(homepage, content1,content2,content3,content4,content5,content6,content7);
			isHomePageContentsAdded = true;
			homepage.refreshContent();
		}
		isHomePageOpen = homepage.isVisible();
		
		/**
		 * Check Home Page is still open every frame with isHomePageOpen.
		 */
		while(isHomePageOpen || (HomePage.returnHomePage && !HomePage.returnLoginPage && !HomePage.returnProfilPage)) {
			if(HomePage.returnHomePage) {
				/**
				 * if HomePage.returnHomePage is true then that means the user wanted to returned Home Page
				 * Assign it to false since user does not request to return Home Page in every frame. 
				 */
				HomePage.returnLoginPage = false;
				HomePage.returnProfilPage = false;
				homepage.setVisible(true);
				HomePage.returnHomePage = false;
			}
			isHomePageOpen = homepage.isVisible();
			System.out.println("");
			

			//
		}
		/**
		 * Check Group Page is opened every frame with isGroupPageOpen.
		 */
		while(isGroupPageOpen || HomePage.returnGroupPage) {
			System.out.println("");
		}
		/*
		 * Initilaze a User instance with logged in user.
		 */
		User profilpage = new User(loginpage.getLoggedUser());
		isProfilPageOpen = profilpage.isVisible();
		
		/**
		 * Check Profil Page is still open every frame with isProfilPageOpen.
		 */
		while(isProfilPageOpen || (HomePage.returnProfilPage && !HomePage.returnLoginPage && !HomePage.returnHomePage)) {
			if(HomePage.returnProfilPage) {
				/**
				 * if HomePage.returnProfilPage is true then that means the user wanted to returned Profil Page
				 * Assign it to false since user does not request to return Profil Page in every frame. 
				 */
				HomePage.returnLoginPage = false;
				HomePage.returnHomePage = false;
				profilpage.setVisible(true);
				HomePage.returnProfilPage = false;
				HomePage.returnGroupPage = false;
			}
			isProfilPageOpen = profilpage.isVisible();
			System.out.println("");
			
		}
		
		
		}
	}
	/**
	 * FOR DEMO
	 * @param page
	 * @param content
	 */
	//THIS METHOD FOR DEMO
	//adds the initially created contents to right places
	public static void addContent(Page page, Content... content ) {
		if(page instanceof HomePage) {
			for(Content current:content) {
				HomePage.contents.add(current);
			}
		
	}
	////

	}
}
