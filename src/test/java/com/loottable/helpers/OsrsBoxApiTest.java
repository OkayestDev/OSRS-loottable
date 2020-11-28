package com.loottable.helpers;

import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONArray;
import org.junit.Test;

public class OsrsBoxApiTest {
    @Test
    public void testReturnsInfo() throws Exception {
        JSONArray dropTable = OsrsBoxApi.getMonsterDropTable(2);
        assertNotNull(dropTable);
    }
}
