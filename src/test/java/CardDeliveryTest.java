import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class CardDeliveryTest {

    @Test
    void cardOrderSuccessTest(){
        open("http://localhost:9999/");

        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=\"city\"] input").setValue("Самара");
        $("[data-test-id=\"date\"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] input").setValue(date);
        $("[data-test-id=\"name\"] input").setValue("Кристобаль Картохель");
        $("[data-test-id=\"phone\"] input").setValue("+79085552266");
        $("[data-test-id=\"agreement\"]").click();
        $("span.button__text").click();

        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }

}