package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(ModuleDeploymentTestState)
@Build(ModuleDeploymentTestState)
class ModuleDeploymentTestStateTests {

    void testDescriptionMaxSize() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert ModuleDeploymentTestState.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ModuleDeploymentTestState.build(name: 'TestModuleDeploymentTestState')
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(name: 'TestModuleDeploymentTestState'), 'name', 'unique')
    }

    void testToString() {
        def domain = ModuleDeploymentTestState.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
