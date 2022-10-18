package com.lifeway.wordcount.controller;

import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;
import com.lifeway.wordcount.service.WordCountService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

/**
 * WordCountController for processing word count REST requests.
 */
@Controller("/words")
@ExecuteOn(TaskExecutors.IO)
public class WordCountController {
    private final WordCountService wordCountService;

    /**
     * WordCountController constructor.
     *
     * @param wordCountService Constructor injected dependency for the service performing the word count operation.
     */
    WordCountController(WordCountService wordCountService) {
        this.wordCountService = wordCountService;
    }

    /**
     * The REST endpoint for the count words operation.
     * Duplicate id values across multiple requests will not be processed.
     * The word count total from the previous request will be returned when a duplicate id is submitted.
     * The number of words in the message will be counted and the word count total for all valid requests will be returned.
     *
     * @param wordCountCommand The command for the count words request.
     *
     * @return The response to the count words request.
     */
    @Tag(name = "Count Words")
    @Post(consumes = "application/json", produces = "application/json")
    public WordCountResponse countWords(@Valid @Body WordCountCommand wordCountCommand) {
        return wordCountService.countWords(wordCountCommand);
    }
}
