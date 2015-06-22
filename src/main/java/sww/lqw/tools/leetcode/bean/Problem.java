package sww.lqw.tools.leetcode.bean;

import java.util.HashMap;

public class Problem {

	private String href;
	private HashMap<String, String> mapLangToHref;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public HashMap<String, String> getMapLangToHref() {
		return mapLangToHref;
	}

	public void setMapLangToHref(HashMap<String, String> mapLangToHref) {
		this.mapLangToHref = mapLangToHref;
	}

	public boolean ok() {
		return mapLangToHref != null && mapLangToHref.size() >= 3;
	}
}
