package ie.gmit.sw.DictionaryService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceRunner {
	public static void main(String[] args) throws Exception
	{
		
		DictionaryService ss = new DictionaryServiceImpl("dictionary.csv");
		//Start the RMI regstry on port 1099
		LocateRegistry.createRegistry(1099);
		
		//Bind our remote object to the registry
		Naming.rebind("dictionaryService", ss);
		
		System.out.println("Server ready");

	}

}