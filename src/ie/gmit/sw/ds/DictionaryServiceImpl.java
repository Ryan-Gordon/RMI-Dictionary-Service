package ie.gmit.sw.ds;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {

	private static final long serialVersionUID = 1L;
	private String word;

	protected DictionaryServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	//Implemented RMI method
	public String searchForDefinition() throws RemoteException {
		//TODO: do stuff to check
		//TODO: Return the definition or 
		return " ";
	}
}
