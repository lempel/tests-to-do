package todo.tests;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        test1();

        System.out.println("-----------------");

        test2();
    }

    private static void test2() throws SocketException {
        Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
        while (enu.hasMoreElements()) {
            NetworkInterface nic = enu.nextElement();
            if (!nic.isLoopback() && nic.isUp()) {
                System.out.println(nic);
                Enumeration<InetAddress> addrs = nic.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress addr = addrs.nextElement();
                    System.out.println("\t" + addr.getCanonicalHostName() + ", " + addr.getHostName() + ", "
                            + addr.isAnyLocalAddress() + ", " + addr.isLinkLocalAddress() + ", "
                            + addr.isSiteLocalAddress() + ", " + addr.getHostAddress());
                }
            }
        }
    }

    private static void test1() throws UnknownHostException {
        InetAddress[] addrs = InetAddress.getAllByName("simon");
        for (InetAddress addr : addrs) {
            System.out.println(addr);
        }
    }
}
