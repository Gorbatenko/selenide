package com.codeborne.selenide;

import com.codeborne.selenide.ex.DialogTextMismatch;
import com.codeborne.selenide.ex.JavaScriptErrorsFound;
import com.codeborne.selenide.impl.LegacyConf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getSelenideDriver;

/**
 * The main starting point of Selenide.
 *
 * You start with methods {@link #open(String)} for opening the tested application page and
 * {@link #$(String)} for searching web elements.
 */
public class Selenide {
  /**
   * The main starting point in your tests.
   * Open a browser window with given URL.
   *
   * If browser window was already opened before, it will be reused.
   *
   * Don't bother about closing the browser - it will be closed automatically when all your tests are done.
   *
   * @param relativeOrAbsoluteUrl
   *   If not starting with "http://" or "https://" or "file://", it's considered to be relative URL.
   *   In this case, it's prepended by baseUrl
   */
  public static void open(String relativeOrAbsoluteUrl) {
    getSelenideDriver().open(relativeOrAbsoluteUrl);
  }

  /**
   * @see Selenide#open(String)
   */
  public static void open(URL absoluteUrl) {
    getSelenideDriver().open(absoluteUrl);
  }

  /**
   * The main starting point in your tests.
   * Open a browser window with given URL and credentials for basic authentication
   *
   * If browser window was already opened before, it will be reused.
   *
   * Don't bother about closing the browser - it will be closed automatically when all your tests are done.
   *
   * @param relativeOrAbsoluteUrl
   * @param domain
   * @param login
   * @param password
   *   If not starting with "http://" or "https://" or "file://", it's considered to be relative URL.
   *   In this case, it's prepended by baseUrl
   */
  public static void open(String relativeOrAbsoluteUrl, String domain, String login, String password) {
    getSelenideDriver().open(relativeOrAbsoluteUrl, domain, login, password);
  }

  /**
   * @see Selenide#open(URL, String, String, String)
   */
  public static void open(URL absoluteUrl, String domain, String login, String password) {
    getSelenideDriver().open(absoluteUrl, domain, login, password);
  }

  /**
   * Update the hash of the window location.
   * Useful to navigate in ajax apps without reloading the page, since open(url) makes a full page reload.
   *
   * @param hash value for window.location.hash - Accept either "#hash" or "hash".
   */
  public static void updateHash(String hash) {
    getSelenideDriver().updateHash(hash);
  }

  /**
   * Open a web page and create PageObject for it.
   * @return PageObject of given class
   */
  public static <PageObjectClass> PageObjectClass open(String relativeOrAbsoluteUrl,
                                                       Class<PageObjectClass> pageObjectClassClass) {
    return getSelenideDriver().open(relativeOrAbsoluteUrl, pageObjectClassClass);
  }

  /**
   * Open a web page and create PageObject for it.
   * @return PageObject of given class
   */
  public static <PageObjectClass> PageObjectClass open(URL absoluteUrl,
                                                       Class<PageObjectClass> pageObjectClassClass) {
    return getSelenideDriver().open(absoluteUrl, pageObjectClassClass);
  }

  /**
   * Open a web page using Basic Auth credentials and create PageObject for it.
   * @return PageObject of given class
   */
  public static <PageObjectClass> PageObjectClass open(String relativeOrAbsoluteUrl,
                                                       String domain, String login, String password,
                                                       Class<PageObjectClass> pageObjectClassClass) {
    return getSelenideDriver().open(relativeOrAbsoluteUrl, domain, login, password, pageObjectClassClass);
  }

  /**
   * Open a web page using Basic Auth credentials and create PageObject for it.
   * @return PageObject of given class
   */
  public static <PageObjectClass> PageObjectClass open(URL absoluteUrl, String domain, String login, String password,
                                                       Class<PageObjectClass> pageObjectClassClass) {
    return getSelenideDriver().open(absoluteUrl, domain, login, password, pageObjectClassClass);
  }

  /**
   * Close the browser if it's open
   */
  public static void close() {
    closeWebDriver();
  }

  /**
   * Reload current page
   */
  public static void refresh() {
    getSelenideDriver().refresh();
  }

  /**
   * Navigate browser back to previous page
   */
  public static void back() {
    getSelenideDriver().back();
  }

  /**
   * Navigate browser forward to next page
   */
  public static void forward() {
    getSelenideDriver().forward();
  }

