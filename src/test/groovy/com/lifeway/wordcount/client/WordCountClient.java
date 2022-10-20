package com.lifeway.wordcount.client;

import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

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
    Mono<WordCountResponse> countWords(@Nullable @Body WordCountCommand wordCountCommand);
}
