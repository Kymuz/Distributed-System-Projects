package SecondPackage;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import FirstProg.Student;
public class Server implements Runnable {

	Socket ClientSocket ;
	public static ArrayList<Student> Students;
	
	public Server(Socket ClientSocket) throws Exception
	{
		
		this.ClientSocket=ClientSocket;
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream Input = new ObjectInputStream(new FileInputStream("StudetSheet.txt"));
			Students=(ArrayList<Student>) Input.readObject();
			Input.close();
			DataInputStream Get_Input= new DataInputStream(ClientSocket.getInputStream());
			ObjectOutputStream Send_Obj = new ObjectOutputStream(ClientSocket.getOutputStream());
			//DataOutputStream Send_Output= new DataOutputStream(ClientSocket.getOutputStream());
			String Name=Get_Input.readUTF();
			Student S = null;
			boolean check=true;
			for(int i =0;i<Students.size();++i) 
			{
				if( Students.get(i).getFirstName().toLowerCase().equals(Name.toLowerCase())) 
				{
					 S = (Student) Students.get(i);
					Send_Obj.writeObject(S);
					check =false;
				}
			}
			if(check)
			{
				Send_Obj.writeObject(S);
			}
			
			Get_Input.close();
			Send_Obj.close();
			//Send_Output.close();
			ClientSocket.close();
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	
	public static void main(String[] args)throws Exception  {
		// TODO Auto-generated method stub
		
		
		ServerSocket ServSocket= new ServerSocket(2560);
		JOptionPane.showMessageDialog(null, "Server online");
		while(true) 
		{
			Socket ClientS= ServSocket.accept();
			Thread NewClient = new Thread(new Server(ClientS));
			NewClient.start();
		}
	}

	

}
