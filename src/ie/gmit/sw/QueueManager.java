package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

import ie.gmit.sw.DictionaryService.DictionaryService;

public class QueueManager implements Runnable {
	private Queue<String> inQueue;
	private Queue<String> outQueue;
	private String response;
	private String jobNumber;

	public QueueManager(long jobNumber,String word) {
		inQueue = new LinkedList<>();
		outQueue = new LinkedList<>();
		this.jobNumber = new String("T" + jobNumber);
		inQueue.add(word);
	}

	public void dispatch() throws RemoteException, MalformedURLException, NotBoundException {
			if ( !inQueue.isEmpty()) { 
				System.out.println("Connecting to RMI service.");
				DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService"); // get remote obj
				
				System.out.println("Attempting to send job to remote object");
				this.response = ds.lookup(inQueue.poll().toUpperCase());
				
				//Add our response to the out queue.
				outQueue.add(response);
				
			} else {
				System.out.println("Queue is empty; nothing to process");
			}
	}// dispatch method
	
	public String getResponse() throws InterruptedException {
		//Check if there is anything on the queue.
		if(outQueue.peek() != null)
			return outQueue.poll();
		else {
			//Send thread to sleep for a little bit
			Thread.sleep(2000);
			//Try to get a response again.
			return getResponse();
		}
			
	}
	public String getJobNumber() {
		return jobNumber;
	}
	/**
	 * On Run we want to invoke the dispatch method to contact the RMI server.
	 */
	public void run() {
		
		try {
			dispatch();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
