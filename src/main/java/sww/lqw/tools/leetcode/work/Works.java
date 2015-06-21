package sww.lqw.tools.leetcode.work;

import java.util.HashMap;
import java.util.Map;

import sww.lqw.tools.leetcode.work.web.AcceptListWork;
import sww.lqw.tools.leetcode.work.web.LoginWork;
import sww.lqw.tools.leetcode.work.web.LogoutWork;

public class Works {

	private static final Map<String, IWork> workMap = new HashMap<>();

	static {
		workMap.put("exit", new ExitWork());
		workMap.put("login", new LoginWork());
		workMap.put("logout", new LogoutWork());
		workMap.put("acceptlist", new AcceptListWork());

	}

	public static IWork getWorkByCommand(String cmd) {
		return workMap.get(cmd);
	}

}
