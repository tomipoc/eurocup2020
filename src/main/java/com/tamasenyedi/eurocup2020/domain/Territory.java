package com.tamasenyedi.eurocup2020.domain;

public enum Territory {

    DE, HU;

    public static Territory asTerritory(String str) {
        for (Territory t : Territory.values()) {
            if (t.name().equalsIgnoreCase(str))
                return t;
        }
        return null;
    }
}
