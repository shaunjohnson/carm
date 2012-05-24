package carm.security

import org.springframework.security.ldap.userdetails.UserDetailsContextMapper
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CarmUserDetailsContextMapper implements UserDetailsContextMapper {

    private static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

    def springSecurityService

    @Override
    public CarmUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<GrantedAuthority> authority) {
        User.withTransaction() {
            User user = User.findByUsername(username)

            if (!user) {
                user = new User(
                        username: ctx.originalAttrs.attrs['uid'].values[0].toString().toLowerCase(),
                        enabled: true,
                        fullName: ctx.originalAttrs.attrs['cn'].values[0],
                        password: "password",
                        email: ctx.originalAttrs.attrs['mail'].values[0].toString().toLowerCase())

                if (!user.save()) {
                    user.errors.each {
                        println it
                    }
                }

                UserRole userRole = new UserRole(user: user, role: Role.findByAuthority('ROLE_USER'))

                if (!userRole.save(flush: true)) {
                    user.errors.each {
                        println it
                    }
                }
            }
            else {
                // Update existing user account with latest full name and email address from LDAP
                user.fullName = ctx.originalAttrs.attrs['cn'].values[0]
                user.email = ctx.originalAttrs.attrs['mail'].values[0].toString().toLowerCase()
            }

            def userDetails = new CarmUserDetails(username, user.password, user.enabled, false, false, false,
                    authority ?: NO_ROLES, user.id, username)

            return userDetails
        }
    }

    @Override
    public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {
        throw new IllegalStateException("Only retrieving data from LDAP is currently supported")
    }
}