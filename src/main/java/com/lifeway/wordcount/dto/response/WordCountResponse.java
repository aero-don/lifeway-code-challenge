package com.lifeway.wordcount.dto.response;

import io.micronaut.core.annotation.Introspected;

/**
 * Response record for the count words REST request.
 *
 * @param count The count returned for a count words request.
 */
@Introspected
public record WordCountResponse(long count) {
}
