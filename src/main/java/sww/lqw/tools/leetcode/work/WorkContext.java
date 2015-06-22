package sww.lqw.tools.leetcode.work;

import java.util.HashMap;
import java.util.TreeSet;

import sww.lqw.tools.leetcode.bean.Problem;

import com.gargoylesoftware.htmlunit.WebClient;

public class WorkContext {

	private static final WorkContext context = new WorkContext();

	public static WorkContext getContext() {
		return context;
	}

	private WebClient webClient;
	private TreeSet<String> acceptList;
	private HashMap<String, Problem> problems = new HashMap<String, Problem>();
	
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

	public TreeSet<String> getAcceptList() {
		return acceptList;
	}

	public void setAcceptList(TreeSet<String> acceptList) {
		this.acceptList = acceptList;
	}

	public HashMap<String, Problem> getProblems() {
		return problems;
	}

	public void setProblems(HashMap<String, Problem> problems) {
		this.problems = problems;
	}
	
}
