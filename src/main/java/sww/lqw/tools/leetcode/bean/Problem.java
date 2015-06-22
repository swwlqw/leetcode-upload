package sww.lqw.tools.leetcode.bean;

import java.util.HashMap;
import java.util.List;

public class Problem {

	private String href;
	private HashMap<String, String> mapLangToHref;
	private List<String> descriptions;
	private HashMap<String, String> mapLangToCode;

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

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public HashMap<String, String> getMapLangToCode() {
		return mapLangToCode;
	}

	public void setMapLangToCode(HashMap<String, String> mapLangToCode) {
		this.mapLangToCode = mapLangToCode;
	}

	public boolean ok() {
		return mapLangToHref != null && mapLangToHref.size() >= 3;
	}
}
