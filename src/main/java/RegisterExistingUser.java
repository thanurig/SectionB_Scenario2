import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Automated Test Scenario :
        When user trying to create new account with existing user credentials, program will redirect user to sign in page
        Then user sign in with existing user credentials
*/
public class RegisterExistingUser {

    ////////////////////............Home  Page........../////////////////////////////////

    public static void main(String[] args) throws InterruptedException {

        RegisterNewUser newUserAccount=new RegisterNewUser();

        //Initiate Chrome browser using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 60);

        driver.get("https://www.pearson.com/en-gb.html");

        WebElement btnYesIAgree = driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"));
        btnYesIAgree.click();

        WebElement lblReadyForUni;
        lblReadyForUni = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bcToasterTitle")));
       // System.out.println(lblReadyForUni.getText());

        // Switch to Iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"bcAnalytics\"]/div/div[2]/iframe")));

        WebElement txtEmail;
        txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tfa_1")));
        txtEmail.sendKeys("thanurig@gmail.com");

        WebElement btnSubmit = driver.findElement(By.xpath("//*[@id=\"submit_button\"]"));
        btnSubmit.click();

        driver.switchTo().defaultContent();

        WebElement btnclose1 = driver.findElement(By.xpath("//button[@class='bcToasterCloseButton']//img"));
        btnclose1.click();

        /////////////////          Navigate to Create Account Page        ///////////////////////

        WebElement signin = driver.findElement(By.xpath("//span[normalize-space()='Sign in']"));
        signin.click();

        WebElement create_account = driver.findElement(By.xpath("(//button[normalize-space()='Create an account'])[1]"));
        create_account.click();

        WebElement drpDwnRole = driver.findElement(By.xpath("//*[@id=\"roles-input\"]"));
        drpDwnRole.click();
        drpDwnRole.sendKeys("Learner");
        drpDwnRole.sendKeys(Keys.ENTER);

        WebElement inputFirstName = driver.findElement(By.xpath("(//input[@id='ies-field-name-firstname'])[1]"));
        inputFirstName.sendKeys("Thanuri");

        WebElement inputLastName = driver.findElement(By.xpath("//input[@id='ies-field-name-lastname']"));
        inputLastName.sendKeys("Geeganage");

        WebElement inputUserName = driver.findElement(By.xpath("(//input[@id='ies-standard-usernamecreateacct'])[1]"));
        inputUserName.sendKeys("thanurig@gmail.com");

        WebElement inputPwd = driver.findElement(By.xpath("(//input[@id='ies-standard-adornment-password-createacct'])[1]"));
        inputPwd.sendKeys("ABCD12345");

        WebElement school_inst = driver.findElement(By.xpath("(//input[@id='institution-input'])"));
        school_inst.sendKeys("I don't see my institution or school.");

        //Wait 1 second until the drop-down values are loaded
            Thread.sleep(1000);
        school_inst.sendKeys(Keys.ENTER);

        WebElement terms = driver.findElement(By.xpath("(//input[@name='consent'])[1]"));
        terms.click();

        WebElement signInLink = driver.findElement(By.xpath("(//button[@class='MuiTypography-root MuiLink-root MuiLink-underlineHover jss10 MuiLink-button MuiTypography-colorPrimary'])[1]"));

        WebElement alertText = driver.findElement(By.xpath("(//div[@role='alert'])[1]"));
        String alertGettext = alertText.getText();

        boolean checkTextall = alertGettext.contains("An account with this information already exists.");
        boolean checkTextusername = alertGettext.contains("Username is already being used.");

       if (terms.isSelected() && checkTextall == true) {
            signInLink.click();
        } else if (terms.isSelected() && checkTextusername == true) {
            signInLink.click();
        }

        ///////////////// Signing Page////////////////////////

        WebElement userNameSigning = driver.findElement(By.xpath("//input[@id='ies-standard-signin-username']"));
        userNameSigning.sendKeys("thanurig@gmail.com");

        WebElement pwdSignIn = driver.findElement(By.xpath("(//input[@id='ies-standard-signin-password'])[1]"));
        pwdSignIn.sendKeys("ABCD12345");

        WebElement btnSubmit2 = driver.findElement(By.xpath("(//button[@id='ies-standard-signin-submit'])[1]"));
        btnSubmit2.click();
        //Wait 5 Seconds until the user profile is logged after signing in
            Thread.sleep(8000);

        driver.quit();
    }

}

