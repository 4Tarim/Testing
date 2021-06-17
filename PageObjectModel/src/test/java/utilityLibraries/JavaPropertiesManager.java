package utilityLibraries;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JavaPropertiesManager {
	
	final static Logger logger = Logger.getLogger(JavaPropertiesManager.class);
	private String propertiesFile;
	private Properties prop;
	private FileInputStream input;
	private FileOutputStream output;

	public JavaPropertiesManager(String propertiesFilePath) {
		try {
			if (propertiesFilePath.length() > 0) {
				propertiesFile = propertiesFilePath;
				prop = new Properties();
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				logger.error("Error: ", e);
				assertTrue(false);
			}
		}
		return value;
	}

	public void setProperty(String key, String value) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
			logger.info("Properties file created ---> " + propertiesFile);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				logger.error("Error: ", e);
				assertTrue(false);
			}
		}
	}

	public void setProperty(String key, String value, String comments) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, comments);
			logger.info("Properties file created ---> " + propertiesFile);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				logger.error("Error: ", e);
				assertTrue(false);
			}
		}
	}

}
