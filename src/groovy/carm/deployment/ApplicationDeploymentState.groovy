package carm.deployment

public enum ApplicationDeploymentState {
    DRAFT,
    SUBMITTED,
    ASSIGNED,
    REJECTED,
    DEPLOYED,
    COMPLETED

    static List<ApplicationDeploymentState> deployedStates = [ DEPLOYED, COMPLETED ]
    static List<ApplicationDeploymentState> pendingStates = [ COMPLETED ]
}