package Server;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA_2_3.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.omg.CORBA.*;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

import javax.imageio.ImageIO;

import ImgApp.Client;
import ImgApp.ImgServer;
import ImgApp.ImgServerHelper;
import ImgApp.ImgServerPOA;
import ImgApp.Client;

public class ImgStorageServer extends ImgServerPOA {
	
	public static ArrayList<Client> Clients=new ArrayList<Client>();
	
	public ImgStorageServer()throws Exception {
		GetAllclients();
	}
	
	public void SaveClients() throws FileNotFoundException, IOException {
		ObjectOutputStream OutTofile = new ObjectOutputStream(new FileOutputStream("ClientRecords.txt"));
		OutTofile.writeObject(Clients);
		OutTofile.close();
	}
	
	public void GetAllclients()throws Exception {
		ObjectInputStream Input = new ObjectInputStream(new FileInputStream("ClientRecords.txt"));
		Clients=(ArrayList<Client>) Input.readObject();
		Input.close();
	}
	@Override
	public void InputUser(String name, String email, int pass) {
		// TODO Auto-generated method stub
		try {
			int id = Clients.size()+1;
			Clients.add(new Client(id,name,email,pass));
			this.SaveClients();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Name: "+name);
		
	}

	@Override
	public boolean GetClient(Client c1) {
		// TODO Auto-generated method stub
		System.out.println("GottenName: "+c1.email+"\n GottenPassword: "+c1.password );
		for(int i =0;i<Clients.size();++i) 
		{
			System.out.println("email : "+Clients.get(i).email);
			System.out.println("password : "+Clients.get(i).password);
			if(Clients.get(i).email.equals(c1.email)&& Clients.get(i).password==c1.password ) {
				return true;	
			}
		}
		return false;
	}

	@Override
	public byte[] Getimg(String ImgName) {
		try {
		File f= new File(ImgName);
		byte[] b=Files.readAllBytes(f.toPath());
		return b;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		ORB orb = (ORB) ORB.init(args, null);
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();
		
		ImgStorageServer ssi = new ImgStorageServer();

		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(ssi);
		ImgServer ss = ImgServerHelper.narrow(ref);
		
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		
		NameComponent path[] = ncRef.to_name("Img_Str");
		ncRef.rebind(path, ss);
		
		
		System.out.println("Server is Running...");
		orb.run();

		
		//SaveClients();
	}

	

}
