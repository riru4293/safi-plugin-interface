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

import java.io.UncheckedIOException;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Container that collection of {@link ExportSource} in key-value format.
 *
 * <p>
 * Implementation requirements.
 * <ul>
 * <li>The collection entity must be kept outside of memory so that it does not occupy exclusive memory.</li>
 * </ul>
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExportSourceContainer {

    /**
     * Get all values stored in this.
     *
     * @return all values stored in this
     * @since 1.0.0
     */
    Stream<ExportSource> stream();

    /**
     * Returns {@code true} if no content storing.
     *
     * @return {@code true} if no content storing
     * @since 1.0.0
     */
    boolean isEmpty();

    /**
     * Returns storing number of content.
     *
     * @return storing number of content
     * @since 1.0.0
     */
    int size();

    /**
     * Returns {@code true} if exists a content associated with {@code key}.
     *
     * @param key content id
     * @return {@code true} if exists a content associated with {@code key}
     * @since 1.0.0
     */
    boolean containsKey(String key);

    /**
     * Returns all content ids.
     *
     * @return all content ids
     * @since 1.0.0
     */
    Set<String> keySet();

    /**
     * Get a value associated with {@code key}.
     *
     * @param key content id
     * @return a value associated with {@code key}. Returns {@code null} if not exists.
     * @throws UncheckedIOException if occurs I/O exception
     * @since 1.0.0
     */
    ExportSource get(String key);
}
