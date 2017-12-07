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
//		String str = "混合词: 做B超检查身体，x射线本质是什么，今天去奇都ktv唱卡拉ok去，哆啦a梦是一个动漫中的主角，";
//		seg.reset(new StringReader(str));
//
//		IWord word = null;
//		while ((word = seg.next()) != null) {
//			System.out.println(word.getValue());
//		}
//
//		keywordsExtractor = new TextRankKeywordsExtractor(seg);
//		List<String> keywords = keywordsExtractor.getKeywordsFromString(str);
//		System.out.println("Top10关键字");
//		System.out.println(keywords);
//	}
}
