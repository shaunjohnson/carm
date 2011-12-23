package carm

import grails.test.*

class ModuleTypeTests extends CarmUnitTestCase {

    void testDescriptionMaxSize() {
        assertHasError(ModuleType.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assertTrue(ModuleType.buildWithoutSave(description: null).validate())
    }

    void testNameBlank() {
        assertHasError(ModuleType.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ModuleType.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ModuleType.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ModuleType.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ModuleType.build(name: 'TestModuleType')
        assertHasError(ModuleType.buildWithoutSave(name: 'TestModuleType'), 'name', 'unique')
    }

    void testToString() {
        def domain = ModuleType.build(name: "Foobar")

        assertNotNull(domain.toString())
        assertTrue(domain.toString() ==~ /.*Foobar.*/)
    }
}
