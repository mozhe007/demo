package jdk.java.net;

import java.net.URL;

public class NetDemo {
    public static void main(String[] args) {
        URL url = NetDemo.class.getResource("");
        // file / jar
        url.getProtocol();
    }
}
