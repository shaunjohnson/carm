package net.lmxm.carm.enums

public enum SourceControlServerType {
    Subversion("Subversion")

    private String value

    SourceControlServerType(String value) {
        this.value = value
    }

    String getKey() {
        name()
    }

    String toString() {
        value
    }
}