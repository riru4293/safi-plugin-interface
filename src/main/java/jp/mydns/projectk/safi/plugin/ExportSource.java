/*
 * Copyright (c) 2024, Project-K
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package jp.mydns.projectk.safi.plugin;

import jakarta.json.JsonObject;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Source values for exportation content.
 *
 * <p>
 * Implementation requirements.
 * <ul>
 * <li>This class is immutable and thread-safe.</li>
 * </ul>
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExportSource {

    /**
     * Get content id.
     *
     * @return content id
     * @since 1.0.0
     */
    String getId();

    /**
     * {@code true} if deletion.
     *
     * <p>
     * Implementation notes.
     * <ul>
     * <li>Default implementation returns {@code true} if {@link #getValue()} is {@code null}.</li>
     * </ul>
     *
     * @return {@code true} if deletion, otherwise registration.
     * @since 1.0.0
     */
    default boolean isDeletion() {
        return getValue() == null;
    }

    /**
     * Get content value.
     *
     * @return content value. In case of deletion, it will be {@code null}.
     * @since 1.0.0
     */
    JsonObject getValue();

    /**
     * Get previous content value.
     *
     * @return previous content value. If new, it will be {@code null}.
     * @since 1.0.0
     */
    JsonObject getPrevious();

    /**
     * Record this content as success. Call this function if the export of this content success. After a series of
     * processes, it is recorded as a success in the database.
     *
     * <p>
     * Implementation requirements.
     * <ul>
     * <li>If it is called outside the plug-in that processes the export, do nothing.</li>
     * </ul>
     * <p>
     * Implementation notes.
     * <ul>
     * <li>Default implementation does nothing.</li>
     * </ul>
     *
     * @param msgs processing result message for this content
     * @throws NullPointerException if {@code msgs} is {@code null} or if contains {@code null} in {@code msgs}.
     * @throws IllegalStateException If already recorded
     * @throws UncheckedIOException if occurs I/O error
     * @since 1.0.0
     */
    default void recordAsSuccess(List<String> msgs) {
        // Do nothing
    }

    /**
     * Record this content as failure. Call this function if the export of this content fails. After a series of
     * processes, it is recorded as a failure in the database.
     *
     * <p>
     * Implementation requirements.
     * <ul>
     * <li>If it is called outside the plug-in that processes the export, do nothing.</li>
     * </ul>
     * <p>
     * Implementation notes.
     * <ul>
     * <li>Default implementation does nothing.</li>
     * </ul>
     *
     * @param msgs processing result message for this content
     * @throws NullPointerException if {@code msgs} is {@code null} or if contains {@code null} in {@code msgs}.
     * @throws IllegalStateException If already recorded
     * @throws UncheckedIOException if occurs I/O error
     * @since 1.0.0
     */
    default void recordAsFailure(List<String> msgs) {
        // Do nothing
    }
}
