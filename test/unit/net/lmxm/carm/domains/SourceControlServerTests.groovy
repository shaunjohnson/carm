package net.lmxm.carm.domains

import grails.test.*
import net.lmxm.carm.enums.SourceControlServerType

class SourceControlServerTests extends GrailsUnitTestCase {
    def server

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SourceControlServer)

        server = new SourceControlServer(name: 'Subversion', description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [server])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testServer = new SourceControlServer(name: 'Name', description: "a" * 4001, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Description is too long', 'maxSize', testServer.errors['description']
    }

    void testDescriptionNullable() {
        def testServer = new SourceControlServer(name: 'Name', description: null, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertTrue 'Should be valid', testServer.validate()
    }

    void testNameBlank() {
        def testServer = new SourceControlServer(name: '', description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is blank', 'blank', testServer.errors['name']
    }

    void testNameMinSize() {
        def testServer = new SourceControlServer(name: 'a', description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is too short', 'minSize', testServer.errors['name']
    }

    void testNameMaxSize() {
        def testServer = new SourceControlServer(name: 'a' * 51, description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is too long', 'maxSize', testServer.errors['name']
    }

    void testNameNullable() {
        def testServer = new SourceControlServer(name: null, description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is blank', 'nullable', testServer.errors['name']
    }

    void testNameUnique() {
        def testServer = new SourceControlServer(name: 'Subversion', description: 'Subversion server', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', server.validate()
        assertEquals 'Name is not unique.', 'unique', server.errors['name']

        server = new SourceControlServer(name: 'Subversion2', description: 'Subversion server', type: SourceControlServerType.Subversion)
        assertTrue 'Should be valid', server.validate()
    }

    void testTypeNullable() {
        def testServer = new SourceControlServer(name: "Test", description: 'Subversion server', type: null)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Type is blank', 'nullable', testServer.errors['type']
    }

    void testToString() {
        assertNotNull server.toString()
        assertTrue server.toString() ==~ /.*Subversion.*/
    }
}
