package sww.lqw.tools.leetcode.work;

public class AutoRunWork implements IWork {

	/**
	 * task list
	 */
	private final static String[] tasks = new String[] { "login", "acceptlist", "status", "submission", "gettags",
			"getcode", "file", "link", "tag" };

	@Override
	public void run() throws Exception {
		for (String task : tasks) {
			Works.getWorkByCommand(task).run();
		}
	}
}
