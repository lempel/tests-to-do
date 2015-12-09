package todo.tests;

import blueprint.sdk.logger.Logger;
import blueprint.sdk.util.buffer.CircularByteBuffer;

/**
 * CircularByteBuffer Test
 *
 * @author Sangmin Lee
 * @create 2008. 11. 25.
 * @see
 * @since 1.5
 */
public class CircularByteBufferTest {
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * Entry Point
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(final String[] args) throws InterruptedException {
        CircularByteBuffer cbb = new CircularByteBuffer(10, false, false);

        cbb.push("12345".getBytes());
        cbb.push("67890".getBytes());
        LOGGER.println(new String(cbb.pop()));

        cbb.push("1234567890".getBytes());
        cbb.push("___".getBytes());
        LOGGER.println(new String(cbb.pop()));

        cbb.push("1234567890".getBytes());
        cbb.push("___123".getBytes(), 0, 3);
        LOGGER.println(new String(cbb.pop()));

        cbb.push("1234567890".getBytes());
        cbb.resize(15);
        cbb.push("_____".getBytes());
        LOGGER.println(new String(cbb.pop()));

        cbb.push("1234567890abcdefghijklmnopqrstuvwxyz".getBytes());
        LOGGER.println(new String(cbb.pop()));

        cbb.push("1234567890abcde".getBytes());
        cbb.push("fghijklmnopqrstuvwxyz!@#$%^&*()".getBytes(), 0, 21);
        LOGGER.println(new String(cbb.pop()));
    }
}
