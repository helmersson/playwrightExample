import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeAll
    void setUpAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true) // run headless in tests
        );
    }

    @BeforeEach
    void setUp() {
        page = browser.newPage();
        page.navigate("https://ec.omniway.se/");
    }

    @AfterEach
    void tearDown() {
        page.close();
    }

    @AfterAll
    void tearDownAll() {
        browser.close();
        playwright.close();
    }

    @Test
    void shouldLoadLoginPage() {
        // Check page title is not empty
        String title = page.title();
        assertNotNull(title);
        assertFalse(title.isEmpty());

        // Playwright assertion
        PlaywrightAssertions.assertThat(page).hasTitle(title);
    }

    @Test
    void shouldDisplayLoginForm() {
        // Assert username/email textbox is visible
        Locator usernameField = page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Elev-ID eller e-post")
        );
        assertTrue(usernameField.isVisible());

        // Assert password input exists
        Locator passwordField = page.locator("#password");
        assertTrue(passwordField.isVisible());

        // Assert login button is visible
        Locator loginButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Logga in")
        );
        assertTrue(loginButton.isVisible());
    }

    @Test
    void shouldNotLoginWithInvalidCredentials() {
        page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Elev-ID eller e-post")
        ).fill("invalidUser");

        page.locator("#password").fill("wrongPassword");

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Logga in")
        ).click();

        // Wait for potential error message (adjust selector based on real DOM)
        page.waitForTimeout(2000);
        Locator errorMsg = page.getByText("Felaktigt användarnamn eller lösenord"); // Example text

        assertTrue(errorMsg.isVisible(), "Expected error message to appear after invalid login");
    }
}