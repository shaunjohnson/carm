package carm.notification

public enum NotificationEvent {
    // Application Deployment events
    APPLICATION_DEPLOYMENT_SUBMITTED,
    APPLICATION_DEPLOYMENT_ASSIGNED,
    APPLICATION_DEPLOYMENT_REJECTED,
    APPLICATION_DEPLOYMENT_DEPLOYED,
    APPLICATION_DEPLOYMENT_COMPLETED,

    // Application Release events
    APPLICATION_RELEASE_SUBMITTED,
    APPLICATION_RELEASE_ASSIGNED,
    APPLICATION_RELEASE_REJECTED,
    APPLICATION_RELEASE_RELEASED,
    APPLICATION_RELEASE_COMPLETED

    String getKey() {
        name()
    }
}