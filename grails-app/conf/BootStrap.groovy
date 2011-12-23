import carm.Application
import carm.ApplicationType
import carm.Module
import carm.Project
import carm.System
import carm.SystemComponent
import carm.security.Role
import carm.SourceControlServer
import carm.security.User
import carm.security.UserRole
import carm.enums.SourceControlServerType
import carm.security.UserRole
import carm.security.User
import carm.SystemComponent
import carm.SystemEnvironment
import carm.ModuleType
import carm.SourceControlRepository
import carm.SourceControlUser
import carm.SourceControlRole
import carm.ApplicationRole
import carm.ApplicationRelease
import org.springframework.security.acls.domain.BasePermission
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import carm.ProjectCategory

class BootStrap {

    def aclUtilService
    def fixtureLoader

    def init = { servletContext ->
        def userFixture = fixtureLoader.load {
            adminRole(Role, authority: 'ROLE_ADMIN')
            userRole(Role, authority: 'ROLE_USER')

            adminUser(User, username: 'admin', enabled: true, password: 'admin')
            adminUserRole(UserRole, user: adminUser, role: adminRole)

            shaunUser(User, username: 'shaun', enabled: true, password: 'shaun')
            shaunUserRole(UserRole, user: shaunUser, role: adminRole)

            scottUser(User, username: 'scott', enabled: true, password: 'scott')
            scottUserRole(UserRole, user: scottUser, role: adminRole)

            userUser(User, username: 'user', enabled: true, password: 'user')
            userUserRole(UserRole, user: userUser, role: userRole)

            susanUser(User, username: 'susan', enabled: true, password: 'susam')
            susanUserRole(UserRole, user: susanUser, role: userRole)
        }

        SpringSecurityUtils.reauthenticate('admin', 'admin')

        def dataFixture = fixtureLoader.load {
            //
            // Application Types
            //
            databaseAppType(ApplicationType, name: 'Database Application', description: 'Database application')
            configAppType(ApplicationType, name: 'Configuration', description: 'Configuration application')
            enterpriseAppType(ApplicationType, name: 'Enterprise Application', description: 'Enterprise application')
            brokerAppType(ApplicationType, name: 'Broker Application', description: 'Broker application')
            portalAppType(ApplicationType, name: 'Portal Application', description: 'Portal application')
            standaloneAppType(ApplicationType, name: 'Standalone Application', description: 'Standalone application')

            //
            // Module Types
            //
            barModuleType(ModuleType, name: 'BAR', description: 'Broker archive')
            configModType(ModuleType, name: 'Configuration Files', description: 'Configuration files')
            ejbModType(ModuleType, name: 'EJB', description: 'Enterprise Java Bean')
            mdbModType(ModuleType, name: 'MDB', description: 'Message Driven Bean')
            portletModType(ModuleType, name: 'Portlet', description: 'Portlet')

            //
            // Project Categories
            //
            generalProjectCategory(ProjectCategory, name: 'General', description: 'General projects')
            otherProjectCategory(ProjectCategory, name: 'Other', description: 'Other projects')

            //
            // Source Control Servers
            //
            subversionServer(SourceControlServer) {
                name = 'Company SVN Server'
                description = 'Our company Subversion server.'
                type = SourceControlServerType.Subversion
                url = 'http://svn.company.com'
            }

            //
            // Source Control Repositories
            //
            subversionRepository(SourceControlRepository) {
                name = 'Big System SVN Repository'
                description = 'Big System Subversion repository.'
                path = '/bigrepo'
                server = subversionServer
            }

            //
            // Source Control Roles
            //
            developerScmRole(SourceControlRole, name: 'developer', description: 'Developer role')
            managerScmRole(SourceControlRole, name: 'manager', description: 'Manager role')

            //
            // Source Control Users
            //
            shaunScmUser(SourceControlUser) {
                name = 'shaun'
                description = 'The user named Shaun'
                sourceControlServer = subversionServer
                user = userFixture.shaunUser
            }

            susanScmUser(SourceControlUser) {
                name = 'susan'
                description = 'The user named Susan'
                sourceControlServer = subversionServer
                user = userFixture.susanUser
            }

            scottScmUser(SourceControlUser) {
                name = 'scott'
                description = 'The user named Scott'
                sourceControlServer = subversionServer
                user = userFixture.scottUser
            }

            //
            // Systems
            //
            bigSystem(System) {
                name = 'Big System'
                description = 'Big System'
                components = [
                        dataLayer(SystemComponent) {
                            name = 'Oracle 10g Database Server'
                            description = 'Oracle 10g database server that provides the data layer for Big System.'
                            system = bigSystem
                        },
                        integrationLayer(SystemComponent) {
                            name = 'WebSphere Broker 7.0'
                            description = 'WebSphere Broker 7.0 is used for the Big System integration layer.'
                            system = bigSystem
                        },
                        businessLayer(SystemComponent) {
                            name = 'WebSphere Application Server 7.0'
                            description = 'The Big System business layer runs on WAS 7.0.'
                            system = bigSystem
                        },
                        presentationLayer(SystemComponent) {
                            name = 'WebSphere Portal Server 7.0'
                            description = 'The Big System presentation layer is WebSphere Portal Server.'
                            system = bigSystem
                        }]
                environments = [
                        sandboxEnv(SystemEnvironment) {
                            name = 'Sandbox'
                            description = 'Sandbox is where applications are initially tested.'
                            system = bigSystem
                        },
                        integrationEnv(SystemEnvironment) {
                            name = 'Integration'
                            description = 'Integration is where applications are tested together.'
                            system = bigSystem
                        },
                        testEnv(SystemEnvironment) {
                            name = 'Test'
                            description = 'Application and user testing environment.'
                            system = bigSystem
                        },
                        stageEnv(SystemEnvironment) {
                            name = 'Stage'
                            description = 'Stage mimics production and is used for performance testing.'
                            system = bigSystem
                        },
                        trainEnv(SystemEnvironment) {
                            name = 'Train'
                            description = 'Train is where users are trained to use the applications.'
                            system = bigSystem
                        },
                        productionEnv(SystemEnvironment) {
                            name = 'Production'
                            description = 'Production is the end goal.'
                            system = bigSystem
                        }
                ]
            }

            standaloneSystem(System) {
                name = 'Standalone System'
                description 'Standalone applications system'
                components = [
                        standaloneComponent(SystemComponent) {
                            name = 'Standalone system component'
                            system = standaloneSystem
                        }
                ]
                environments = [
                        developmentStandaloneEnvironemnt(SystemEnvironment) {
                            name = 'Development (Standalone)'
                            description = 'Development system for standalone applications.'
                            system = standaloneSystem
                        },
                        testStandaloneEnvironemnt(SystemEnvironment) {
                            name = 'Test (Standalone)'
                            description = 'Test system for standalone applications.'
                            system = standaloneSystem
                        },
                        productionStandaloneEnvironemnt(SystemEnvironment) {
                            name = 'Production (Standalone)'
                            description = 'Production system for standalone applications.'
                            system = standaloneSystem
                        }
                ]
            }

            //
            // Configure Project with applications and modules
            //
            standaloneProject(Project) {
                name = 'Standalone Project'
                description = 'This is a small stadalone application project.'
                category = otherProjectCategory
                applications = [
                        standaloneApplication(Application) {
                            name = 'Standalone Application'
                            description = 'This is a small standalone application'
                            project = standaloneProject
                            type = standaloneAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/standalone'
                            system: standaloneSystem
                        }
                ]
            }

            myBigProject(Project) {
                name = 'Big Project'
                description = 'Big project'
                category = generalProjectCategory
                applications = [
                        dataApplication(Application) {
                            name = 'Database Scripts'
                            description = 'Big Project db scripts'
                            project = myBigProject
                            type = databaseAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/database'
                            system = bigSystem
                        },
                        configApplication(Application) {
                            name = 'Big Project Configuration'
                            description = 'Big Project configuration files'
                            project = myBigProject
                            type = configAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/config'
                            system = bigSystem
                            modules = [
                                    configModule(Module) {
                                        name = 'Configuration Files Module'
                                        description = 'Configuration files for the Big Project'
                                        application = configApplication
                                        type = configModType
                                        deployInstructions = 'Copy to file system.Restart server.'
                                        systemComponents = [
                                                businessLayer, integrationLayer, presentationLayer
                                        ]
                                    }
                            ]
                        },
                        earApplication(Application) {
                            name = 'Business Services EAR'
                            description = 'Big Project EAR application'
                            project = myBigProject
                            type = enterpriseAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/ear'
                            system = bigSystem
                            modules = [
                                    bussEjbModule(Module) {
                                        name = 'Business Services EJB'
                                        description = 'Business Services EJB module'
                                        application = earApplication
                                        type = ejbModType
                                        deployInstructions = 'Update existing application.\nRestart server.'
                                        systemComponents = [ businessLayer ]
                                    },
                                    transEjbModule(Module) {
                                        name = 'Transition Services EJB'
                                        description = 'Transition Services EJB module'
                                        application = earApplication
                                        type = ejbModType
                                        deployInstructions = 'Update existing application.\nRestart server.'
                                        systemComponents = [ businessLayer ]
                                    },
                                    bussMdbModule(Module) {
                                        name = 'Business Services MDB'
                                        description = 'Business Services MDB module'
                                        application = earApplication
                                        type = mdbModType
                                        deployInstructions = 'Update existing application.\nRestart server.'
                                        systemComponents = [ businessLayer ]
                                    }
                            ]
                        },
                        brokerApplication(Application) {
                            name = 'Business Services Broker'
                            description = 'Big Project broker aplication'
                            project = myBigProject
                            type = brokerAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/broker'
                            system = bigSystem
                            modules = [
                                    brokerModule(Module) {
                                        name = 'Broker BAR'
                                        description = 'Broker BAR module'
                                        application = brokerApplication
                                        type = barModuleType
                                        deployInstructions = 'Update existing BAR file.'
                                        systemComponents = [ integrationLayer ]
                                    }
                            ]
                            releases = [
                                    brokerRelease105(ApplicationRelease) {
                                        application = brokerApplication
                                        releaseNumber = '1.0.5'
                                        description = 'Release broker application to fix message flow issue.'
                                    }
                            ]
                        },
                        portalApplication(Application) {
                            name = 'Portal Application'
                            description = 'My Big Project portal application'
                            project = myBigProject
                            type = portalAppType
                            sourceControlRepository = subversionRepository
                            sourceControlPath = '/portal'
                            system = bigSystem
                            modules = [
                                    userPortletModule(Module) {
                                        name = 'User Portlet'
                                        description = 'Big Project User Portlet'
                                        application = portalApplication
                                        type = portletModType
                                        deployInstructions = 'Update existing application.'
                                        systemComponents = [ presentationLayer ]
                                    },
                                    adminPortletModule(Module) {
                                        name = 'Admin Portlet'
                                        description = 'Big Project Administration Portlet'
                                        application = portalApplication
                                        type = portletModType
                                        deployInstructions = 'Update existing application.'
                                        systemComponents = [ presentationLayer ]
                                    }
                            ]
                            releases = [
                                    portalRelease210(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.1.0'
                                        description = 'Release portal application to update to latest jQuery version.'
                                    }
                            ]
                        }
                ]
            }

            //
            // Application Roles
            //
            shaunDataAppDevRole(ApplicationRole) {
                application = dataApplication
                role = developerScmRole
                sourceControlUser = shaunScmUser
            }
            shaunEarAppDevRole(ApplicationRole) {
                application = earApplication
                role = developerScmRole
                sourceControlUser = shaunScmUser
            }
            shaunBrokerAppDevRole(ApplicationRole) {
                application = brokerApplication
                role = developerScmRole
                sourceControlUser = shaunScmUser
            }
            shaunPortalAppDevRole(ApplicationRole) {
                application = portalApplication
                role = developerScmRole
                sourceControlUser = shaunScmUser
            }

            susanDataAppDevRole(ApplicationRole) {
                application = dataApplication
                role = developerScmRole
                sourceControlUser = susanScmUser
            }
            susanEarAppDevRole(ApplicationRole) {
                application = earApplication
                role = developerScmRole
                sourceControlUser = susanScmUser
            }
            susanBrokerAppDevRole(ApplicationRole) {
                application = brokerApplication
                role = developerScmRole
                sourceControlUser = susanScmUser
            }
            susanPortalAppDevRole(ApplicationRole) {
                application = portalApplication
                role = developerScmRole
                sourceControlUser = susanScmUser
            }

            scottDataAppManagerRole(ApplicationRole) {
                application = dataApplication
                role = managerScmRole
                sourceControlUser = scottScmUser
            }
            scottEarAppManagerRole(ApplicationRole) {
                application = earApplication
                role = managerScmRole
                sourceControlUser = scottScmUser
            }
            scottBrokerAppManagerRole(ApplicationRole) {
                application = brokerApplication
                role = managerScmRole
                sourceControlUser = scottScmUser
            }
            scottPortalAppManagerRole(ApplicationRole) {
                application = portalApplication
                role = managerScmRole
                sourceControlUser = scottScmUser
            }
        }

        aclUtilService.addPermission(dataFixture.standaloneProject, 'shaun', BasePermission.ADMINISTRATION)

        aclUtilService.addPermission(dataFixture.myBigProject, 'shaun', BasePermission.ADMINISTRATION)
        aclUtilService.addPermission(dataFixture.myBigProject, 'scott', BasePermission.ADMINISTRATION)
    }

    def destroy = {

    }
}

