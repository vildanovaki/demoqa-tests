package com.vildanova.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CheckFormTests extends TestBase {

    @Tag("properties")
    @Test
    @Owner("vildanovaki")
    @Link(name = "GitHub", url = "https://demoqa.com/automation-practice-form")
    @Feature("Проверка формы")
    @Severity(SeverityLevel.NORMAL)
    void fillFormTest() {
        step("Открыть страницу https://demoqa.com/automation-practice-form", () -> {
            open("/automation-practice-form");
        });

        step("Ввести имя", () -> {
            $("#firstName").setValue("Kamilya");
        });

        step("Ввести фамилию", () -> {
            $("#lastName").setValue("Vildanova");
        });

        step("Ввести почту", () -> {
            $("#userEmail").setValue("vildanova-kamilya99@yandex.ru");
        });

        step("Выбрать пол", () -> {
            $(byText("Female")).click();
        });

        step("Ввестиномер телефона", () -> {
            $("#userNumber").setValue("9655976283");
        });

        step("Выбрать дату рождения", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("November");
            $(".react-datepicker__year-select").selectOption("1999");
            $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
        });

        step("Выбрать предмет", () -> {
            $("#subjectsInput").setValue("Maths").pressEnter();
        });

        step("Ввести хобби", () -> {
            $("#hobbiesWrapper").$(byText("Music")).click();
        });

        step("Выбрать изображение", () -> {
            $("#uploadPicture").uploadFromClasspath("img/photo.png");
        });

        step("Ввести текущий адрес", () -> {
            $("#currentAddress").setValue("Ulyanovsk");
        });
    }
}
