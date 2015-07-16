package sww.lqw.tools.leetcode.work.web;

import java.util.List;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * Get Accept problem list
 * 
 * @author sww
 *
 */
public class AcceptListWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		WebClient webClient = context.getWebClient();
		if (webClient == null) {
			throw new WorkException("You have not logged in!");
		}
		HtmlPage page = webClient.getPage(Const.LEETCODE_URL + "/problemset/algorithms/");
		HtmlTable table = (HtmlTable) page.getElementById("problemList");
		HtmlTableBody body = table.getBodies().get(0);
		TreeSet<String> acceptList = new TreeSet<>();
		List<HtmlTableRow> rows = body.getRows();
		for (HtmlTableRow row : rows) {
			HtmlTableCell cell = row.getCell(0);
			HtmlSpan span = (HtmlSpan) cell.getElementsByTagName("span").get(0);
			String clz = span.getAttribute("class");
			if (clz.equals("ac")) {
				HtmlTableCell second = row.getCell(2);
				HtmlElement a = second.getElementsByTagName("a").get(0);
				String href = a.getAttribute("href");
				String title = a.getTextContent();
				Problem p = new Problem();
				p.setHref(href);
				acceptList.add(title);
				context.addProblem(title, p);
			}
		}
		context.setAcceptList(acceptList);
		System.out.format("Successfully Obtain the Accept List (%d/%d).\n", acceptList.size(), rows.size());
		int i = 1;
		for (String title : acceptList) {
			System.out.format(" | %d\t| %s\n", i++, title);
		}
	}

}
