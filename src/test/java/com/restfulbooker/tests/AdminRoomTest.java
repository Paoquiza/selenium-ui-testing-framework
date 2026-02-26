package com.restfulbooker.tests;

import com.restfulbooker.pages.AdminPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminRoomTest extends BaseTest {

    private AdminPage adminPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        adminPage = new AdminPage(driver);
        adminPage.openAdminPanel();
        adminPage.loginAsAdmin();
        Assert.assertTrue(adminPage.isLoggedIn(), "Should be logged in before room tests");
    }

    @Test(groups = {"regression"}, description = "Verify room management panel is displayed")
    public void testRoomManagementPanelDisplayed() {
        Assert.assertTrue(adminPage.isRoomNameInputDisplayed(),
                "Room name input should be displayed in admin panel");
    }

    @Test(groups = {"regression"}, description = "Verify existing rooms are listed")
    public void testExistingRoomsListed() {
        int roomCount = adminPage.getRoomCount();
        Assert.assertTrue(roomCount > 0,
                "At least one room should be listed in admin panel");
    }

    @Test(groups = {"regression"}, description = "Create a new room successfully")
    public void testCreateNewRoom() {
        int initialCount = adminPage.getRoomCount();

        adminPage.createRoom("201", "Single", "true", "100");

        // Wait briefly for the room to appear
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        int newCount = adminPage.getRoomCount();
        Assert.assertTrue(newCount > initialCount,
                "Room count should increase after creating a new room");
    }

    @Test(groups = {"regression"}, description = "Create and delete a room")
    public void testDeleteRoom() {
        adminPage.createRoom("999", "Single", "false", "50");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        int countBeforeDelete = adminPage.getRoomCount();
        adminPage.deleteLastRoom();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        int countAfterDelete = adminPage.getRoomCount();
        Assert.assertTrue(countAfterDelete < countBeforeDelete,
                "Room count should decrease after deleting a room");
    }

    @Test(groups = {"regression"}, description = "Create room with different type")
    public void testCreateDoubleRoom() {
        int initialCount = adminPage.getRoomCount();

        adminPage.createRoom("301", "Double", "true", "150");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        int newCount = adminPage.getRoomCount();
        Assert.assertTrue(newCount > initialCount,
                "Room count should increase after creating a double room");
    }
}
