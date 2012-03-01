package carm.system

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SystemComponent)
@Build(SystemComponent)
class SystemComponentTests {

    void testDescriptionMaxSize() {
        assertHasError(SystemComponent.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SystemComponent.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SystemComponent.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SystemComponent.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SystemComponent.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SystemComponent.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SystemComponent.build(name: 'TestSystemComponent', system: System.build(name: 'TestSystem'))
        assertHasError(SystemComponent.buildWithoutSave(name: 'TestSystemComponent'), 'name', 'unique')
    }

    void testSystemNullable() {
        def domain = SystemComponent.buildWithoutSave()
        domain.system = null
        assertHasError(domain, 'system', 'nullable')
    }

    void testToString() {
        def domain = SystemComponent.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
