package sww.lqw.tools.leetcode.web.test;

import java.lang.reflect.Method;

import org.junit.Test;

import sww.lqw.tools.leetcode.Main;
import sww.lqw.tools.leetcode.work.Works;

public class RunTest {

	@Test
	public void testRun() throws Exception {
		Class<?> clz = Main.class;
		Method load = clz.getDeclaredMethod("loadConfig");
		load.setAccessible(true);
		load.invoke(null);
		String[] tasks = new String[] { "login","acceptlist","status", "submission"};
		for (String task : tasks) {
			Works.getWorkByCommand(task).run();
		}
	}
}
