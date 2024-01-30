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

import jp.mydns.projectk.plugin.PluginExecutionException;
import jp.mydns.projectk.safi.plugin.BatchPlugin.AbstractBatchPlugin;

/**
 * Export the export-content. Provides processing specific to the export destination in the process of exporting
 * contents to the outside.
 *
 * @author riru
 * @version 2.0.0
 * @since 1.0.0
 */
public interface ExporterPlugin extends SafiPlugin {

    /**
     * Execute exporting.
     *
     * @param sources exportation contents source.
     * @throws PluginExecutionException if processing cannot be continued
     * @throws InterruptedException if interrupted
     * @since 2.0.0
     */
    void doExport(ExportSourceContainer sources) throws InterruptedException;

    /**
     * Abstract implements of the {@code ExporterPlugin}.
     *
     * @author riru
     * @version 2.0.0
     * @since 1.0.0
     */
    abstract class AbstractExporterPlugin extends AbstractBatchPlugin implements ExporterPlugin {

        /**
         * {@inheritDoc}
         *
         * @throws PluginExecutionException if processing cannot be continued
         * @since 2.0.0
         */
        @Override
        public final void doExport(ExportSourceContainer sources) throws InterruptedException {
            try {
                doExportProcessing(sources);
            } catch (PluginExecutionException | InterruptedException ex) {
                throw ex;
            } catch (Throwable ignore) {
                // Note:
                // Cause exception does not wrap because it may be contaminated by an exception class
                // loaded with another class loader.
                throw new PluginUnknownException();
            }
        }

        /**
         * Execute exporting.
         *
         * @param sources exportation contents source.
         * @throws PluginExecutionException if processing cannot be continued
         * @throws InterruptedException if interrupted
         * @since 2.0.0
         */
        abstract void doExportProcessing(ExportSourceContainer sources) throws InterruptedException;
    }
}
