import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AqaShopTest extends BaseTest {

    @Test
    @Description("Positive UI test for tour purchase with debit card")
    public void purchaseTourWithDebitCardTest() {
        openApplication();
        selectTour("Super Amazing Tour");
        goToPayment();
        payWithDebitCard("valid_card_data");
        Assert.assertTrue(isPaymentSuccessful());
    }

    @Test
    @Description("Negative UI test for tour purchase with invalid credit card data")
    public void purchaseTourWithInvalidCreditCardTest() {
        openApplication();
        selectTour("Super Amazing Tour");
        goToPayment();
        payWithInvalidCreditCard("invalid_card_data");
        Assert.assertTrue(isErrorDisplayed());
    }
}