package org.example.serverLabelTracker;

import java.util.Objects;

public class Label {
    private int number;
    private String server;

    public Label(String server, int number) {
        this.number = number;
        this.server = server;
    }

    public Label(String format) {
        String[] labelData = format.split(":");
        this.server = labelData[0];
        this.number = Integer.parseInt(labelData[1]);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return server+ ":" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return getNumber() == label.getNumber() && Objects.equals(getServer(), label.getServer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getServer());
    }
}
