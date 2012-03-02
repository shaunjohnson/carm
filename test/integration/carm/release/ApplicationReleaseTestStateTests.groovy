package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(ApplicationReleaseTestState)
@Build(ApplicationReleaseTestState)
class ApplicationReleaseTestStateTests {

    void testDescriptionMaxSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert ApplicationReleaseTestState.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ApplicationReleaseTestState.build(name: 'TestApplicationReleaseTestState')
        assertHasError(ApplicationReleaseTestState.buildWithoutSave(name: 'TestApplicationReleaseTestState'), 'name', 'unique')
    }

    void testToString() {
        def domain = ApplicationReleaseTestState.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
