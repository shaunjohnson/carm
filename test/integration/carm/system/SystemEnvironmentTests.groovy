package carm.system

import static carm.CarmUnitTestCase.assertHasError

import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SystemEnvironment)
@Build(SystemEnvironment)
class SystemEnvironmentTests {

    void testDescriptionMaxSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SystemEnvironment.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SystemEnvironment.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SystemEnvironment.build(name: 'TestSystem')
        assertHasError(SystemEnvironment.buildWithoutSave(name: 'TestSystem'), 'name', 'unique')
    }

    void testToString() {
        def domain = SystemEnvironment.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
