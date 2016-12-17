package taskManagement;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskBean {
	String name,desc, tag,tarik;
	int prio;
	
	
	public TaskBean(String name, String desc, String tag, String tarik, int prio) {
		super();
		this.name = name;
		this.desc = desc;
		this.tag = tag;
		this.tarik = tarik;
		this.prio = prio;
	}
	public TaskBean() {
		// no arg constructor..
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTarik() {
		return tarik;
	}
	public void setTarik(String tarik) {
		this.tarik = tarik;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	
	@Override
	public String toString() {
		return "TaskBean [name=" + name + ", desc=" + desc + ", tag=" + tag + ", tarik=" + tarik + ", prio=" + prio
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + prio;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((tarik == null) ? 0 : tarik.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prio != other.prio)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (tarik == null) {
			if (other.tarik != null)
				return false;
		} else if (!tarik.equals(other.tarik))
			return false;
		return true;
	}
	
	
	
	// validation ... from model..
	public String validate() {
		String msg="";
		// for name..
		if(name==null||name.equals(""))
		{
			msg="Task name should not be blank or null";
		}
		if(name.split(" ").length>1)
		{
			msg="multiple words are not allowed";
		}
		else
		{
		 for(int i=0;i<name.length();i++)
		 {
			 char c=name.charAt(i);
				if(!(Character.isDigit(c)||Character.isLetter(c)))
				{
					msg="special characters are not allowed";
					break;
				}
		 }
		}
			
		
			// for Description...
			if(desc==null||desc.equals(""))
			{
				msg="Task descrshould not be blank or null";
			}
			
			
			 // for tag...
			 
			 if(tag==null||tag.equals(""))
				{
					msg="Task tags not be blank or null";
				}
				
			 // for date   { imp here}
			 if(tarik!=null)
			 {
			 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			 sdf.setLenient(false);   // very imp here..
			 try {
				Date d=sdf.parse(tarik);
			} catch (ParseException e) {
				
			//	msg="date should be correct foramat";
				msg=e.getMessage();
			}
			 }
			// for prority..
			 if(prio<1||prio>10)
			 {
				 msg="prority range is 1 to 10 oly pa";
			 }
				
				if(msg.equals(""))
				{
					return Constant.SUCCESS;
				}
				else
			return msg;
	}
	
}
