/**
 * TranslateTest.java
 *
 * Copyright (C) 2008,  Richard Midwinter
 * 
 * This file is part of google-api-translate-java.
 *
 * google-api-translate-java is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * google-api-translate-java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with google-api-translate-java. If not, see <http://www.gnu.org/licenses/>.
 */
package com.google.api.translate;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.api.Files;
import com.google.api.GoogleAPI;

/**
 * @author Richard Midwinter
 */
public class TranslateTest extends TestCase {
	
	private Translate translate;
	
	@Override
	public void setUp() throws Exception {
        GoogleAPI.setHttpReferrer(Files.read(new File(System.getProperty("user.home") + "/.google-translate-api-referrer.txt")).trim());
        GoogleAPI.setKey(Files.read(new File(System.getProperty("user.home") + "/.google-translate-api.key")).trim());
		
		translate = Translate.DEFAULT;
	}
	
	@Test
	public void testHttpReferrerRequired() throws Exception {
		System.out.println("testHttpReferrerRequired");

		GoogleAPI.setHttpReferrer(null);

		try {
			translate.execute("Hello world", Language.ENGLISH, Language.ARABIC);
			fail("Should have thrown an error as HTTP referrer is not set.");
		} catch (Exception e) {
			assertEquals("java.lang.Exception: [google-api-translate-java] Referrer is not set. Call setHttpReferrer().", e.getMessage());
		}
	}
	
	@Test
	public void testTranslate() throws Exception {
		System.out.println("testTranslate");
		
		assertEquals("مرحبا بالعالم", translate.execute("Hello world", Language.ENGLISH, Language.ARABIC));
		assertEquals("你好世界", translate.execute("Hello world", Language.ENGLISH, Language.CHINESE));
		assertEquals("Bonjour le monde", translate.execute("Hello world", Language.ENGLISH, Language.FRENCH));
		assertEquals("Hallo Welt", translate.execute("Hello world", Language.ENGLISH, Language.GERMAN));
		assertEquals("हैलो वर्ल्ड", translate.execute("Hello world", Language.ENGLISH, Language.HINDI));
		assertEquals("Ciao mondo", translate.execute("Hello world", Language.ENGLISH, Language.ITALIAN));
		assertEquals("こんにちは世界", translate.execute("Hello world", Language.ENGLISH, Language.JAPANESE));
		assertEquals("안녕하세요 세계", translate.execute("Hello world", Language.ENGLISH, Language.KOREAN));
		assertEquals("Olá Mundo", translate.execute("Hello world", Language.ENGLISH, Language.PORTUGUESE));
		assertEquals("Привет мир", translate.execute("Hello world", Language.ENGLISH, Language.RUSSIAN));
		assertEquals("Hola Mundo", translate.execute("Hello world", Language.ENGLISH, Language.SPANISH));
		assertEquals("Hello dinja", translate.execute("Hello world", Language.ENGLISH, Language.MALTESE));
		assertEquals("สวัสดีชาวโลก", translate.execute("Hello world", Language.ENGLISH, Language.THAI));
		assertEquals("Selam Dünya", translate.execute("Hello world", Language.ENGLISH, Language.TURKISH));
		assertEquals("Hola Mundo", translate.execute("Привет мир", Language.RUSSIAN, Language.SPANISH));
		assertEquals("Ciao Mondo", translate.execute("Hallo welt", Language.GERMAN, Language.ITALIAN));
		assertEquals("D'Accord", translate.execute("Ok", Language.ENGLISH, Language.FRENCH));
		assertEquals("Iawn", translate.execute("Ok", Language.ENGLISH, Language.WELSH));
		
		assertEquals("Bonjour Le Monde", translate.execute("Hallo welt", Language.AUTO_DETECT, Language.FRENCH));
	}
	
	@Test
	public void testUnHtmlEntities() throws Exception {
		System.out.println("testUnHtmlEntities");
		
		assertEquals("זיהוי", translate.execute("ID", Language.ENGLISH, Language.HEBREW));
	}
	
