
package carm

import grails.test.*

class CarmUnitTestCase {
    
    final static assertHasError(Object domain, String field, String errorCode) {
        assert !domain.validate()
        assert domain.errors.hasFieldErrors(field)
        assert domain.errors.getFieldErrors(field).toString().contains(errorCode)
    }
}