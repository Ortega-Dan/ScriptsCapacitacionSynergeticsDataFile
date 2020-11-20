package datafileTablas;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SynerProperties {

	private static String SYNERENV;

	private static String getFromProps(String batchClass, String property) throws Exception {

		final String synerenv = System.getenv(SYNERENV);
		final String environmentShareFolders = System.getenv("SHARED_FOLDERS");
		Properties prop = new Properties();
		InputStream input = new FileInputStream(
				environmentShareFolders + "\\" + batchClass + "\\script-config\\" + synerenv + ".properties");
		prop.load(input);
		String propresponse = prop.getProperty(property);
		input.close();
		return propresponse;
	}

}
