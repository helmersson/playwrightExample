package intro;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

// mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chrome"

public class dRunHeadedMode {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Channel can be "chrome", "msedge", "chrome-beta", "msedge-beta" or "msedge-dev".
            BrowserType browserType = playwright.chromium();
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
            Page page = browser.newPage();

            page.navigate("https://www.playwright.dev/");
            String title = page.title();
            System.out.println("This is the title of the current page " + title);

            page.close();
            browser.close();
            playwright.close();
        }
    }
}
