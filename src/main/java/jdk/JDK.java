package jdk;

import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.InetAddress;

public class JDK {
    public static void main(String[] args) {
        try {
            DNSNameService dnsNameService = new DNSNameService();
            InetAddress[] a = dnsNameService.lookupAllHostAddr("adfadf");
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
