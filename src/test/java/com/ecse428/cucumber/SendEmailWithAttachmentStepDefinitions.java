package test.java.com.ecse428.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendEmailWithAttachmentStepDefinitions {

	// Change this to the absolute path of the Google Chrome WebDriver executable
	static final String PATH_TO_WEBDRIVER = "C:\\Users\\Rami\\Desktop\\ECSE428\\assignment-b\\chromedriver.exe";

	private static WebDriver driver;
	private static String subjectToSend;

	
	//@Given("^I am logged in to gmail on my homepage$")
	public static void givenIAmLoggedInToMyGmailOnMyHomepage() {
		
		initializeDriver();
		
		// Randomly generate an subject string to send
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 20) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphanumeric.length());
            sb.append(alphanumeric.charAt(index));
        }
        
        subjectToSend = sb.toString();
        
		driver.get("https://mail.google.com/mail/");
		WebElement emailAddress = driver.findElement(By.name("identifier"));
		emailAddress.sendKeys("boblamar9999");
		WebElement next = driver.findElement(By.id("identifierNext"));
		next.click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.name("password"))));
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Boblamar_9999");
		WebElement passwordNext = driver.findElement(By.id("passwordNext"));
		passwordNext.click();	
		
		wait.until(ExpectedConditions.presenceOfElementLocated((By.className("z0"))));
		
		// Check that the system is in its initial state
		systemInInitialState();
		
	}
	
	//@When("^I click on compose$")
	public static void whenIClickOnCompose() {
		
		WebElement compose = driver.findElement(By.className("z0"));
		compose.click();
		
	}
	
	//@And("^I add a recipient to my message$")
	public static void andIAddARecipientToMyMessage() {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.name("to"))));
		WebElement recipient = driver.findElement(By.name("to"));
		recipient.sendKeys("boblamar9999@gmail.com");
		
	}
	
	//@And("^I add a different recipient to my message$")
	public static void andIAddADifferentRecipientToMyMessage() {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.name("to"))));
		WebElement recipient = driver.findElement(By.name("to"));
		recipient.sendKeys("rami.djema@gmail.com");
		
	}
	
	//@And("^I add a subject to my message$")
	public static void andIAddASubjectToMyMessage() {
		
		WebElement subject = driver.findElement(By.name("subjectbox")); 
		subject.sendKeys(subjectToSend);
		
	}
	
	//@And(^I add an attachment to my message$)
	public static void andIAddAnAttachmentToMyMessage() throws Exception{
		
		WebElement attachmentButton = driver.findElement(By.xpath("//*[@class='a1 aaA aMZ']"));
		attachmentButton.click();
		systemWaitUntilRefreshed();
		
		File image = new File("egg.jpg");
		
		StringSelection ss = new StringSelection(image.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		
		// Copy paste the path into the file location box
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//@And(^I add a different attachment to my message$)
	public static void andIAddADifferentAttachmentToMyMessage() throws Exception{
		
		WebElement attachmentButton = driver.findElement(By.xpath("//*[@class='a1 aaA aMZ']"));
		attachmentButton.click();
		systemWaitUntilRefreshed();
		
		File image = new File("dog.jpg");
		
		StringSelection ss = new StringSelection(image.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		
		// Copy paste the path into the file location box
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//@And(^I click Send$)
	public static void andIclickSend() {
		
		WebElement sendButton = driver.findElement(By.xpath("//div[text()='Send']"));
		sendButton.click();
		
	}
	
	//@Then(^the recipient receives the message with the right subject and attachment$)
	public static void thenTheRecipientReceivesTheMessageWithTheRightSubjectAndAttachment() {
		
		// Navigate to sent messages
		WebElement sentEmails = driver.findElement(By.xpath("//*[@class='TN bzz aHS-bnu']"));
		sentEmails.click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[text()='To: ']"))));
		
		// Check that the recipient gets the email
		emailWasSent();

		// Check that the system returned to its initial state
		systemReturnedToInitialState();
		
	}
	
	//@Then(^the recipient receives the message with no subject and an attachment$)
	public static void thenTheRecipientReceivesTheMessageWithNoSubjectAndAnAttachment() {
		
		// Navigate to sent messages
		WebElement sentEmails = driver.findElement(By.xpath("//*[@class='TN bzz aHS-bnu']"));
		sentEmails.click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[text()='To: ']"))));
		
		// Check that the recipient gets the email
		emailWasSentWithNoSubject();

		// Check that the system returned to its initial state
		systemReturnedToInitialState();
	}
	
	//@And(^I accept the prompt window$)
	public static void andIAcceptThePromptWindow() throws InterruptedException, AWTException {
		
		// Press enter to accept the prompt window
		systemWaitUntilRefreshed();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		systemWaitUntilRefreshed();
		
	}
	
	//@Then(^an error message is displayed specifying to add a recipient$)
	public static void thenAnErrorMessageIsDisplayedSpecifyingToAddARecipient() throws InterruptedException, AWTException {
		
		// Press enter to accept the prompt window
		systemWaitUntilRefreshed();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		systemWaitUntilRefreshed();
		
		// Check that the system returned to its initial state
		systemReturnedToInitialState();
	}
	
	//@And(^I add a large attachment to my message$)
	public static void andIAddALargeAttachmentToMyMessage() throws InterruptedException, AWTException {
		
		WebElement attachmentButton = driver.findElement(By.xpath("//*[@class='a1 aaA aMZ']"));
		attachmentButton.click();
		systemWaitUntilRefreshed();
		
		File image = new File("large_file.pdf");
		
		StringSelection ss = new StringSelection(image.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		
		// Copy paste the path into the file location box
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//@And(^I add an alternate attachment to my message$)
	public static void andIAddAnAlternateAttachmentToMyMessage() throws InterruptedException, AWTException {
		
		WebElement attachmentButton = driver.findElement(By.xpath("//*[@class='a1 aaA aMZ']"));
		attachmentButton.click();
		systemWaitUntilRefreshed();
		
		File image = new File("cat.jpg");
		
		StringSelection ss = new StringSelection(image.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		
		// Copy paste the path into the file location box
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//@Then(^an error message is displayed specifying the attachment is too large$)
	public static void thenAnErrorMessageIsDisplayedSpecifyingTheAttachmentIsTooLarge() throws InterruptedException, AWTException {
		
		systemWaitUntilRefreshed();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		systemWaitUntilRefreshed();
		
		// Check that the system returned to its initial state
		systemReturnedToInitialState();
		
	}
	
	public static void initializeDriver() {
		System.setProperty("webdriver.chrome.driver", PATH_TO_WEBDRIVER);
		driver = new ChromeDriver();
	}
	
	public static void systemInInitialState() {
		
        // Email not yet received
		assertFalse(driver.getPageSource().contains(subjectToSend));
		
		// Check that I am logged in and on the Gmail home page
		assertTrue(driver.getPageSource().contains("Bob"));
		assertTrue(driver.getPageSource().contains("Social"));
		assertTrue(driver.getPageSource().contains("Promotions"));
		
	} 
	
	public static void systemReturnedToInitialState(){
		
		// Check that I am still logged in and the system is back to its initial state (home page)
		assertTrue(driver.getPageSource().contains("Bob"));
		assertTrue(driver.getPageSource().contains("Social"));
		assertTrue(driver.getPageSource().contains("Promotions"));
		
		driver.close();
	}
	
	private static void systemWaitUntilRefreshed() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
	}
	
	public static void emailWasSent() {
		assertTrue(driver.getPageSource().contains(subjectToSend));
	}
	
	public static void emailWasSentWithNoSubject() {
		assertTrue(driver.getPageSource().contains("no subject"));
	}
	
	//The tests begin here. Since we were unable to configure cucumber, we had to manually write down the tests
	//step by step so that they can be run as JUnit tests, rather than cucumber gluing them to the methods.
	
	@Test
	public void normalFlowTest1() throws Exception {
		
		givenIAmLoggedInToMyGmailOnMyHomepage();
		whenIClickOnCompose();
		andIAddARecipientToMyMessage();
		andIAddASubjectToMyMessage();
		andIAddAnAttachmentToMyMessage();
		andIclickSend();
		thenTheRecipientReceivesTheMessageWithTheRightSubjectAndAttachment();
		
	}
	
	// Send email with a different recipient, attachment and subject
	@Test
	public void normalFlowTest2() throws Exception {
		
		givenIAmLoggedInToMyGmailOnMyHomepage();
		whenIClickOnCompose();
		andIAddADifferentRecipientToMyMessage();
		andIAddASubjectToMyMessage();
		andIAddADifferentAttachmentToMyMessage();
		andIclickSend();
		thenTheRecipientReceivesTheMessageWithTheRightSubjectAndAttachment();
		
	}
	
	@Test
	public void alternateFlowTest() throws Exception {
		
		givenIAmLoggedInToMyGmailOnMyHomepage();
		whenIClickOnCompose();
		andIAddARecipientToMyMessage();
		andIAddAnAlternateAttachmentToMyMessage();
		andIclickSend();
		andIAcceptThePromptWindow();
		thenTheRecipientReceivesTheMessageWithNoSubjectAndAnAttachment();
		
	}
	
	@Test
	public void errorFlow1() throws Exception{
		
		givenIAmLoggedInToMyGmailOnMyHomepage();
		whenIClickOnCompose();
		andIAddASubjectToMyMessage();
		andIAddAnAttachmentToMyMessage();
		andIclickSend();
		thenAnErrorMessageIsDisplayedSpecifyingToAddARecipient();
		
	}
	
	@Test
	public void errorFlow2() throws Exception{
		
		givenIAmLoggedInToMyGmailOnMyHomepage();
		whenIClickOnCompose();
		andIAddARecipientToMyMessage();
		andIAddASubjectToMyMessage();
		andIAddALargeAttachmentToMyMessage();
		thenAnErrorMessageIsDisplayedSpecifyingTheAttachmentIsTooLarge();
		
	}

}
