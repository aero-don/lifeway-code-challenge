package com.lifeway.wordcount;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Application class for Lifeway code challenge REST application.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Word Count",
                version = "0.1",
                description = "Lifeway code challenge: word count application"
        )
)
public class Application {
    /**
     * Main method for word count application.
     *
     * @param args Command line arguments. None supported at this time.
     */
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
