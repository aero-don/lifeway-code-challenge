package com.lifeway.wordcount

import com.lifeway.wordcount.client.WordCountClient
import com.lifeway.wordcount.dto.command.WordCountCommand
import com.lifeway.wordcount.dto.response.WordCountResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class WordCountSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    @Inject
    WordCountClient wordCountClient

    static long wordCountTotal = 0

    void 'test valid ids and messages'() {
        when:
        WordCountResponse wordCountResponse = wordCountClient.countWords(new WordCountCommand(id, message))
        wordCountTotal += wordCount

        then:
        wordCountResponse.count() == wordCountTotal

        where:
        id               || message                    || wordCount
        '123'            || 'hello world'              || 2
        '234'            || 'hello world again'        || 3
        '234'            || 'hello world yet again'    || 0 // duplicate id, so not counting words in message
        '345'            || '          '               || 0 // contains only blanks, so word count is zero
        '345'            || 'duplicate id not counted' || 0 // duplicate id, so not counting words in message
        'id'             || 'non-numeric id'           || 2
        'id with spaces' || 'the id can have spaces'   || 5
        'id-with-dashes' || 'the id can have dashes'   || 5
        '456'            || '1 message with 2 numbers' || 5 // numbers count as words
        '234'            || 'another duplicate id'     || 0 // duplicate id, so not counting words in message
    }

    void 'test invalid ids and messages'() {
        when:
        wordCountClient.countWords(new WordCountCommand(id, message))

        then:
        HttpClientResponseException exception = thrown(HttpClientResponseException)
        exception.status == HttpStatus.BAD_REQUEST

        where:
        id          || message
        'valid id'  || ''
        'valid id'  || null
        ''          || 'valid message'
        '        '  || 'valid message'
        null        || 'valid message'
        null        || null
        ''          || ''
        null        || ''
        ''          || null
    }
}
