package carm.system

import static carm.CarmUnitTestCase.assertHasError

import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SystemServer)
@Build(SystemServer)
class SystemServerTests {

    void testDescriptionMaxSize() {
        assertHasError(SystemServer.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SystemServer.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SystemServer.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SystemServer.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SystemServer.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SystemServer.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SystemServer.build(name: 'TestSystemComponent', system: SystemEnvironment.build(name: 'TestSystem'))
        assertHasError(SystemServer.buildWithoutSave(name: 'TestSystemComponent'), 'name', 'unique')
    }

    void testSystemNullable() {
        def domain = SystemServer.buildWithoutSave()
        domain.system = null
        assertHasError(domain, 'system', 'nullable')
    }

    void testToString() {
        def domain = SystemServer.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
