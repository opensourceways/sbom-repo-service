package org.opensourceways.sbom.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SbomRepoApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(SbomRepoApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SbomRepoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SbomRepoApplication.class, args);
        logger.info("Sbom repo service has started");
    }
}
