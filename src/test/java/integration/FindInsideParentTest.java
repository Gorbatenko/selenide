package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

class FindInsideParentTest extends IntegrationTest {
  @BeforeEach
  void openTestPage() {
    timeout = 2500;
    openFile("long_ajax_request.html");
  }

  @Test
  void findWaitsForParentAndChildElements() {
    $(byText("Result 1")).find("#result-1").shouldNotBe(visible);
    $("#results li", 1).find("#result-2").shouldNotBe(visible);

    $(byText("Run long request")).click();

    $(byText("Result 1")).shouldBe(visible);
    $(byText("Result 1")).find("#result-1").shouldHave(text("r1"));

    $("#results li", 1).shouldBe(visible);
    $("#results li", 1).find("#result-2").shouldHave(text("r2"));
  }

  @Test
  void findWaitsForParentAndChildElementsMeetsCondition() {
    $("#unexisting-parent").shouldNotBe(visible);
    $("#unexisting-parent").find("#unexisting-child").shouldNotBe(visible);
  }
}
