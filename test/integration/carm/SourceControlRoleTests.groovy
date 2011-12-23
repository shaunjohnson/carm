package carm

import grails.test.*

class SourceControlRoleTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlRole.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(SourceControlRole.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(SourceControlRole.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SourceControlRole.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SourceControlRole.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SourceControlRole.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SourceControlRole.build(name: 'TestSourceControlRole')
        assertHasError(SourceControlRole.buildWithoutSave(name: 'TestSourceControlRole'), 'name', 'unique')
    }

    void testToString() {
        def domain = SourceControlRole.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
