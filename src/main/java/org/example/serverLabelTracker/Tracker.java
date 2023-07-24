package org.example.serverLabelTracker;

import java.util.LinkedList;

public class Tracker {

    private final LinkedList<Label> attached = new LinkedList<>();

    private final LinkedList<Label> detached = new LinkedList<>();



    public LinkedList<Label> getAttached() {
        return attached;
    }

    public LinkedList<Label> getDetached() {
        return detached;
    }

    public String attach(String labelName) {
        if (detached.isEmpty()) {
            if (attached.isEmpty()) { //totally new lists
                addLast(labelName, 1);
            } else {
                Label last = attached.getLast();
                addLast(labelName, last.getNumber() + 1);
            }
            return attached.getLast().toString();
        } else {
            Label first = detached.removeFirst();
            addToList(first, attached);
            return first.toString();
        }
    }

    void addToList(Label first, LinkedList<Label> list) {
        boolean added = false;
        for (int i = 0; i < list.size(); i++) {
            Label label = list.get(i);
            if (label.getNumber() > first.getNumber()) {
                list.add(i, first);
                added = true;
                break;
            }
        }
        if (!added) {
            list.addLast(first);
        }
    }

    private void addLast(String labelName, int number) {
        Label label = new Label(labelName, number);
        attached.addLast(label);
    }

    public void detach(String labelStr) {
        Label label = new Label(labelStr);

        attached.remove(label);

        addToList(label, detached);

    }
}
