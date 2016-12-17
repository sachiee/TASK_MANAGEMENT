package taskManagement;
import java.io.Reader;
import java.io.Writer;

public class CloseHelper {

	// writer part
    public static void close(Writer c) {
    	  try
        	{
        		if(c!=null)
        		{
        			c.close();
        		}
        		
            }
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    // reading part
    public static void close(Reader c) {
  	  try
      	{
      		if(c!=null)
      		{
      			c.close();
      		}
      		
          }
      	catch(Exception e)
      	{
      		e.printStackTrace();
      	}
      }
		
	
}
