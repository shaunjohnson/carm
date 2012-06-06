package carm.security

class AclService {

    static transactional = false

    /**
     * Adds a new group permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param userGroup UserGroup to be granted access
     * @param permission Permission to grant user
     */
    void addUserGroupPermission(domainObject, UserGroup userGroup, CarmPermission permission) {
        def prefix = "addUserGroupPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, userGroup=$userGroup, permission=$permission"

        AclEntity aclEntity = AclEntity.findOrSaveWhere([name: permission.generateName(domainObject.id)])

        if (AclGroupEntry.countByAclEntityAndUserGroup(aclEntity, userGroup) == 0) {
            AclGroupEntry groupEntry = new AclGroupEntry(aclEntity: aclEntity, userGroup: userGroup)
            aclEntity.addToGroupEntries(groupEntry)
            aclEntity.save()
        }

        log.debug "$prefix permission added. leaving."
    }

    /**
     * Adds a new user permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param user User to be granted access
     * @param permission Permission to grant user
     */
    void addUserPermission(domainObject, User user, CarmPermission permission) {
        def prefix = "addUserPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, user=$user, permission=$permission"

        AclEntity aclEntity = AclEntity.findOrSaveWhere([name: permission.generateName(domainObject.id)])

        if (AclUserEntry.countByAclEntityAndUser(aclEntity, user) == 0) {
            AclUserEntry userEntry = new AclUserEntry(aclEntity: aclEntity, user: user)
            aclEntity.addToUserEntries(userEntry)
            aclEntity.save()
        }

        log.debug "$prefix permission added. leaving."
    }

    /**
     * Deletes all ACLs for the provided domain object.
     *
     * @param domain Domain object for which ACLs are to be deleted
     * @param permission ACL permissions to delete
     */
    void deleteAllAclsByDomain(domain, CarmPermission permission) {
        AclEntity aclEntity = AclEntity.findByName(permission.generateName(domain.id))
        if (aclEntity) {
            AclGroupEntry.executeUpdate("delete from AclGroupEntry where aclEntity = :aclEntity", [aclEntity: aclEntity])
            AclUserEntry.executeUpdate("delete from AclUserEntry where aclEntity = :aclEntity", [aclEntity: aclEntity])
            aclEntity.delete()
        }
    }

    /**
     * Delete all ACLs for the provided User.
     *
     * @param user User for which ACLs are deleted
     */
    void deleteAllAclsByUser(User user) {
        AclUserEntry.executeUpdate("delete from AclUserEntry where user = :user", [user: user])
    }

    /**
     * Find all users for a specific domain object assigned the specific permission.
     *
     * @param domainObject Domain object for which ACLs are defined.
     * @param permission Permission used to filter ACLs
     * @return List of User objects
     */
    List<UserGroup> findAllGroupsByDomainAndPermission(domainObject, CarmPermission permission) {
        def prefix = "findAllUsersByDomainAndPermission() : "

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

        String entityName = permission.generateName(domainObject.id)

        def userGroups = AclGroupEntry.executeQuery("""
                    select
                        age.userGroup
                    from
                        AclGroupEntry age
                    where
                        age.aclEntity.name = :entityName
                    order by
                        age.userGroup.name
                """, [entityName: entityName])

        log.debug "$prefix returning ${userGroups.size()} users"

        userGroups
    }

    /**
     * Find all users for a specific domain object assigned the specific permission.
     *
     * @param domainObject Domain object for which ACLs are defined.
     * @param permission Permission used to filter ACLs
     * @return List of User objects
     */
    List<User> findAllUsersByDomainAndPermission(domainObject, CarmPermission permission) {
        def prefix = "findAllUsersByDomainAndPermission() : "

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

        String entityName = permission.generateName(domainObject.id)

        def users = AclUserEntry.executeQuery("""
                    select
                        aue.user
                    from
                        AclUserEntry aue
                    where
                        aue.aclEntity.name = :entityName
                    order by
                        aue.user.fullName
                """, [entityName: entityName])

        log.debug "$prefix returning ${users.size()} users"

        users
    }

    /**
     * Adds a new group permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param userGroup UserGroup to be granted access
     * @param permission Permission to grant user
     */
    void removeUserGroupPermission(domainObject, UserGroup userGroup, CarmPermission permission) {
        def prefix = "removeUserGroupPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, userGroup=$userGroup, permission=$permission"

        AclEntity aclEntity = AclEntity.findByName(permission.generateName(domainObject.id))
        AclGroupEntry.executeUpdate("""
            delete
                from AclGroupEntry
            where
                userGroup = :userGroup
                and aclEntity = :aclEntity
            """, [userGroup: userGroup, aclEntity: aclEntity])

        log.debug "$prefix permission removed. leaving."
    }

    /**
     * Adds a new user permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param user User to be granted access
     * @param permission Permission to grant user
     */
    void removeUserPermission(domainObject, User user, CarmPermission permission) {
        def prefix = "removeUserPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, user=$user, permission=$permission"

        AclEntity aclEntity = AclEntity.findByName(permission.generateName(domainObject.id))
        AclUserEntry.executeUpdate("""
            delete
                from AclUserEntry
            where
                user = :user
                and aclEntity = :aclEntity
            """, [user: user, aclEntity: aclEntity])

        log.debug "$prefix permission removed. leaving."
    }
}
