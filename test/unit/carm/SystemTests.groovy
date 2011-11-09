package carm

import grails.test.*

class SystemTests extends GrailsUnitTestCase {
    def system

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(System)

        system = new System(name: 'System')
        mockForConstraintsTests(System, [system])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testSystem = new System(name: 'Test System', description: "a" * 4001)
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', testSystem.validate()
        assertEquals 'Description is too long', 'maxSize', testSystem.errors['description']
    }

    void testDescriptionNullable() {
        def testSystem = new System(name: 'Test System', description: null)
        mockForConstraintsTests(System, [testSystem])

        assertTrue 'Should be valid', testSystem.validate()
    }

    void testNameBlank() {
        def testSystem = new System(name: '')
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', testSystem.validate()
        assertEquals 'Name is blank', 'blank', testSystem.errors['name']
    }

    void testNameMinSize() {
        def testSystem = new System(name: 'a')
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', testSystem.validate()
        assertEquals 'Name is too short', 'minSize', testSystem.errors['name']
    }

    void testNameMaxSize() {
        def testSystem = new System(name: 'a' * 51)
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', testSystem.validate()
        assertEquals 'Name is too long', 'maxSize', testSystem.errors['name']
    }

    void testNameNullable() {
        def testSystem = new System(name: null)
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', testSystem.validate()
        assertEquals 'Name is null', 'nullable', testSystem.errors['name']
    }

    void testNameUnique() {
        def testSystem = new System(name: 'System')
        mockForConstraintsTests(System, [testSystem])

        assertFalse 'Should not be valid', system.validate()
        assertEquals 'Name is not unique.', 'unique', system.errors['name']

        system = new System(name: 'System2')
        assertTrue 'Should be valid', system.validate()
    }

    void testToString() {
        assertNotNull system.toString()
        assertTrue system.toString() ==~ /.*System.*/
    }
}
