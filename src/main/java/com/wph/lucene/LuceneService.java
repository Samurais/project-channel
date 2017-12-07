package com.wph.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

public interface LuceneService {
	public void init() throws IOException;

	public void init(String path)  throws IOException;

	public Directory getDirectory();

	public Analyzer getAnalyzer();
	
	public IndexWriterConfig getIndexWriterConfig();

	public void closeIndexWriter(IndexWriter indexWriter);
}
