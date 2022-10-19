package de.apwolf;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NewClassNamingConvention") // failsafe likes these names and so do I
public class AppIT {

    @Test
    public void fstITTest() {
        App.executeSomeSerialization();
    }
}
