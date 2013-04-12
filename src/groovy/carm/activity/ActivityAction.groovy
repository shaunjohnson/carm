package carm.activity

public enum ActivityAction {
    COMPLETED,
    CREATED,
    DELETED,
    MOVED,
    SUBMITTED,
    UPDATED

    String getKey() {
        name()
    }
}