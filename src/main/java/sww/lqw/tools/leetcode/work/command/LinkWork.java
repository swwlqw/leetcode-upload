package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.bean.LinkFile;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

/**
 * generate files
 * 
 * @author quanwei.lqw
 *
 */
public class LinkWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		TreeSet<String> toLinkList = context.getToLinkList();
		if (toLinkList == null) {
			throw new WorkException("toLinkList is null!");
		}

		RunConfig config = RunConfig.getRunConfig();
		File dir = new File(config.getRepository());
		Commands.exec("git checkout auto", dir);

		File file = new File(dir, "README.md");
		Scanner scan = new Scanner(file);
		LinkFile lf = new LinkFile();
		lf.init(scan);
		scan.close();

		for (String title : toLinkList) {
			lf.addLink(title);
			PrintWriter pw = new PrintWriter(file);
			pw.write(lf.toFileString());
			pw.flush();
			pw.close();
			System.out.format("Successfully add link \"%s\"\n", title);
			Commands.exec("git add README.md", dir);
			String commitCmd = String.format("git commit -m \"add link %s (%s)\"", title, Const.COMMIT_MESSAGE);
			Commands.exec(commitCmd, dir);
			Commands.exec("git push", dir);
			System.out.format("Successfully push link \"%s\"\n", title);
		}
		context.setToLinkList(null);
	}

}
