package com.wph.lucene;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.elasticsearch.index.analysis.JcsegAnalyzerProvider;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.springframework.stereotype.Component;

@Component("LuceneService")
public class LuceneServiceImpl implements LuceneService {
	private Analyzer analyzer = null;

	private IndexWriterConfig cfg = null;

	private Directory directory = null;

	private String path = null;

	public void init() throws IOException {
		path = "G:\\demo";
		directory = FSDirectory.open(FileSystems.getDefault().getPath(path));
		JcsegTaskConfig tokenizerConfig = new JcsegTaskConfig(true);
		analyzer = new JcsegAnalyzer(tokenizerConfig.SIMPLE_MODE);
		cfg = new IndexWriterConfig(analyzer);
	}

	public Directory getDirectory() {
		return directory;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public IndexWriterConfig getIndexWriterConfig(){
		return cfg;
	}
	public void closeIndexWriter(IndexWriter indexWriter) {
		if (indexWriter != null) {
			try {
				indexWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void init(String path) throws IOException {
		this.path = path;
		directory = FSDirectory.open(FileSystems.getDefault().getPath(path));
		JcsegTaskConfig tokenizerConfig = new JcsegTaskConfig(true);
		analyzer = new JcsegAnalyzer(tokenizerConfig.SIMPLE_MODE);
		cfg = new IndexWriterConfig(analyzer);
		
	}
}
