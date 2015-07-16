package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.PrintWriter;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.work.IWork;

/**
 * init a new repository on github
 * 
 * @author sww
 *
 */
public class InitWork implements IWork {

	@Override
	public void run() throws Exception {
		RunConfig config = RunConfig.getRunConfig();
		String repo = config.getRepository();
		Commands.exec("rm -fR " + repo);
		Commands.exec("mkdir " + repo);
		File dir = new File(repo);
		Commands.exec("git init", dir);

		File file = new File(dir, Const.README_FILE);
		PrintWriter pw = new PrintWriter(file);
		pw.write(String.format("# %s\n\n%s\n", repo, Const.DESCRIPTION));
		pw.flush();
		pw.close();

		Commands.exec("git add " + Const.README_FILE, dir);
		String commitCmd = String.format("git commit -m \"first commit (%s)\"", Const.COMMIT_MESSAGE);
		Commands.exec(commitCmd, dir);
		
		Commands.exec("git remote add origin " + config.getCloneUrl(), dir);
		Commands.exec("git push -u origin master", dir);
		Commands.exec("git checkout -b auto", dir);
		Commands.exec("git push -u origin auto", dir);
		System.out.println("Successfully init a new repository on github.");
	}

}
