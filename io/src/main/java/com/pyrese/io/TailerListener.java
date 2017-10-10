package com.pyrese.io;

import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.apache.commons.lang3.StringUtils;

/**
 * Connect a PipedInputStream to receive lines of text tailed to the Listener.
 */
public class TailerListener extends TailerListenerAdapter implements Closeable {

    private final OutputStream tailPublish;

    public TailerListener (PipedInputStream tailPipeIn) throws IOException {
        PipedOutputStream tailPipeOut = new PipedOutputStream(tailPipeIn);
        tailPublish = new BufferedOutputStream(tailPipeOut);
    }

    @Override
    public void handle(String line) {
        try {
            if(!StringUtils.isBlank(line)) {
                tailPublish.write((line + "\n").getBytes());
                tailPublish.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     * <p>
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        tailPublish.close();
    }
}
