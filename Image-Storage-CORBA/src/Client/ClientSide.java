package Client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA_2_3.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import ImgApp.ImgServer;
import ImgApp.ImgServerHelper;
import ImgApp.Client;

public class ClientSide {

	public static String[] argv;
	/*private static  JFrame frame;
	private static JFrame frame1;
	private static JTextField textField;
	private static JLabel lblNewLabel;
	private static JPasswordField passwordField;*/
	
	public static void BuildFrame() {
		JFrame frame;
		JFrame frame1;
		JTextField textField;
		JLabel lblNewLabel;
		JPasswordField passwordField;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(108, 125, 55, 17);
		frame.getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(207, 123, 155, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("PassWord:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(108, 174, 66, 20);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(207, 174, 155, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				ORB orb = (ORB) ORB.init(argv, null);
				org.omg.CORBA.Object objRef;
				objRef = orb.resolve_initial_references("NameService");
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
				ImgServer ss = ImgServerHelper.narrow(ncRef.resolve_str("Img_Str"));
				
				Client c1 = new Client(0,"",textField.getText(),Integer.parseInt(passwordField.getText()));
				if(ss.GetClient(c1)) {
					JOptionPane.showMessageDialog(null, "Login Succsful");
					frame.setVisible(false);
					ImgFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "Email or Password are incorrect");
				}
				}
				 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(187, 243, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Signup");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SignupFrame();
				
			}
		});
		btnNewButton_1.setBounds(286, 243, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
	
	public static void SignupFrame() {
		
		JFrame frame;
		JFrame frame1;
		JTextField textField;
		JTextField textField_1;
		JLabel lblNewLabel;
		JPasswordField passwordField;
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(85, 134, 55, 20);
		frame.getContentPane().add(lblEmail);
		
		textField = new JTextField();//email
		textField.setBounds(173, 137, 155, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("PassWord:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(85, 179, 89, 20);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();//password
		passwordField.setBounds(173, 182, 155, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(85, 96, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();//Name
		textField_1.setBounds(173, 96, 155, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Signup");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ORB orb = (ORB) ORB.init(argv, null);
				org.omg.CORBA.Object objRef;
				try {
				objRef = orb.resolve_initial_references("NameService");
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
				ImgServer ss = ImgServerHelper.narrow(ncRef.resolve_str("Img_Str"));
				String Name= textField_1.getText();
				String Email=textField.getText();
				int pass= Integer.parseInt(passwordField.getText());
				ss.InputUser(Name,Email,pass);
				JOptionPane.showMessageDialog(null, "Welcome");
				frame.setVisible(false);
				BuildFrame();
				}
				 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				
				
			}
		});
		
		btnNewButton_1.setBounds(215, 234, 113, 23);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
	
	public static void ImgFrame()throws Exception {
	 JFrame frame;
	 JFrame frame1;
	 JTextField textField;
	 
		frame = new JFrame();
		BufferedImage img = null;
		frame.setBounds(100, 100, 542, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblImageName = new JLabel("Image Name:");
		lblImageName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblImageName.setBounds(61, 11, 112, 21);
		frame.getContentPane().add(lblImageName);
		
		textField = new JTextField();
		textField.setBounds(184, 14, 112, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//frame1= new JFrame(img);
		JLabel lblNewLabel = new JLabel(new ImageIcon(""));
		lblNewLabel.setBounds(10, 43, 506, 254);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					ORB orb = (ORB) ORB.init(argv, null);
					org.omg.CORBA.Object objRef;
					objRef = orb.resolve_initial_references("NameService");
					NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
					ImgServer ss = ImgServerHelper.narrow(ncRef.resolve_str("Img_Str"));
					String imgPath = textField.getText();
					byte[] b=ss.Getimg(imgPath);
					frame.setVisible(false);
					ShowImgFrame(b);
					}
					 catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					}
				
			}
		});
		btnSearch.setBounds(207, 308, 89, 23);
		frame.getContentPane().add(btnSearch);
		frame.setVisible(true);
	}
	public static void ShowImgFrame(byte [] b) 
	{
		 JFrame frame;
		 JFrame frame1;
		 JTextField textField;
		 
			frame = new JFrame();
			BufferedImage img = null;
			frame.setBounds(100, 100, 542, 393);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel(new ImageIcon(b));
			lblNewLabel.setBounds(10, 43, 506, 254);
			frame.getContentPane().add(lblNewLabel);
			
			JButton btnSearch = new JButton("Back");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setVisible(false);
					try {
						ImgFrame();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnSearch.setBounds(207, 308, 89, 23);
			frame.getContentPane().add(btnSearch);
			
			frame.setVisible(true);
	}
	
	public static void main(String[] args)throws Exception {
		
		
		// TODO Auto-generated method stub
		argv=args;
		/*ORB orb = (ORB) ORB.init(args, null);
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		ImgServer ss = ImgServerHelper.narrow(ncRef.resolve_str("Img_Str"));*/
		BuildFrame();
		
	}

}
