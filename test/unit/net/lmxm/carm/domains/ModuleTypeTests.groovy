package net.lmxm.carm.domains

import grails.test.*

class ModuleTypeTests extends GrailsUnitTestCase {
    def moduleType
    
    protected void setUp() {
        super.setUp()
        
        mockForConstraintsTests(ModuleType)

        moduleType = new ModuleType(name: 'Portlet')
        mockForConstraintsTests(ModuleType, [moduleType])
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testModuleType = new ModuleType(name: 'Test Type', description: "a" * 4001)
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', testModuleType.validate()
        assertEquals 'Description is too long', 'maxSize', testModuleType.errors['description']
    }

    void testDescriptionNullable() {
        def testModuleType = new ModuleType(name: 'Test Type', description: null)
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertTrue 'Should be valid', testModuleType.validate()
    }

    void testNameBlank() {
        def testModuleType = new ModuleType(name: '')
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', testModuleType.validate()
        assertEquals 'Name is blank', 'blank', testModuleType.errors['name']
    }

    void testNameMinSize() {
        def testModuleType = new ModuleType(name: 'a')
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', testModuleType.validate()
        assertEquals 'Name is too short', 'minSize', testModuleType.errors['name']
    }

    void testNameMaxSize() {
        def testModuleType = new ModuleType(name: 'a' * 51)
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', testModuleType.validate()
        assertEquals 'Name is too long', 'maxSize', testModuleType.errors['name']
    }

    void testNameNullable() {
        def testModuleType = new ModuleType(name: null)
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', testModuleType.validate()
        assertEquals 'Name is null', 'nullable', testModuleType.errors['name']
    }

    void testNameUnique() {
        def testModuleType = new ModuleType(name: 'Portlet')
        mockForConstraintsTests(ModuleType, [testModuleType])

        assertFalse 'Should not be valid', moduleType.validate()
        assertEquals 'Name is not unique.', 'unique', moduleType.errors['name']

        moduleType = new ModuleType(name: 'Portlet2')
        assertTrue 'Should be valid', moduleType.validate()
    }

    void testToString() {
        assertNotNull moduleType.toString()
        assertTrue moduleType.toString() ==~ /.*Portlet.*/
    }
}
