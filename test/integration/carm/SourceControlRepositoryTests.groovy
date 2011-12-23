package carm

import grails.test.*

class SourceControlRepositoryTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlRepository.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(SourceControlRepository.buildWithoutSave(description: null).validate())
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

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
