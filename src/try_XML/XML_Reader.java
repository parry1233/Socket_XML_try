package try_XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class XML_Reader
{
	public XML_Reader()
	{
		
	}
	
	public List<Doctor> all_DOCread()
	{
		List<Doctor> doclist=new ArrayList<>();
		GetAllDoc_handler handler=new GetAllDoc_handler();
		Doctor d=new Doctor();
		
		try 
		{
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser saxParser=factory.newSAXParser();
			saxParser.parse("Doctors.xml", handler);
		}
		catch(Exception e)
		{
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<handler.Read().get(0).size();i++)
    	{
    		d=new Doctor(handler.Read().get(0).get(i),handler.Read().get(1).get(i),handler.Read().get(2).get(i),handler.Read().get(3).get(i),handler.Read().get(4).get(i),handler.Read().get(5).get(i),handler.Read().get(6).get(i),handler.Read().get(7).get(i),handler.Read().get(8).get(i),handler.Read().get(9).get(i),handler.Read().get(10).get(i),handler.Read().get(11).get(i),handler.Read().get(12).get(i));
    		doclist.add(d);
    	}
    	
        return doclist;
	}
	
	public List<Patient> all_PATread()
    {
		List<Patient> patList=new ArrayList<>();
		GetAllPat_handler handler=new GetAllPat_handler();
    	Patient p=new Patient();
    	try 
		{
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser saxParser=factory.newSAXParser();
			saxParser.parse("Patients.xml", handler);
		}
		catch(Exception e)
		{
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for(int i=0;i<handler.Read().get(0).size();i++)
    	{
    		p=new Patient(handler.Read().get(0).get(i),handler.Read().get(1).get(i),handler.Read().get(2).get(i),handler.Read().get(3).get(i),handler.Read().get(4).get(i),handler.Read().get(5).get(i),handler.Read().get(6).get(i),handler.Read().get(7).get(i),handler.Read().get(8).get(i),handler.Read().get(9).get(i),handler.Read().get(10).get(i));
    		patList.add(p);
    	}
    	
        return patList;
    }
}
