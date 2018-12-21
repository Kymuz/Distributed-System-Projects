package FirstProg;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.bind.ParseConversionEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.*;
import java.io.*;

public class MainGui {

	private static JFrame Frame1;
	private static JTextField Tf;
	private static JTextField Tf_1;
	private static JTextField Tf_2;
	private static JComboBox combo_Box;
	public static ArrayList<Student> Students;
	
	
	public static void buildframe() 
	{
		Frame1= new JFrame();
		Frame1.setBounds(100,100,450,300);
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame1.getContentPane().setLayout(null);
		//FirstName
		JLabel lbl_FirstName=new JLabel("First Name:");
		lbl_FirstName.setBounds(10, 35, 77, 14);
		Frame1.getContentPane().add(lbl_FirstName);
		
		Tf=new JTextField();
		Tf.setBounds(97, 32,86, 20);
		Frame1.getContentPane().add(Tf);
		
		//LastName
		JLabel lbl_LastName=new JLabel("Last Name:");
		lbl_LastName.setBounds(10, 63, 77, 14);
		Frame1.getContentPane().add(lbl_LastName);
		
		Tf_1=new JTextField();
		Tf_1.setBounds(97, 60, 86, 20);
		Frame1.getContentPane().add(Tf_1);
		
		//Age
		JLabel lbl_Age=new JLabel("Age:");
		lbl_Age.setBounds(10, 88, 52, 14);
		Frame1.getContentPane().add(lbl_Age);
		
		Tf_2=new JTextField();
		Tf_2.setBounds(97, 85, 86, 20);
		Frame1.getContentPane().add(Tf_2);
		
		//fav_Course
		JLabel lbl_FavCourse = new JLabel("Fav_Course:");
		lbl_FavCourse.setBounds(10, 118, 77, 14);
		Frame1.getContentPane().add(lbl_FavCourse);
		
		combo_Box = new JComboBox();
		combo_Box.setModel(new DefaultComboBoxModel(new String[] {"Math", "English", "French", "Science", "History", "Geography"}));
		combo_Box.setBounds(97, 115, 86, 20);
		Frame1.getContentPane().add(combo_Box);
		
		JButton Submitbtn= new JButton("Submit");
		Submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Submitbtn();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					e.getMessage();
					
				} 
			} 	
		});
		Submitbtn.setBounds(175, 206, 89, 23);
		Frame1.getContentPane().add(Submitbtn);
		
		
		JButton Savebtn = new JButton("Save");
		Savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar0) {
				try {
					Savebtn();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Savebtn.setBounds(274, 206, 89, 23);
		Frame1.getContentPane().add(Savebtn);
		Frame1.setVisible(true);
		
	}
	
	public static void Submitbtn() throws Exception
	{
		
		//Students=new ArrayList<Student>();
		String fname = Tf.getText();
		String lname=Tf_1.getText();
		int age = Integer.parseInt(Tf_2.getText());
		String Fav_course=(String) combo_Box.getSelectedItem();
		Students.add(new Student(fname,lname,age,Fav_course));
		Tf.setText("");
		Tf_1.setText("");
		Tf_2.setText("");
		
		//Student s =(Student)Students.get(0);
		//System.out.println(s);
	}
	
	public static void Savebtn()throws Exception 
	{
		ObjectOutputStream OutTofile = new ObjectOutputStream(new FileOutputStream("StudetSheet.txt"));
		OutTofile.writeObject(Students);
		OutTofile.close();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		buildframe();
		Students = new ArrayList<Student>();
		ObjectInputStream Input = new ObjectInputStream(new FileInputStream("StudetSheet.txt"));
		Students=(ArrayList<Student>) Input.readObject();
		Input.close();
		//Students.get(0).getFirstName();
		/*for(int i =0;i<Students.size();++i) 
		{
			System.out.println(Students.get(i));
		}*/
		
		
	}

}
