package sww.lqw.tools.leetcode.work;

public class WorkContext {

	private static final WorkContext context = new WorkContext();

	public static WorkContext getContext() {
		return context;
	}

	private WorkContext() {

	}
}
