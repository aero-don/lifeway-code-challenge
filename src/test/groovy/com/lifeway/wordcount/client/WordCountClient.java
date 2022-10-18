package com.lifeway.wordcount.client;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

/**
 * REST client for the word count REST web service.
 */
@Client("/words")
public interface WordCountClient {

    /**
     * The REST client for the count words operation.
     *
     * @param wordCountCommand The command for the count words request.
     *
     * @return The response to the count words request.
     */
    @Post()
    WordCountResponse countWords(@Body WordCountCommand wordCountCommand);
}
