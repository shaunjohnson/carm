package carm

import grails.test.*

class ApplicationTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(Application.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(Application.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(Application.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(Application.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(Application.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(Application.buildWithoutSave(name: null), 'name', 'nullable')
    }

    void testSourceControlRepositoryNullable() {
        assertHasError(Application.buildWithoutSave(sourceControlRepository: null), 'sourceControlRepository', 'nullable')
    }

    void testSourceControlPathMaxSize() {
        assertHasError(Application.buildWithoutSave(sourceControlPath: 'a' * 201), 'sourceControlPath', 'maxSize')
    }

    void testSystemNullable() {
        assertTrue(Application.buildWithoutSave(system: null).validate())
    }

    void testTypeNullable() {
        assertHasError(Application.buildWithoutSave(type: null), 'type', 'nullable')
    }

    void testToString() {
        def domain = Application.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
