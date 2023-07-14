package test;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class LoginTest {

	    public static void main(String[] args) {
	        
			System.setProperty("webdriver.gecko.driver", "path_of_exe_file");
	        WebDriver driver = new FirefoxDriver();
	        driver.get("https://test.website.com/login");

	        // Test Case 1: Verify successful login
	        testSuccessfulLogin(driver);

	        // Test Case 2: Verify error message for invalid credentials
	        testInvalidCredentials(driver);

	        // Test Case 3: Verify retention of username and password after a failed login attempt
	        testRetainUsernamePassword(driver);

	        // Test Case 4: Verify the 'Login' button is disabled when both username and password fields are empty
	        testLoginButtonDisabled(driver);

	        // Test Case 5: Verify the 'Login' button is enabled only when both username and password fields are filled
	        testLoginButtonEnabled(driver);

	        // Test Case 6: Verify clicking the 'Forgot Password' link redirects to the password recovery page
	        testForgotPasswordLink(driver);

	        // Test Case 7: Validate the password recovery functionality
	        testPasswordRecovery(driver);

	        // Close the browser
	        driver.quit();
	    }

	    private static void testSuccessfulLogin(WebDriver driver) {
	        String username = "valid_username";
	        String password = "valid_password";

	        // Enter valid username and valid password
	        driver.findElement(By.id("username")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);

	        // Click on the Login button
	        driver.findElement(By.id("login-button")).click();

	        // Wait for the system to load and check if login was successful
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.titleContains("Dashboard"));

	        // Assertion: Verify that user has successfully logged in
	        assert driver.getTitle().contains("Dashboard");

	        System.out.println("Test Case 1: Successful login - Passed");
	    }

	    private static void testInvalidCredentials(WebDriver driver) {
	        String username = "invalid_username";
	        String password = "invalid_password";

	        // Enter invalid username and password
	        driver.findElement(By.id("username")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);

	        // Click on The Login Button
	        driver.findElement(By.id("login-button")).click();

	        // Wait for the error message to be displayed
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));

	        // Assertion: Verify that an error message is displayed
	        assert errorMessage.getText().contains("Invalid credentials");

	        System.out.println("Test Case 2: Invalid credentials - Passed");
	    }

	    private static void testRetainUsernamePassword(WebDriver driver) {
	        String username = "valid_username";
	        String password = "invalid_password";

	        // Enter valid username and invalid password
	        driver.findElement(By.id("username")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);

	        // Click the Login button
	        driver.findElement(By.id("login-button")).click();

	        // Wait for the error message to be displayed
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));

	        // Assertion: Verify that username and password fields retain their values
	        assert driver.findElement(By.id("username")).getAttribute("value").equals(username);
	        assert driver.findElement(By.id("password")).getAttribute("value").equals(password);

	        System.out.println("Test Case 3: Retain username and password - Passed");
	    }

	    private static void testLoginButtonDisabled(WebDriver driver) {
	        // Clear username and password fields
	        driver.findElement(By.id("username")).clear();
	        driver.findElement(By.id("password")).clear();

	        // Assertion: Verify that the 'Login' button is disabled
	        assert !driver.findElement(By.id("login-button")).isEnabled();

	        System.out.println("Test Case 4: Login button disabled - Passed");
	    }

	    private static void testLoginButtonEnabled(WebDriver driver) {
	        String username = "valid_username";
	        String password = "valid_password";

	        // Enter valid username and password
	        driver.findElement(By.id("username")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);

	        // Assertion: Verify that the 'Login' button is enabled
	        assert driver.findElement(By.id("login-button")).isEnabled();

	        System.out.println("Test Case 5: Login button enabled - Passed");
	    }

	    private static void testForgotPasswordLink(WebDriver driver) {
	        // Click the 'Forgot Password' link
	        driver.findElement(By.linkText("Forgot Password")).click();

	        // Assertion: Verify that the user is on the password recovery page
	        assert driver.getTitle().contains("Password Recovery");

	        System.out.println("Test Case 6: Forgot Password link redirection - Passed");
	    }

	    private static void testPasswordRecovery(WebDriver driver) {
	        String email = "test@example.com";

	        // Click the 'Forgot Password' link
	        driver.findElement(By.linkText("Forgot Password")).click();

	        // Enter the registered email address
	        driver.findElement(By.id("recovery-email")).sendKeys(email);

	        // Click the 'Recover Password' button
	        driver.findElement(By.id("recover-button")).click();

	        // Wait for the success message to be displayed
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-message")));

	        // Assertion: Verify that a success message is displayed
	        assert successMessage.getText().contains("Password recovery instructions sent");

	        // Assertion: Verify that after successfully resetting the password, the user is redirected to the login page
	        assert driver.getTitle().contains("Login");

	        // Validate that the user can log in with the new password
	        String newPassword = "new_password";

	        // Enter the updated password
	        driver.findElement(By.id("username")).sendKeys(email);
	        driver.findElement(By.id("password")).sendKeys(newPassword);

	        // Click the Login button
	        driver.findElement(By.id("login-button")).click();

	        // Wait for the system to load and check if login was successful
	        WebDriverWait wait2 = new WebDriverWait(driver, 10);
	        wait2.until(ExpectedConditions.titleContains("Dashboard"));

	        // Assertion: Verify that the user has successfully logged in with the new password
	        assert driver.getTitle().contains("Dashboard");

	        System.out.println("Test Case 7: Password recovery - Passed");
	    }
	}
