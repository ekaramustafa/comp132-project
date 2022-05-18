package dialog;

import java.awt.BorderLayout;
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

import content.Content;
import pages.Page;


/**
 * JDialog for editing content
 * @author Ebubekir Karamustafa
 *
 */

public class EditContentDialog extends JDialog implements ActionListener{
	
	/**
	 * content which is going to be edited
	 */
	private Content selected;
	/**
	 * parent for JDialogs
	 */
	private Page parent;
	
	/**
	 * title label
	 */
	private JLabel titleLabel;
	/**
	 * content label
	 */
	private JLabel contentLabel;
	/**
	 * field for typing new title
	 */
	private JTextField title;
	/**
	 * filed for typing the new content
	 */
	private JTextArea content;
	/**
	 * image Label
	 */
	private JLabel image;
	
	/**
	 * file chooser for new image
	 */
	private JFileChooser chooser;
	
	/**
	 * for applying the changes
	 */
	private JButton apply;

	/**
	 * to open file chooser
	 */
	private JButton choose;
	
	/**
	 * selected file by file chooser
	 */
	private File selectedFile;
	
	/**
	 * Initializes the Dialog with field variables
	 * @param parent class that instansiated the this dialog
	 * @param selected content which is to be edited
	 */
	public EditContentDialog(Page parent, Content selected) {
		super(parent);
		setSize(600,600);
		setTitle("Edit Group");
		setModal(true);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		
		//init of the selected content
		this.selected = selected;
		
		//init of the parent
		this.parent = parent;
		
		//init of the title label
		titleLabel = new JLabel("Change title to");
		
		//init of the content label
		contentLabel = new JLabel("Change content to");
		
		//init of the image label
		image = new JLabel("Change image to");
		
		//init of the field for typing new title
		title = new JTextField();
		
		//init of the new text of the content
		content = new JTextArea();
		content.setLineWrap(true);
		
		//init of the apply & choose buttons
		apply = new JButton("Apply");
		choose = new JButton("Choose");
		apply.addActionListener(this);
		choose.addActionListener(this);
		
		//topPanel for design purposes
			
			//init of generalMargin
			Border generalMargin = BorderFactory.createEmptyBorder(10,10,10,10);

			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(2,2,5,5));
			topPanel.setBorder(generalMargin);
			
			topPanel.add(titleLabel);
			topPanel.add(title);
			topPanel.add(image);
			topPanel.add(choose);
		
		//centerPanel for design purposes
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			centerPanel.setBorder(generalMargin);
			
			centerPanel.add(contentLabel,BorderLayout.NORTH);
			centerPanel.add(content, BorderLayout.CENTER);
			centerPanel.add(apply,BorderLayout.SOUTH);
			
		//adds two panel to current frame
		add(topPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	/**
	 * actionPerformed method is overridden to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//apply button checks whether any changes have been made, if yes assign replace changed values with new values. 
		if(arg0.getSource() == apply) {
			if(!content.getText().isEmpty()) {
				selected.setContent(content.getText());
			}
			if(!title.getText().isEmpty()) {
				selected.setTitle(title.getText());
			}
			if(selectedFile != null) {
				selected.setIconByPath(selectedFile.getAbsolutePath());
			}
			dispose();
			
		}
		//chooseFileButton initialize the filechooser with a filter then initialize the selected file if the user selected right type of document
		if(arg0.getSource() == choose) {
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
	
	
}