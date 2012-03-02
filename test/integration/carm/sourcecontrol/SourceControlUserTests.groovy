package carm.sourcecontrol

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(SourceControlUser)
@Build(SourceControlUser)
class SourceControlUserTests {

    void testDescriptionMaxSize() {
        assertHasError(SourceControlUser.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert SourceControlUser.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(SourceControlUser.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(SourceControlUser.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(SourceControlUser.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(SourceControlUser.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        SourceControlUser.build(name: 'TestUser')
        assertHasError(SourceControlUser.buildWithoutSave(name: 'TestUser'), 'name', 'unique')
    }

    void testToString() {
        def domain = SourceControlUser.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
