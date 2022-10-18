package com.lifeway.wordcount.service;

import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the word count service.
 */
@Singleton
public record WordCountServiceImpl() implements WordCountService {
    private static final Logger LOG = LoggerFactory.getLogger(WordCountServiceImpl.class);

    // Local cache for the ids that have been processed.
    private static Map<String, Integer> idToMessageWordCountMap = new HashMap<>();

    // Local cache for the word count total.
    private static long wordCountTotal = 0L;

    /**
     * Counts the number of words in the message property of the word count command.
     *
     * @param wordCountCommand The word count command containing the message whose number of words are counted,
     *                         and the id associated with the message.
     *
     * @return The word count response containing the running word count total of the number of words the messages
     * processed.  A message with a duplicate id will not be processed and the previous running word count total
     * will be returned in the response.
     */
    @Override
    public WordCountResponse countWords(WordCountCommand wordCountCommand) {

        // Check to see if id has already been processed.
        if (!idToMessageWordCountMap.containsKey(wordCountCommand.id())) {
            int messageWordCount = wordCountCommand.message().split("\\s+").length;

            // Add the id and the message word count to the local cache.
            idToMessageWordCountMap.put(wordCountCommand.id(), messageWordCount);

            // Update the running word count total in the local cache.
            wordCountTotal += messageWordCount;

            LOG.debug("Processed word count request, id: \"{}\", message: \"{}\", messageWordCount: {}, wordCountTotal: {}",
                    wordCountCommand.id(), wordCountCommand.message(), messageWordCount, wordCountTotal);
        } else {
            LOG.warn("Duplicate id: {}, ignoring message: \"{}\"", wordCountCommand.id(), wordCountCommand.message());
        }

        return new WordCountResponse(wordCountTotal);
    }
}
