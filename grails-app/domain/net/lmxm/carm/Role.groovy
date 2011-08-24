package net.lmxm.carm

class Role {
    String authority
    Date dateCreated
    Date lastUpdated

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
