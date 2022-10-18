package com.lifeway.wordcount.service;

import com.lifeway.wordcount.dto.command.WordCountCommand;
import com.lifeway.wordcount.dto.response.WordCountResponse;

/**
 * Interface for the word count service.
 */
public interface WordCountService {

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
    WordCountResponse countWords(WordCountCommand wordCountCommand);
}