	@Test
	public void testTranslateMultipleTexts() throws Exception {
		System.out.println("testTranslateMultipleTexts");
		
		final String[] results = translate.execute(new String[] {
			"Hello world",
			"See you soon"
		}, Language.ENGLISH, Language.FRENCH);
		
		assertEquals("Bonjour le monde", results[0]);
		assertEquals("À bientôt", results[1]);
	}
	
	@Test
	public void testTranslateMultipleLanguages() throws Exception {
		System.out.println("testTranslateMultipleLanguages");
		
		final String[] results = translate.execute("Hello world", Language.ENGLISH, new Language[] {
			Language.AFRIKAANS,
			Language.ALBANIAN,
			Language.ARABIC,
			Language.BELARUSIAN,
			Language.CATALAN,
			Language.CHINESE,
			Language.CHINESE_SIMPLIFIED,
			Language.CHINESE_TRADITIONAL,
			Language.CROATIAN,
			Language.CZECH,
			Language.DANISH,
			Language.DUTCH,
			Language.ESTONIAN,
			Language.FILIPINO,
			Language.FINNISH,
			Language.FRENCH,
			Language.GALICIAN,
			Language.GERMAN,
			Language.GREEK,
			Language.HEBREW,
			Language.HINDI,
			Language.HUNGARIAN,
			Language.INDONESIAN,
			Language.IRISH,
			Language.ITALIAN,
			Language.JAPANESE,
			Language.KOREAN,
			Language.LATVIAN,
			Language.LITHUANIAN,
			Language.MACEDONIAN,
			Language.MALAY,
			Language.MALTESE,
			Language.NORWEGIAN,
			Language.PERSIAN,
			Language.POLISH,
			Language.PORTUGUESE,
			Language.ROMANIAN,
			Language.RUSSIAN,
			Language.SERBIAN,
			Language.SLOVAK,
			Language.SLOVENIAN,
			Language.SPANISH,
			Language.SWAHILI,
			Language.SWEDISH,
			Language.THAI,
			Language.TURKISH,
			Language.UKRANIAN,
			Language.VIETNAMESE,
			Language.WELSH,
			Language.YIDDISH
		});

		assertEquals("Ola Mundo", results[16]);
		assertEquals("Ciao mondo", results[24]);
		assertEquals("Wapendwa dunia", results[42]);
	}
	
	@Test
	public void testTranslateMultipleTextsAndLanguagesSingleEntry() throws Exception {
		System.out.println("testTranslateMultipleTextsAndLanguagesSingleEntry");
		
		final String[] results = translate.execute(new String[] {
			"Hello world"
		}, new Language[] {
			Language.ENGLISH
		}, new Language[] {
			Language.FRENCH
		});
		
		assertEquals("Bonjour le monde", results[0]);
	}
	
	@Test
	public void testTranslateMultipleTextsAndLanguages() throws Exception {
		System.out.println("testTranslateMultipleTextsAndLanguages");
		
		final String[] results = translate.execute(new String[] {
			"Hello world",
			"Hello world"
		}, new Language[] {
			Language.ENGLISH,
			Language.ENGLISH
		}, new Language[] {
			Language.FRENCH,
			Language.SPANISH
		});
		
		assertEquals("Bonjour le monde", results[0]);
	}
	
