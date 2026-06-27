package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']"); //By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

    public InventoryPage login(String username, String password){
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        //画面遷移後の状態を表す新しいPageオブジェクトを生成して、それを呼び出し元に渡している、次のページが明確
        return new InventoryPage(driver, timeoutSeconds);
    }

    public void invalidLogin(String username, String password){
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getErrorMessage(){
        return textOf(errorMessage);
    }

}
