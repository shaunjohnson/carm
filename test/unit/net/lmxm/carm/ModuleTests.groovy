package net.lmxm.carm

import grails.test.*

class ModuleTests extends GrailsUnitTestCase {    
    def moduleType
    def application
    
    protected void setUp() {
        super.setUp()
        
        moduleType = new ModuleType(name: "JSF Portlet", description: "JSF Portlet Application")
        application = new Application(name: "Portal Application", description: "Generic Portal Application")
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testModule = new Module(name: 'Test Module', description: "a" * 4001, type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Description is too long', 'maxSize', testModule.errors['description']
    }

    void testDescriptionNullable() {
        def testModule = new Module(name: 'Test Module', description: null, type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertTrue 'Should be valid', testModule.validate()
    }

    void testNameBlank() {
        def testModule = new Module(name: '', type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is blank', 'blank', testModule.errors['name']
    }

    void testNameMinSize() {
        def testModule = new Module(name: 'a', type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is too short', 'minSize', testModule.errors['name']
    }

    void testNameMaxSize() {
        def testModule = new Module(name: 'a' * 51, type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is too long', 'maxSize', testModule.errors['name']
    }

    void testNameNullable() {
        def testModule = new Module(name: null, type: moduleType, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is null', 'nullable', testModule.errors['name']
    }

    void testApplicationNullable() {
        def testModule = new Module(name: "Test Module", type: moduleType, application: null)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Application is null', 'nullable', testModule.errors['application']
    }

    void testTypeNullable() {
        def testModule = new Module(name: "Test Module", type: null, application: application)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Type is null', 'nullable', testModule.errors['type']
    }
    
    void testToString() {
        def module = new Module(name: "Foobar")
        
        assertNotNull module.toString()
        assertTrue module.toString() ==~ /.*Foobar.*/
    }
}
