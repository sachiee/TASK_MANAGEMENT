package taskManagement;

import java.io.File;
import java.util.Scanner;

public class TaskApp {
	
	    //Input validationds..
	
	public static String validate(String catName) {
		
		String msg="";
		if(catName==null ||catName.equals(""))
		{
			msg="name should be not null or blank";
		}
		if(catName.split(" ").length>1)
		{
			msg="multiple words not alowed";
		}
		else
		{
		for(int i=0;i<catName.length();i++)
		{
			char c=catName.charAt(i);
			if(!(Character.isDigit(c)||Character.isLetter(c)))
			{
				msg="special characters are not allowed";
				break;
			}
		}
		}
		if(msg.equals(""))
			return Constant.SUCCESS;
		else
		return msg;
	}
	
	 // instatnce variable declaration....
	
	 static Scanner sc1=new Scanner(System.in);
     static Scanner sc2=new Scanner(System.in);
     static int ch1=0;
     static  int ch2=0;
     static Model m=new Model();
     
  //<.....................This code which is used for reusing................................>
    
    public static void reuseCode(String catName)
	  {
    	 ch2=0;  // very imp to write here bcz 2nd tym while u cmng for dis loop ch2 value will be 6 so dat....
	  while(ch2!=6)   // 2nd while loop..  bcz in side category,...
	  {
		System.out.println("Enter 1 to add task");  
		System.out.println("Enter 2 to edit task");
		System.out.println("Enter 3 to remove task");
		System.out.println("Enter 4 to list of tasks");
		System.out.println("Enter 5 to search");
		System.out.println("Enter 6 to go back");
		System.out.println(" ");
	
	  
	  while(!sc1.hasNextInt())
	  {
		  System.out.println("Enter integer input oly dude");
		  sc1.next();
	  }
	  ch2=sc1.nextInt();
	  System.out.println("you have selected option "+ch2);
	  
	  switch (ch2) {
	case 1:System.out.println("Adding of tasku");
	System.out.println("Enter name of the task");
	String name=sc2.nextLine();
	System.out.println("Enter Description of the task");
	String desc=sc2.nextLine();
	System.out.println("Enter search Tag");
	String tag=sc2.nextLine();
	System.out.println("Enter end date in dd/MM/yyyy format");
	String tarik=sc2.nextLine();
	System.out.println("Enter priority for the task range of 1 to 10");
	 while(!sc1.hasNextInt())
	  {
		  System.out.println("Enter integer input oly dude");
		  sc1.next();
	  }
	 int prio=sc1.nextInt();
	 
	  TaskBean tb=new TaskBean(name,desc,tag,tarik, prio);
	  String hold=m.addTask(catName,tb);
	  if(hold.equals(Constant.SUCCESS))
		  System.out.println("Task is addded sucess fully");
	  else
		  System.out.println(hold);
	
	break;
	case 2:System.out.println("<-----Editing of tasku---->");
	     m.editTask(catName);
	
	break;
	case 3:System.out.println("<------Removing of tasku----->");
	       m.removeTask(catName);
	break;
	case 4:System.out.println("<-----Listing of tasku---->");
	   m.list(catName);
	break;
	case 5:System.out.println("<-----Searching of tasku----->");
	   m.search(catName);
	break;
	case 6:System.out.println("<-----TATA BYE BYE----->");
		break;
  default:System.out.println("Option not supported yet");
		break;
	}
	  
	  }// 2nd while loop ends here
	  
	  }
    
