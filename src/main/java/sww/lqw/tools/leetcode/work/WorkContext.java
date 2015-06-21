package sww.lqw.tools.leetcode.work;

import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;

import com.gargoylesoftware.htmlunit.WebClient;

public class WorkContext {

	private static final WorkContext context = new WorkContext();

	public static WorkContext getContext() {
		return context;
	}

	private WebClient webClient;
	private TreeSet<Problem> acceptList;
	
	private WorkContext() {
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void login(WebClient webClient){
		this.webClient = webClient;
	}
	
	public void logout(){
		this.webClient = null;
	}

	public TreeSet<Problem> getAcceptList() {
		return acceptList;
	}

	public void setAcceptList(TreeSet<Problem> acceptList) {
		this.acceptList = acceptList;
	}
	
}
