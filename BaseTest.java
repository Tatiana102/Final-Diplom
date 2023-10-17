import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Configuration.startMaximized = true;
    }

    @AfterMethod
    public void tearDown() {
        close();
    }

    protected void openApplication() {
        open("http://localhost:8080");
    }

    protected void selectTour(String tourName) {
        // Действия для выбора тура
        $(By.linkText(tourName)).click();
    }

    protected void goToPayment() {
        // Действия для перехода к оплате
        $(By.id("paymentButton")).click();
    }

    protected void payWithDebitCard(String cardData) {
        // Действия для оплаты дебетовой картой
        fillPaymentForm(cardData);
        $(By.id("payButton")).click();
    }

    protected void payWithInvalidCreditCard(String invalidCardData) {
        // Действия для оплаты с неверными данными кредитной карты
        fillPaymentForm(invalidCardData);
        $(By.id("payButton")).click();
    }

    protected boolean isPaymentSuccessful() {
        // Действия для проверки успешной оплаты
        return $(By.id("successMessage")).waitUntil(Condition.appears, 5000).exists();
    }

    protected boolean isErrorDisplayed() {
        // Действия для проверки отображения ошибки
        return $(By.id("errorMessage")).waitUntil(Condition.appears, 5000).exists();
    }

    private void fillPaymentForm(String cardData) {
        // Логика заполнения формы оплаты
        // Например: $(By.id("cardNumber")).setValue(cardData);
    }
}
