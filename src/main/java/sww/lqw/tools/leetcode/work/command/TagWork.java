package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.bean.TagFile;
import sww.lqw.tools.leetcode.work.AbstractContextWork;

/**
 * generate tag files
 * 
 * @author quanwei.lqw
 *
 */
public class TagWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {

		RunConfig config = RunConfig.getRunConfig();
		File dir = new File(config.getRepository() + "/" + Const.TAG_DIR);
		if (!dir.exists()) {
			dir.mkdir();
		}
		Commands.exec("git checkout auto", dir);

		File problems = new File(config.getRepository() + "/" + Const.PROBLEM_DIR);
		TreeSet<String> mdList = new TreeSet<>();
		if (problems.exists()) {
			for (File file : problems.listFiles()) {
				String fileName = file.getName();
				String title = fileName.substring(0, fileName.length() - 4);
				mdList.add(title);
				Problem p = context.getProblem(title);
				if (p.getTags() == null) {
					Scanner scan = new Scanner(file);
					p.initTags(scan);
					scan.close();
				}
			}
		}

		for (String title : mdList) {
			List<String> tags = context.getProblem(title).getTags();
			boolean add = false;
			if (!tags.isEmpty()) {
				for (String tag : tags) {
					File file = new File(dir, tag + ".md");
					TagFile tf = new TagFile(tag);
					if (file.exists()) {
						Scanner scan = new Scanner(file);
						tf.init(scan);
						scan.close();
					}
					if (tf.addProblem(title)) {
						PrintWriter pw = new PrintWriter(file);
						pw.write(tf.toFileString());
						pw.flush();
						pw.close();
						add = true;
					}
				}
			}
			if (add) {
				Commands.exec("git add *.md", dir);
				String cmd = String.format("git commit -m \"add tags of '%s' (%s)\"", title, Const.COMMIT_MESSAGE);
				Commands.exec(cmd, dir);
				Commands.exec("git push", dir);
				System.out.format("Successfully push tags of \"%s\" (size=%d)\n", title, tags.size());
			}
		}
	}

}
