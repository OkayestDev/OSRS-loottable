package com.loottable.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONArray;
import org.junit.Test;

public class OsrsBoxApiTest {
    @Test
    public void testReturnsInfo() throws Exception {
        JSONArray dropTable = OsrsBoxApi.getMonsterDropTable(2);
        assertNotNull(dropTable);
    }

    @Test
    public void testGetMonsterId() {
        int monsterId = OsrsBoxApi.getMonsterId("guard");
        assertNotNull(monsterId);
    }

    @Test
    public void testGetMonsterIdWithBadMonsterName() {
        int monsterId = OsrsBoxApi.getMonsterId("hooblahh");
        assertEquals(0, monsterId);
    }

    @Test
    public void testGetMonsterIdWithMonsterNameWithSpace() {
        int monsterId = OsrsBoxApi.getMonsterId("Lesser Demon");
        assertNotNull(monsterId);
    }
}
