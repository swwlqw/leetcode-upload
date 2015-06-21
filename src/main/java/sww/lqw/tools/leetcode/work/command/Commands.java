package sww.lqw.tools.leetcode.work.command;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import sww.lqw.tools.leetcode.work.WorkException;

public class Commands {

	private static void printStream(InputStream stream) {
		Scanner scan = new Scanner(stream);
		while (scan.hasNextLine()) {
			System.out.println("  |  " + scan.nextLine());
		}
		scan.close();
	}

	public static void exec(String cmd) throws Exception {
		System.out.format("exec `%s`\n", cmd);
		exec(cmd, null);
	}

	public static void exec(String cmd, File file) throws Exception {
		if (file != null) {
			System.out.format("exec `%s` in dir \"%s\"\n", cmd, file.getName());
		}
		Process p = Runtime.getRuntime().exec(cmd, null, file);
		p.waitFor();
		int code = p.exitValue();
		if (code != 0) {
			printStream(p.getErrorStream());
			String message = String.format("Exit whith code %d while exec `%s`", code, cmd);
			throw new WorkException(message);
		} else {
			printStream(p.getInputStream());
		}
	}
}
