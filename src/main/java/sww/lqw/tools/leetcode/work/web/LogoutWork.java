package sww.lqw.tools.leetcode.work.web;

import sww.lqw.tools.leetcode.work.AbstractContextWork;

import com.gargoylesoftware.htmlunit.WebClient;

public class LogoutWork extends AbstractContextWork {

	@Override
	public void run() throws Exception {
		WebClient webClient = context.getWebClient();
		if (webClient == null) {
			System.out.println("You have not logged in yet!");
		}else{
			webClient.close();
			context.logout();
			System.out.println("Successfully logged out!");
		}
	}
	
}
