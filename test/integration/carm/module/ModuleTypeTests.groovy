package carm.module

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(ModuleType)
@Build(ModuleType)
class ModuleTypeTests {

    void testDescriptionMaxSize() {
        assertHasError(ModuleType.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert ModuleType.buildWithoutSave(description: null).validate()
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

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
