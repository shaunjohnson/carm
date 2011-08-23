package net.lmxm.carm.domains

import net.lmxm.carm.enums.SourceControlServerType

class SourceControlServer {
    String name
    String description
    SourceControlServerType type

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
        type(null: false)
    }

    public String toString() {
        return "SourceControlServer [name='$name', type='$type']";
    }
}
