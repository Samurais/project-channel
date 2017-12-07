package com.wph.lucene;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.wph.entities.Product;
import com.wph.service.ProductService;
import com.wph.service.impl.ProductServiceImpl;

public class IndexManagertest {

	public void createIndex() throws IOException {
		List<Product> list = new ArrayList<Product>();
		Product product1 = new Product(null,"今天天气不错啊",200,"A","B","C",50,null);
		Product product2 = new Product(null,"A32C",200,"A","B","C",50,null);
		Product product3 = new Product(null,"A23BC",200,"A","B","C",50,null);
		Product product4 = new Product(null,"AB112C",200,"A","B","C",50,null);
		Product product5 = new Product(null,"A31BC",200,"A","B","C",50,null);
		Product product6 = new Product(null,"AB312C",200,"A","B","C",50,null);
		product1.setId(1);
		product2.setId(2);
		product3.setId(3);
		product4.setId(4);
		product5.setId(5);
		product6.setId(6);
		list.add(product1);
		list.add(product2);
		list.add(product3);
		list.add(product4);
		list.add(product5);
		list.add(product6);
		List<Document> docList = new ArrayList<Document>();
		Document document;
		for (Product product : list) {
			document = new Document();
			Field id = new TextField("id",Integer.toString(product.getId()),Field.Store.YES);
			Field name = new TextField("name",product.getName(),Field.Store.YES);
			Field price = new TextField("price",Integer.toString(product.getPrice()),Field.Store.YES);
			document.add(id);
			document.add(name);
			document.add(price);
			docList.add(document);

		}
		
		Analyzer analyzer = new StandardAnalyzer();
		
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("C:\\demo"));
		
		IndexWriter writer = new IndexWriter(directory,cfg);
		
		writer.deleteAll();
		
		for(Document doc:docList){
			writer.addDocument(doc);
		}
		
		writer.close();
	}
}
