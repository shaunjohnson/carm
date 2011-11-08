package net.lmxm.carm

import grails.test.*

class SystemEnvironmentTests extends GrailsUnitTestCase {
    def systemEnvironment
    def system

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SystemEnvironment)

        system = new System(name: 'System')

        systemEnvironment = new SystemEnvironment(name: 'SystemEnvironment', system: system)
        mockForConstraintsTests(SystemEnvironment, [systemEnvironment])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testSystemEnvironment = new SystemEnvironment(name: 'Test SystemEnvironment', system: system, description: "a" * 4001)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Description is too long', 'maxSize', testSystemEnvironment.errors['description']
    }

    void testDescriptionNullable() {
        def testSystemEnvironment = new SystemEnvironment(name: 'Test SystemEnvironment', system: system, description: null)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertTrue 'Should be valid', testSystemEnvironment.validate()
    }

    void testNameBlank() {
        def testSystemEnvironment = new SystemEnvironment(name: '', system: system)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is blank', 'blank', testSystemEnvironment.errors['name']
    }

    void testNameMinSize() {
        def testSystemEnvironment = new SystemEnvironment(name: 'a', system: system)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is too short', 'minSize', testSystemEnvironment.errors['name']
    }

    void testNameMaxSize() {
        def testSystemEnvironment = new SystemEnvironment(name: 'a' * 51, system: system)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is too long', 'maxSize', testSystemEnvironment.errors['name']
    }

    void testNameNullable() {
        def testSystemEnvironment = new SystemEnvironment(name: null, system: system)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is null', 'nullable', testSystemEnvironment.errors['name']
    }

    void testSystemNullable() {
        def testSystemEnvironment = new SystemEnvironment(name: 'SystemEnvironment', system: null)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is null', 'nullable', testSystemEnvironment.errors['system']
    }

    void testToString() {
        assertNotNull systemEnvironment.toString()
        assertTrue systemEnvironment.toString() ==~ /.*SystemEnvironment.*/
    }
}
