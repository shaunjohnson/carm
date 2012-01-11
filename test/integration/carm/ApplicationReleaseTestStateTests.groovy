package carm

import grails.test.GrailsUnitTestCase

class ApplicationReleaseTestStateTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(ApplicationReleaseTestState.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ApplicationReleaseTestState.build(name: 'TestApplicationReleaseTestState')
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'TestApplicationReleaseTestState'), 'name', 'unique')
    }

    void testToString() {
        def domain = ApplicationReleaseTestState.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
