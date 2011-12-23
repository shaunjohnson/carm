
package carm

import grails.test.*

class CarmUnitTestCase extends GrailsUnitTestCase {
    final static assertHasError(Object domain, String field, String errorCode) {
        assertFalse "Domain object should not be valid", domain.validate()
        assertTrue "Domain object should have error for field $field",
                domain.errors.hasFieldErrors(field)
        assertTrue "Error message should be for error code $errorCode",
                domain.errors.getFieldErrors(field).toString().contains(errorCode)
    }
}