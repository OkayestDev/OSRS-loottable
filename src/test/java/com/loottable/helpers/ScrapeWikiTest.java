package com.loottable.helpers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ScrapeWikiTest {
    @Test
    public void testScrapeWikiFetchesWikiPage() {
        List<String[]> lootTable = ScrapeWiki.scrapeWiki("Fire giant");
        // Assuming bones still at top of page
        String[] bonesTable = lootTable.get(0);
        assertEquals(bonesTable[0], "Big bones");
        assertEquals(bonesTable[1], "1");
        assertEquals(bonesTable[2], "Always");
    }

    @Test
    public void testFilterWikiTableContent() {
        String testContent = "1/428;1/76[d2]";
        String expected = "1/428;1/76";
        // Expect [d2] to be removed
        String result = ScrapeWiki.filterWikiTableContent(testContent);
        assertEquals(expected, result);
    }
}