package taskManagement;

import java.util.Comparator;

public class NameCompare implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean o1, TaskBean o2) {
	
		return o1.getName().compareTo(o2.getName());
	}

}
