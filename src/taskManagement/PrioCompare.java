package taskManagement;

import java.util.Comparator;

public class PrioCompare implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean o1, TaskBean o2) {

		return o1.getPrio()-o2.getPrio();
	}

}
