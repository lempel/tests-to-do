package todo.tests;

import blueprint.sdk.util.lang.UnsignedInteger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class UnsignedIntegerTest {
    public static void main(String[] args) throws IOException {
        byte[] little = new byte[]{0x01, 0x00, 0x00, 0x00};
        byte[] big = new byte[]{0x00, 0x00, 0x00, 0x01};

        DataInputStream littleStream = new DataInputStream(new ByteArrayInputStream(little));
        DataInputStream bigStream = new DataInputStream(new ByteArrayInputStream(big));
        System.out.println("readInt(little) = " + littleStream.readInt());
        System.out.println("readInt(big) = " + bigStream.readInt());

        System.out.println("intValue(little) = " + UnsignedInteger.intValue(little, true));
        System.out.println("intValue(big) = " + UnsignedInteger.intValue(big, false));
        System.out.println();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        little = new byte[]{0x00, 0x00, 0x00, (byte) 0xFF};
        big = new byte[]{(byte) 0xFF, 0x00, 0x00, 0x00};

        littleStream = new DataInputStream(new ByteArrayInputStream(little));
        bigStream = new DataInputStream(new ByteArrayInputStream(big));
        System.out.println("readInt(unsigned little) = " + littleStream.readInt());
        System.out.println("readInt(unsigned big) = " + bigStream.readInt());

        try {
            System.out.println("intValue(unsigned little) = " + UnsignedInteger.intValue(little, true));
        } catch (NumberFormatException e) {
            System.out.println("intValue(unsigned little) = can't be expressed as singed int");
        }
        try {
            System.out.println("intValue(unsigned big) = " + UnsignedInteger.intValue(big, false));
        } catch (NumberFormatException e) {
            System.out.println("intValue(unsigned little) = can't be expressed as singed int");
        }
        System.out.println("longValue(unsigned little) = " + UnsignedInteger.longValue(little, true));
        System.out.println("longValue(unsigned big) = " + UnsignedInteger.longValue(big, false));

        System.out.println("longValue(unsigned little) = 0x" + Long.toHexString(UnsignedInteger.longValue(little, true)));
        System.out.println();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        UnsignedInteger uint = new UnsignedInteger(new byte[]{(byte) 0xFF, (byte) 0x10, (byte) 0x20, (byte) 0x30}, true);
        little = uint.toByteArray(true);
        System.out.print("toByteArray(true) = {");
        for (int i = 0; i < little.length; i++) {
            System.out.print(" 0x" + Integer.toHexString(Byte.toUnsignedInt(little[i])));
        }
        System.out.println(" }");

        little = uint.toByteArray(false);
        System.out.print("toByteArray(false) = {");
        for (int i = 0; i < little.length; i++) {
            System.out.print(" 0x" + Integer.toHexString(Byte.toUnsignedInt(little[i])));
        }
        System.out.println(" }");
    }
}
