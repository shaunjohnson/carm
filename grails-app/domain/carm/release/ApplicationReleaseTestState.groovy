package carm.release

class ApplicationReleaseTestState implements Comparable<ApplicationReleaseTestState> {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    int compareTo(ApplicationReleaseTestState o) {
        return name.compareTo(o?.name)
    }
}
