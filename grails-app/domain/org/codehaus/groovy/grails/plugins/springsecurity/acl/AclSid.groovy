package org.codehaus.groovy.grails.plugins.springsecurity.acl

import org.apache.commons.lang.builder.HashCodeBuilder

class AclSid {

	String sid
	boolean principal

	static mapping = {
		version false
	}

	static constraints = {
		principal unique: 'sid'
		sid blank: false, size: 1..255
	}

    @Override
    String toString() {
        "AclSid id $id, sid $sid, principal $principal"
    }

    boolean equals(other) {
        if (!(other instanceof AclSid)) {
            return false
        }

        other.sid == sid
    }

    int hashCode() {
        new HashCodeBuilder().append(sid).toHashCode()
    }
}
