package ie.gmit.sw.DictionaryService;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import com.opencsv.CSVReader;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {

	private static final long serialVersionUID = 1L;
	private Map<String, String> dict;
	private File file;
	private CSVReader reader;
	private String next[];

	public DictionaryServiceImpl(String filename) throws IOException, RemoteException {
		dict = new HashMap<>();
		file = new File(filename); //Initialize file for parsing.
		fillDictionary(); //Try to fill the dictionary with definitions.
	}
	
	public void fillDictionary() throws RemoteException, IOException {
		System.out.println("Filling dictionary");
		
		//http://opencsv.sourceforge.net/apidocs/com/opencsv/CSVReader.html
		reader = new CSVReader(new FileReader(file));
		
		while ((next = reader.readNext()) != null) { //Loop through each line in the dictionary file
			dict.put(next[0].toUpperCase(), next[2].toUpperCase());
			//System.out.println("Word "+next[0].toUpperCase()+" \t Definition: "+next[2]);
		}
		
		System.out.println("Dictionary filled. "+dict.size()+" words defined in dictionary.");
	}//fillDictionary
	//Implemented RMI method
	public String lookup(String wordToSearch) throws RemoteException {
		//Check for the word in our map
		if(dict.containsKey(wordToSearch)) {
			return(dict.get(wordToSearch));
		}
		else {
			System.out.println(wordToSearch);
			return ("String not found");
		}
		
	}//lookup
}
