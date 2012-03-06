package carm.release

public enum ApplicationReleaseState {
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    ASSIGNED("Assigned"),
    REJECTED("Rejected"),
    RELEASED("Released"),
    COMPLETED("Completed"),
    ARCHIVED("Archived")

    static List<ApplicationReleaseState> pendingStates = [ ARCHIVED, COMPLETED ]
    static List<ApplicationReleaseState> submittableStates = [ DRAFT, REJECTED ]

    private String value

    ApplicationReleaseState(String value) {
        this.value = value
    }

    String getKey() {
        name()
    }

    String toString() {
        value
    }
}