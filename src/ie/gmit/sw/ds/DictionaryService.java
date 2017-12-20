package ie.gmit.sw.ds;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote{
	
	public String searchForDefinition() throws RemoteException;
	
}
