package org.example.serverLabelTracker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

public class TrackerTest {

    private Tracker tracker;

    private Map<String, Tracker> trackerMap;

    @Before
    public void setUp() throws Exception {
        tracker = new Tracker();

        trackerMap = new HashMap<>();
        trackerMap.put("apiBox", new Tracker());
        trackerMap.put("sitebox", new Tracker());
    }

    @Test
    public void testAttachWhenBothListsAreEmpty() {
        String label = tracker.attach("test");
        assertEquals("test:1", label);
        assertEquals(1, tracker.getAttached().size());
        assertEquals(0, tracker.getDetached().size());
    }

    @Test
    public void testAttachWhenDetachedListIsNotEmpty() {
        tracker.detach("test:1");
        String label = tracker.attach("test");
        assertEquals("test:1", label);
        assertEquals(1, tracker.getAttached().size());
        assertEquals(0, tracker.getDetached().size());
    }

    @Test
    public void testAttachWhenDetachedListIsEmptyButAttachedListIsNotEmpty() {
        tracker.attach("test");
        String label = tracker.attach("test");
        assertEquals("test:2", label);
        assertEquals(2, tracker.getAttached().size());
        assertEquals(0, tracker.getDetached().size());
    }

    @Test
    public void testDetach() {
        tracker.attach("test");
        tracker.detach("test:1");
        assertEquals(0, tracker.getAttached().size());
        assertEquals(1, tracker.getDetached().size());
    }

    @Test
    public void testAddToList() {
        LinkedList<Label> list = new LinkedList<>();
        list.add(new Label("test", 2));
        list.add(new Label("test", 4));
        list.add(new Label("test", 5));

        Label label = new Label("test", 3);
        tracker.addToList(label, list);

        assertEquals(4, list.size());
        assertEquals(label, list.get(1));  // Verify that the label was added at the correct position
    }

    @Test
    public void testMultipleTrackers() {
        Tracker apiBox = trackerMap.get("apiBox");

        assertEquals("apibox:1", apiBox.attach("apibox"));
        assertEquals("apibox:2", apiBox.attach("apibox"));
        assertEquals("apibox:3", apiBox.attach("apibox"));
        assertEquals("apibox:4", apiBox.attach("apibox"));

        apiBox.detach("apibox:3");
        assertEquals(1, apiBox.getDetached().size());
        assertEquals(3, apiBox.getAttached().size());
        apiBox.detach("apibox:2");
        assertEquals(2, apiBox.getDetached().size());
        assertEquals(2, apiBox.getAttached().size());

        assertEquals("apibox:2", apiBox.attach("apibox"));
        assertEquals("apibox:3", apiBox.attach("apibox"));

        apiBox.detach("apibox:2");
        assertEquals(1, apiBox.getDetached().size());
        assertEquals(3, apiBox.getAttached().size());
        assertEquals("apibox:2", apiBox.attach("apibox"));

        Tracker sitebox = trackerMap.get("sitebox");

        assertEquals("sitebox:1", sitebox.attach("sitebox"));
        assertEquals("sitebox:2", sitebox.attach("sitebox"));
        assertEquals("apibox:5", apiBox.attach("apibox"));
    }
}