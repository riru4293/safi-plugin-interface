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
import jakarta.json.JsonValue;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Plug-in interface for batch-processing.
 *
 * @author riru
 * @version 2.0.3
 * @since 2.0.0
 */
public interface BatchPlugin extends SafiPlugin {

    /**
     * Get plug-in properties. Used to convey environment-dependent values ​​of the plug-in caller to the plug-in. Must
     * not be thrown exception and never return {@code null}.
     *
     * @return plug-in properties. It never {@code null}.
     * @since 2.0.0
     */
    JsonObject getPluginProperties();

    /**
     * Set plug-in properties. This method is used by the plug-in loader.
     *
     * @param props properties
     * @throws NullPointerException if {@code props} is {@code null}
     * @since 2.0.0
     */
    void setPluginProperties(JsonObject props);

    /**
     * Get the result reporter. The reporter is responsible for passing the received messages to the plugin caller
     * processing. The main use is expected to be to report warning messages and result messages.
     *
     * @return the result reporter
     * @since 2.0.0
     */
    Consumer<String> getReporter();

    /**
     * Set the result reporter.
     *
     * @param reporter result reporter
     * @throws NullPointerException if {@code reporter} is {@code null}
     * @throws IllegalStateException if already sets a reporter
     * @since 2.0.0
     */
    void setReporter(Consumer<String> reporter);

    /**
     * Abstract implements of the {@code BatchPlugin}.
     *
     * @author riru
     * @version 2.0.0
     * @since 2.0.0
     */
    public static abstract class AbstractBatchPlugin implements BatchPlugin {

        private JsonObject props = null;
        private Consumer<String> reporter = null;

        /**
         * {@inheritDoc}
         *
         * @since 2.0.0
         */
        @Override
        public final JsonObject getPluginProperties() {
            return Optional.ofNullable(props).orElse(JsonValue.EMPTY_JSON_OBJECT);
        }

        /**
         * Set plug-in properties. This method is used by the plug-in loader. This function can only be executed once.
         * Also, this is done by the plugin loader, so there's nothing you can actually do.
         *
         * @param props properties
         * @throws NullPointerException if {@code props} is {@code null}
         * @throws IllegalStateException if already sets a properties
         * @since 2.0.0
         */
        @Override
        public final void setPluginProperties(JsonObject props) {
            Objects.requireNonNull(props);

            if (this.props != null) {
                throw new IllegalStateException("Plug-in properties are already set.");
            }

            this.props = props;
        }

        /**
         * {@inheritDoc}
         *
         * @since 2.0.0
         */
        @Override
        public final Consumer<String> getReporter() {
            return Optional.ofNullable(reporter).orElseGet(() -> m -> {
            });
        }

        /**
         * Set the result reporter. This method is used by the plug-in loader. This function can only be executed once.
         * Also, this is done by the plugin loader, so there's nothing you can actually do.
         *
         * @param reporter result reporter
         * @throws NullPointerException if {@code reporter} is {@code null}
         * @throws IllegalStateException if already sets a reporter
         * @since 2.0.0
         */
        @Override
        public final void setReporter(Consumer<String> reporter) {
            Objects.requireNonNull(reporter);

            if (this.reporter != null) {
                throw new IllegalStateException("Result reporter is already set.");
            }

            this.reporter = reporter;
        }

        /**
         * Throw {@code InterruptedException} if current thread is interrupted.
         *
         * @throws InterruptedException if current thread is interrupted
         * @since 2.0.3
         */
        protected final void throwIfInterrupted() throws InterruptedException {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
        }
    }
}
