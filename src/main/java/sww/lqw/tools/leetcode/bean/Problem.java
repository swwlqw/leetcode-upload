package sww.lqw.tools.leetcode.bean;

public class Problem implements Comparable<Problem>{

	private String title;
	private String href;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public int compareTo(Problem o) {
		return title.compareTo(o.title);
	}

}
