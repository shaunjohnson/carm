package net.lmxm.carm

import grails.test.*

class SystemComponentTests extends GrailsUnitTestCase {
    def systemComponent
    def system

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SystemComponent)

        system = new System(name: 'System')

        systemComponent = new SystemComponent(name: 'SystemComponent', system: system)
        mockForConstraintsTests(SystemComponent, [systemComponent])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testSystemComponent = new SystemComponent(name: 'Test SystemComponent', system: system, description: "a" * 4001)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', testSystemComponent.validate()
        assertEquals 'Description is too long', 'maxSize', testSystemComponent.errors['description']
    }

    void testDescriptionNullable() {
        def testSystemComponent = new SystemComponent(name: 'Test SystemComponent', system: system, description: null)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertTrue 'Should be valid', testSystemComponent.validate()
    }

    void testNameBlank() {
        def testSystemComponent = new SystemComponent(name: '', system: system)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', testSystemComponent.validate()
        assertEquals 'Name is blank', 'blank', testSystemComponent.errors['name']
    }

    void testNameMinSize() {
        def testSystemComponent = new SystemComponent(name: 'a', system: system)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', testSystemComponent.validate()
        assertEquals 'Name is too short', 'minSize', testSystemComponent.errors['name']
    }

    void testNameMaxSize() {
        def testSystemComponent = new SystemComponent(name: 'a' * 51, system: system)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', testSystemComponent.validate()
        assertEquals 'Name is too long', 'maxSize', testSystemComponent.errors['name']
    }

    void testNameNullable() {
        def testSystemComponent = new SystemComponent(name: null, system: system)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', testSystemComponent.validate()
        assertEquals 'Name is null', 'nullable', testSystemComponent.errors['name']
    }

    void testNameUnique() {
        def testSystemComponent = new SystemComponent(name: 'SystemComponent', system: system)
        mockForConstraintsTests(SystemComponent, [testSystemComponent])

        assertFalse 'Should not be valid', systemComponent.validate()
        assertEquals 'Name is not unique.', 'unique', systemComponent.errors['name']

        systemComponent = new SystemComponent(name: 'SystemComponent2', system: system)
        assertTrue 'Should be valid', systemComponent.validate()
    }

    void testSystemNullable() {
        def testSystemEnvironment = new SystemEnvironment(name: 'SystemEnvironment', system: null)
        mockForConstraintsTests(SystemEnvironment, [testSystemEnvironment])

        assertFalse 'Should not be valid', testSystemEnvironment.validate()
        assertEquals 'Name is null', 'nullable', testSystemEnvironment.errors['system']
    }

    void testToString() {
        assertNotNull systemComponent.toString()
        assertTrue systemComponent.toString() ==~ /.*SystemComponent.*/
    }
}
