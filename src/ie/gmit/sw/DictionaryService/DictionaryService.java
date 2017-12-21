package ie.gmit.sw.DictionaryService;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Interface - DictionaryService
 * 
 * Exposes remote methods to be run by the ServiceImpl.
 * @author ryangordon
 *
 */
public interface DictionaryService extends Remote{
	/**
	 * 
	 * @param lookup
	 * @return <result> OR <"String not found">
	 * @throws RemoteException
	 */
	public String lookup(String wordToSearch) throws RemoteException;
	
	/**
	 * Used to fill the dictionary with definitions.
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void fillDictionary() throws RemoteException, IOException;
	
}
