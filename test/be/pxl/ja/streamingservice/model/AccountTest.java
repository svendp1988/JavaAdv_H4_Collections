package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account("test", "test");
    }

    @Test
    void cannotAddMoreProfilesToAccountThanNumberOfProfilesInPlan() {
        testAccount.setStreamingPlan(StreamingPlan.STANDARD);
        int amountOfProfilesAllowed = testAccount.getStreamingPlan().getNumberOfScreens();
        int amountOfProfilesCurrent = testAccount.getProfiles().size();
        int amountLefToAdd = amountOfProfilesAllowed - amountOfProfilesCurrent;
        while (amountLefToAdd < amountOfProfilesAllowed) {
            testAccount.addProfile(new Profile("test"));
            amountLefToAdd++;
        }
        assertThrows(TooManyProfilesException.class, () -> testAccount.addProfile(new Profile("test")));
    }
}
