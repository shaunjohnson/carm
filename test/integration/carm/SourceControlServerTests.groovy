package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import carm.enums.SourceControlServerType
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SourceControlServer)
@Build(SourceControlServer)
class SourceControlServerTests {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlServer.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SourceControlServer.buildWithoutSave(description: null).validate()
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
        assert SourceControlServer.buildWithoutSave(url: 'https://bitbucket.org/lmxm/carm').validate()
        assert SourceControlServer.buildWithoutSave(url: 'ssh://bitbucket.org/lmxm/carm').validate()
    }
    void testUrlMaxSize() {
        assertHasError(SourceControlServer.buildWithoutSave(url: 'a' * 201), 'url', 'maxSize')
    }
    void testUrlNullable() {
        assert SourceControlServer.buildWithoutSave(url: null).validate()
    }
    void testUrlUrl() {
        assertHasError(SourceControlServer.buildWithoutSave(url: 'test'), 'url', 'url')
    }

    void testToString() {
        def domain = SourceControlServer.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
