package com.wph.util.impl;


import org.lionsoul.jcseg.extractor.KeyphraseExtractor;
import org.lionsoul.jcseg.extractor.KeywordsExtractor;
import org.lionsoul.jcseg.extractor.SummaryExtractor;
import org.lionsoul.jcseg.extractor.impl.TextRankKeywordsExtractor;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;
import org.springframework.stereotype.Component;

import com.wph.util.Jcseg;

@Component("jcseg")
public class JcsegUtils implements Jcseg {
	JcsegTaskConfig config = null;
	ADictionary dic = null;

	ISegment tokenizerSeg = null;
	ISegment extractorSeg = null;

	KeywordsExtractor keywordsExtractor = null;
	KeyphraseExtractor keyphraseExtractor = null;
	SummaryExtractor summaryExtractor = null;

	public JcsegUtils() {
		config = new JcsegTaskConfig();
		dic = DictionaryFactory.createDefaultDictionary(config);
	}

	public ISegment createISegmentCOMPLEX() throws JcsegException {
		config = new JcsegTaskConfig();
		dic = DictionaryFactory.createDefaultDictionary(config);
		ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new Object[] { config, dic });
		return seg;
	}

	public ISegment createISegmentSIMPLE() throws JcsegException {
		config = new JcsegTaskConfig();
		dic = DictionaryFactory.createDefaultDictionary(config);
		ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.SIMPLE_MODE, new Object[] { config, dic });
		return seg;
	}

	public KeywordsExtractor createKeywordsExtractorCOMPLEX() throws JcsegException {
		ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new Object[] { config, dic });
		return new TextRankKeywordsExtractor(seg);
	}

	public KeywordsExtractor createKeywordsExtractorSIMPLE() throws JcsegException {
		config = new JcsegTaskConfig();
		dic = DictionaryFactory.createDefaultDictionary(config);
		ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.SIMPLE_MODE, new Object[] { config, dic });
		return new TextRankKeywordsExtractor(seg);
	}

//	@Test
//	public void test() throws JcsegException, IOException {
//		config = new JcsegTaskConfig();
//		dic = DictionaryFactory.createDefaultDictionary(config);
//		ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new Object[] { config, dic });
//
//		String str = "��ϴ�: ��B��������壬x���߱�����ʲô������ȥ�涼ktv������okȥ������a����һ�������е����ǣ�";
//		seg.reset(new StringReader(str));
//
//		IWord word = null;
//		while ((word = seg.next()) != null) {
//			System.out.println(word.getValue());
//		}
//
//		keywordsExtractor = new TextRankKeywordsExtractor(seg);
//		List<String> keywords = keywordsExtractor.getKeywordsFromString(str);
//		System.out.println("Top10�ؼ���");
//		System.out.println(keywords);
//	}
}
