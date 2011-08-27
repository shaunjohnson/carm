package net.lmxm.carm

import grails.test.*

class SourceControlUserTests extends GrailsUnitTestCase {
    def sourceControlUser

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SourceControlUser)

        sourceControlUser = new SourceControlUser(name: 'Developer')
        mockForConstraintsTests(SourceControlUser, [sourceControlUser])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testSourceControlUser = new SourceControlUser(name: 'Test User', description: "a" * 4001)
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should not be valid', testSourceControlUser.validate()
        assertEquals 'Description is too long', 'maxSize', testSourceControlUser.errors['description']
    }

    void testDescriptionNullable() {
        def testSourceControlUser = new SourceControlUser(name: 'Test User', description: null)
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertTrue 'Should be valid', testSourceControlUser.validate()
    }

    void testNameBlank() {
        def testSourceControlUser = new SourceControlUser(name: '')
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should not be valid', testSourceControlUser.validate()
        assertEquals 'Name is blank', 'blank', testSourceControlUser.errors['name']
    }

    void testNameMinSize() {
        def testSourceControlUser = new SourceControlUser(name: 'a')
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should not be valid', testSourceControlUser.validate()
        assertEquals 'Name is too short', 'minSize', testSourceControlUser.errors['name']
    }

    void testNameMaxSize() {
        def testSourceControlUser = new SourceControlUser(name: 'a' * 51)
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should not be valid', testSourceControlUser.validate()
        assertEquals 'Name is too long', 'maxSize', testSourceControlUser.errors['name']
    }

    void testNameNullable() {
        def testSourceControlUser = new SourceControlUser(name: null)
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should not be valid', testSourceControlUser.validate()
        assertEquals 'Name is null', 'nullable', testSourceControlUser.errors['name']
    }

    void testNameUnique() {
        def testSourceControlUser = new SourceControlUser(name: 'Developer')
        mockForConstraintsTests(SourceControlUser, [testSourceControlUser])

        assertFalse 'Should be valid', sourceControlUser.validate()
    }

    void testToString() {
        assertNotNull sourceControlUser.toString()
        assertTrue sourceControlUser.toString() ==~ /.*Developer.*/
    }
}
