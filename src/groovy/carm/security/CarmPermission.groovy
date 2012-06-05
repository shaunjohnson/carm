package carm.security

public enum CarmPermission {
    APPLICATION_DEVELOPER,
    APPLICATION_TEAM_LEADER,
    PROJECT_ADMINISTRATOR;

    String generateName(id) {
        new StringBuilder().append(name()).append("_").append(id).toString()
    }
}
