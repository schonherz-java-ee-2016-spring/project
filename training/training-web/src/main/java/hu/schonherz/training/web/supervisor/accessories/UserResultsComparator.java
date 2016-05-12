package hu.schonherz.training.web.supervisor.accessories;

import java.util.Comparator;

public class UserResultsComparator implements Comparator<UserResults> {

	@Override
	public int compare(UserResults o1, UserResults o2) {
		return (o1.getExamSum() + o1.getHomeworkSum()) > (o2.getExamSum() + o2.getHomeworkSum()) ? 1
				: (o1.getExamSum() + o1.getHomeworkSum()) == (o2.getExamSum() + o2.getHomeworkSum()) ? 0 : -1;
	}

}
