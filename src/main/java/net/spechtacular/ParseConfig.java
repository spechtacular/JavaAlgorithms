package net.spechtacular;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class ParseConfig {

    private Logger LOGGER;
    private String configFile = "./src/main/resources/config.yaml";

    ParseConfig(Logger LOGGER, String configFile) {
        this.LOGGER = LOGGER;
        this.configFile = configFile;
    }

    ParseConfig(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    ConfigData getConfig() {
        ConfigData sd = null;
        Yaml yaml = new Yaml();
        Path pathToFile = Paths.get(configFile);
        System.out.println(pathToFile.toAbsolutePath());
        try( InputStream in = Files.newInputStream( Paths.get( configFile ) ) ) {
                sd = yaml.loadAs( in, ConfigData.class );
                System.out.println( sd.toString() );
        } catch (IOException e) {
            LOGGER.severe("load error on yaml config file="+configFile);
            e.printStackTrace();
        }
        return sd;

    }



}
