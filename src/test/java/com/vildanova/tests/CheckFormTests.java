package com.vildanova.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckFormTests extends TestBase{

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Kamilya");
        $("#lastName").setValue("Vildanova");
        $("#userEmail").setValue("vildanova-kamilya99@yandex.ru");
        $(byText("Female")).click();
        $("#userNumber").setValue("9655976283");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("img/photo.png");
        $("#currentAddress").setValue("Ulyanovsk");
    }
}
