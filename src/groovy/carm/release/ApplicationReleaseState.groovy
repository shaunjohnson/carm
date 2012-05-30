package carm.release

public enum ApplicationReleaseState {
    DRAFT,
    SUBMITTED,
    ASSIGNED,
    REJECTED,
    RELEASED,
    COMPLETED

    static List<ApplicationReleaseState> pendingStates = [ COMPLETED ]
    static List<ApplicationReleaseState> submittableStates = [ DRAFT, REJECTED ]

    String getKey() {
        name()
    }
}