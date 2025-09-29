package intro;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class cExecuteHeadedMode {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium();
            Browser browser  = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://www.playwright.dev/");
            String title = page.title();
            System.out.println("This is the title " + title);

            page.close();
            browser.close();
            playwright.close();

        }
    }
}