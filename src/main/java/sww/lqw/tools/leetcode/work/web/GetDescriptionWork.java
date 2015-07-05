package sww.lqw.tools.leetcode.work.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * get description from leetcode
 * 
 * @author quanwei.lqw
 *
 */
public class GetDescriptionWork extends AbstractContextWork {

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

			DomNodeList<DomElement> divs = page.getElementsByTagName("div");
			HtmlDivision content = null;
			for (DomElement div : divs) {
				if (div.getAttribute("class").equals("question-content")) {
					content = (HtmlDivision) div;
					break;
				}
			}

			List<String> descriptions = new ArrayList<>();
			DomNodeList<HtmlElement> hps = content.getElementsByTagName("p");
			for (HtmlElement hp : hps) {
				String xml = hp.asXml();

				StringBuilder sb = new StringBuilder();
				Scanner scan = new Scanner(xml);
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					sb.append(line);
				}
				scan.close();
				String out = sb.toString().replaceAll(" +", " ");
				out = out.replaceAll("<p[^>]*>", ">");
				out = out.replaceAll("</p>", "");
				if (!out.trim().equals(">")) {
					descriptions.add(out);
				}
			}
			p.setDescriptions(descriptions);
			System.out.format("Successfully Obtain description of \"%s\".\n", title);
			for (String description : descriptions) {
				System.out.format(" | %s\n", description);
			}
		}
	}
	
}
