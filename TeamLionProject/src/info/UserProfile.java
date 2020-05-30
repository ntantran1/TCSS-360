package info;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6160667524963929997L;
	public String firstName;
	public String email;

	public UserProfile() {
		this.firstName = "N/A";
		this.email = "N/A";
	}
	
	public UserProfile(String firstName, String email)
	{   
		this.firstName = firstName;
		this.email = email;
	}
	public String toString()
	{
		return firstName + " " + email;
	}
	
	public void exportProfile()
	{
		JFileChooser files = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		files.setCurrentDirectory(workingDirectory);
		
		files.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		files.setAcceptAllFileFilterUsed(false);
		files.showOpenDialog(null);
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{ 
			fout = new FileOutputStream(files.getSelectedFile() + "/profile.ser", false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
			fout.close();
		} catch (Exception ex) {}
		finally {
			if(oos != null){
				try {
					oos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	public void importProfile()
	{
		JFileChooser files = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		files.setCurrentDirectory(workingDirectory);
		
		ObjectInputStream ois = null;
		FileInputStream fin = null;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("profile","ser");
		files.addChoosableFileFilter(filter);
		files.setAcceptAllFileFilterUsed(true);
		files.showOpenDialog(null);
		File file = files.getSelectedFile();
		UserProfile input = new UserProfile();
		try{ 
			fin = new FileInputStream(file);
			ois = new ObjectInputStream(fin);
			input = (UserProfile)ois.readObject();
			ois.close();
			fin.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(ois != null){
				try {
					ois.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		email = input.email;
		firstName = input.firstName;
	}
}
