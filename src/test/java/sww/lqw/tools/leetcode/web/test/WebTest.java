package sww.lqw.tools.leetcode.web.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class WebTest {

	@Test
	public void testGetCode() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient
				.getPage("https://leetcode.com/accounts/login/");
		HtmlTextInput inputUser = (HtmlTextInput) page
				.getElementById("id_login");
		HtmlPasswordInput inputPassword = (HtmlPasswordInput) page
				.getElementById("id_password");
		inputUser.setText("swwlqw");
		inputPassword.setText("**********");
		System.out.println(inputUser);
		System.out.println(inputPassword);

		List<HtmlForm> forms = page.getForms();
		HtmlForm form = forms.get(0);
		System.out.println(form);

		DomNodeList<HtmlElement> buttons = form.getElementsByTagName("button");
		HtmlButton button = (HtmlButton) buttons.get(0);
		System.out.println(button);
		System.out.println("---------------------------------");

		HtmlPage next = button.click();
		System.out.println(next.asXml());
		System.out.println("---------------------------------");

		HtmlPage progressPage = webClient
				.getPage("https://leetcode.com/progress/");
		System.out.println(progressPage.asXml());
		System.out.println("---------------------------------");

		HtmlPage resultPage = webClient
				.getPage("https://leetcode.com/submissions/detail/30587226/");
		HtmlSpan span = (HtmlSpan) resultPage.getElementById("result_language");
		String language = span.asText();
		System.out.println(language);
		String origin = resultPage.asXml();
		String startString = String.format("scope.code.%s = ", language);
		int start = origin.indexOf(startString) + startString.length() + 1;
		int end = origin.lastIndexOf("scope.$apply();") - 7;
		String out = origin.substring(start, end);
		System.out.println(out);
		System.out.println("---------------------------------");

		String finalString = unicodeToString(out);
		System.out.println(finalString);
		System.out.println("---------------------------------");
		webClient.close();
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
