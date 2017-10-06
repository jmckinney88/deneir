package com.pyrese.eq.deneir;

import com.pyrese.io.TailInputStream;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jonathan.mckinney on 10/5/17.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);

        System.out.println("Welcome: starting read");
        try {
            final InputStream tailInputStream = new TailInputStream(file, true);
            Thread readerThread = new Thread(new Runnable() {
                public void run() {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    tailInputStream
                            )
                    );
                    try {
                        while (true) {
                            int available = tailInputStream.available();
                            if (available > 0) {
                                System.out.println(reader.readLine()); //blocking due to not seeing EOL character.
                            }
                        }
                    } catch (IOException ex){
                        System.out.println(ex);
                    }
                }
            });
            readerThread.start();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
