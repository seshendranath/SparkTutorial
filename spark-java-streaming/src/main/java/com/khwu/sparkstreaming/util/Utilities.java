package com.khwu.sparkstreaming.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Utilities {

    public static void setUpLogging() {
        Logger.getLogger("org").setLevel(Level.ERROR);
    }

    public static void setUptTwitter() {
        Set<String> credentials = new HashSet<>(Arrays.asList("consumerKey", "consumerSecret", "accessToken", "accessTokenSecret"));
        try (Scanner scanner = new Scanner(Paths.get("../credentials.txt"))) {
            Properties props = new Properties(System.getProperties());
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split("=");
                if (fields.length == 2) {
                    if (credentials.contains(fields[0])) {
                        props.setProperty("twitter4j.oauth." + fields[0], fields[1]);
                    }
                }
            }
            System.setProperties(props);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    public static void main(String[] args) {
        Utilities.setUptTwitter();
    }
}
