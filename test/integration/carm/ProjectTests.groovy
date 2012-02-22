package carm

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock

@TestFor(Project)
@Build(Project)
class ProjectTests {

    void testDescriptionMaxSize() {
        assertHasError(Project.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert Project.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(Project.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(Project.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(Project.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(Project.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        Project.build(name: 'TestProject')
        assertHasError(Project.buildWithoutSave(name: 'TestProject'), 'name', 'unique')
    }

    void testToString() {
        def domain = Project.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
