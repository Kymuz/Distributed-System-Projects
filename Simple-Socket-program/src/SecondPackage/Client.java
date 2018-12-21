package SecondPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import FirstProg.Student;

public class Client {
	
	private static JFrame Frame1;
	private static JTextField Tf;
	private static JTextField Tf_1;
	private static JTextField Tf_2;
	private static JTextField Tf_3;
	private static Socket Client_Socket;
	
	public static void buildframe() 
	{
		Frame1= new JFrame();
		
		Frame1.setBounds(500,250,450,300);
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
		Tf_1.setDisabledTextColor(Color.BLUE);
		Tf_1.disable();
		Frame1.getContentPane().add(Tf_1);
		
		//Age
		JLabel lbl_Age=new JLabel("Age:");
		lbl_Age.setBounds(10, 88, 52, 14);
		Frame1.getContentPane().add(lbl_Age);
		
		Tf_2=new JTextField();
		Tf_2.setBounds(97, 85, 86, 20);
		Tf_2.setDisabledTextColor(Color.BLUE);
		Tf_2.disable();
		Frame1.getContentPane().add(Tf_2);
		
		//fav_Course
		JLabel lbl_FavCourse = new JLabel("Fav_Course:");
		lbl_FavCourse.setBounds(10, 118, 77, 14);
		Frame1.getContentPane().add(lbl_FavCourse);
		
		Tf_3= new JTextField();
		Tf_3.setBounds(97, 115, 86, 20);
		Tf_3.setDisabledTextColor(Color.BLUE);
		Tf_3.disable();
		Frame1.getContentPane().add(Tf_3);
		
		JButton Submitbtn= new JButton("Find");
		Submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Find();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} 	
		});
		Submitbtn.setBounds(175, 206, 89, 23);
		Frame1.getContentPane().add(Submitbtn);
		Frame1.setVisible(true);
	}
	
	public static void Find() throws Exception
	{
		Client_Socket = new Socket("localhost",2560);
		DataOutputStream Send_Output= new DataOutputStream(Client_Socket.getOutputStream());
		ObjectInputStream Receive_obj= new ObjectInputStream(Client_Socket.getInputStream());
		String Name = Tf.getText();
		Send_Output.writeUTF(Name);
		Student s1 = null;
		s1 = (Student)Receive_obj.readObject();
		if(s1!=null)
		{
			Tf_1.setText(s1.getLastName());
			Tf_2.setText(""+s1.getAge());
			Tf_3.setText(s1.getFav_Course());
		}
		else {
			JOptionPane.showMessageDialog(null, "Student Not Found");
			Tf.setText("");
			Tf_1.setText("");
			Tf_2.setText("");
			Tf_3.setText("");
		}
		
		Send_Output.close();
		Receive_obj.close();
		Client_Socket.close();
		
		
	}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		buildframe();
		JOptionPane.showMessageDialog(null, "Welcome\nyour now Connected");
		
	
		
	}

}
