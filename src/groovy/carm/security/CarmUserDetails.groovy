package carm.security

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority

class CarmUserDetails extends GrailsUser {

    final String fullName

    CarmUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<GrantedAuthority> authorities, long id, String fullName) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.fullName = fullName
    }
}