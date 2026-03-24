package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.MainMenu;

public class MainMenuTest {

    @Test
    public void testCreateAdditionalAccount() {
        MainMenu menu = new MainMenu();
        assertEquals(1, menu.getNumberOfAccounts());
        menu.createAdditionalAccount();
        assertEquals(2, menu.getNumberOfAccounts());
    }
}