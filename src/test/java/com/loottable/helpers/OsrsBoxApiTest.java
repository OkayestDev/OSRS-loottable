package com.loottable.helpers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class OsrsBoxApiTest {
    @Test
    public void testReturnsInfo() throws Exception {
        Object dropTable = OsrsBoxApi.getMonsterDropTableById(2);
        assertNotNull(dropTable);
    }

    @Test
    public void testGetMonsterId() {
        Object dropTable = OsrsBoxApi.getOSRSBoxItemsByName("guard");
        assertNotNull(dropTable);
    }

    @Test
    public void testGetMonsterIdWithMonsterNameWithSpace() {
        Object dropTable = OsrsBoxApi.getOSRSBoxItemsByName("Lesser Demon");
        assertNotNull(dropTable);
    }

    @Test
    public void testGetLongTailedWyvernDropTable() {
        Object dropTable = OsrsBoxApi.getOSRSBoxItemsByName("Long-Tailed Wyvern");
        assertNotNull(dropTable);
    }

    @Test
    public void testGetZulrahDropTable() {
        Object dropTable = OsrsBoxApi.getOSRSBoxItemsByName("Zulrah");
        assertNotNull(dropTable);
    }
    
}
