package carm.system

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(System)
@Build(System)
class SystemTests {

    void testDescriptionMaxSize() {
        assertHasError(System.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert System.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(System.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(System.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(System.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(System.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        System.build(name: 'TestSystem')
        assertHasError(System.buildWithoutSave(name: 'TestSystem'), 'name', 'unique')
    }

    void testToString() {
        def domain = System.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
