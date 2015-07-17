package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.bean.TagFile;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

/**
 * generate files
 * 
 * @author quanwei.lqw
 *
 */
public class TagWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		TreeSet<String> tagList = context.getTagList();
		if (tagList == null) {
			throw new WorkException("tagList is null!");
		}

		RunConfig config = RunConfig.getRunConfig();
		File dir = new File(config.getRepository() + "/" + Const.TAG_DIR);
		Commands.exec("git checkout auto", dir);
		if (!dir.exists()) {
			dir.mkdir();
		}

		for (String title : tagList) {
			List<String> tags = context.getProblem(title).getTags();
			if (!tags.isEmpty()) {
				for (String tag : tags) {
					File file = new File(dir, tag + ".md");
					TagFile tf = new TagFile(tag);
					if (file.exists()) {
						Scanner scan = new Scanner(file);
						tf.init(scan);
						scan.close();
					}
					tf.addProblem(title);
					PrintWriter pw = new PrintWriter(file);
					pw.write(tf.toFileString());
					pw.flush();
					pw.close();
				}

				System.out.format("Successfully add tags of \"%s\"\n", title);
				Commands.exec("git add *.md", dir);
				String commitCmd = String.format("git commit -m \"add tags of %s (%s)\"", title, Const.COMMIT_MESSAGE);
				Commands.exec(commitCmd, dir);
				Commands.exec("git push", dir);
			}
			System.out.format("Successfully push tags of \"%s\" (size=%d)\n", title, tags.size());
		}
		context.setTagList(null);
	}

}
