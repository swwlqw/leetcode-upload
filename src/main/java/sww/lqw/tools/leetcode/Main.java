package sww.lqw.tools.leetcode;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;


public class Main {

	private final static String CONFIG_FILE_NAME = "config\\run.properties";
	
	/**
	 * read config file, construct RunConfig object.
	 */
	private static void loadConfig() throws Exception{
		Properties props = new Properties();
		File file = new File(CONFIG_FILE_NAME);
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
	}
	
	public static void main(String[] args) {
		try {
			loadConfig();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Successfully Read Config");
		System.out.println(RunConfig.getRunConfig());
	}

}
