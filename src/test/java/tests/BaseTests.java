package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class BaseTests {
    Logger logger = LoggerFactory.getLogger(BaseTests.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setup() {
        app.init();
    }

    @AfterSuite
    public void stop() {
        app.tearDown();
    }

}
