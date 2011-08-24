package net.lmxm.carm

import grails.test.*
import net.lmxm.carm.enums.SourceControlServerType

class SourceControlServerTests extends GrailsUnitTestCase {
    def server

    protected void setUp() {
        super.setUp()

        mockForConstraintsTests(SourceControlServer)

        server = new SourceControlServer(name: 'Subversion', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [server])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDescriptionMaxSize() {
        def testServer = new SourceControlServer(name: 'Test Server', description: "a" * 4001, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Description is too long', 'maxSize', testServer.errors['description']
    }

    void testDescriptionNullable() {
        def testServer = new SourceControlServer(name: 'Test Server', description: null, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertTrue 'Should be valid', testServer.validate()
    }

    void testNameBlank() {
        def testServer = new SourceControlServer(name: '', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is blank', 'blank', testServer.errors['name']
    }

    void testNameMinSize() {
        def testServer = new SourceControlServer(name: 'a', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is too short', 'minSize', testServer.errors['name']
    }

    void testNameMaxSize() {
        def testServer = new SourceControlServer(name: 'a' * 51, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is too long', 'maxSize', testServer.errors['name']
    }

    void testNameNullable() {
        def testServer = new SourceControlServer(name: null, type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is null', 'nullable', testServer.errors['name']
    }

    void testNameUnique() {
        def testServer = new SourceControlServer(name: 'Subversion', type: SourceControlServerType.Subversion)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', server.validate()
        assertEquals 'Name is not unique.', 'unique', server.errors['name']

        server = new SourceControlServer(name: 'Subversion2', type: SourceControlServerType.Subversion)
        assertTrue 'Should be valid', server.validate()
    }

    void testToString() {
        assertNotNull server.toString()
        assertTrue server.toString() ==~ /.*Subversion.*/
    }

    void testTypeNullable() {
        def testServer = new SourceControlServer(name: "Test Server", type: null)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Type is blank', 'nullable', testServer.errors['type']
    }

    void testUrlNullable() {
        def testServer = new SourceControlServer(name: "Test Server", type: SourceControlServerType.Subversion, url: null)
        mockForConstraintsTests(SourceControlServer, [testServer])

        assertTrue 'Should be valid', testServer.validate()
        
        testServer.url = "test"
        assertFalse 'Should not be valid', testServer.validate()
        assertEquals 'Name is blank', 'url', testServer.errors['url']
                
        testServer.url = "https://bitbucket.org/lmxm/carm"
        assertTrue 'Should be valid', testServer.validate()
        
        testServer.url = "ssh://bitbucket.org/lmxm/carm"
        assertTrue 'Should be valid', testServer.validate()
    }
}
