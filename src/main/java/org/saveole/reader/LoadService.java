package org.saveole.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class LoadService {

    @Autowired
    VectorStore vectorStore;

    public void load(MultipartFile file) {
        /* PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(this.pdfResource,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfBottomTextLinesToDelete(3)
                                .withNumberOfTopPagesToSkipBeforeDelete(1)
                                .build())
                        .withPagesPerDocument(1)
                        .build()); */
        var pdfReader = new ParagraphPdfDocumentReader(new InputStreamResource(file));

        var tokenTextSplitter = new TokenTextSplitter();
        vectorStore.accept(tokenTextSplitter.apply(pdfReader.get()));
    }
}
