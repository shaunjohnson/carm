package carm.release

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A single activity record for an application release object.
 */
class ApplicationReleaseHistory {
    String summary
    String comments
    String username
    
    Date dateCreated
    Date lastUpdated
    
    static constraints = {
        applicationRelease nullable: false
        summary maxSize: 255, nullable: false, blank: false
        comments nullable: true
        username maxSize: 255, nullable: false, blank: false
    }

    static belongsTo = [applicationRelease: ApplicationRelease]

    static mapping = {
        sort "dateCreated":"desc"
        comments type: 'text'
    }

    String toString() {
        "Application release history $summary $username $dateCreated"
    }

    boolean equals(other) {
        if (!(other instanceof ApplicationReleaseHistory)) {
            return false
        }

        other.summary == summary && other.username == username && other.dateCreated == dateCreated
    }

    int hashCode() {
        new HashCodeBuilder().append(summary).append(username).append(dateCreated).toHashCode()
    }
}
