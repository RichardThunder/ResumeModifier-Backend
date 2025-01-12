package org.example.resumemodifierbackend.service;


import java.io.InputStream;

public interface PDFParserService {
    public String parsePDF(InputStream pdfFile);
}
