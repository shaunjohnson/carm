package org.codehaus.groovy.grails.plugins.springsecurity.acl

import org.apache.commons.lang.builder.HashCodeBuilder

class AclClass {

	String className

	static mapping = {
		className column: 'class'
		version false
	}

	static constraints = {
		className unique: true, blank: false, size: 1..255
	}

    @Override
    String toString() {
        "AclClass id $id, className $className"
    }

    boolean equals(other) {
        if (!(other instanceof AclClass)) {
            return false
        }

        other.className == className
    }

    int hashCode() {
        new HashCodeBuilder().append(className).toHashCode()
    }
}
