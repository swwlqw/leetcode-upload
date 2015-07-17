package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

/**
 * calculate problem status
 * @author quanwei.lqw
 *
 */
public class StatusWork extends AbstractContextWork{

	@Override
	public void run() throws Exception {
		TreeSet<String> acceptList = context.getAcceptList();
		if (acceptList == null){
			throw new WorkException("The Accept List is null");
		}
		
		RunConfig config = RunConfig.getRunConfig();
		File dir = new File(config.getRepository());
		Commands.exec("git checkout auto", dir);
		Commands.exec("git pull", dir);
		
		@SuppressWarnings("unchecked")
		TreeSet<String> toUploadList = (TreeSet<String>) acceptList.clone();
		File problems = new File(dir, Const.PROBLEM_DIR);
		TreeSet<String> mdList= new TreeSet<>();
		if (problems.exists()){
			for (File file: problems.listFiles()){
				String fileName = file.getName();
				String title = fileName.substring(0, fileName.length()-4);
				mdList.add(title);
			}
		}
		toUploadList.removeAll(mdList);
		System.out.format("Successfully Obtain the List to Upload (%d/%d).\n", toUploadList.size(), acceptList.size());
		int i = 1;
		for (String title : toUploadList) {
			System.out.format(" | %d\t| %s\n", i++, title);
		}
		context.setToUploadList(toUploadList);
		
		@SuppressWarnings("unchecked")
		TreeSet<String> toLinkList = (TreeSet<String>) mdList.clone();
		File readme = new File(dir, "README.md");
		Scanner scan = new Scanner(new FileInputStream(readme));
		TreeSet<String> linkList= new TreeSet<>();
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			if (line.startsWith("> [")){
				int end = line.indexOf(']');
				String title = line.substring(3, end);
				linkList.add(title);
			}
		}
		scan.close();

		toLinkList.removeAll(linkList);
		System.out.format("Successfully Obtain the List to Add Link (%d/%d).\n", toLinkList.size(), mdList.size());
		i = 1;
		for (String title : toLinkList) {
			System.out.format(" | %d\t| %s\n", i++, title);
		}
		context.setToLinkList(toLinkList);
	}

}
