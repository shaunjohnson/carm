package carm.sourcecontrol

public enum SourceControlServerType {
    CVS("CVS"),
    Git("Git"),
    Mercurial("Mercurial"),
    Subversion("Subversion"),
    TFS("Team Foundation Server")

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