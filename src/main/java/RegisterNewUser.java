import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterNewUser {

////////////////////............Home  Page........../////////////////////////////////

    public static void main(String[] args) {

        SampleUtil sampleUtil = new SampleUtil();

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
        System.out.println(lblReadyForUni.getText());

        // Switch to Iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"bcAnalytics\"]/div/div[2]/iframe")));

        WebElement txtEmail;
        txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tfa_1")));
        txtEmail.sendKeys("thanurig@gmail.com");
        WebElement btnSubmit = driver.findElement(By.xpath("//*[@id=\"submit_button\"]"));
        btnSubmit.click();

        driver.switchTo().defaultContent();

        WebElement close1 = driver.findElement(By.xpath("//button[@class='bcToasterCloseButton']//img"));
        close1.click();


        /////////////////           Create Account Page        ///////////////////////

        WebElement signin = driver.findElement(By.xpath("//span[normalize-space()='Sign in']"));
        signin.click();

        WebElement create_account = driver.findElement(By.xpath("(//button[normalize-space()='Create an account'])[1]"));
        create_account.click();

        WebElement drpDwnRole = driver.findElement(By.xpath("//*[@id=\"roles-input\"]"));
        drpDwnRole.click();
        drpDwnRole.sendKeys("Learner");
        drpDwnRole.sendKeys(Keys.ENTER);

        //Generate unique firstname
        WebElement inputFirstName = driver.findElement(By.xpath("(//input[@id='ies-field-name-firstname'])[1]"));
        inputFirstName.sendKeys(sampleUtil.generateTime() + "_Thanuri");

        //Generate unique lastname
        WebElement inputLastName = driver.findElement(By.xpath("//input[@id='ies-field-name-lastname']"));
        inputLastName.sendKeys(sampleUtil.generateTime() + "_Geeganage");

        //Generate unique username
        WebElement inputUserName = driver.findElement(By.xpath("(//input[@id='ies-standard-usernamecreateacct'])[1]"));
        String userName = sampleUtil.generateTime() +"_thanurig@gmail.com";
        inputUserName.sendKeys(userName);

        WebElement inputPassword = driver.findElement(By.xpath("(//input[@id='ies-standard-adornment-password-createacct'])[1]"));
        String password = "ABCD12345";
        inputPassword.sendKeys(password);

        WebElement school_inst = driver.findElement(By.xpath("(//input[@id='institution-input'])"));
        school_inst.sendKeys("I don't see my institution or school.");
        //Wait 1 second until the drop-down values are loaded
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        school_inst.sendKeys(Keys.ENTER);


        WebElement chBxTerms = driver.findElement(By.xpath("(//input[@name='consent'])[1]"));
        chBxTerms.click();

        WebElement btnCreateAccount;
        btnCreateAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create-account-submit-btn\"]/span")));
        btnCreateAccount.click();

        //Wait 10 seconds until confirmation dialog box appears
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

    }
}
