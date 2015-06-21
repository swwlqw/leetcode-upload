package sww.lqw.tools.leetcode.work;

public class WorkContext {

	private static WorkContext context = new WorkContext();

	public static WorkContext getContext() {
		return context;
	}

	public static void setContext(WorkContext context) {
		WorkContext.context = context;
	}

	private WorkContext() {

	}
}
