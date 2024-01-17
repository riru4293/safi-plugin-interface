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

/**
 * The result of one imported (or attempted if unsuccessful) content. This includes content values and result status.
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
public interface ImportResult {

    /**
     * Returns a {@code true} if import was successful.
     *
     * @return {@code true} if import was successful, otherwise {@code false}.
     * @since 1.0.0
     */
    boolean isSuccess();

    /**
     * Get result kind.
     *
     * @return result kind. It value is {@code REGISTER}, {@code DELETION}, or {@code FAILURE}.
     * @since 1.0.0
     */
    String getKindName();

    /**
     * Get format name of content value.
     *
     * @return format name of content value
     * @since 1.0.0
     */
    String getFormatName();

    /**
     * Get content value.
     *
     * @return content value
     * @since 1.0.0
     */
    JsonObject getValue();

    /**
     * Get result message for this content.
     *
     * @return result message for this content
     * @since 1.0.0
     */
    String getMessage();

}
