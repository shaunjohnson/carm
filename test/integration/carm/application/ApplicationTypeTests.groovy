package carm.application

import static carm.CarmUnitTestCase.assertHasError
import grails.test.GrailsUnitTestCase
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(ApplicationType)
@Build(ApplicationType)
class ApplicationTypeTests {

    void testDescriptionMaxSize() {
        assertHasError(ApplicationType.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert ApplicationType.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(ApplicationType.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ApplicationType.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ApplicationType.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ApplicationType.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ApplicationType.build(name: 'TestApplicationType')
        assertHasError(ApplicationType.buildWithoutSave(name: 'TestApplicationType'), 'name', 'unique')
    }

    void testToString() {
        def domain = ApplicationType.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
