package com.pyrese.eq.deneir;

import com.pyrese.eq.deneir.events.LogEventListenerImpl;
import com.pyrese.eq.parser.EverquestLogParser;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Deneir extends Application {

    private static final String title = "Deneir: Everquest Log Parser";

    private static final LogEventListenerImpl logEventListener = new LogEventListenerImpl();
    private static EverquestLogParser parser;

    public static void main(String[] args) throws IOException {
        final File file = new File(args[0]);
        parser = new EverquestLogParser(file, EverquestLogParser.Mode.Tail);
        parser.registerEventListener(logEventListener);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
