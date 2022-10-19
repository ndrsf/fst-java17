package de.apwolf;


import org.nustaq.serialization.FSTConfiguration;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class App implements Serializable {

    public static void main(String[] args) {
        executeSomeSerialization();
    }

    public static void executeSomeSerialization() {
        FSTConfiguration conf = FSTConfiguration.createJsonConfiguration();
        Object p = new App();

        byte[] bytes = conf.asByteArray(p);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
        Object deser = conf.asObject(bytes);
    }
}
