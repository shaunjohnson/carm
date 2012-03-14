package org.codehaus.groovy.grails.plugins.springsecurity.acl

import org.apache.commons.lang.builder.HashCodeBuilder

class AclEntry {

	AclObjectIdentity aclObjectIdentity
	int aceOrder
	AclSid sid
	int mask
	boolean granting
	boolean auditSuccess
	boolean auditFailure

	static mapping = {
		version false
		sid column: 'sid'
		aclObjectIdentity column: 'acl_object_identity'
	}

	static constraints = {
		aceOrder unique: 'aclObjectIdentity'
	}

    @Override
    String toString() {
        "AclEntry id $id, aceOrder $aceOrder, mask $mask, granting $granting, " +
                "aclObjectIdentity $aclObjectIdentity"
    }

    boolean equals(other) {
        if (!(other instanceof AclEntry)) {
            return false
        }
        
        other.aclObjectIdentity == aclObjectIdentity && other.aceOrder == aceOrder && other.sid == sid && other.mask == mask
    }

    int hashCode() {
        new HashCodeBuilder().append(aclObjectIdentity).append(aceOrder).append(sid).append(mask).toHashCode()
    }
}
