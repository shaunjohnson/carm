package carm.project

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(ProjectCategory)
@Build(ProjectCategory)
class ProjectCategoryTests {

    void testDescriptionMaxSize() {
        assertHasError(ProjectCategory.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert ProjectCategory.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(ProjectCategory.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(ProjectCategory.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(ProjectCategory.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(ProjectCategory.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        ProjectCategory.build(name: 'TestProjectCategory')
        assertHasError(ProjectCategory.buildWithoutSave(name: 'TestProjectCategory'), 'name', 'unique')
    }

    void testToString() {
        def domain = ProjectCategory.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
