package com.wph.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;

public class DocumentUtils {
	public static Document knowledgebaseJSONToDocument(KnowledgebaseJSON json) {
		Document doc = new Document();
		Field id = new Field("id", String.valueOf(json.getId()), TextField.TYPE_STORED);
		Field category = new Field("category", json.getCategory(), TextField.TYPE_STORED);
		Field content = new Field("content", json.getContent(), TextField.TYPE_STORED);
		Field title = new Field("title", json.getTitle(), TextField.TYPE_STORED);
		//这里用String value 如果为null 则在索引中就为关键字null
		//如果不设置company_id属性的话,则get的对象就为对象null

		doc.add(id);
		doc.add(category);
		doc.add(content);
		doc.add(title);
		return doc;
	}

	public static KnowledgebaseJSON ducumentToKnowledgebaseJSON(Document doc) {
		KnowledgebaseJSON json = new KnowledgebaseJSON();
		String id = doc.get("id");
		if (!id.equals("null")) {
			json.setId(Integer.parseInt(doc.get("id")));
		}
		json.setCategory(doc.get("category"));
		json.setContent(doc.get("content"));
		json.setTitle(doc.get("title"));
		return json;
	}

	public static Document knowledgetbaseToDocument(Knowledgebase know) {
		Document doc = new Document();
		doc.add(new Field("id", know.getId().toString(), TextField.TYPE_STORED));
		doc.add(new Field("title", know.getTitle(), TextField.TYPE_STORED));
		doc.add(new Field("category", know.getCategory(), TextField.TYPE_STORED));
		doc.add(new Field("content", know.getContent(), TextField.TYPE_STORED));
		return doc;
	}

	public static Knowledgebase documentToknowledgetbase(Document doc) {
		Knowledgebase know = new Knowledgebase();
		know.setId(Integer.parseInt(doc.get("id")));
		know.setTitle(doc.get("title"));
		know.setCategory(doc.get("category"));
		know.setContent(doc.get("content"));
		return know;
	}
}
