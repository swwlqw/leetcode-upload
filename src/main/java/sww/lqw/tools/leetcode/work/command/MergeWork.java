package sww.lqw.tools.leetcode.work.command;

import java.io.File;

import sww.lqw.tools.leetcode.work.IWork;

/**
 * clone leetcode project from github
 * @author sww
 *
 */
public class MergeWork implements IWork {
	
	@Override
	public void run() throws Exception {
		File dir = new File("leetcode");
		Commands.exec("git checkout auto", dir);
		Commands.exec("git pull", dir);
		Commands.exec("git checkout master", dir);
		Commands.exec("git merge auto", dir);
		Commands.exec("git push", dir);
		System.out.println("Successfully merge auto into master.");
	}
	
}
