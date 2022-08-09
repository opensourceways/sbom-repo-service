package org.opensourceways.sbom.repo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogUtils {

    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    private static final Logger processLogger = LoggerFactory.getLogger("sbom-tools-process");

    public static void printMessage(final InputStream inputStream) {
        threadPool.execute(() -> {
            BufferedReader br = new BufferedReader(new InputStreamReader((inputStream)));
            String line;

            try {
                while ((line = br.readLine()) != null) {
                    processLogger.info(line);
                }
            } catch (IOException e) {
                processLogger.error("", e);
            }
        });
    }

}
