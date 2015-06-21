package sww.lqw.tools.leetcode.work;

import com.gargoylesoftware.htmlunit.WebClient;

public class WorkContext {

	private static final WorkContext context = new WorkContext();

	public static WorkContext getContext() {
		return context;
	}

	private WebClient webClient;
	
	private WorkContext() {
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void login(WebClient webClient){
		this.webClient = webClient;
	}
	
}
