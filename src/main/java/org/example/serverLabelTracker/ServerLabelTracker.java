package org.example.serverLabelTracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Server Label Tracker
 *
 * Physical servers used by our project are labeled using printed labels,
 * with exactly one label  being attached to a server at any given time.
 * Each label consists of an alphabetic host type (e.g. "apibox") concatenated with the server number,
 * with server numbers allocated sequentially (so, the first label will be "apibox:1", next one "apibox:2", etc).
 * As servers are provisioned and deprovisioned, the labels get attached and detached;
 * detached labels can later be reused for the same host type. We would like to keep the total number
 * of labels printed to a minimum (we’re environmentally friendly!).
 * We would also like to maintain sequential numbering to the extent possible,
 * so when we need a label, we always pick the one with the lowest number.
 *
 * Your task is to write a label tracking class with two operations, "attach(hostType)" and "detach(label)".
 * The former should return the next label to use, while the latter should return the label back into the pool.
 */
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
