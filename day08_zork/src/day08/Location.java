package day08;

import java.util.List;

public class Location {
    private final String NAME;
    private final List<String> DESCRIPTION;
    private final List<String> DIRECTIONS;
    private Boolean inRoom = false;
    public static String currLocation;

    public Location(String name, List<String> descriptions, List<String> directions) {
        NAME = name;
        DESCRIPTION = descriptions;
        DIRECTIONS = directions;
    }

    public String getNAME() {
        return NAME;
    }

    public List<String> getDESCRIPTION() {
        return DESCRIPTION;
    }

    public List<String> getDIRECTIONS() {
        return DIRECTIONS;
    }

    public Boolean getInRoom() {
        return inRoom;
    }

    public void setInRoom(Boolean inRoom) {
        this.inRoom = inRoom;
    }
}
