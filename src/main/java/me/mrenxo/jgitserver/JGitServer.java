package me.mrenxo.jgitserver;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JGitServer {

    private static Config config;

    public static void main(String[] args) throws IOException, GitAPIException {

        File configFile = new File(System.getProperty("user.dir") + "/jgit.yml");

        if (!configFile.exists()) {
            InputStream configResource = JGitServer.class.getClassLoader().getResourceAsStream("jgit.yml");
            String resourceString = new BufferedReader(new InputStreamReader(configResource,
                    Charset.forName("UTF-8"))

            ).lines().collect(Collectors.joining((CharSequence) Collectors.joining("\n")));

            FileWriter writer = new FileWriter(configFile);
            writer.write(resourceString);
            writer.flush();
            writer.close();

        }


        Yaml yaml = new Yaml();
        config = yaml.load(new FileReader(configFile));

        File gitDir = new File(System.getProperty("user.dir") + config.Git.Folder);
        Git.cloneRepository()
                .setURI(config.Git.URI)
                .setDirectory(gitDir)
                .setBranchesToClone(Arrays.asList("refs/heads/" + config.Git.Branch))
                .setBranch("refs/heads/" + config.Git.Branch)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(config.Git.Email, config.Git.Token)).call();


    }
}
