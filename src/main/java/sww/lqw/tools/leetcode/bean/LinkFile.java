package sww.lqw.tools.leetcode.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class LinkFile {

	private List<String> starts = new ArrayList<>();
	private TreeSet<String> titles = new TreeSet<>();
	private HashMap<String, TreeSet<String>> links = new HashMap<>();

	public void init(Scanner scan) {
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (!line.isEmpty()) {
				if (line.startsWith("## ")) {
					String title = line.substring(3);
					titles.add(title);
					links.put(title, new TreeSet<>());
				} else if (line.startsWith("> [")) {
					int end = line.indexOf(']');
					String link = line.substring(3, end);
					String title = link.substring(0, 1);
					links.get(title).add(link);
				} else {
					starts.add(line);
				}
			}
		}
	}

	public void addLink(String link) {
		String title = link.substring(0, 1);
		if (!titles.contains(title)) {
			titles.add(title);
			links.put(title, new TreeSet<>());
		}
		links.get(title).add(link);
	}

	public String toFileString() {
		StringBuilder sb = new StringBuilder();

		for (String str : starts) {
			sb.append(str);
			sb.append("\n\n");
		}

		for (String title : titles) {
			sb.append("## ");
			sb.append(title);
			sb.append("\n\n");
			TreeSet<String> linkSet = links.get(title);
			for (String link : linkSet) {
				String str = String.format("> [%s](problems/%s.md)\n\n", link, link);
				sb.append(str);
			}
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
}
