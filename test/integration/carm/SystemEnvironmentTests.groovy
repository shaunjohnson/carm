package carm

import grails.test.*

class SystemEnvironmentTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(SystemEnvironment.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: null), 'name', 'nullable')
    }

    void testSystemNullable() {
        def domain = SystemEnvironment.buildWithoutSave()
        domain.system = null
        assertHasError(domain, 'system', 'nullable')
    }

    void testToString() {
        def domain = SystemEnvironment.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
