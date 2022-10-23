package com.lifeway.wordcount.service

import com.lifeway.wordcount.dto.command.WordCountCommand
import com.lifeway.wordcount.dto.response.WordCountResponse
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

@MicronautTest(startApplication = false)
class WordCountServiceSpec extends Specification {

    @Inject
    WordCountService wordCountService

    static long wordCountTotal = 0L

    @Unroll
    void 'test valid ids and messages'() {
        when:
        WordCountResponse wordCountResponse = wordCountService.countWords(new WordCountCommand(id, message))
        wordCountTotal += wordCount

        then:
        wordCountResponse.count() == wordCountTotal

        where:
        id               || message                        || wordCount
        '123'            || 'hello world'                  || 2
        '234'            || 'hello world again'            || 3
        '234'            || 'hello world yet again'        || 0 // duplicate id, so not counting words in message
        '345'            || '          '                   || 0 // contains only blanks, so word count is zero
        '345'            || 'duplicate id not counted'     || 0 // duplicate id, so not counting words in message
        'id'             || 'non-numeric id'               || 2
        'id with spaces' || 'the id can have spaces'       || 5
        'id-with-dashes' || 'the id can have dashes'       || 5
        '456'            || '1 message with 2 numbers'     || 5 // numbers count as words
        '123'            || 'another duplicate id'         || 0 // duplicate id, so not counting words in message
        '~!@#$%^&*()/\\' || '~!@#$%^&*()/\\ weird message' || 3
    }

    void 'test that null word count command results in a ConstraintViolationException'() {
        when:
        wordCountService.countWords(null)

        then:
        thrown(ConstraintViolationException)
    }

        void 'test that invalid ids and messages result in a ConstraintViolationException'() {
        when:
        wordCountService.countWords(new WordCountCommand(id, message))

        then:
        thrown(ConstraintViolationException)

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
