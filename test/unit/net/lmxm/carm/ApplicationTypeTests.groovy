package net.lmxm.carm

import grails.test.GrailsUnitTestCase

class ApplicationTypeTests extends GrailsUnitTestCase {
    def applicationType
    
    protected void setUp() {
        super.setUp()
        
        mockForConstraintsTests(ApplicationType)

        applicationType = new ApplicationType(name: 'Portlet')
        mockForConstraintsTests(ApplicationType, [applicationType])
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testDescriptionMaxSize() {
        def testApplicationType = new ApplicationType(name: 'Test Type', description: "a" * 4001)
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', testApplicationType.validate()
        assertEquals 'Description is too long', 'maxSize', testApplicationType.errors['description']
    }

    void testDescriptionNullable() {
        def testApplicationType = new ApplicationType(name: 'Test Type', description: null)
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertTrue 'Should be valid', testApplicationType.validate()
    }

    void testNameBlank() {
        def testApplicationType = new ApplicationType(name: '')
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', testApplicationType.validate()
        assertEquals 'Name is blank', 'blank', testApplicationType.errors['name']
    }

    void testNameMinSize() {
        def testApplicationType = new ApplicationType(name: 'a')
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', testApplicationType.validate()
        assertEquals 'Name is too short', 'minSize', testApplicationType.errors['name']
    }

    void testNameMaxSize() {
        def testApplicationType = new ApplicationType(name: 'a' * 51)
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', testApplicationType.validate()
        assertEquals 'Name is too long', 'maxSize', testApplicationType.errors['name']
    }

    void testNameNullable() {
        def testApplicationType = new ApplicationType(name: null)
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', testApplicationType.validate()
        assertEquals 'Name is null', 'nullable', testApplicationType.errors['name']
    }

    void testNameUnique() {
        def testApplicationType = new ApplicationType(name: 'Portlet')
        mockForConstraintsTests(ApplicationType, [testApplicationType])

        assertFalse 'Should not be valid', applicationType.validate()
        assertEquals 'Name is not unique.', 'unique', applicationType.errors['name']

        applicationType = new ApplicationType(name: 'Portlet2')
        assertTrue 'Should be valid', applicationType.validate()
    }

    void testToString() {
        assertNotNull applicationType.toString()
        assertTrue applicationType.toString() ==~ /.*Portlet.*/
    }
}