  /**
   * @return title of the page
   */
  public static String title() {
    return getSelenideDriver().title();
  }

  /**
   * Not recommended. Test should not sleep, but should wait for some condition instead.
   * @param milliseconds Time to sleep in milliseconds
   */
  public static void sleep(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  /**
   * Take the screenshot of current page and save to file fileName.html and fileName.png
   * @param fileName Name of file (without extension) to save HTML and PNG to
   * @return The name of resulting file
   */
  public static String screenshot(String fileName) {
    return getSelenideDriver().screenshot(fileName);
  }

  /**
   * Wrap standard Selenium WebElement into SelenideElement
   * to use additional methods like shouldHave(), selectOption() etc.
   *
   * @param webElement standard Selenium WebElement
   * @return given WebElement wrapped into SelenideElement
   */
  public static SelenideElement $(WebElement webElement) {
    return getSelenideDriver().$(webElement);
  }

  /**
   * Locates the first element matching given CSS selector
   * ATTENTION! This method doesn't start any search yet!
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @return SelenideElement
   */
  public static SelenideElement $(String cssSelector) {
    return getSelenideDriver().$(cssSelector);
  }

  /**
   * Locates the first element matching given XPATH expression
   * ATTENTION! This method doesn't start any search yet!
   * @param xpathExpression any XPATH expression //*[@id='value'] //E[contains(@A, 'value')]
   * @return SelenideElement which locates elements via XPath
   */
  public static SelenideElement $x(String xpathExpression) {
    return getSelenideDriver().$x(xpathExpression);
  }

  /**
   * Locates the first element matching given CSS selector
   * ATTENTION! This method doesn't start any search yet!
   * @param seleniumSelector any Selenium selector like By.id(), By.name() etc.
   * @return SelenideElement
   */
  public static SelenideElement $(By seleniumSelector) {
    return getSelenideDriver().$(seleniumSelector);
  }

  /**
   * @see #getElement(By, int)
   */
  public static SelenideElement $(By seleniumSelector, int index) {
    return getSelenideDriver().$(seleniumSelector, index);
  }

  /**
   * @deprecated please use $(parent).$(String) which is the same
   * (method will not be removed until 5.x or later)
   * @see  #$(String)
   *
   * Locates the first element matching given CSS selector
   * ATTENTION! This method doesn't start any search yet!
   * @param parent the WebElement to search elements in
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @return SelenideElement
   */
  @Deprecated
  public static SelenideElement $(WebElement parent, String cssSelector) {
    return getSelenideDriver().$(parent).$(cssSelector);
  }

  /**
   * Locates the Nth element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @param index 0..N
   * @return SelenideElement
   */
  public static SelenideElement $(String cssSelector, int index) {
    return getSelenideDriver().$(cssSelector, index);
  }

  /**
   * @deprecated please use $(parent).$(String, int) which is the same
   * (method will not be removed until 4.x or later)
   * @see  #$(String, int)
   *
   * Locates the Nth element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param parent the WebElement to search elements in
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @param index 0..N
   * @return SelenideElement
   */
  @Deprecated
  public static SelenideElement $(WebElement parent, String cssSelector, int index) {
    return getSelenideDriver().$(parent).$(cssSelector, index);
  }

  /**
   * @deprecated please use $(parent).$(By) which is the same
   * (method will not be removed until 4.x or later)
   * @see  #$(By)
   *
   * Locates the first element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param parent the WebElement to search elements in
   * @param seleniumSelector any Selenium selector like By.id(), By.name() etc.
   * @return SelenideElement
   */
  @Deprecated
  public static SelenideElement $(WebElement parent, By seleniumSelector) {
    return getSelenideDriver().$(parent).$(seleniumSelector);
  }

  /**
   * @deprecated please use $(parent).$(By, int) which is the same
   * (method will not be removed until 4.x or later)
   * @see  #$(By, int)
   *
   * Locates the Nth element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param parent the WebElement to search elements in
   * @param seleniumSelector any Selenium selector like By.id(), By.name() etc.
   * @param index 0..N
   * @return SelenideElement
   */
  @Deprecated
  public static SelenideElement $(WebElement parent, By seleniumSelector, int index) {
    return getSelenideDriver().$(parent).$(seleniumSelector, index);
  }

  /**
   * Initialize collection with Elements
   * @param elements
   * @return
   */
  public static ElementsCollection $$(Collection<? extends WebElement> elements) {
    return getSelenideDriver().$$(elements);
  }

  /**
   * Locates all elements matching given CSS selector.
   * ATTENTION! This method doesn't start any search yet!
   * Methods returns an ElementsCollection which is a list of WebElement objects that can be iterated,
   * and at the same time is implementation of WebElement interface,
   * meaning that you can call methods .sendKeys(), click() etc. on it.
   *
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @return empty list if element was no found
   */
  public static ElementsCollection $$(String cssSelector) {
    return getSelenideDriver().$$(cssSelector);
  }

  /**
   * Locates all elements matching given XPATH expression.
   * ATTENTION! This method doesn't start any search yet!
   * Methods returns an ElementsCollection which is a list of WebElement objects that can be iterated,
   * and at the same time is implementation of WebElement interface,
   * meaning that you can call methods .sendKeys(), click() etc. on it.
   * @param xpathExpression any XPATH expression //*[@id='value'] //E[contains(@A, 'value')]
   * @return ElementsCollection which locates elements via XPath
   */
  public static ElementsCollection $$x(String xpathExpression) {
    return getSelenideDriver().$$x(xpathExpression);
  }

  /**
   * Locates all elements matching given CSS selector.
   * ATTENTION! This method doesn't start any search yet!
   * Methods returns an ElementsCollection which is a list of WebElement objects that can be iterated,
   * and at the same time is implementation of WebElement interface,
   * meaning that you can call methods .sendKeys(), click() etc. on it.
   *
   * @param seleniumSelector any Selenium selector like By.id(), By.name() etc.
   * @return empty list if element was no found
   */
  public static ElementsCollection $$(By seleniumSelector) {
    return getSelenideDriver().$$(seleniumSelector);
  }

  /**
   * @deprecated please use $(parent).$$(String) which is the same
   * (method will not be removed until 4.x or later)
   * @see  #$$(String)
   *
   * Locates all elements matching given CSS selector inside given parent element
   * ATTENTION! This method doesn't start any search yet!
   * Methods returns an ElementsCollection which is a list of WebElement objects that can be iterated,
   * and at the same time is implementation of WebElement interface,
   * meaning that you can call methods .sendKeys(), click() etc. on it.
   *
   * @param parent the WebElement to search elements in
   * @param cssSelector any CSS selector like "input[name='first_name']" or "#messages .new_message"
   * @return empty list if element was no found
   */
  @Deprecated
  public static ElementsCollection $$(WebElement parent, String cssSelector) {
    return getSelenideDriver().$(parent).$$(cssSelector);
  }

  /**
   * @deprecated please use $(parent).$$(By) which is the same
   * (method will not be removed until 4.x or later)
   * @see  #$$(By)
   *
   * Locates all elements matching given criteria inside given parent element
   * ATTENTION! This method doesn't start any search yet!
   * @see Selenide#$$(WebElement, String)
   */
  @Deprecated
  public static ElementsCollection $$(WebElement parent, By seleniumSelector) {
    return getSelenideDriver().$(parent).$$(seleniumSelector);
  }

  /**
   * Locates the first element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param criteria instance of By: By.id(), By.className() etc.
   * @return SelenideElement
   */
  public static SelenideElement getElement(By criteria) {
    return getSelenideDriver().getElement(criteria);
  }

  /**
   * Locates the Nth element matching given criteria
   * ATTENTION! This method doesn't start any search yet!
   * @param criteria instance of By: By.id(), By.className() etc.
   * @param index 0..N
   * @return SelenideElement
   */
  public static SelenideElement getElement(By criteria, int index) {
    return getSelenideDriver().getElement(criteria, index);
  }

  /**
   * Locates all elements matching given CSS selector
   * ATTENTION! This method doesn't start any search yet!
   * @param criteria instance of By: By.id(), By.className() etc.
   * @return empty list if element was no found
   */
  public static ElementsCollection getElements(By criteria) {
    return getSelenideDriver().getElements(criteria);
  }

  /**
   * Executes JavaScript
   */
  public static <T> T executeJavaScript(String jsCode, Object... arguments) {
    return getSelenideDriver().executeJavaScript(jsCode, arguments);
  }

  /**
   * @deprecated Not recommended. Use method {@code $(radioField).selectRadio(value);} instead
   *
   * Select radio field by value
   * @param radioField any By selector for finding radio field
   * @param value value to select (should match an attribute "value")
   * @return the selected radio field
   */
  @Deprecated
  public static SelenideElement selectRadio(By radioField, String value) {
    return getSelenideDriver().$(radioField).selectRadio(value);
  }

  /**
   * Returns selected element in radio group
   * @param radioField
   * @return null, if nothing selected
   */
  public static SelenideElement getSelectedRadio(By radioField) {
    return getSelenideDriver().getSelectedRadio(radioField);
  }

  /**
   * Mock confirm dialog that return given value
   * @param confirmReturnValue true = OK, false = CANCEL
   */
  public static void onConfirmReturn(boolean confirmReturnValue) {
    getSelenideDriver().onConfirmReturn(confirmReturnValue);
  }

  /**
   * Accept (Click "Yes" or "Ok") in the confirmation dialog (javascript 'alert' or 'confirm').
   * @return actual dialog text
   */
  public static String confirm() {
    return getSelenideDriver().confirm();
  }

  /**
   * Accept (Click "Yes" or "Ok") in the confirmation dialog (javascript 'alert' or 'confirm').
   *
   * @param expectedDialogText if not null, check that confirmation dialog displays this message (case-sensitive)
   * @throws DialogTextMismatch if confirmation message differs from expected message
   * @return actual dialog text
   */
  public static String confirm(String expectedDialogText) {
    return getSelenideDriver().confirm(expectedDialogText);
  }

  /**
   * Accept (Click "Yes" or "Ok") in the confirmation dialog (javascript 'prompt').
   * @return actual dialog text
   */
  public static String prompt() {
    return getSelenideDriver().prompt();
  }

  /**
   * Accept (Click "Yes" or "Ok") in the confirmation dialog (javascript 'prompt').
   * @param inputText if not null, sets value in prompt dialog input
   * @return actual dialog text
   */
  public static String prompt(String inputText) {
    return getSelenideDriver().prompt(inputText);
  }

  /**
   * Accept (Click "Yes" or "Ok") in the confirmation dialog (javascript 'prompt').
   *
   * @param expectedDialogText if not null, check that confirmation dialog displays this message (case-sensitive)
   * @param inputText if not null, sets value in prompt dialog input
   * @throws DialogTextMismatch if confirmation message differs from expected message
   * @return actual dialog text
   */
  public static String prompt(String expectedDialogText, String inputText) {
    return getSelenideDriver().prompt(expectedDialogText, inputText);
  }

  /**
   * Dismiss (click "No" or "Cancel") in the confirmation dialog (javascript 'alert' or 'confirm').
   * @return actual dialog text
   */
  public static String dismiss() {
    return getSelenideDriver().dismiss();
  }

  /**
   * Dismiss (click "No" or "Cancel") in the confirmation dialog (javascript 'alert' or 'confirm').
   *
   * @param expectedDialogText if not null, check that confirmation dialog displays this message (case-sensitive)
   * @throws DialogTextMismatch if confirmation message differs from expected message
   * @return actual dialog text
   */
  public static String dismiss(String expectedDialogText) {
    return getSelenideDriver().dismiss(expectedDialogText);
  }

  /**
   * Switch to window/tab/frame/parentFrame/innerFrame/alert.
   * Allows switching to window by title, index, name etc.
   *
   * Similar to org.openqa.selenium.WebDriver#switchTo(), but all methods wait until frame/window/alert
   * appears if it's not visible yet (like other Selenide methods).
   *
   * @return SelenideTargetLocator
   */
  public static SelenideTargetLocator switchTo() {
    return getSelenideDriver().switchTo();
  }

  /**
   * @return WebElement, not SelenideElement! which has focus on it
   */
  public static WebElement getFocusedElement() {
    return getSelenideDriver().getFocusedElement();
  }

  /**
   * Create a Page Object instance
   */
  public static <PageObjectClass> PageObjectClass page(Class<PageObjectClass> pageObjectClass) {
    return getSelenideDriver().page(pageObjectClass);
  }

  /**
   * Create a Page Object instance
   */
  public static <PageObjectClass, T extends PageObjectClass> PageObjectClass page(T pageObject) {
    return getSelenideDriver().page(pageObject);
  }

  /**
   * Create a org.openqa.selenium.support.ui.FluentWait instance with Selenide timeout/polling.
   *
   * Sample usage:
   * {@code
   *   Wait().until(invisibilityOfElementLocated(By.id("magic-id")));
   * }
   *
   * @return instance of org.openqa.selenium.support.ui.FluentWait
   */
  public static FluentWait<WebDriver> Wait() {
    return getSelenideDriver().Wait();
  }

  /**
   * With this method you can use Selenium Actions like described in the
   * <a href="http://code.google.com/p/selenium/wiki/AdvancedUserInteractions">AdvancedUserInteractions</a> page.
   *
   * <pre>
   *   actions()
   *    .sendKeys($(By.name("rememberMe")), "John")
   *    .click($(#rememberMe"))
   *    .click($(byText("Login")))
   *    .build()
   *    .perform();
   * </pre>
   */
  public static Actions actions() {
    return getSelenideDriver().actions();
  }

  /**
   * Get JavaScript errors that happened on this page.
   *
   * Format can differ from browser to browser:
   *  - Uncaught ReferenceError: $ is not defined at http://localhost:35070/page_with_js_errors.html:8
   *  - ReferenceError: Can't find variable: $ at http://localhost:8815/page_with_js_errors.html:8
   *
   * Function returns nothing if the page has its own "window.onerror" handler.
   *
   * @return list of error messages. Returns empty list if webdriver is not started properly.
   */
  public static List<String> getJavascriptErrors() {
    return getSelenideDriver().getJavascriptErrors();
  }

  /**
   * Check if there is not JS errors on the page
   * @throws JavaScriptErrorsFound
   */
  public static void assertNoJavascriptErrors() throws JavaScriptErrorsFound {
    getSelenideDriver().assertNoJavascriptErrors();
  }

  /**
   * Zoom current page (in or out).
   * @param factor e.g. 1.1 or 2.0 or 0.5
   */
  public static void zoom(double factor) {
    getSelenideDriver().zoom(factor);
  }

  /**
   * Same as com.codeborne.selenide.Selenide#getWebDriverLogs(java.lang.String, java.util.logging.Level)
   */
  public static List<String> getWebDriverLogs(String logType) {
    return getSelenideDriver().getWebDriverLogs(logType);
  }

  /**
   * Getting and filtering of the WebDriver logs for specified LogType by specified logging level
   * <br />
   * For example to get WebDriver Browser's console output (including JS info, warnings, errors, etc. messages)
   * you can use:
   * <br />
   * <pre>
   *   {@code
   *     for(String logEntry : getWebDriverLogs(LogType.BROWSER, Level.ALL)) {
   *       Reporter.log(logEntry + "<br />");
   *     }
   *   }
   * </pre>
   * <br />
   * Be aware that currently "manage().logs()" is in the Beta stage, but it is beta-then-nothing :)
   * <br />
   * List of the unsupported browsers and issues:
   * <br />
   * http://bit.ly/RZcmrM
   * <br />
   * http://bit.ly/1nZTaqu
   * <br />
   *
   * @param logType WebDriver supported log types
   * @param logLevel logging level that will be used to control logging output
   * @return list of log entries
   * @see org.openqa.selenium.logging.LogType,
   * @see java.util.logging.Level
   */
  public static List<String> getWebDriverLogs(String logType, Level logLevel) {
    return getSelenideDriver().getWebDriverLogs(logType, logLevel);
  }

  /**
   * Clear browser cookies.
   *
   * In case if you are trying to avoid restarting browser
   *
   */
  public static void clearBrowserCookies() {
    getSelenideDriver().clearBrowserCookies();
  }

  /**
   *  Clear browser local storage.
   *
   *  In case if you need to be sure that browser's localStorage is empty
   */
  public static void clearBrowserLocalStorage() {
    getSelenideDriver().clearBrowserLocalStorage();
  }

  /**
   * Get current user agent from browser session
   *
   * @return browser user agent
   */
  public static String getUserAgent() {
    return getSelenideDriver().getUserAgent();
  }

  /**
   * Return true if bottom of the page is reached
   *
   * Useful if you need to scroll down by x pixels unknown number of times.
   */
  public static boolean atBottom() {
    return getSelenideDriver().atBottom();
  }
}
