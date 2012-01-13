package carm

import grails.test.*

class ModuleDeploymentTestStateTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(ModuleDeploymentTestState.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(ModuleDeploymentTestState.buildWithoutSave(description: null).validate())
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

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
