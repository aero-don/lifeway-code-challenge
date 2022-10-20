package com.lifeway.wordcount.service;

import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the word count service.
 */
@Singleton
public class WordCountServiceImpl implements WordCountService {
    private static final Logger LOG = LoggerFactory.getLogger(WordCountServiceImpl.class);

    // Local cache for word count commands as a list events.
    // Keeping all events and recalculating state based on those events allows
    // the system to maintain all knowledge associated with all events even
    // if the event is currently ignored by the word count algorithm.
    private final List<WordCountCommand> wordCountEvents = new ArrayList<>();

    /**
     * Counts the number of words in the message property of the word count command.
     *
     * @param wordCountCommand A valid word count command containing the message whose number
     *                         of words are counted, and the id associated with the message.
     *
     * @return The word count response containing the running word count total of the number of words the messages
     * processed.  A message with a duplicate id will not be processed and the previous running word count total
     * will be returned in the response.
     */
    @Override
    public WordCountResponse countWords(@Valid @NotNull WordCountCommand wordCountCommand) {

        // Ensure thread safety of word count events local cache.
        synchronized (wordCountEvents) {
            wordCountEvents.add(wordCountCommand);
        }

        // Calculate the running total of the number of words in messages with unique ids.
        WordCountResponse wordCountResponse = new WordCountResponse(wordCountEvents.stream()
                .distinct()
                .map(event -> event.message().split("\\s+").length)
                .reduce(0, Integer::sum));

        LOG.debug("Processed wordCountCommand: {} and responded with wordCountResponse: {}",
                wordCountCommand, wordCountResponse);

        return wordCountResponse;
    }

}
