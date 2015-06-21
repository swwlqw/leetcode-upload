package sww.lqw.tools.leetcode.work.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

/**
 * Get Accept problem list
 * @author sww
 *
 */
public class AcceptListWork extends AbstractContextWork{

	@Override
	public void run() throws Exception {
		WebClient webClient = context.getWebClient();
		if (webClient == null) {
			throw new WorkException("You have not logged in!");
		}
		HtmlPage page = webClient.getPage("https://leetcode.com/problemset/algorithms/");
		System.out.println(page.asText());
	}

}
