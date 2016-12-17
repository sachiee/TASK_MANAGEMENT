package taskManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Model {
	Scanner sc1=new Scanner(System.in);
	Scanner sc2=new Scanner(System.in);
	BufferedReader br=null;
	BufferedWriter bw=null;

	// business validation for Categeory name...
	public boolean chkCatExists(String catName) {
	File f=new File(Constant.PATH+catName);
	  return f.exists();
	}
    // to create categeory file..
	public boolean createCatFile(String catName) {
		File f=new File(Constant.PATH+catName);
	   System.out.println("File path---- >"+f.getAbsolutePath());
		boolean res=f.mkdir();                                    // very imp to make folder in computer..
		return res;
	}
	
	// adding task..
	public String addTask(String catName, TaskBean tb) {
		
		// validation using bean class..
		String result=tb.validate();
		if(result.equals(Constant.SUCCESS))
		{
			BufferedWriter bw=null;
			try
			{
			
				bw=new BufferedWriter(new FileWriter(Constant.PATH+catName+"/"+catName,true));
				bw.write(tb.getName()+":"+tb.getDesc()+":"+tb.getTag()+":"+tb.getTarik()+":"+tb.getPrio());
				bw.newLine();
			}
			catch(Exception e)
			{
				result="your  task is not added sucessfully";
			}
			finally
			{
				
				CloseHelper.close(bw);
			}
		
		}
		
		return result;
	}
	
	// editing task
	public void editTask(String catName) {
		
		String line=null;
		List<TaskBean> al=new ArrayList<TaskBean>();
		boolean b=false;
		try {
			System.out.println("Enter name of the task to edit");
			String name=sc2.nextLine();
			 br=new BufferedReader(new FileReader(Constant.PATH+catName+"/"+catName));
			
			while((line=br.readLine())!=null)       // while loop starts here...
			{
				TaskBean tb=new TaskBean();
				String [] arr=line.split(":");
				tb.setName(arr[0]);
				tb.setDesc(arr[1]);
				tb.setTag(arr[2]);
				tb.setTarik(arr[3]);
			 int temp=Integer.parseInt(arr[4]);
			 tb.setPrio(temp);
			 if(!arr[0].equals(name))
			 {
				 al.add(tb); 
			 }
			 else
			 {
				 b=true; // jus for best practice to show successfully edited anta.,..
				 System.out.println("Enter what to edit in dis task? name/descr/tag/tarik/prio");
				 String sel=sc2.nextLine();
				 switch (sel) 
				 {
				case "name":System.out.println("What do yu want to change this task name as??");
				String newname=sc2.nextLine();
				tb.setName(newname);
				al.add(tb);//here new test bean is added..
				break;
				case "descr":System.out.println("What do yu want to change this descrption  as??");
			    String newdescr=sc2.nextLine();
			    tb.setDesc(newdescr);
			    al.add(tb);//here new test bean is added..
			    break;
				case "tag":System.out.println("What do yu want to change this tag name as??");
			    String newtag=sc2.nextLine();
			    tb.setTag(newtag);
			    al.add(tb);//here new test bean is added..
			    break;
				case "tarik":System.out.println("What do yu want to change this Tarik as??");
			    String newtarik=sc2.nextLine();
			    tb.setTarik(newtarik);
			    al.add(tb);//here new test bean is added..
                   break;
				case "prio":System.out.println("What do yu want to change this prio as??");
			    int newprio=sc1.nextInt();
			    tb.setPrio(newprio);
			    al.add(tb);//here new test bean is added..
                   break;
                   
				default:System.out.println("option not supported yet");
					break;
					
				}
			 }
			
			}// while loop ends here...
			
			bw=new BufferedWriter(new FileWriter(Constant.PATH+catName+"/"+catName));
			for(TaskBean m:al)
			{
				bw.write(m.getName()+":"+m.getDesc()+":"+m.getTag()+":"+m.getTarik()+":"+m.getPrio());
				bw.newLine();
			}
			if(b)
			System.out.println("Edit Hogay Shabasss"); 
			else
				System.out.println("Task name correct ag kodbekittu pa");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		finally
		{
			CloseHelper.close(br);
			CloseHelper.close(bw);
		}
		
	}
	
	// removing of task..
	public void removeTask(String catName) {
		
		String line=null;
		boolean b =false;
		List<TaskBean> al=new ArrayList<TaskBean>();
		try {
			System.out.println("Enter name of the task to remove");
			String name=sc2.nextLine();
			 br=new BufferedReader(new FileReader(Constant.PATH+catName+"/"+catName));
			 
			 while((line=br.readLine())!=null)       // while loop starts here...
				{
					TaskBean tb=new TaskBean();
					String [] arr=line.split(":");
					tb.setName(arr[0]);
					tb.setDesc(arr[1]);
					tb.setTag(arr[2]);
					tb.setTarik(arr[3]);
				 int temp=Integer.parseInt(arr[4]);
				 tb.setPrio(temp);
				 if(!arr[0].equals(name))
				 {
					 al.add(tb); 
				 }
				 else
				 {
					 b=true;
					 al.remove(tb);
				 }
				 
				} // while loop ends here...
			 
			 
			 bw=new BufferedWriter(new FileWriter(Constant.PATH+catName+"/"+catName));
				for(TaskBean m:al)
				{
					bw.write(m.getName()+":"+m.getDesc()+":"+m.getTag()+":"+m.getTarik()+":"+m.getPrio());
					bw.newLine();
				}
				if(b)
					System.out.println("removed pa hero"); 
					else
						System.out.println("Task name correct ag kodbekittu pa");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			CloseHelper.close(br);
			CloseHelper.close(bw);
		}
		
	}
	
	// listing task
	public void list(String catName) {
		 List<TaskBean> al=new ArrayList<TaskBean>();
		 String line=null;
		try 
		{
			
			 br=new BufferedReader(new FileReader(Constant.PATH+catName+"/"+catName));
			 System.out.println("Listing of Tasks in "+catName);
			
			 while((line=br.readLine())!=null)// while loop starts here...
				{
		           TaskBean tb=new TaskBean();
		           String [] arr=line.split(":");
					tb.setName(arr[0]);
					tb.setDesc(arr[1]);
					tb.setTag(arr[2]);
					tb.setTarik(arr[3]);
				 int temp=Integer.parseInt(arr[4]);
				 tb.setPrio(temp);
				 al.add(tb);
		           
				} // while loop ends here...
			 //System.out.println(al);   // by default listing...
			 int ch3=0;
				
				while(ch3!=4)
				{
					System.out.println("press 1 to list by name");
					System.out.println("press 2 to list by date");
					System.out.println("press 3 to list by priority ");
					System.out.println("press 4 to exit");
					ch3=sc1.nextInt();
					switch (ch3) {
					case 1:Collections.sort(al,new NameCompare());
					System.out.println(al);
					break;
					case 2:Collections.sort(al,new DinaankCompare());
					System.out.println(al);
						break;
					case 3:Collections.sort(al, new PrioCompare());
					System.out.println(al);
                       break;
					case 4:System.out.println("<-----TATA BYE BYE----->");
					break;
					default:System.out.println("option not supporetd yet");
						break;
					}
		        }
		}
		catch (Exception e) {
			 e.printStackTrace();
			}
		finally
		{
			CloseHelper.close(br);
			CloseHelper.close(bw);
		}
		
	}
	
	// searching of task....
	public void search(String catName) {
	   System.out.println("Enter name of the tag to search tasks");
	   String tagu=sc2.nextLine();
	   String line=null;
	   int flag=0;
	   try 
	   {
		   br=new BufferedReader(new FileReader(Constant.PATH+catName+"/"+catName));
		   while((line=br.readLine())!=null)
		   {
			   TaskBean tb=new TaskBean();
	           String [] arr=line.split(":");
				tb.setName(arr[0]);
				tb.setDesc(arr[1]);
				tb.setTag(arr[2]);
				tb.setTarik(arr[3]);
			 int hold=Integer.parseInt(arr[4]);
			 tb.setPrio(hold);
			 if(tb.getTag().equals(tagu))
			 {
				 System.out.println(tb);
				 flag=1;
			 }	
		   }
		   if(flag==0)
		   {
			   System.out.println("searching thing is not found");
		   }
	   } 
	   
	   catch (Exception e)
	   {
		 e.printStackTrace();
	   }
	   finally
	   {
		   CloseHelper.close(br);
	   }
		
	}

}
