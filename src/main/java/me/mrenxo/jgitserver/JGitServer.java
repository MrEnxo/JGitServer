package me.mrenxo.jgitserver;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class JGitServer {

    private static

    public static void main(String[] args) {

        File configFile = new File(System.getProperty("user.dir") + "/jgit.yml");

        if (!configFile.exists()) {
            InputStream configResource = JGitServer.class.getClassLoader().getResourceAsStream("jgit.yml");
            String resourceString = new BufferedReader(new InputStreamReader(configResource,
                    Charset.forName("UTF-8"))

            ).lines().collect(Collectors.joining((CharSequence) Collectors.joining("\n")));

            Yaml yaml = new Yaml();

        }



    }
}
