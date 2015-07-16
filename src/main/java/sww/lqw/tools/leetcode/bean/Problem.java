package sww.lqw.tools.leetcode.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Problem {

	private String href;
	private HashMap<String, String> mapLangToHref;
	private List<String> tags;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
	
	public String toFileString(String title){
		StringBuilder sb = new StringBuilder();
		sb.append("# ");
		sb.append(title);
		sb.append("\n\n");
		
		for (String tag : tags){
			sb.append(tag);
			sb.append("\n\n");
		}
		
		for (Entry<String, String> entry : mapLangToCode.entrySet()) {
			String language = entry.getKey();
			String code = entry.getValue();
			sb.append("## ");
			sb.append(language);
			sb.append("\n\n");
			sb.append("```");
			sb.append(language);
			sb.append('\n');
			sb.append(code);
			sb.append('\n');
			sb.append("```\n\n");
		}
		sb.setLength(sb.length()-2);
		return sb.toString();
	}
}
