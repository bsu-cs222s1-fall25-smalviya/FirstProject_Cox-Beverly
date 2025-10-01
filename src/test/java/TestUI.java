package bsu.edu.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TestUI {

    private static class TestApp extends UI {
        Stage stage;

        @Override
        public void start(Stage stage) {
            this.stage = stage;
            super.start(stage);
        }
    }

    @Test
    public void testOutputAreaInitiallyEmpty() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        TestApp app = new TestApp();

        // Launch JavaFX app in its own thread
        Thread fxThread = new Thread(() -> Application.launch(app.getClass()));
        fxThread.setDaemon(true);
        fxThread.start();

        // Wait for JavaFX to initialize
        Platform.runLater(latch::countDown);
        assertTrue(latch.await(5, TimeUnit.SECONDS), "Timeout waiting for JavaFX to initialize");

        // Access UI elements safely on the JavaFX thread
        CountDownLatch uiLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            TextArea output = app.getOutputArea();
            assertNotNull(output, "Output area should not be null");
            assertTrue(output.getText().isEmpty(), "Output area should be empty initially");
            uiLatch.countDown();
        });

        assertTrue(uiLatch.await(5, TimeUnit.SECONDS), "Timeout waiting for UI assertion");
    }
}




