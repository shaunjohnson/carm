package carm

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
    def project
    def projectCategory
    
    protected void setUp() {
        super.setUp()
        
        mockForConstraintsTests(Project)

        projectCategory = new ProjectCategory(name: 'Project Category')
        mockForConstraintsTests(ProjectCategory, [projectCategory])

        project = new Project(name: 'Project', category: projectCategory)
        mockForConstraintsTests(Project, [project])
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testProject = new Project(name: 'Test Project', category: projectCategory, description: "a" * 4001)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Description is too long', 'maxSize', testProject.errors['description']
    }

    void testDescriptionNullable() {
        def testProject = new Project(name: 'Test Project', category: projectCategory, description: null)
        mockForConstraintsTests(Project, [testProject])

        assertTrue 'Should be valid', testProject.validate()
    }

    void testNameBlank() {
        def testProject = new Project(name: '', category: projectCategory)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is blank', 'blank', testProject.errors['name']
    }

    void testNameMinSize() {
        def testProject = new Project(name: 'a', category: projectCategory)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is too short', 'minSize', testProject.errors['name']
    }

    void testNameMaxSize() {
        def testProject = new Project(name: 'a' * 51, category: projectCategory)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is too long', 'maxSize', testProject.errors['name']
    }

    void testNameNullable() {
        def testProject = new Project(name: null, category: projectCategory)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', testProject.validate()
        assertEquals 'Name is null', 'nullable', testProject.errors['name']
    }

    void testNameUnique() {
        def testProject = new Project(name: 'Project', category: projectCategory)
        mockForConstraintsTests(Project, [testProject])

        assertFalse 'Should not be valid', project.validate()
        assertEquals 'Name is not unique.', 'unique', project.errors['name']

        project = new Project(name: 'Project2', category: projectCategory)
        assertTrue 'Should be valid', project.validate()
    }

    void testToString() {
        assertNotNull project.toString()
        assertTrue project.toString() ==~ /.*Project.*/
    }
}
