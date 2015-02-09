package game;


import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class GameTimer {
	 private static final String EOF = new String();

	 private final SynchronousQueue<String> pipe = new SynchronousQueue<String>();

	 private static BufferedReader input;
     String textRead;
     private long timeout;
     private BufferedReader in;
	 private static GameTimer sInstance = null; 
	 private GameTimer(BufferedReader input, long timeout) { 
		 
		 this.input = input;
		 this.timeout = timeout;
	 } 
		
     public synchronized static GameTimer getInstance() {
		if (sInstance == null)
		sInstance = new GameTimer(null,0);
		return sInstance;
		}
	
		
		
		
	public String GetTime(int y)
	    throws Exception
	    {
		   
	     in = new BufferedReader(new InputStreamReader(System.in));
	     try{
				
				Robot robot = new Robot();
		   
				robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		        robot.mousePress(InputEvent.BUTTON1_MASK);
			      
			    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (Exception e) {
		        e.printStackTrace();
		}
	    GameTimer input =  new GameTimer(in, y);
	   
	    textRead = input.read();

	    return textRead;
	  }

	  String read() throws InterruptedException, IOException
	  {
		 
	    Thread t = new Consumer();
	    
	       t.start();
	       String line = ""; 
	      
	       line = pipe.poll(timeout, TimeUnit.MILLISECONDS);
            
	       
	      if (line == EOF)
	    	  line="input format wrong"; 
	      
	      if (line == null) {
	          t.interrupt();
	         // input.close();
	      }
	      return line;
	      
	  }

	  private class Consumer
	    extends Thread
	  {
	    Consumer()
	    {
	      setDaemon(true);
	    }

	    @Override
	    public void run()
	    {
	    	
	    	while (!Thread.interrupted()) {
	    		//System.out.print("hello");
	        String line;
	        try {
	         
	          line = input.readLine();
	           
	        }
	        catch (IOException ex) {
	          throw new RuntimeException(ex);
	        }
	        
	        try {
	        	
	          if ((line == null) || (line.length() == 0)) {
	           pipe.put(EOF);
	            //break;
	          }
	          else {
	            pipe.put(line);
	            //break;
	          }
	        }
	        catch (InterruptedException ex) {
	          break;
	        }
	      }
	    
	    }
	    
	  }

	  
	  
	  boolean checkTime(String theText) 
	 	{
		  
	 		return  theText.equals("timeout");
	 		
	 	}
	
	
}
