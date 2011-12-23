package carm

import grails.test.GrailsUnitTestCase

class ApplicationTypeTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(ApplicationType.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(ApplicationType.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(ApplicationType.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ApplicationType.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ApplicationType.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ApplicationType.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ApplicationType.build(name: 'TestApplicationType')
        assertHasError(ApplicationType.buildWithoutSave(name: 'TestApplicationType'), 'name', 'unique')
    }

    void testToString() {
        def domain = ApplicationType.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
