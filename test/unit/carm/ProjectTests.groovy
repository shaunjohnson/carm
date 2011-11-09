package carm

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
    def project
    
    protected void setUp() {
        super.setUp()
        
        mockForConstraintsTests(Project)

        project = new Project(name: 'Project')
        mockForConstraintsTests(Project, [project])
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testProject = new Project(name: 'Test Project', description: "a" * 4001)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Description is too long', 'maxSize', testProject.errors['description']
    }

    void testDescriptionNullable() {
        def testProject = new Project(name: 'Test Project', description: null)
        mockForConstraintsTests(Project, [testProject])

        assertTrue 'Should be valid', testProject.validate()
    }

    void testNameBlank() {
        def testProject = new Project(name: '')
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is blank', 'blank', testProject.errors['name']
    }

    void testNameMinSize() {
        def testProject = new Project(name: 'a')
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is too short', 'minSize', testProject.errors['name']
    }

    void testNameMaxSize() {
        def testProject = new Project(name: 'a' * 51)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is too long', 'maxSize', testProject.errors['name']
    }

    void testNameNullable() {
        def testProject = new Project(name: null)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is null', 'nullable', testProject.errors['name']
    }

    void testNameUnique() {
        def testProject = new Project(name: 'Project')
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', project.validate()
        assertEquals 'Name is not unique.', 'unique', project.errors['name']

        project = new Project(name: 'Project2')
        assertTrue 'Should be valid', project.validate()
    }

    void testToString() {
        assertNotNull project.toString()
        assertTrue project.toString() ==~ /.*Project.*/
    }
}
