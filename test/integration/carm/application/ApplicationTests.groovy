package carm.application

import static carm.CarmUnitTestCase.assertHasError
import grails.test.*
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

@TestFor(Application)
@Build(Application)
class ApplicationTests {
    void testBuildInstructionsNullable() {
        assert Application.buildWithoutSave(buildInstructions: null).validate()
    }

    void testDeployInstructionsNullable() {
        assert Application.buildWithoutSave(deployInstructions: null).validate()
    }

    void testDescriptionMaxSize() {
        assertHasError(Application.buildWithoutSave(description: 'a' * 4001), 'description', 'maxSize')
    }
    void testDescriptionNullable() {
        assert Application.buildWithoutSave(description: null).validate()
    }

    void testNameBlank() {
        assertHasError(Application.buildWithoutSave(name: ''), 'name', 'blank')
    }
    void testNameMinSize() {
        assertHasError(Application.buildWithoutSave(name: 'a'), 'name', 'minSize')
    }
    void testNameMaxSize() {
        assertHasError(Application.buildWithoutSave(name: 'a' * 51), 'name', 'maxSize')
    }
    void testNameNullable() {
        assertHasError(Application.buildWithoutSave(name: null), 'name', 'nullable')
    }
    void testNameUnique() {
        Application.build(name: 'TestApplication')
        assertHasError(Application.buildWithoutSave(name: 'TestApplication'), 'name', 'unique')
    }

    void testSourceControlRepositoryNullable() {
        assertHasError(Application.buildWithoutSave(sourceControlRepository: null), 'sourceControlRepository', 'nullable')
    }

    void testSourceControlPathMaxSize() {
        assertHasError(Application.buildWithoutSave(sourceControlPath: 'a' * 201), 'sourceControlPath', 'maxSize')
    }

    void testSystemNullable() {
        assert Application.buildWithoutSave(system: null).validate()
    }

    void testTypeNullable() {
        assertHasError(Application.buildWithoutSave(type: null), 'type', 'nullable')
    }

    void testToString() {
        def domain = Application.build(name: "Foobar")

        assert domain.toString() != null
        assert domain.toString() ==~ /.*Foobar.*/
    }
}
