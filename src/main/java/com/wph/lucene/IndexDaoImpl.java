package com.wph.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.stereotype.Component;

import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;

@Component("indexDao")
public class IndexDaoImpl implements IndexDao {
	@Resource
	private LuceneService luceneService;

	public void setLuceneService(LuceneService luceneService) {
		this.luceneService = luceneService;
	}

	public void save(Knowledgebase know) {
		try {
			luceneService.init();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		Document doc = DocumentUtils.knowledgetbaseToDocument(know);
		IndexWriter indexWriter = null;
		try {
			// IndexWriterConfig config = new
			// IndexWriterConfig(luceneService.getAnalyzer());

			indexWriter = new IndexWriter(luceneService.getDirectory(), luceneService.getIndexWriterConfig());
			indexWriter.addDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			luceneService.closeIndexWriter(indexWriter);
		}
	}

	@Override
	public void saveByCompany(KnowledgebaseJSON json, String path) {
		try {
			luceneService.init(path);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		Document doc = DocumentUtils.knowledgebaseJSONToDocument(json);
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(luceneService.getDirectory(), luceneService.getIndexWriterConfig());
			indexWriter.addDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			luceneService.closeIndexWriter(indexWriter);
		}

	}

	public void delete(String id) {
		try {
			luceneService.init();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		IndexWriter indexWriter = null;
		try {
			Term term = new Term("id", id);
			IndexWriterConfig config = new IndexWriterConfig(luceneService.getAnalyzer());
			indexWriter = new IndexWriter(luceneService.getDirectory(), config);
			indexWriter.deleteDocuments(term);// ɾ������ָ��term�������ĵ�
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			luceneService.closeIndexWriter(indexWriter);
		}
	}

	@Override
	public void deleteByCompany(String id, String path) {
		try {
			luceneService.init(path);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		IndexWriter indexWriter = null;
		try {
			Term term = new Term("id", id);
			IndexWriterConfig config = new IndexWriterConfig(luceneService.getAnalyzer());
			indexWriter = new IndexWriter(luceneService.getDirectory(), config);
			indexWriter.deleteDocuments(term);// ɾ������ָ��term�������ĵ�
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			luceneService.closeIndexWriter(indexWriter);
		}
	}

	public void update(Knowledgebase know) {
		try {
			luceneService.init();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		Document doc = DocumentUtils.knowledgetbaseToDocument(know);
		IndexWriter indexWriter = null;
		try {
			Term term = new Term("id", know.getId().toString());
			IndexWriterConfig config = new IndexWriterConfig(luceneService.getAnalyzer());
			indexWriter = new IndexWriter(luceneService.getDirectory(), config);
			indexWriter.updateDocument(term, doc);// ��ɾ�����󴴽���
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateByCompany(Knowledgebase know, String path) {
		try {
			luceneService.init(path);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		Document doc = DocumentUtils.knowledgetbaseToDocument(know);
		IndexWriter indexWriter = null;
		try {
			Term term = new Term("id", know.getId().toString());
			IndexWriterConfig config = new IndexWriterConfig(luceneService.getAnalyzer());
			indexWriter = new IndexWriter(luceneService.getDirectory(), config);
			indexWriter.updateDocument(term, doc);// ��ɾ�����󴴽���
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	public QueryResult search(String queryString, int firstResult, int maxResult) {
		try {
			luceneService.init();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		List<Knowledgebase> list = new ArrayList<Knowledgebase>();
		try {
			DirectoryReader ireader = DirectoryReader.open(luceneService.getDirectory());
			// 2���ڶ���������������
			IndexSearcher isearcher = new IndexSearcher(ireader);

			// 3��������������SQL�����йؼ��ֲ�ѯ
			String[] fields = { "title", "content", "category" };
			QueryParser parser = new MultiFieldQueryParser(fields, luceneService.getAnalyzer());
			Query query = parser.parse(queryString);
			TopDocs topDocs = isearcher.search(query, firstResult + maxResult);
			int count = topDocs.totalHits;// �ܼ�¼��
			System.out.println("�ܼ�¼��Ϊ��" + topDocs.totalHits);// �ܼ�¼��
			ScoreDoc[] hits = topDocs.scoreDocs;// �ڶ���������ָ����෵��ǰn�����

			// ����
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Scorer source = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter, source);

			// ժҪ
			// Fragmenter fragmenter = new SimpleFragmenter(5);
			// highlighter.setTextFragmenter(fragmenter);

			// ������
			int endIndex = Math.min(firstResult + maxResult, hits.length);
			for (int i = firstResult; i < endIndex; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				Knowledgebase know = DocumentUtils.documentToknowledgetbase(hitDoc);
				//
				String text = highlighter.getBestFragment(luceneService.getAnalyzer(), "content",
						hitDoc.get("content"));
				if (text != null) {
					know.setContent(text);
				}
				list.add(know);
			}
			ireader.close();
			return new QueryResult(count, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QueryResult searchByCompany(String queryString, int firstResult, int maxResult, String companypath) {
		try {
			luceneService.init(companypath);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		List<KnowledgebaseJSON> list = new ArrayList<KnowledgebaseJSON>();
		try {
			DirectoryReader ireader = DirectoryReader.open(luceneService.getDirectory());
			// 2���ڶ���������������
			IndexSearcher isearcher = new IndexSearcher(ireader);

			// 3��������������SQL�����йؼ��ֲ�ѯ
			String[] fields = { "title", "content", "category" };
			QueryParser parser = new MultiFieldQueryParser(fields, luceneService.getAnalyzer());
			Query query = parser.parse(queryString);

			TopDocs topDocs = isearcher.search(query, firstResult + maxResult);
			int count = topDocs.totalHits;// �ܼ�¼��
			System.out.println("�ܼ�¼��Ϊ��" + topDocs.totalHits);// �ܼ�¼��
			ScoreDoc[] hits = topDocs.scoreDocs;// �ڶ���������ָ����෵��ǰn�����

			// ����
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Scorer source = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter, source);

			// ժҪ
			// Fragmenter fragmenter = new SimpleFragmenter(5);
			// highlighter.setTextFragmenter(fragmenter);

			// ������
			int endIndex = Math.min(firstResult + maxResult, hits.length);
			for (int i = firstResult; i < endIndex; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				KnowledgebaseJSON know = DocumentUtils.ducumentToKnowledgebaseJSON(hitDoc);
				// �����ʾ500
				String content = highlighter.getBestFragment(luceneService.getAnalyzer(), "content",
						hitDoc.get("content"));

				if (content != null) {
					know.setContent(content);
				}

				String title = highlighter.getBestFragment(luceneService.getAnalyzer(), "title", hitDoc.get("title"));
				if (title != null) {
					know.setTitle(title);
				}
				String category = highlighter.getBestFragment(luceneService.getAnalyzer(), "content",
						hitDoc.get("category"));
				if (category != null) {
					know.setCategory(category);
				}
				list.add(know);
			}
			ireader.close();
			return new QueryResult(count, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
