package org.incode.example.document.dom.impl.renderers;

import java.io.IOException;
import java.net.URL;

import org.incode.example.document.dom.impl.types.DocumentType;

public interface RendererFromCharsToBytesWithPreviewToUrl extends RendererFromCharsToBytes, PreviewToUrl {

    URL previewCharsToBytes(
            final DocumentType documentType,
            final String atPath,
            final long templateVersion,
            final String templateChars,
            final Object dataModel) throws IOException;

}
