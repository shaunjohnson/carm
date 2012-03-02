package carm.release

import carm.module.Module

class ModuleRelease {
    Module module

    Date dateCreated
    Date lastUpdated

    static constraints = {
        applicationRelease(nullable: false)
        module(nullable: false)
    }

    static belongsTo = [applicationRelease: ApplicationRelease]
}