	@Test
	public void testExample() throws Exception {
		System.out.println("testExample");
		
		assertEquals("Hello World", translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH));
	}
	
	@Test
	public void testLarge() throws Exception {
		System.out.println("testLarge");
		
		try {
			translate.execute("Figures from the Office for National Statistics (ONS) show that between December and April, "
					+ "the five-month period typically regarded as peak bonus season, those working in the financial "
					+ "intermediation sector received bonuses worth ¬¨¬£7.6bn. The figure is more than 40pc lower than last"
					+ "year's total of ¬¨¬£13.2bn, but the fact that it came during a period where the banking system owed its"
					+ "survival to the rescue support of taxpayers\' money will spark further outrage. Related Articles USS"
					+ "pays bonuses despite fund fall Ex-HBOS chief Hornby gives up ¬¨¬£1m redundancyBankers blind to bonus "
					+ "'furore' Barclays and Lloyds to dish out millions in bonuses. City bonuses defy credit crunch and "
					+ "hit new record of ¬¨¬£13bn. We are still mad with the banks but we are no closer to getting even. News"
					+ "of the huge sums being offered by Barclays to five traders at JP Morgan will also stoke the row. "
					+ "Barclays is close to poaching Todd Edgar, 37, a star commodity trader at JP Morgan, and his four "
					+ "team members to head up the foreign exchange trading desk. Mr Edgar is responsible for a $2bn book "
					+ "at JP Morgan and single-handedly made the US investment bank a $100m profit last year. At Barclays,"
					+ "the team will have an emerging markets focus, with two members based in Asia and Mr Edgar and the "
					+ "others operating out of London. They will also continue to trade commodities, but to a lesser degree"
					+ "than before. Barclays has offered the team a combined $25m in salaries and bonuses paid in cash "
					+ "guarantees and deferred stock. In addition, they will take a share of future profits that could lift"
					+ "the package to $50m. Market-leading rates on profit shares are currently 12pc, according to bankers,"
					+ "but Mr Edgar and his team are said to have been offered even more generous terms. Sources suggest Mr"
					+ "Edgar himself could receive up to half the total. It is understood the pay package does not "
					+ "contravene any of the Financial Service Authority's guidelines. At JP Morgan, Mr Edgar was largely a"
					+ "proprietary trader, gambling with the bank's own money. At Barclays, although he will take "
					+ "proprietary positions, his main role will be client business. Mr Edgar's appointment would follow "
					+ "public outrage last week over a ¬¨¬£7m \"market leading\" deal agreed by Royal Bank of Scotland, 70pc "
					+ "owned by the taxpayer, for a Merrill Lynch banker, Antonio Polverino. Although Barclays has not "
					+ "taken any cash directly from the state, critics say it is the beneficiary of ¬¨¬£1.2 trillion of "
					+ "taxpayer support for the financial system as a whole. Senior Treasury officials believe that the "
					+ "bank would have collapsed were it not for their assistance. In an interview this weekend, the Shadow"
					+ "Chancellor, George Osborne said it was \"totally unacceptable\" that the major banks are paying "
					+ "large bonuses on the back of taxpayer guarantees. Mr Osborne said: \"There are hundreds of billions "
					+ "of pounds of guarantees in existence: guarantees provided by the taxpayer to all banks. The reason "
					+ "those guarantees are in place is not so the bankers can pay themselves large bonuses. \"The scale of"
					+ "this year's bonus payments, as revealed by the ONS statistics, would be enough to finance an almost "
					+ "2p reduction in the basic rate of income tax. The payments came after the unprecedented bail-out of "
					+ "British banks, which cost the taxpayer some ¬¨¬£35bn in capital infusions. Lord Oakeshott, Liberal "
					+ "Democrat Treasury spokesman, said: \"These figures suggest that the bankers are taking most of the "
					+ "profits and the taxpayer is taking most of the risk. \"The official confirmation of the scale of "
					+ "City bonuses in the past year underlines the fact that even against the backdrop of the worst "
					+ "financial crisis in British history, bankers awarded themselves bonuses which were still "
					+ "significantly larger, even in nominal terms, than those handed out five years ago in 2004, when the "
					+ "City was entering the credit boom. Barclays and JP Morgan declined to comment.",
					Language.ENGLISH, Language.FRENCH);
		} catch (final Exception e) {
			assertEquals("Server returned HTTP response code: 414", e.getCause().getCause().getMessage().substring(0, 39));
		}
	}
}