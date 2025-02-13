package org.saveole.reader;

import lombok.extern.slf4j.Slf4j;
// import org.springframework.ai.reader.ExtractedTextFormatter;
// import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
// import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoadService {

    @Value("classpath:pdf/Writing Maintainable Unit Tests-2020.pdf")
    private Resource pdfResource;

    @Autowired
    VectorStore vectorStore;

    public void load() {
        /* PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(this.pdfResource,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfBottomTextLinesToDelete(3)
                                .withNumberOfTopPagesToSkipBeforeDelete(1)
                                .build())
                        .withPagesPerDocument(1)
                        .build()); */
        var pdfReader = new ParagraphPdfDocumentReader(pdfResource);

        var tokenTextSplitter = new TokenTextSplitter();
        vectorStore.accept(tokenTextSplitter.apply(pdfReader.get()));
    }
}
