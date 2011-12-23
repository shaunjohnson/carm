package carm

import grails.test.*

class SystemTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(System.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(System.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(System.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(System.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(System.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(System.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        System.build(name: 'TestSystem')
        assertHasError(System.buildWithoutSave(name: 'TestSystem'), 'name', 'unique')
    }

    void testToString() {
        def domain = System.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
