package sww.lqw.tools.leetcode.work.web;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * get description from leetcode
 * 
 * @author quanwei.lqw
 *
 */
public class GetTagsWork extends AbstractContextWork {

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
			String url = String.format("https://leetcode.com%s", p.getHref());
			HtmlPage page = webClient.getPage(url);
			DomElement element = page.getElementById("tags");
			DomElement parent =(DomElement) element.getParentNode();
			DomNodeList<HtmlElement> domList = parent.getElementsByTagName("a");
			List<String> descriptions = new ArrayList<>();

			for (HtmlElement a : domList){
				String href = a.getAttribute("href");
				String tag = a.getTextContent();
				descriptions.add(tag+"\t"+href);
			}
	
			p.setTags(descriptions);
			System.out.format("Successfully Obtain description of \"%s\".\n", title);
			for (String description : descriptions) {
				System.out.format(" | %s\n", description);
			}
		}
	}
	
}
