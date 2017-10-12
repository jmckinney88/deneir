package com.pyrese.eq.deneir;

import com.pyrese.eq.deneir.events.LogEventListenerImpl;
import com.pyrese.eq.parser.EverquestLogParser;
import com.pyrese.eq.parser.LogEventListener;
import com.pyrese.eq.parser.events.CombatEvent;
import com.pyrese.eq.parser.events.EnterZoneEvent;
import com.pyrese.eq.parser.events.LocationEvent;
import com.pyrese.eq.parser.events.LogEvent;
import com.pyrese.eq.parser.events.SpellDamageEvent;
import com.pyrese.eq.parser.events.SpellEffectEvent;
import com.pyrese.eq.parser.events.SpellEvent;
import com.pyrese.io.TailInputStream;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * Created by jonathan.mckinney on 10/5/17.
 */
public class Application extends javafx.application.Application{

    private static final String title = "Deneir: Everquest Log Parser";

    private static final LogEventListenerImpl logEventListener = new LogEventListenerImpl();
    private static EverquestLogParser parser;

    public static void main(String[] args) throws IOException {
        final File file = new File(args[0]);
        parser = new EverquestLogParser(file, EverquestLogParser.Mode.Tail);
        parser.registerEventListener(logEventListener);
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(Application.title);

        Text zoneTxt = new Text();
        zoneTxt.setText("unknown");
        Text locationTxt = new Text();
        zoneTxt.setText("unknown");

        StackPane layout = new StackPane();
        layout.getChildren().add(zoneTxt);

        Scene scene = new Scene(layout, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();

        logEventListener.addOnLocationHandler((LocationEvent event) -> { locationTxt.setText(
                event.getY() + ", " +
                event.getX() + ", " +
                event.getZ()
        ); });

        logEventListener.addOnEnterZoneHandler((EnterZoneEvent event) -> {
            String newZone = event.getZoneName();
            zoneTxt.setText(newZone);
        });

        parser.start();

    }
}
