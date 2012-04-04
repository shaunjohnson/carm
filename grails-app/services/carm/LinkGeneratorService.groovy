package carm

import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class LinkGeneratorService {

    static transactional = false
    
    String createLink(attrs) {
        attrs.absolute = true
        new ApplicationTagLib().createLink(attrs)
    }
}
