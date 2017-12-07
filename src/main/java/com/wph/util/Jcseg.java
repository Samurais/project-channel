package com.wph.util;

import org.lionsoul.jcseg.extractor.KeywordsExtractor;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;

public interface Jcseg {
	public ISegment createISegmentCOMPLEX() throws JcsegException;
	public ISegment createISegmentSIMPLE() throws JcsegException;
	
	public KeywordsExtractor createKeywordsExtractorCOMPLEX() throws JcsegException;
	public KeywordsExtractor createKeywordsExtractorSIMPLE() throws JcsegException;
}
