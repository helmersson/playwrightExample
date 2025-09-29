package intro;

import com.microsoft.playwright.*;

/*
    mvn clean install before you run it
    playwright.chromium can be changed too playwright.firefox or playwright.webkit to change browsers

 */

public class aMain {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();

            page.navigate("https://www.playwright.dev/");
            System.out.println(page.title());
        }
    }
}