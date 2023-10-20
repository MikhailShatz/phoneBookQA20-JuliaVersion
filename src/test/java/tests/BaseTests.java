package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;


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

    @BeforeMethod(alwaysRun = true)
        public void beforeEachMethod(Method method){
            logger.info("---------------------------------------");
            logger.info("started method: "  + method.getName());
            logger.info("started method with params: " + Arrays.toString(method.getParameters()));
        }

    @AfterMethod(alwaysRun = true)
    public void afterEachMethod(Method method){
        logger.info("stopped method: "  + method.getName());
        logger.info("stopped method with params: " + Arrays.toString(method.getParameters()));
    }
}
