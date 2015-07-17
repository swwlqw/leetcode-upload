package sww.lqw.tools.leetcode.bean;

import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;

public class TagFile {

	private final String tag;
	private final TreeSet<String> problems = new TreeSet<>();

	public TagFile(String tag) {
		super();
		this.tag = tag;
	}

	public void init(Scanner scan) {
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (!line.isEmpty()) {
				if (line.startsWith("> [")) {
					int end = line.indexOf(']');
					String problem = line.substring(3, end);
					problems.add(problem);
				}
			}
		}
	}

	public boolean addProblem(String problem) {
		return problems.add(problem);
	}

	public String toFileString() {
		StringBuilder sb = new StringBuilder();

		sb.append("# ");
		sb.append(tag);
		sb.append("\n\n");

		for (String problem : problems) {
			String str = String.format("> [%s](../%s/%s .md)\n\n", problem, Const.PROBLEM_DIR, problem);
			sb.append(str);
		}

		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
}
