import carm.security.CarmUserDetailsContextMapper
import carm.security.CarmPermissionEvaluator
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler

beans = {
    ldapUserDetailsMapper(CarmUserDetailsContextMapper) {
        springSecurityService = ref("springSecurityService")
    }

    permissionEvaluator(CarmPermissionEvaluator) { }

    expressionHandler(DefaultMethodSecurityExpressionHandler) {
        permissionEvaluator = ref("permissionEvaluator")
    }

    xmlns security: "http://www.springframework.org/schema/security"

    security.'global-method-security'('pre-post-annotations': 'enabled') {
        security.'expression-handler'(ref: 'expressionHandler')
    }
}
