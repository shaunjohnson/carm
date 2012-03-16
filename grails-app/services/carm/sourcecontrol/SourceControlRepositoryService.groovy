package carm.sourcecontrol

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.application.Application
import carm.exceptions.DomainInUseException

class SourceControlRepositoryService {

    static transactional = false

    def grailsApplication

    /**
     * Determines if the provided repository is in use.
     *
     * @param repository Repository to test
     * @return True if the repository is in use
     */
    boolean isInUse(SourceControlRepository repository) {
        Application.countBySourceControlRepository(repository) > 0
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
        def prefix = "delete() :"

        log.debug "$prefix entered, sourceControlRepository=$sourceControlRepository"

        if (isInUse(sourceControlRepository)) {
            log.error "$prefix Source control repository is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        sourceControlRepository.delete()

        log.debug "$prefix leaving"
    }

    /**
     * Gets the SourceControlRepository object with the provided ID.
     *
     * @param id ID of SourceControlRepository object
     * @return Matching SourceControlRepository object
     */
    SourceControlRepository get(Serializable id) {
        SourceControlRepository.get(id)
    }

    /**
     * Gets a list of all SourceControlRepository objects.
     *
     * @param params Query parameters
     * @return List of SourceControlRepository objects
     */
    List<SourceControlRepository> list(Map params) {
        SourceControlRepository.list([
                max: grailsApplication.config.ui.sourceControlRepository.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
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