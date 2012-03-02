package carm.deployment

public enum ApplicationDeploymentState {
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    ASSIGNED("Assigned"),
    REJECTED("Rejected"),
    DEPLOYED("Deployed"),
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