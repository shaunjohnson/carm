package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SourceControlRepository)
@Build(SourceControlRepository)
class SourceControlRepositoryTests {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlRepository.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SourceControlRepository.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SourceControlRepository.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SourceControlRepository.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SourceControlRepository.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SourceControlRepository.buildWithoutSave(name: null), 'name', 'nullable')
    }

    void testPathBlank() {
        assertHasError(SourceControlRepository.buildWithoutSave(path: ''), 'path', 'blank')
    }
    void testPathMaxSize() {
        assertHasError(SourceControlRepository.buildWithoutSave(path: 'a' * 201), 'path', 'maxSize')
    }

    void testSourceControlServerNullable() {
        def domain = SourceControlRepository.buildWithoutSave()
        domain.server = null
        assertHasError(domain, 'server', 'nullable')
    }

    void testToString() {
        def domain = SourceControlRepository.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
