import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import com.microsoft.playwright.options.AriaRole;


public class aLogInLogOff {

    // mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen ec.omniway.se/"

    public static void main(String[] args) {


        try (Playwright playwright = Playwright.create()) {
            
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://ec.omniway.se/");
            String title = page.title();
            //This can be done without saving the title as a string
            PlaywrightAssertions.assertThat(page).hasTitle(title);

            /*
                These are the recommended built-in locators.

                Page.getByRole() to locate by explicit and implicit accessibility attributes.
                Page.getByText() to locate by text content.
                Page.getByLabel() to locate a form control by associated label's text.
                Page.getByPlaceholder() to locate an input by placeholder.
                Page.getByAltText() to locate an element, usually image, by its text alternative.
                Page.getByTitle() to locate an element by its title attribute.
                Page.getByTestId() to locate an element based on its data-testid attribute (other attributes can be configured).
                You can also use page.locator(#email).fill("abc123) but it's not as stable when it comes to updates
                https://playwright.dev/java/docs/locators#introduction


             */
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Elev-ID eller e-post")).fill("abc123");
            page.locator("#password").fill("password"); //bad way to do it but possible

            page.waitForTimeout(3000); //only done in debugging to pause the window, the number is in ms
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logga in")).click();




        }
    }

}
