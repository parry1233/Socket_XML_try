package try_XML;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;


public class Client
{
	private String address="127.0.0.1";
	private int port=9997;
	static boolean finished=false;
	
	public static void main(String[] args)
	{
		Client client=new Client();
	}

	public Client()
	{
		//List<Patient> patList;
		//XML_Reader reader=new XML_Reader();
		//patList=reader.all_PATread();
		
		Socket client_socket=new Socket();
		
		InetSocketAddress isa=new InetSocketAddress(this.address, this.port);
		
		DataInputStream input=null;
		DataOutputStream output=null;
		
		try
		{
			client_socket.connect(isa, 10000);
			input=new DataInputStream(client_socket.getInputStream());
			output=new DataOutputStream(client_socket.getOutputStream());
			String s;
			if(!finished)
			{
				Scanner in=new Scanner(System.in);
				System.out.print("[Client](1)New Xml (2)Rewrite Xml (3)End:");
				int choose=in.nextInt();
				//output.writeUTF(String.valueOf(guess));
				//output.flush();
				//s=input.readUTF();
				//System.out.println(s);
				if(choose==1)
				{
					System.out.println("Writing new xml....");
					s=input.readUTF();
					FileWriter out=new FileWriter("Doctors1.xml",false);
					
					out.write(s);
					out.close();
				}
				else if(choose==2)
				{
					System.out.println("Rewriting xml....");
					s=input.readUTF();
					FileWriter out=new FileWriter("Doctors.xml",false);
					
					out.write(s);
					out.close();
				}
				else if(choose==3)
				{
					System.out.println("Socket connection error!");
					//set the check boolean to true 
					finished=true;
					//close input, output and client socket.
					output.close();
					input.close();
					client_socket.close();
				}
			}
		}
		catch (java.io.IOException e)
		{
			System.out.println("Socket connection error!");
			System.out.println("IOException: "+e.toString());
		}
	}
}
