package sww.lqw.tools.leetcode;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

import sww.lqw.tools.leetcode.work.IWork;
import sww.lqw.tools.leetcode.work.Works;


public class Main {
	
	/**
	 * read config file, construct RunConfig object.
	 */
	private static void loadConfig() throws Exception{
		Properties props = new Properties();
		File file = new File(Const.CONFIG_FILE_NAME);
		FileInputStream input = new FileInputStream(file);
		props.load(input);
		RunConfig runConfig = RunConfig.getRunConfig();
		Class<?> clz = RunConfig.class;
		for (Field field: clz.getDeclaredFields()){
			if (!Modifier.isStatic(field.getModifiers())){
				field.setAccessible(true);
				String name = field.getName();
				String value = props.getProperty(name);
				if (value == null){
					throw new FieldNotConfigException(name);
				}
				field.set(runConfig, value);
			}
		}
		System.out.println("Successfully Read Config.");
		System.out.println(runConfig);
	}
	
	public static void main(String[] args) {
		try {
			loadConfig();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		startWork();
	}

	/**
	 * read command from stdin, execute the work. 
	 */
	private static void startWork() {
		Scanner scan  = new Scanner(System.in);
		System.out.print('>');
		while (scan.hasNext()){
			String cmd = scan.next();
			IWork work = Works.getWorkByCommand(cmd);
			if (work == null){
				System.out.format("No such command \"%s\"\n", cmd);
				System.out.println(JSON.toJSON(Works.getCommands()));
			}else{
				try {
					work.run();
				} catch (Exception e) {
					System.out.format("Failed run task \"%s\"\n", cmd);
					e.printStackTrace(System.out);
				}
			}
			System.out.print('>');
		}
		scan.close();
	}

}
