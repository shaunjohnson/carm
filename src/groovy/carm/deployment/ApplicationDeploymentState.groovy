package carm.deployment

public enum ApplicationDeploymentState {
    DRAFT,
    SUBMITTED,
    ASSIGNED,
    REJECTED,
    DEPLOYED,
    COMPLETED,
    ARCHIVED

    static List<ApplicationDeploymentState> deployedStates = [ DEPLOYED, COMPLETED, ARCHIVED ]
    static List<ApplicationDeploymentState> pendingStates = [ COMPLETED, ARCHIVED ]
}