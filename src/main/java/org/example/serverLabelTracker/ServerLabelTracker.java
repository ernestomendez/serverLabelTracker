package org.example.serverLabelTracker;

import java.util.HashMap;
import java.util.Map;

public class ServerLabelTracker {

    private Map<String, Tracker> trackerMap;

    public void labelsWork() {

        trackerMap = new HashMap<>();
        trackerMap.put("apiBox",new Tracker());
        trackerMap.put("sitebox", new Tracker());

        Tracker apiBox = trackerMap.get("apiBox");

        System.out.println(apiBox.attach("apibox"));
        System.out.println(apiBox.attach("apibox"));
        System.out.println(apiBox.attach("apibox"));
        System.out.println(apiBox.attach("apibox"));

        apiBox.detach("apibox:3");
        apiBox.detach("apibox:2");

        System.out.println(apiBox.attach("apibox"));
        System.out.println(apiBox.attach("apibox"));

        apiBox.detach("apibox:2");
        System.out.println(apiBox.attach("apibox"));

        Tracker sitebox = trackerMap.get("sitebox");

        System.out.println(sitebox.attach("sitebox"));
        System.out.println(sitebox.attach("sitebox"));
        System.out.println(apiBox.attach("apibox"));
    }
}
