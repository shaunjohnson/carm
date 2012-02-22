package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(Module)
@Build(Module)
class ModuleTests {
    void testApplicationNullable() {
        def module = Module.buildWithoutSave()
        module.application = null
        assertHasError(module, 'application', 'nullable')
    }

    void testDeployInstructionsNull() {
        assert Module.buildWithoutSave(deployInstructions: null).validate()
    }

    void testDescriptionMaxSize() {
        assertHasError(Module.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }

    void testDescriptionNullable() {
        assert Module.buildWithoutSave(description: null).validate()
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

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
