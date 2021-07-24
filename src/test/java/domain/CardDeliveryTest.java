package domain;

import com.codeborne.selenide.selector.ByText;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDeliveryTest {


    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBeRegister () {
        $("[placeholder=Город]").setValue("Ма");
        $(byText("Майкоп")).click();
        $("[data-test-id=date] input").setValue("2021-07-29");
        $(("[data-test-id=name] input")).setValue("Иванов Тарас Игнатьевич");
        $(("[data-test-id=phone] input")).setValue("+79264775516");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(14));
    }
}
