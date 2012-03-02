package carm.enums

public enum ApplicationDeploymentState {
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    ASSIGNED("Assigned"),
    REJECTED("Rejected"),
    RELEASED("Released"),
    COMPLETED("Completed"),
    ARCHIVED("Archived")

    private String value

    ApplicationDeploymentState(String value) {
        this.value = value
    }

    String getKey() {
        name()
    }

    String toString() {
        value
    }
}