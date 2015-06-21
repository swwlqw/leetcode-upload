package sww.lqw.tools.leetcode.work.command;

import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.work.IWork;

/**
 * clone leetcode project from github
 * @author sww
 *
 */
public class CloneWork implements IWork {
	
	@Override
	public void run() throws Exception {
		RunConfig config = RunConfig.getRunConfig();
		Commands.exec("rm -fR leetcode");
		Commands.exec("git clone " + config.getCloneUrl());
	}
	
}
