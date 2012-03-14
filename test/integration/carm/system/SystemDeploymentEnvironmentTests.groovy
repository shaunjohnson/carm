package carm.system

import static carm.CarmUnitTestCase.assertHasError

import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SystemDeploymentEnvironment)
@Build(SystemDeploymentEnvironment)
class SystemDeploymentEnvironmentTests {

    void testDescriptionMaxSize() {
        assertHasError(SystemDeploymentEnvironment.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SystemDeploymentEnvironment.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SystemDeploymentEnvironment.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SystemDeploymentEnvironment.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SystemDeploymentEnvironment.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SystemDeploymentEnvironment.buildWithoutSave(name: null), 'name', 'nullable')
    }

    void testSystemNullable() {
        def domain = SystemDeploymentEnvironment.buildWithoutSave()
        domain.system = null
        assertHasError(domain, 'system', 'nullable')
    }

    void testToString() {
        def domain = SystemDeploymentEnvironment.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
