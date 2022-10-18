package com.lifeway.wordcount.dto.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Command record for the count words REST request.
 *
 * @param id The id of the word count request.
 * @param message The message whose words are to be counted.
 */
@Introspected
public record WordCountCommand(@NotNull @NotEmpty @NotBlank String id,
                               @NotNull @NotEmpty String message) {
}
