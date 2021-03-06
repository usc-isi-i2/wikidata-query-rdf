package org.wikidata.query.rdf.tool.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.wikidata.query.rdf.tool.change.IdRangeChangeSource.forItems;

import org.junit.Test;
import org.wikidata.query.rdf.tool.exception.RetryableException;

public class IdRangeChangeSourceUnitTest {
    @Test
    public void empty() throws RetryableException {
        IdRangeChangeSource changeSource = forItems(0, -1, 1);
        assertEquals(0, changeSource.firstBatch().advanced());
        assertTrue(changeSource.firstBatch().last());
    }

    @Test
    public void one() throws RetryableException {
        IdRangeChangeSource changeSource = forItems(0, 5, 10);
        IdRangeChangeSource.Batch batch = changeSource.firstBatch();
        assertEquals(6, batch.advanced());
        assertTrue(batch.last());
        assertEquals("5", batch.leftOffHuman());
    }

    @Test
    public void oneExact() throws RetryableException {
        IdRangeChangeSource changeSource = forItems(0, 9, 10);
        IdRangeChangeSource.Batch batch = changeSource.firstBatch();
        assertEquals(10, batch.advanced());
        assertTrue(batch.last());
    }

    @Test
    public void two() throws RetryableException {
        IdRangeChangeSource changeSource = forItems(0, 10, 10);
        IdRangeChangeSource.Batch batch = changeSource.firstBatch();
        assertEquals(10, batch.advanced());
        assertFalse(batch.last());
        batch = changeSource.nextBatch(batch);
        assertEquals(1, batch.advanced());
        assertTrue(batch.last());
    }

    @Test
    public void many() throws RetryableException {
        IdRangeChangeSource changeSource = forItems(0, 10000, 10);
        IdRangeChangeSource.Batch batch = changeSource.firstBatch();
        while (!batch.last()) {
            assertEquals(10, batch.advanced());
            batch = changeSource.nextBatch(batch);
        }
        assertEquals(1, batch.advanced());
    }
}
