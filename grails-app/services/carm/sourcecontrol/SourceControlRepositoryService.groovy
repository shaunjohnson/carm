package carm.sourcecontrol

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.application.Application

class SourceControlRepositoryService {

    static transactional = false

    /**
     * Determines if the provided repository is in use.
     *
     * @param repository Repository to test
     * @return True if the repository is in use
     */
    boolean isInUse(SourceControlRepository repository) {
        Application.findAllBySourceControlRepository(repository).size() > 0
    }

    /**
     * Returns a count of all SourceControlRepository objects.
     *
     * @return Total number of SourceControlRepository objects.
     */
    int count() {
        SourceControlRepository.count()
    }

    /**
     * Creates and saves a new SourceControlRepository instance.
     *
     * @param params SourceControlRepository properties
     * @return newly created SourceControlRepository object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SourceControlRepository create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SourceControlRepository sourceControlRepository = new SourceControlRepository(params)
        sourceControlRepository.save()

        log.debug "$prefix returning $sourceControlRepository"

        sourceControlRepository
    }

    /**
     * Deletes the provided SourceControlRepository object.
     *
     * @param sourceControlRepository SourceControlRepository object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlRepository sourceControlRepository) {
        sourceControlRepository.delete()
    }

    /**
     * Gets the SourceControlRepository object with the provided ID.
     *
     * @param id ID of SourceControlRepository object
     * @return Matching SourceControlRepository object
     */
    SourceControlRepository get(long id) {
        SourceControlRepository.get(id)
    }

    /**
     * Gets a list of all SourceControlRepository objects.
     *
     * @param params Query parameters
     * @return List of SourceControlRepository objects
     */
    List<SourceControlRepository> list(Map params) {
        SourceControlRepository.list(params)
    }

    /**
     * Updates the provided SourceControlRepository object with the new properties.
     *
     * @param sourceControlRepository SourceControlRepository to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlRepository sourceControlRepository, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlRepository.properties = params

        log.debug "$prefix leaving"
    }
}