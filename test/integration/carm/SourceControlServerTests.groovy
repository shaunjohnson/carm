package carm

import grails.test.*
import carm.enums.SourceControlServerType

class SourceControlServerTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlServer.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(SourceControlServer.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(SourceControlServer.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SourceControlServer.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SourceControlServer.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SourceControlServer.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SourceControlServer.build(name: 'TestSourceControlServer')
        assertHasError(SourceControlServer.buildWithoutSave(name: 'TestSourceControlServer'), 'name', 'unique')
    }

    void testTypeNullable() {
        assertHasError(SourceControlServer.buildWithoutSave(type: null), 'type', 'nullable')
    }

    void testUrl() {
        assertTrue(SourceControlServer.buildWithoutSave(url: 'https://bitbucket.org/lmxm/carm').validate())
        assertTrue(SourceControlServer.buildWithoutSave(url: 'ssh://bitbucket.org/lmxm/carm').validate())
    }
    void testUrlMaxSize() {
        assertHasError(SourceControlServer.buildWithoutSave(url: 'a' * 201), 'url', 'maxSize')
    }
    void testUrlNullable() {
        assertTrue(SourceControlServer.buildWithoutSave(url: null).validate())
    }
    void testUrlUrl() {
        assertHasError(SourceControlServer.buildWithoutSave(url: 'test'), 'url', 'url')
    }

    void testToString() {
        def domain = SourceControlServer.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
