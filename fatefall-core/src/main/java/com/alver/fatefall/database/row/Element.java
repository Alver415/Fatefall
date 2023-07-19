package com.alver.fatefall.database.row;

public record Element (String id, String parentId, String name, String value) implements Identifiable {


}
