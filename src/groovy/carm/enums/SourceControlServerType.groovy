package carm.enums

public enum SourceControlServerType {
    CVS("CVS"),
    Git("Git"),
    Mercurial("Mercurial"),
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