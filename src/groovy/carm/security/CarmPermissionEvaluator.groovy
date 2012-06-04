package carm.security

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.transaction.annotation.Transactional
import carm.exceptions.PermissionNotDefinedException
import org.springframework.security.core.userdetails.UserDetails

class CarmPermissionEvaluator implements PermissionEvaluator {
    @Override
    @Transactional
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean hasPermission = false
        if (canHandle(authentication, targetDomainObject, permission)) {
            hasPermission = checkPermission(authentication, targetDomainObject, (String) permission)
        }
        return hasPermission
    }

    private boolean canHandle(Authentication authentication, Object targetDomainObject, Object permission) {
        return targetDomainObject != null && authentication != null && permission instanceof String
    }

    private boolean checkPermission(Authentication authentication, Object targetDomainObject, String permissionKey) {
        String entityName = permissionKey + "_" + targetDomainObject.id

        if (authentication?.principal instanceof UserDetails) {
            String username = ((UserDetails) authentication.principal).username
            Serializable userId = User.findByUsername(username)?.id

            int userCount = AclEntity.executeQuery("""
                    select
                        count(*)
                    from
                        AclEntity e
                        join e.userEntries userEntry
                    where
                        e.name = :entityName
                        and userEntry.user.id = :userId
                """, [entityName: entityName, userId: userId])[0] as int

            if (userCount) {
                return true
            }

            int groupCount = AclEntity.executeQuery("""
                    select
                        count(*)
                    from
                        AclEntity e
                        join e.groupEntries groupEntry
                        join groupEntry.userGroup userGroup
                        join userGroup.users user
                    where
                        e.name = :entityName
                        and user.id = :userId
                """, [entityName: entityName, userId: userId])[0] as int

            if (groupCount) {
                return true
            }
        }

        return false

//        UserGroup userGroup = UserGroup.findByName(groupName)
//
//        if (userGroup) {
//            println "User Group found " + groupName
//
//            if (authentication?.principal instanceof UserDetails) {
//                String username = ((UserDetails) authentication.principal).username
//                User user = User.findByUsername(username)
//
//                return userGroup.users.contains(user)
//            }
//        }
//
//        return false
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new PermissionNotDefinedException("Id and Class permissions are not supperted by " + this.getClass().toString())
    }
}
