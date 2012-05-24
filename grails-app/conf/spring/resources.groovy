import carm.security.CarmUserDetailsContextMapper

beans = {
    ldapUserDetailsMapper(CarmUserDetailsContextMapper) {
        springSecurityService = ref("springSecurityService")
    }
}