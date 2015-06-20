package sww.lqw.tools.leetcode;

import com.alibaba.fastjson.JSON;

public class RunConfig {
	/**
	 * leetcode username
	 */
	private String username;

	/**
	 * leetcode password
	 */
	private String password;

	/**
	 * git clone url
	 */
	private String cloneUrl;


	private static final RunConfig runConfig = new RunConfig();

	public static RunConfig getRunConfig() {
		return runConfig;
	}
	
	private RunConfig() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
