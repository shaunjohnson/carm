package net.lmxm.carm

import grails.test.*
import net.lmxm.carm.enums.SourceControlServerType

class ModuleTests extends GrailsUnitTestCase {    
    def moduleType
    def project
    def sourceControlServer
    
    protected void setUp() {
        super.setUp()
        
        moduleType = new ModuleType(name: "JSF Portlet", description: "JSF Portlet Application")
        sourceControlServer = new SourceControlServer(name: 'Subversion', type: SourceControlServerType.Subversion)
        project = new Project(name: "Portal Project", description: "Generic Portal Project")
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testModule = new Module(name: 'Test Module', description: "a" * 4001, type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Description is too long', 'maxSize', testModule.errors['description']
    }

    void testDescriptionNullable() {
        def testModule = new Module(name: 'Test Module', description: null, type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertTrue 'Should be valid', testModule.validate()
    }

    void testNameBlank() {
        def testModule = new Module(name: '', type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is blank', 'blank', testModule.errors['name']
    }

    void testNameMinSize() {
        def testModule = new Module(name: 'a', type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is too short', 'minSize', testModule.errors['name']
    }

    void testNameMaxSize() {
        def testModule = new Module(name: 'a' * 51, type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is too long', 'maxSize', testModule.errors['name']
    }

    void testNameNullable() {
        def testModule = new Module(name: null, type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Name is null', 'nullable', testModule.errors['name']
    }

    void testProjectNullable() {
        def testModule = new Module(name: "Test Module", type: moduleType, project: null)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Project is null', 'nullable', testModule.errors['project']
    }

    void testTypeNullable() {
        def testModule = new Module(name: "Test Module", type: null, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Type is null', 'nullable', testModule.errors['type']
    }
    
    void testSourceControlNullable() {        
        def testModule = new Module(name: "Test Module", type: moduleType, project: project)
        mockForConstraintsTests(Module, [testModule])

        assertTrue 'Should be valid', testModule.validate()
        
        testModule.sourceControlServer = sourceControlServer
        assertTrue 'Should be valid', testModule.validate()
    }
    
    void testSourceControlPathMaxSize() {
        def testModule = new Module(name: 'Test Module', description: "description", type: moduleType, project: project,
            sourceControlServer: sourceControlServer, sourceControlPath: "a" * 201)
        mockForConstraintsTests(Module, [testModule])

        assertFalse 'Should not be valid', testModule.validate()
        assertEquals 'Source control path is too long', 'maxSize', testModule.errors['sourceControlPath']
    }

    void testSourceControlPathNullable() {
        def testModule = new Module(name: 'Test Module', description: "description", type: moduleType, project: project,
            sourceControlServer: sourceControlServer, sourceControlPath: null)
        mockForConstraintsTests(Module, [testModule])

        assertTrue 'Should be valid', testModule.validate()
    }

    void testToString() {
        def module = new Module(name: "Foobar")
        
        assertNotNull module.toString()
        assertTrue module.toString() ==~ /.*Foobar.*/
    }
}
