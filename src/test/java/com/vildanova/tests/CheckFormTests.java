package com.vildanova.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.vildanova.config.CredentialConfig;
import com.vildanova.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class CheckFormTests {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "100.0");
        Configuration.browserSize = System.getProperty("size", "1366x768");
        Configuration.baseUrl = "https://demoqa.com";

        SelenideLogger.addListener("AllureListener", new AllureSelenide());

        String remoteUrl = System.getProperty("remoteUrl", "selenoid.autotests.cloud");
        Configuration.remote = format("https://%s:%s@%s/wd/hub/", credentials.login(), credentials.password(), remoteUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

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
        $(By.xpath("//div[@class=' css-yk16xz-control'][1]")).click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Kamilya Vildanova"), text("vildanova-kamilya99@yandex.ru"),
                text("Female"), text("9655976283"), text("17 November,1999"), text("Maths"), text("Music"),
                text("photo.png"), text("Ulyanovsk"), text("NCR Delhi"));
    }
}
