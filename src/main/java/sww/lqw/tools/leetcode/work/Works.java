package sww.lqw.tools.leetcode.work;

import java.util.HashMap;
import java.util.Map;

public class Works {

	private static final Map<String, IWork> workMap = new HashMap<>();

	static {
		workMap.put("exit", new ExitWork());
	}

	public static IWork getWorkByCommand(String cmd) {
		return workMap.get(cmd);
	}

}
