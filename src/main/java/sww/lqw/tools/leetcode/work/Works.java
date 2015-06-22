package sww.lqw.tools.leetcode.work;

import java.util.HashMap;
import java.util.Map;

import sww.lqw.tools.leetcode.work.command.CloneWork;
import sww.lqw.tools.leetcode.work.command.FileWork;
import sww.lqw.tools.leetcode.work.command.StatusWork;
import sww.lqw.tools.leetcode.work.web.AcceptListWork;
import sww.lqw.tools.leetcode.work.web.GetCodeWork;
import sww.lqw.tools.leetcode.work.web.LoginWork;
import sww.lqw.tools.leetcode.work.web.LogoutWork;
import sww.lqw.tools.leetcode.work.web.SubmissionWork;

public class Works {

	private static final Map<String, IWork> workMap = new HashMap<>();

	static {
		workMap.put("exit", new ExitWork());
		workMap.put("login", new LoginWork());
		workMap.put("logout", new LogoutWork());
		workMap.put("acceptlist", new AcceptListWork());
		workMap.put("clone", new CloneWork());
		workMap.put("status", new StatusWork());
		workMap.put("submission", new SubmissionWork());
		workMap.put("getcode", new GetCodeWork());
		workMap.put("file", new FileWork());

	}

	public static IWork getWorkByCommand(String cmd) {
		return workMap.get(cmd);
	}

}
