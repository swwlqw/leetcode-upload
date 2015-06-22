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
	
 	private HashMap<String, Problem> problems = new HashMap<String, Problem>();

	private TreeSet<String> acceptList;
	private TreeSet<String> toUploadList;
	private TreeSet<String> toLinkList;
	
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

	public TreeSet<String> getToUploadList() {
		return toUploadList;
	}

	public void setToUploadList(TreeSet<String> toUploadList) {
		this.toUploadList = toUploadList;
	}

	public TreeSet<String> getToLinkList() {
		return toLinkList;
	}

	public void setToLinkList(TreeSet<String> toLinkList) {
		this.toLinkList = toLinkList;
	}
	
}
