package carm

import grails.test.*

class SourceControlRoleTests extends GrailsUnitTestCase {
    def sourceControlRole

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SourceControlRole)

        sourceControlRole = new SourceControlRole(name: 'Developer')
        mockForConstraintsTests(SourceControlRole, [sourceControlRole])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testSourceControlRole = new SourceControlRole(name: 'Test Role', description: "a" * 4001)
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', testSourceControlRole.validate()
        assertEquals 'Description is too long', 'maxSize', testSourceControlRole.errors['description']
    }

    void testDescriptionNullable() {
        def testSourceControlRole = new SourceControlRole(name: 'Test Role', description: null)
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertTrue 'Should be valid', testSourceControlRole.validate()
    }

    void testNameBlank() {
        def testSourceControlRole = new SourceControlRole(name: '')
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', testSourceControlRole.validate()
        assertEquals 'Name is blank', 'blank', testSourceControlRole.errors['name']
    }

    void testNameMinSize() {
        def testSourceControlRole = new SourceControlRole(name: 'a')
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', testSourceControlRole.validate()
        assertEquals 'Name is too short', 'minSize', testSourceControlRole.errors['name']
    }

    void testNameMaxSize() {
        def testSourceControlRole = new SourceControlRole(name: 'a' * 51)
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', testSourceControlRole.validate()
        assertEquals 'Name is too long', 'maxSize', testSourceControlRole.errors['name']
    }

    void testNameNullable() {
        def testSourceControlRole = new SourceControlRole(name: null)
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', testSourceControlRole.validate()
        assertEquals 'Name is null', 'nullable', testSourceControlRole.errors['name']
    }

    void testNameUnique() {
        def testSourceControlRole = new SourceControlRole(name: 'Developer')
        mockForConstraintsTests(SourceControlRole, [testSourceControlRole])

        assertFalse 'Should not be valid', sourceControlRole.validate()
        assertEquals 'Name is not unique.', 'unique', sourceControlRole.errors['name']

        sourceControlRole = new SourceControlRole(name: 'Developer2')
        assertTrue 'Should be valid', sourceControlRole.validate()
    }

    void testToString() {
        assertNotNull sourceControlRole.toString()
        assertTrue sourceControlRole.toString() ==~ /.*Developer.*/
    }
}
