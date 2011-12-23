package carm

import grails.test.*

class ProjectTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(Project.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(Project.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(Project.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(Project.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(Project.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(Project.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        Project.build(name: 'TestProject')
        assertHasError(Project.buildWithoutSave(name: 'TestProject'), 'name', 'unique')
    }

    void testToString() {
        def domain = Project.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
