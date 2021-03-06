package sww.lqw.tools.leetcode.work.web;

import java.util.List;

import sww.lqw.tools.leetcode.Const;
import sww.lqw.tools.leetcode.RunConfig;
import sww.lqw.tools.leetcode.work.AbstractContextWork;
import sww.lqw.tools.leetcode.work.WorkException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class LoginWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		if (context.getWebClient() != null) {
			System.out.println("You have already logged in!");
			return;
		}
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);

		HtmlPage page = webClient.getPage(Const.LOGIN_URL);
		HtmlTextInput inputUser = (HtmlTextInput) page.getElementById("id_login");
		HtmlPasswordInput inputPassword = (HtmlPasswordInput) page.getElementById("id_password");
		List<HtmlForm> forms = page.getForms();
		HtmlForm form = forms.get(0);
		DomNodeList<HtmlElement> buttons = form.getElementsByTagName("button");
		HtmlButton button = (HtmlButton) buttons.get(0);

		RunConfig config = RunConfig.getRunConfig();
		inputUser.setText(config.getUsername());
		inputPassword.setText(config.getPassword());

		HtmlPage next = button.click();
		String origin = next.asText();
		if (origin.contains(String.format("Welcome, %s!", config.getUsername()))) {
			System.out.format("Successfully logged in as %s\n", config.getUsername());
			context.login(webClient);
		} else {
			webClient.close();
			throw new WorkException("Login Failed!");
		}
	}

}
