package sww.lqw.tools.leetcode.work.web;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * get the code leetcode submissions
 * 
 * @author quanwei.lqw
 *
 */
public class GetCodeWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		WebClient webClient = context.getWebClient();
		if (webClient == null) {
			throw new WorkException("You have not logged in!");
		}
		TreeSet<String> okList = context.getOkList();
		if (okList == null) {
			throw new WorkException("okList is null!");
		}

		for (String title : okList) {
			Problem p = context.getProblem(title);
			HashMap<String, String> mapLangToCode = new HashMap<>();
			for (Entry<String, String> entry : p.getMapLangToHref().entrySet()) {
				String language = entry.getKey();
				String codeUrl = String.format("https://leetcode.com%s", entry.getValue());
				HtmlPage resultPage = webClient.getPage(codeUrl);

				String origin = resultPage.asXml();
				String startString = String.format("scope.code.%s = ", language);
				int start = origin.indexOf(startString) + startString.length() + 1;
				int end = origin.lastIndexOf("scope.$apply();") - 7;
				String out = origin.substring(start, end);
				String finalString = unicodeToString(out);
				mapLangToCode.put(language, finalString);
			}
			p.setMapLangToCode(mapLangToCode);
			System.out.format("Successfully Obtain code of \"%s\".\n", title);
			for (Entry<String, String> entry : p.getMapLangToCode().entrySet()) {
				String language = entry.getKey();
				String code = entry.getValue();
				System.out.format(" | %s\n", language);
				for (String str : code.split("\n")) {
					System.out.format(" | | %s\n", str);
				}
			}
		}
	}

	private String unicodeToString(String origin) {
		String temp = origin.replaceAll("\\\\u000D\\\\u000A", "\n");
		temp = temp.replaceAll("\\\\u0009", "\t");
		temp = temp.replaceAll("\\\\u003B", ";");
		String[] strs = temp.split("\\\\u");
		StringBuilder sb = new StringBuilder();
		sb.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			String str = strs[i];
			String pre = str.substring(0, 4);
			char ch = (char) Integer.parseInt(pre, 16);
			sb.append(ch);
			sb.append(str.substring(4));
		}
		return sb.toString();
	}
}