    //<.....................Reusing ended................................>
    
	
	// main starts here..
	public static void main(String[] args) {
      
    
     
      while(ch1!=5)    // 1st while starting here..
      {
    	
           System.out.println("Enter 1 to create Categeory");
    	  System.out.println("Enter  2 to load Categeory");
    	  System.out.println("Enter  3 to search Categeory");
    	  System.out.println("Enter  4 to list Categeory");
    	   System.out.println("Enter 5 to edit categeory");
    	  System.out.println("Enter  6 to Exit");
    	  System.out.println(" ");
    	  
    	  while(!sc1.hasNextInt())
    	  {
    		  System.out.println("Enter correct integr input oly man..");
    		  sc1.next();
    	  }
    	  ch1=sc1.nextInt();
    	
    	  System.out.println("you selected "+ch1);
    	  switch (ch1)
    	  {
    	  case 1:
    		  System.out.println("<----Creation Of Categeory----->");
    	      System.out.println("Enter name of the Categeory to create");
    	      String catName=sc2.nextLine();
    	      String msg=validate(catName);
    	      if(msg.equals(Constant.SUCCESS))
    	      {
    	    	  System.out.println("i/p validations for "+catName+" is successed ");
    	    	  
    	    	  boolean res=m.chkCatExists(catName);   // sending model for business  validation...
    	    	  if(res)
    	    	  {
    	    		  System.out.println("Duplicates names of categeory is not allowed");
    	           }
    	    	  else
    	    	  {
    	    		  boolean result=m.createCatFile(catName);// to make folder of catgry...
    	    		  if(result)
    	    		  {
    	    		  System.out.println("your "+catName+" is created");
    	    		  
    	    		  reuseCode(catName);// calling 2nd while loop here.....
    	    		  }
    	    		  else
    	    		  {
    	    			  System.out.println("your categeory "+catName+" is not created");
    	    		  }
    	    	 }
    	      }// 1st case i/P validation ends here...
    	      else
    	    	 System.out.println(msg);
    	  break;
    	  case 2:System.out.println("<------Loading Of Categeory------>");
    	  System.out.println("Enter name of the Categeory to load");
    	   String catHesru=sc2.nextLine();
    	   
    	File f=null;
    	  try
    	  {
    		  f=new File(Constant.PATH);
    		  File[] fl=f.listFiles();
    		  boolean b =false;
    		  
    		  for(File g:fl)
    		  {
    			  if(g.getName().equals(catHesru))
    			  {
    				  System.out.println(g.getName()+" is loaded");
    				  // since we r in particular category ..we should go for inner actions...
    				  // calling second while loop here
    				  reuseCode(g.getName());
    				 b=true;
    			  }
    				 
    		}
    		  if(!b)
    		  {
    			  System.out.println("There is no categeory by dis name "+catHesru+" load");
    		  }
    		  
    	  }
    	  catch (Exception e) {
			e.printStackTrace();
		}
    	  break;
    	  case 3:System.out.println("<--------Searching Of Categeory------>");
    	  System.out.println("Enter categeory name to search");
    	  String catHesr=sc2.nextLine();
    	  boolean res=m.chkCatExists(catHesr);  // same code which i had used in 1st case...
    	  if(res)
    	  {
    		  System.out.println(catHesr+" is found dude:))");
    	  }
    	  else
    		  System.out.println(catHesr+" is not present in dis file man:((((");
    	  break;
    	  case 4:System.out.println("<------Listing Of Categeory------->");
    	  System.out.println("Enter name of the Categeory to list");
    	 
    	  f=null;
    	  try
    	  {
    		
    		  f=new File(Constant.PATH);
    		  File[] fl=f.listFiles();
    		  
    		  for(File g:fl)
    		  {
    		 System.out.println(g.getName());
    		}
    		  
    	  }
    	  catch (Exception e) {
			e.printStackTrace();
		}
    	  
    	  break;
    	  
    	  
    	  case 5:System.out.println("<........Editing Categoery.........>");
    	     
    	      f=null;
        	  try
        	  {

        		  f=new File(Constant.PATH);
        		  File[] fl=f.listFiles();
        		  
        		  for(File g:fl)
        		  {
        		 System.out.println(g.getName());
        		}
        		  
        		  System.out.println("Enter which Categeory name which u want to edit???");
        	      String catn=sc2.nextLine();
        		   File old = new File(Constant.PATH+catn) ;
        		  System.out.println("Enter wt name u want to give fa dis category???");
        	      String catn2=sc2.nextLine();
        	 
        	      File ne = new File(Constant.PATH+catn2) ;
        				   
        				boolean b= old.renameTo(ne);
        			
        				 
        	  }
        	  catch (Exception e) {
    			e.printStackTrace();
    		}
    	      
    	      
    		  break;
    	  case 6:System.out.println("<-----TATA BYE BYE------>");
			break;
    	 default:System.out.println("<-------Option not supported yet----->");
    	  break;
    	  }
    	  
      }                // 1st while ending here..

	}

	
	}


