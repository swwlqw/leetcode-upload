package sww.lqw.tools.leetcode.work.web;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * get submissions from leetcode
 * 
 * @author quanwei.lqw
 *
 */
public class SubmissionWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		WebClient webClient = context.getWebClient();
		if (webClient == null) {
			throw new WorkException("You have not logged in!");
		}
		TreeSet<String> toUploadList = context.getToUploadList();
		if (toUploadList == null) {
			throw new WorkException("toUploadList is null!");
		}

		TreeSet<String> okList = new TreeSet<String>();
		System.out.println("Outputing Accepted Language...");
		for (String title : toUploadList) {
			Problem p = context.getProblem(title);
			String url = String.format("https://leetcode.com%ssubmissions/", p.getHref());
			HtmlPage page = webClient.getPage(url);
			HtmlTable table = (HtmlTable) page.getElementById("result_testcases");
			HtmlTableBody body = table.getBodies().get(0);
			List<HtmlTableRow> rows = body.getRows();
			HashMap<String, String> map = new HashMap<>();
			for (HtmlTableRow row : rows) {
				HtmlTableCell second = row.getCell(2);
				HtmlElement a = second.getElementsByTagName("a").get(0);
				if (a.asText().equals("Accepted")) {
					String lang = row.getCell(4).asText();
					if (!map.containsKey(lang)) {
						String href = a.getAttribute("href");
						map.put(lang, href);
					}
				}
			}
			p.setMapLangToHref(map);
			System.out.format(" | %s | %s\n", title, JSON.toJSON(map.keySet()));
			if (p.ok()) {
				okList.add(title);
			}
		}

		context.setOkList(okList);
		System.out.format("Successfully Obtain the Ok List (%d/%d).\n", okList.size(), toUploadList.size());
		int i = 1;
		for (String title : okList) {
			System.out.format(" | %d\t| %s\n", i++, title);
		}
	}

}
