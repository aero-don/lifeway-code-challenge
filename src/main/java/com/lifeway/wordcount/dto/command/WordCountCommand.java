package com.lifeway.wordcount.dto.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Command record for the count words REST request.
 *
 * @param id The id of the word count request.
 * @param message The message whose words are to be counted.
 */
@Introspected
public record WordCountCommand(@NotNull @NotEmpty @NotBlank String id,
                               @NotNull @NotEmpty String message) {

    // Since the id is the only field required to uniquely identify a WordCountCommand,
    // override the default implementations of equals and hash methods for the record type
    // because the default implementations of these methods include all declared fields.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCountCommand that = (WordCountCommand) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
