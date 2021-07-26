package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBeRegister() {
        $("[placeholder=Город]").setValue("Ма");
        $(byText("Майкоп")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = newDate.format(formatter);
        $("[data-test-id=date] input").setValue(dateText);
        $(("[data-test-id=name] input")).setValue("Иванов Тарас Игнатьевич");
        $(("[data-test-id=phone] input")).setValue("+79264775516");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("успешно"))
                .shouldBe(visible, Duration.ofSeconds(14))
                .shouldHave(exactText("Встреча успешно забронирована на " + dateText));
    }

    @Test
    void shouldBeErrorOnCityField() {
      $("[placeholder=Город]").setValue("");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = newDate.format(formatter);
        $("[data-test-id=date] input").setValue(dateText);
        $(("[data-test-id=name] input")).setValue("Иванов Тарас Игнатьевич");
        $(("[data-test-id=phone] input")).setValue("+79264775516");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения"))
                .shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldBeReturnErrorOnEmptyTelField() {
        $("[placeholder=Город]").setValue("Ма");
        $(byText("Майкоп")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        $(("[data-test-id=name] input")).setValue("Иванов Тарас Игнатьевич");
        $(("[data-test-id=phone] input")).setValue("+79264775516");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Неверно введена дата"))
                .shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldBeReturnErrorOnEmptyTelField2() {
        $("[placeholder=Город]").setValue("Ма");
        $(byText("Майкоп")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = newDate.format(formatter);
        $("[data-test-id=date] input").setValue(dateText);
        $(("[data-test-id=name] input")).setValue("Иванов Тарас Игнатьевич");
        $(("[data-test-id=phone] input")).setValue("+79264775516");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("успешно"))
                .shouldBe(visible, Duration.ofSeconds(14))
                .shouldHave(exactText("Встреча успешно забронирована на " + dateText));
    }
}