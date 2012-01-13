package carm

import grails.test.*

class ModuleTests extends CarmUnitTestCase {

    void testApplicationNullable() {
        def module = Module.buildWithoutSave()
        module.application = null
        assertHasError(module, 'application', 'nullable')
    }

    void testDeployInstructionsNull() {
        assertTrue(Module.buildWithoutSave(deployInstructions: null).validate())
    }

    void testDescriptionMaxSize() {
        assertHasError(Module.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(Module.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(Module.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(Module.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(Module.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(Module.buildWithoutSave(name: null), 'name', 'nullable')
    }

    void testTypeNullable() {
        assertHasError(Module.buildWithoutSave(type: null), 'type', 'nullable')
    }

    void testToString() {
        def domain = Module.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
