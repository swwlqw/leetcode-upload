package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

/**
 * generate files
 * 
 * @author quanwei.lqw
 *
 */
public class FileWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		TreeSet<String> okList = context.getOkList();
		if (okList == null) {
			throw new WorkException("okList is null!");
		}

		File dir = new File("leetcode/problems");
		Commands.exec("git checkout auto", dir);
		for (String title : okList) {
			Problem p = context.getProblem(title);
			String context = p.toFileString(title);
			String fileName = title + ".md";
			File file = new File(dir, fileName);
			PrintWriter pw = new PrintWriter(file);
			pw.write(context);
			pw.flush();
			pw.close();
			System.out.format("Successfully Write File \"%s\"\n", fileName);
			String addCmd = String.format("git add \"%s\"", fileName);
			Commands.exec(addCmd, dir);
			String autoMessage = "Auto commit by https://github.com/swwlqw/leetcode-upload";
			String commitCmd = String.format("git commit -m \"Create %s(%s)\"", fileName, autoMessage);
			Commands.exec(commitCmd, dir);
			Commands.exec("git push", dir);
			System.out.format("Successfully push File \"%s\"\n", fileName);
		}
		context.setOkList(null);
		context.getToLinkList().addAll(okList);
	}

}
