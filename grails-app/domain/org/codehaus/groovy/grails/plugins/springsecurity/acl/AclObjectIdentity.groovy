package org.codehaus.groovy.grails.plugins.springsecurity.acl

import grails.plugins.springsecurity.acl.AbstractAclObjectIdentity
import org.apache.commons.lang.builder.HashCodeBuilder

class AclObjectIdentity extends AbstractAclObjectIdentity {

	Long objectId

	static mapping = {
		version false
		aclClass column: 'object_id_class'
		owner column: 'owner_sid'
		parent column: 'parent_object'
		objectId column: 'object_id_identity'
	}

	static constraints = {
		objectId unique: 'aclClass'
	}

    @Override
    String toString() {
        "AclObjectIdentity id $id, aclClass $aclClass.className, " +
                "objectId $objectId, entriesInheriting $entriesInheriting"
    }

    boolean equals(other) {
        if (!(other instanceof AclObjectIdentity)) {
            return false
        }

        other.objectId == objectId
    }

    int hashCode() {
        new HashCodeBuilder().append(objectId).toHashCode()
    }
}
