package utilityLibraries;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


/***
 * This class have all Selenium/WebDriver related wrapper methods and features.
 * @author Administrator Created on 10/01/2021
 */
public class GlobalSeleniumLibrary {
	final Logger logger = Logger.getLogger(GlobalSeleniumLibrary.class);

	private WebDriver driver;
	private boolean isDemoMode = false;
	private boolean isChromeHeadless = false;

	public boolean getChromeHeadless() {
		return isChromeHeadless;
	}

	public void setChromeHeadless(boolean _isChromeHeadless) {
		this.isChromeHeadless = _isChromeHeadless;
	}

	private boolean isRemote = false;

	public boolean getIsRemote() {
		return isRemote;
	}

	public void setIsRemote(boolean _isRemote) {
		this.isRemote = _isRemote;
	}

	private boolean isBrowserTypeFirefox;
	public List<String> errorScreenshots;

	public void setDemoMode(boolean isDemoMode) {
		this.isDemoMode = isDemoMode;
	}

	enum Browser {
		IE, FIREFOX, CHROME, SAFARI, EDGE
	}

	public GlobalSeleniumLibrary() {

	}

	public GlobalSeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver _driver) {
		try {
			if (_driver != null) {
				this.driver = _driver;
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	private WebDriver startRemoteIEBroser(String hubURL) {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			InternetExplorerOptions IEOps = new InternetExplorerOptions();
			IEOps.merge(cap);
			driver = new RemoteWebDriver(new URL(hubURL), IEOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private WebDriver startRemoteChromeBroser(String hubURL) {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			ChromeOptions chromeOps = new ChromeOptions();
			if (isChromeHeadless) {
				chromeOps.setHeadless(true);
			}
			chromeOps.merge(cap);
			driver = new RemoteWebDriver(new URL(hubURL), chromeOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
		return driver;
	}

	private WebDriver startRemoteFirefoxBroser(String hubURL) {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			FirefoxOptions firefoxOps = new FirefoxOptions();
			firefoxOps.merge(cap);
			driver = new RemoteWebDriver(new URL(hubURL), firefoxOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private WebDriver startRemoteSafariBroser(String hubURL) {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			SafariOptions safariOps = new SafariOptions();
			safariOps.merge(cap);
			driver = new RemoteWebDriver(new URL(hubURL), safariOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private WebDriver startRemoteEdgeBroser(String hubURL) {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			EdgeOptions edgeOps = new EdgeOptions();
			edgeOps.merge(cap);
			driver = new RemoteWebDriver(new URL(hubURL), edgeOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public WebDriver startRemoteBrowser(String hubURL, Browser browser) {
		try {
			switch (browser) {
			case IE:
				driver = startRemoteIEBroser(hubURL);
				break;

			case CHROME:
				driver = startRemoteChromeBroser(hubURL);
				break;

			case FIREFOX:
				driver = startRemoteFirefoxBroser(hubURL);
				break;

			case SAFARI:
				driver = startRemoteSafariBroser(hubURL);
				break;

			case EDGE:
				driver = startRemoteEdgeBroser(hubURL);

			default:
				logger.error("Currently we are not suporting this browser type!");
				logger.error("Default browser set to Remote Chrome.");
				driver = startRemoteChromeBroser(hubURL);
				break;
			}
			isRemote = true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public WebDriver startLocalBrowser(Browser browser) {
		try {
			switch (browser) {
			case IE:
				driver = startIEBrowser();
				break;

			case CHROME:
				driver = startChromeBrowser();
				break;

			case FIREFOX:
				driver = startFirefoxBrowser();
				break;

			case EDGE:
				driver = startEdgeBrowser();

			default:
				logger.error("Currently we are not suporting this browser type!");
				logger.error("Default browser set to Chrome.");
				driver = startChromeBrowser();
				break;
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts/launch a Chrome Browser
	 * 
	 * @return WebDriver
	 */
	private WebDriver startChromeBrowser() {
		try {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			ChromeOptions chromeOps = new ChromeOptions();
			if (isChromeHeadless) {
				chromeOps.setHeadless(true);
			}
			driver = new ChromeDriver(chromeOps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			logger.debug("Maximize the browser");
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
		return driver;
	}

	/***
	 * This method starts/launch a Firefox Browser
	 * @return WebDriver
	 */
	private WebDriver startFirefoxBrowser() {
		try {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			logger.debug("maximize the browser");
			driver.manage().window().maximize();
			isBrowserTypeFirefox = true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts/launch a IE Browser
	 * @return WebDriver
	 */
	private WebDriver startIEBrowser() {
		try {
			InternetExplorerOptions IEOps = new InternetExplorerOptions();
			IEOps.introduceFlakinessByIgnoringSecurityDomains();
			// IEOps.ignoreZoomSettings();
			System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(IEOps);
			// resetting the IE zoom to 100%
			//driver.findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.CONTROL, "0"));

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
		return driver;
	}


	/***
	 * This method starts/launch a Edge Browser
	 * @return WebDriver
	 */
	private WebDriver startEdgeBrowser() {
		try {
			EdgeOptions edgeOps = new EdgeOptions();
			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			
			driver = new EdgeDriver(edgeOps);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method scroll the scroll bar until element is present
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This methos scroll down and we will give pixel value
	 * @param pixels
	 */
	public void scrollUpDown(int pixels) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + pixels + ")");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollRightLeft(int pixels) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + pixels + ",0)");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method highlight the Webelements, advantage is provide good vision on
	 * the presentation
	 * @param element
	 */
	public void highlightElement(WebElement element) {
		if (isDemoMode == true) {
			try {
				for (int i = 0; i < 4; i++) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow");
					customWait(0.5);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					customWait(0.5);
				}
			} catch (Exception e) {
				logger.error("Error: ", e);
				assertTrue(false);
			}
		}
	}

	/***
	 * This method is Explicit wait ( wait until WebElement is visible, time unit is
	 * second)
	 * @param by
	 * @return
	 */
	public WebElement waitForElementVisibility(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			elem = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	/***
	 * Explicit wait for an element to be present
	 * @param by
	 * @return WebElement
	 */
	public WebElement waitForElementPresence(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			elem = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	/***
	 * Explicit wait for an element to be clickable
	 * 
	 * @param by
	 * @return WebElement
	 */
	public WebElement waitForElementToBeClickable(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			elem = wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	/***
	 * Enter text to text after locate the WebElement
	 * 
	 * @param by
	 * @param inputTxt
	 */
	public void enterTxt(By by, String inputTxt) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(inputTxt);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void enterTxt(WebElement element, String inputTxt) {
		try {
			element.clear();
			element.sendKeys(inputTxt);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * Click button
	 * 
	 * @param by
	 */
	public void clickElement(By by) {
		try {
			WebElement elem = driver.findElement(by);
			elem.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void selectDropDown(By by, String optionValue) {
		try {
			WebElement elem = driver.findElement(by);
			Select dropdown = new Select(elem);
			dropdown.selectByVisibleText(optionValue);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
	}

	public void selectDropDown(WebElement element, String optionValue) {
		try {
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(optionValue);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * Custom wait, time unit is second
	 * 
	 * @param inSeconds
	 */
	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * Method returns current timestamp
	 * 
	 * @return String
	 */
	public String getCurrentTime() {
		String finalTime = null;
		try {
			Date date = new Date();
			String tempTime = new Timestamp(date.getTime()).toString();
			logger.debug("Time: " + tempTime);
			finalTime = tempTime.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
			logger.info("getCurrentTime() ---> " + finalTime);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return finalTime;
	}

	/***
	 * This method return total number of iframe if they exist, if not it will
	 * return zero
	 * 
	 * @return int
	 */
	public int getAlliframes() {
		int totalIframe = 0;
		try {
			List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			totalIframe = iframes.size();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return totalIframe;
	}

	public WebDriver switchToIframeByIdex(int index) {
		try {
			driver = driver.switchTo().frame(index);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method is handling switch browser.
	 * 
	 * @param index
	 * @return
	 */
	public WebDriver switchToBrowserByIndex(int index) {
		int totalBrowsers = 0;
		try {
			Set<String> setBrowsers = driver.getWindowHandles();
			totalBrowsers = setBrowsers.size();
			if (index < totalBrowsers) {
				List<String> listBrowsers = new ArrayList<String>();
				for (String browser : setBrowsers) {
					listBrowsers.add(browser);
				}
				String windowName = listBrowsers.get(index);
				driver = driver.switchTo().window(windowName);
			} else {
				int tempBrowsers = index + 1;
				logger.info("There are only [" + totalBrowsers + "] open browser available, "
						+ "can't switch to browser number [" + tempBrowsers + "], that doesn't exit!");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public WebDriver switchIframe(String htmlAttributeName, String htmlAttributeValue) {
		try {
			String value = "";
			List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			for (WebElement element : iframes) {
				if (htmlAttributeName.toLowerCase().contains("class")) {
					value = element.getAttribute("class");
					if (value.contains(htmlAttributeValue)) {
						driver = driver.switchTo().frame(element);
						break;
					}
				} else if (htmlAttributeName.toLowerCase().contains("for")) {
					value = element.getAttribute("for");
					if (value.contains(htmlAttributeValue)) {
						driver = driver.switchTo().frame(element);
						break;
					}
				} else if (htmlAttributeName.toLowerCase().contains("id")) {
					value = element.getAttribute("id");
					if (value.contains(htmlAttributeValue)) {
						driver = driver.switchTo().frame(element);
						break;
					}
				} else if (htmlAttributeName.toLowerCase().contains("name")) {
					value = element.getAttribute("name");
					if (value.contains(htmlAttributeValue)) {
						driver = driver.switchTo().frame(element);
						break;
					}
				} else if (htmlAttributeName.toLowerCase().contains("src")) {
					value = element.getAttribute("src");
					if (value.contains(htmlAttributeValue)) {
						driver = driver.switchTo().frame(element);
						break;
					}
				} else {
					logger.info("Error ---- ");
					logger.info("The parameter html attirbute name [" + htmlAttributeName
							+ "], is not yet supported at this time.");
					logger.info("Please check method [switchIframe()] under 'GlobalSeleniumLibrary' class.");
				}
				logger.info("attribute [" + htmlAttributeName + "], value [" + value + "]");
				logger.info("parameter attribute_value: " + htmlAttributeValue);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method handles the checkbox
	 * 
	 * @param by
	 * @param isCheck
	 */
	public void handleCheckBox(By by, boolean isCheck) {
		// scenarios
		// 1) user wants to check the check-box,
		// I) check-box is NOT checked by default ==>
		// II) check-box is already checked by default ==>

		// 2)user wants to un-check the check-box,
		// III) check-box is NOT checked by default ==>
		// IV) check-box is already checked by default ==>
		try {
			WebElement checkboxElem = driver.findElement(by);
			handleCheckBox(checkboxElem, isCheck);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void handleCheckBox(WebElement element, boolean isCheck) {
		try {
			boolean checkboxStatus = element.isSelected();
			if (isCheck == true) {
				if (checkboxStatus == false) {
					// scenario 1: ---> click the check-box
					element.click();
					customWait(0.5);
				} else {
					// scenario 2: ---> do nothing
				}
			} else {
				if (checkboxStatus == false) {
					// scenario 3: ---> do nothing
				} else {
					// scenario 4: ---> click the check-box to un-check
					element.click();
					customWait(0.5);
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * this method capture the screen shot when we use it in the test, data format
	 * is .png, stored in screenshots folder
	 * 
	 * @param screnshotFileName
	 * @param filePath
	 */
	public void captureScreenshot(String screnshotFileName, String filePath) {
		String finalScreenshotPath = null;
		try {
			String timeStamp = getCurrentTime();
			if (filePath.isEmpty()) {
				checkDirectory("target/screenshots/");
				finalScreenshotPath = "target/screenshots/" + screnshotFileName + "_" + timeStamp + ".png";
			} else {
				checkDirectory(filePath);
				finalScreenshotPath = filePath + screnshotFileName + "_" + timeStamp + ".png";
			}
			if (isRemote) {
				driver = new Augmenter().augment(driver);
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(finalScreenshotPath));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
		String fullPath = getAbsulatePath(finalScreenshotPath);
		logger.info("Screenshot location: " + fullPath);
	}

	/***
	 * This method check the directory and if no exists, create input path.
	 * 
	 * @param inputPath
	 */
	private void checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		try {
			if (!file2.exists()) {
				if (file2.mkdirs()) {
					logger.info("Directories are created...");
				} else {
					logger.info("Directories are NOT created...");
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method get Absolute path
	 * 
	 * @param inputPath
	 * @return
	 */
	private String getAbsulatePath(String inputPath) {
		String abPath = null;
		try {
			File file = new File(inputPath);
			abPath = file.getAbsolutePath();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return abPath;
	}

	/***
	 * This will capture screen shot at the break point and auto attach email sender
	 * auto send email to team with reports together.
	 * 
	 * @return
	 */
	public List<String> autoAttachErrorImgToEmail() {
		List<String> fileNames = new ArrayList<>();
		try {
			JavaPropertiesManager sessionTimeProp = new JavaPropertiesManager(
					"src/test/resources/sessionConfig.properties/");
			String startTimeStamp = sessionTimeProp.readProperty("sessionTime");
			// String imgTimeStamp = null;
			// long testStatTime = Long.parseLong(startTimeStamp);
			File file = new File("target/screenshots/");
			if (file.isDirectory()) {
				if (file.list().length > 0) {
					File[] screenShotFiles = file.listFiles();
					for (int i = 0; i < screenShotFiles.length; i++) {
						// checking if file is a file, not a folder
						if (screenShotFiles[i].isFile()) {
							String eachFileName = screenShotFiles[i].getName();
							// logger.info("Image file name: " + eachFileName);
							int indexOfUnderScore = eachFileName.lastIndexOf("_");
							int indexOfLast = eachFileName.length() - 4;
							String imgTemTimeStamp = eachFileName.substring(indexOfUnderScore + 1, indexOfLast);
							// logger.info("data of image time stamps....___________");
							// logger.info(imgTemTimeStamp);
							long actualStartTimeStamp = Long.parseLong(startTimeStamp.substring(0, 14));
							long actualImgTimeStamp = Long.parseLong(imgTemTimeStamp.substring(0, 14));
							if (actualImgTimeStamp > actualStartTimeStamp) {
								fileNames.add("target/screenshots/" + eachFileName);
								logger.info("Screenshots attaching: " + eachFileName);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		errorScreenshots = fileNames;
		return fileNames;
	}

	/***
	 * this method is uploading a file
	 * 
	 * @param filePath
	 * @param by
	 */
	public void uploadFile(String filePath, By by) {
		String absulateFilePath = null;
		try {
			File file = new File(filePath);
			absulateFilePath = file.getAbsolutePath();
			WebElement fileUpload = driver.findElement(by);
			if (isRemote) {
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			}
			fileUpload.sendKeys(absulateFilePath);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		logger.info("file uploaded: " + absulateFilePath);
	}

	/***
	 * this methos is close browser, opend browser webpage
	 */
	public void closeBrowsers() {
		if (driver != null) {
			driver.close();
			// if (isBrowserTypeFirefox) { // this line of code is same as below line
			if (isBrowserTypeFirefox == true) {
				// driver.quit();
			} else {
				// driver.quit();
			}
		}
	}
/*
	public static void main(String[] args) {
		GlobalSeleniumLibrary myObject = new GlobalSeleniumLibrary();
		myObject.autoAttachErrorImgToEmail();*/

	}


