package tn.kaz.ospas.tb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tn.kaz.ospas.tb.pageobjects.TBLoginView;
import tn.kaz.ospas.tb.pageobjects.TBMainView;
import tn.kaz.ospas.tb.pageobjects.TBProfileWindow;
import com.vaadin.testbench.TestBenchTestCase;

public class MainViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testEditProfile() {
        TBProfileWindow profile = mainView.openProfileWindow();
        profile.setName("Test", "User");
        profile.commit();
        Assert.assertEquals("Test User", mainView.getUserFullName());
    }

    @After
    public void tearDown() {
        loginView.getDriver().quit();
    }
}
