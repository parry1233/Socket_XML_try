package try_XML;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;


public class Server extends Thread
{	
	public static final int Listen_port=9997;
	
	public static void main(String[] args)
	{
		Server server=new Server();
		server.start();
	}
	
	@Override
	public void run()
	{
		List<Doctor> docList;
		XML_Reader reader=new XML_Reader();
		docList=reader.all_DOCread();
		
		try
		{
			ServerSocket serverSocket=new ServerSocket(Listen_port);
			System.out.println("[Server]Waiting for connect...");
			Socket socket=serverSocket.accept();
			System.out.println("[Server]Connect Success!!");
			serverSocket.close();
			
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output =new DataOutputStream(socket.getOutputStream());
			
			String s;
			s="<?xml version=\"1.0\"?>";
			s+="<Doctors>";
			
			if(!Client.finished)
			{
				for(Doctor d:docList)
				{
					s+="<Doctor id=\""+d.getID()+"\">";
					s+="<password>"+d.getPW()+"</password>";
					s+="<Fname>"+d.getFName()+"</Fname>";
					s+="<Lname>"+d.getLName()+"</Lname>";
					s+="<Department>"+d.getDep()+"</Department>";
					s+="<PhoneNumber>"+d.getPhone()+"</PhoneNumber>";
					s+="</Doctor>";
				}
				
				s+="</Doctors>";
				
				output.writeUTF(s);
				output.flush();
			}
			//close input, output and socket while answer is correct. 
			input.close();
			output.close();
			socket.close();
			
			
		} 
		catch (BindException e) 
		{
			// TODO: handle bind exception
			System.out.println("Address Already in use. Change a port and try it again.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			// TODO: handle IOexception
			Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE,null,e);
		}
	}
}
