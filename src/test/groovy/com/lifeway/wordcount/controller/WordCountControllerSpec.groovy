package com.lifeway.wordcount.controller

import com.lifeway.wordcount.client.WordCountClient
import com.lifeway.wordcount.constants.TestConstants
import com.lifeway.wordcount.dto.command.WordCountCommand
import com.lifeway.wordcount.dto.response.WordCountResponse
import com.lifeway.wordcount.service.WordCountService
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Unroll

@MicronautTest
class WordCountControllerSpec extends Specification {

    @Inject
    EmbeddedApplication<EmbeddedApplication> application

    @Inject
    WordCountService wordCountService

    @Inject
    WordCountClient wordCountClient

    static long wordCountTotal = 0L

    @Unroll
    void 'test valid ids and messages'() {
        when:
        WordCountResponse wordCountResponse = wordCountClient.countWords(new WordCountCommand(id, message))
                .block(TestConstants.HTTP_RESPONSE_DURATION)
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
        '123'            || 'another duplicate id'     || 0 // duplicate id, so not counting words in message
    }

    void 'test that null word count command results is an HTTP bad request'() {
        when:
        wordCountClient.countWords(null).block(TestConstants.HTTP_RESPONSE_DURATION)

        then:
        HttpClientResponseException exception = thrown(HttpClientResponseException)
        exception.status == HttpStatus.BAD_REQUEST
    }

    void 'test that invalid ids and messages result is an HTTP bad request'() {
        when:
        wordCountClient.countWords(new WordCountCommand(id, message)).block(TestConstants.HTTP_RESPONSE_DURATION)

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
