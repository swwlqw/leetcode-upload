package sww.lqw.tools.leetcode.work;

import java.util.HashMap;
import java.util.Map;

import sww.lqw.tools.leetcode.work.web.LoginWork;

public class Works {

	private static final Map<String, IWork> workMap = new HashMap<>();

	static {
		workMap.put("exit", new ExitWork());
		workMap.put("login", new LoginWork());

	}

	public static IWork getWorkByCommand(String cmd) {
		return workMap.get(cmd);
	}

}
