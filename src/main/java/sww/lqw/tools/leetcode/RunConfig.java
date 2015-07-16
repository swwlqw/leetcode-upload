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
	 * github username or organization
	 */
	private String space;
	
	/**
	 * github repository
	 */
	private String repository;


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
	
	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getCloneUrl() {
		return String.format("git@github.com:%s/%s.git", space, repository);
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
