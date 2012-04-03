import carm.application.Application
import carm.deployment.ApplicationDeployment
import carm.release.ApplicationReleaseState
import carm.release.ApplicationReleaseTestState
import carm.application.ApplicationType
import carm.module.Module
import carm.deployment.ModuleDeployment
import carm.deployment.ModuleDeploymentTestState
import carm.release.ModuleRelease
import carm.project.Project
import carm.system.SystemEnvironment
import carm.system.SystemServer
import carm.security.Role
import carm.sourcecontrol.SourceControlServer
import carm.security.User
import carm.security.UserRole
import carm.sourcecontrol.SourceControlServerType
import carm.security.UserRole
import carm.security.User
import carm.system.SystemServer
import carm.system.SystemDeploymentEnvironment
import carm.module.ModuleType
import carm.sourcecontrol.SourceControlRepository
import carm.sourcecontrol.SourceControlUser
import carm.sourcecontrol.SourceControlRole
import carm.application.ApplicationRole
import carm.release.ApplicationRelease
import org.springframework.security.acls.domain.BasePermission
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import carm.project.ProjectCategory
import carm.system.SystemServer
import carm.system.SystemDeploymentEnvironment
import carm.system.SystemEnvironment

class BootStrap {

    def aclUtilService
    def fixtureLoader
    def applicationService
    def mailService

    def init = { servletContext ->
        def roleAdmin
        
        if (!Role.findByAuthority('ROLE_ADMIN')) {
            roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        }

        if (!Role.findByAuthority('ROLE_USER')) {
            new Role(authority: 'ROLE_USER').save()
        }

        if (!User.findByUsername('admin')) {
            def adminUser = new User(username: 'admin', enabled: true, password: 'admin', email: "admin@localhost.com")
            adminUser.save()

            new UserRole(user: adminUser, role: roleAdmin).save()
        }

        // Return immediately to disable bootstrapping data
        return

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
            // Application Release Test States
            //
            notTestedAppReleaseTestState(ApplicationReleaseTestState, name: 'Not Tested', description: 'Application release was not tested')
            testedErrorsFoundAppReleaseTestState(ApplicationReleaseTestState, name: 'Tested, errors found', description: 'Application release tested and errors were found')
            testedNoErrorsAppReleaseTestState(ApplicationReleaseTestState, name: 'Tested, no errors', description: 'Application release tested and no errors were found')

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
            // Module Deployment Test States
            //
            notTestedModuleDeploymentTestState(ModuleDeploymentTestState, name: 'Not Tested', description: 'Application deployment was not tested')
            testedErrorsFoundModuleDeploymentTestState(ModuleDeploymentTestState, name: 'Tested, errors found', description: 'Application release tested and errors were found')
            testedNoErrorsModuleDeploymentTestState(ModuleDeploymentTestState, name: 'Tested, no errors', description: 'Application release tested and no errors were found')

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
                name = 'Big SystemEnvironment SVN Repository'
                description = 'Big SystemEnvironment Subversion repository.'
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
                server = subversionServer
                user = userFixture.shaunUser
            }

            susanScmUser(SourceControlUser) {
                name = 'susan'
                description = 'The user named Susan'
                server = subversionServer
                user = userFixture.susanUser
            }

            scottScmUser(SourceControlUser) {
                name = 'scott'
                description = 'The user named Scott'
                server = subversionServer
                user = userFixture.scottUser
            }

            //
            // Systems
            //
            bigSystem(SystemEnvironment) {
                name = 'Big SystemEnvironment'
                description = 'Big SystemEnvironment'
                servers = [
                        dataLayer(SystemServer) {
                            name = 'Oracle 10g Database Server'
                            description = 'Oracle 10g database server that provides the data layer for Big SystemEnvironment.'
                            system = bigSystem
                        },
                        integrationLayer(SystemServer) {
                            name = 'WebSphere Broker 7.0'
                            description = 'WebSphere Broker 7.0 is used for the Big SystemEnvironment integration layer.'
                            system = bigSystem
                        },
                        businessLayer(SystemServer) {
                            name = 'WebSphere Application Server 7.0'
                            description = 'The Big SystemEnvironment business layer runs on WAS 7.0.'
                            system = bigSystem
                        },
                        presentationLayer(SystemServer) {
                            name = 'WebSphere Portal Server 7.0'
                            description = 'The Big SystemEnvironment presentation layer is WebSphere Portal Server.'
                            system = bigSystem
                        }]
                environments = [
                        sandboxEnv(SystemDeploymentEnvironment) {
                            name = 'Sandbox'
                            description = 'Sandbox is where applications are initially tested.'
                            system = bigSystem
                        },
                        integrationEnv(SystemDeploymentEnvironment) {
                            name = 'Integration'
                            description = 'Integration is where applications are tested together.'
                            system = bigSystem
                        },
                        testEnv(SystemDeploymentEnvironment) {
                            name = 'Test'
                            description = 'Application and user testing environment.'
                            system = bigSystem
                        },
                        stageEnv(SystemDeploymentEnvironment) {
                            name = 'Stage'
                            description = 'Stage mimics production and is used for performance testing.'
                            system = bigSystem
                        },
                        trainEnv(SystemDeploymentEnvironment) {
                            name = 'Train'
                            description = 'Train is where users are trained to use the applications.'
                            system = bigSystem
                        },
                        productionEnv(SystemDeploymentEnvironment) {
                            name = 'Production'
                            description = 'Production is the end goal.'
                            system = bigSystem
                        }
                ]
            }

            standaloneSystem(SystemEnvironment) {
                name = 'Standalone SystemEnvironment'
                description 'Standalone applications system'
                servers = [
                        standaloneServer(SystemServer) {
                            name = 'Standalone system server'
                            system = standaloneSystem
                        }
                ]
                environments = [
                        developmentStandaloneEnvironemnt(SystemDeploymentEnvironment) {
                            name = 'Development (Standalone)'
                            description = 'Development system for standalone applications.'
                            system = standaloneSystem
                        },
                        testStandaloneEnvironemnt(SystemDeploymentEnvironment) {
                            name = 'Test (Standalone)'
                            description = 'Test system for standalone applications.'
                            system = standaloneSystem
                        },
                        productionStandaloneEnvironemnt(SystemDeploymentEnvironment) {
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
                                        systemServers = [
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
                                        systemServers = [businessLayer]
                                    },
                                    transEjbModule(Module) {
                                        name = 'Transition Services EJB'
                                        description = 'Transition Services EJB module'
                                        application = earApplication
                                        type = ejbModType
                                        deployInstructions = 'Update existing application.\nRestart server.'
                                        systemServers = [businessLayer]
                                    },
                                    bussMdbModule(Module) {
                                        name = 'Business Services MDB'
                                        description = 'Business Services MDB module'
                                        application = earApplication
                                        type = mdbModType
                                        deployInstructions = 'Update existing application.\nRestart server.'
                                        systemServers = [businessLayer]
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
                                        systemServers = [integrationLayer]
                                    }
                            ]
                            releases = [
                                    brokerRelease105(ApplicationRelease) {
                                        application = brokerApplication
                                        releaseNumber = '1.0.5'
                                        changeLog = 'Release broker application to fix message flow issue.'
                                        releaseState = ApplicationReleaseState.SUBMITTED
                                        testState = notTestedAppReleaseTestState
                                        moduleReleases = [
                                                brokerModuleRelease105(ModuleRelease) {
                                                    applicationRelease: brokerRelease105
                                                    module: brokerModule
                                                }
                                        ]
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
                                    adminPortletModule(Module) {
                                        name = 'Admin Portlet'
                                        description = 'Big Project Administration Portlet'
                                        application = portalApplication
                                        type = portletModType
                                        deployInstructions = 'Update existing application.'
                                        systemServers = [presentationLayer]
                                    },
                                    userPortletModule(Module) {
                                        name = 'User Portlet'
                                        description = 'Big Project User Portlet'
                                        application = portalApplication
                                        type = portletModType
                                        deployInstructions = 'Update existing application.'
                                        systemServers = [presentationLayer]
                                    }
                            ]
                            releases = [
                                    portalRelease210(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.1.0'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.SUBMITTED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease210(ModuleRelease) {
                                                    applicationRelease: portalRelease210
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease210(ModuleRelease) {
                                                    applicationRelease: portalRelease210
                                                    module: userPortletModule
                                                }
                                        ]
                                    },
                                    portalRelease209(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.0.9'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.SUBMITTED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease209(ModuleRelease) {
                                                    applicationRelease: portalRelease209
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease209(ModuleRelease) {
                                                    applicationRelease: portalRelease209
                                                    module: userPortletModule
                                                }
                                        ]
                                    },
                                    portalRelease208(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.0.8'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.COMPLETED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease208(ModuleRelease) {
                                                    applicationRelease: portalRelease208
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease208(ModuleRelease) {
                                                    applicationRelease: portalRelease208
                                                    module: userPortletModule
                                                }
                                        ]
                                    },
                                    portalRelease207(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.0.7'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.COMPLETED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease207(ModuleRelease) {
                                                    applicationRelease: portalRelease207
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease207(ModuleRelease) {
                                                    applicationRelease: portalRelease207
                                                    module: userPortletModule
                                                }
                                        ]
                                    },
                                    portalRelease206(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.0.6'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.COMPLETED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease206(ModuleRelease) {
                                                    applicationRelease: portalRelease206
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease206(ModuleRelease) {
                                                    applicationRelease: portalRelease206
                                                    module: userPortletModule
                                                }
                                        ]
                                    },
                                    portalRelease205(ApplicationRelease) {
                                        application = portalApplication
                                        releaseNumber = '2.0.5'
                                        changeLog = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
                                        releaseState = ApplicationReleaseState.COMPLETED
                                        testState = testedNoErrorsAppReleaseTestState
                                        moduleReleases = [
                                                adminPortletModuleRelease205(ModuleRelease) {
                                                    applicationRelease: portalRelease205
                                                    module: adminPortletModule
                                                },
                                                userPortletModuleRelease205(ModuleRelease) {
                                                    applicationRelease: portalRelease205
                                                    module: userPortletModule
                                                }
                                        ]
                                    }
                            ]
                        }
                ]
            }

//            portalRelease210DeploymentIntegration(ApplicationDeployment) {
//                applicationRelease = portalRelease210
//                systemDeploymentEnvironment = ref('integrationEnv')
//                deploymentInstructions = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus ipsum, et egestas lectus. Nulla facilisi. Aliquam erat volutpat. Mauris id tortor lacus. In eros nisi, gravida quis aliquet sit amet, sodales ac augue. Sed rutrum, lectus sit amet viverra tincidunt, metus ligula vehicula arcu, sit amet sollicitudin lectus mi ut justo. Suspendisse suscipit tempus ligula et molestie. Duis nisl lorem, tincidunt venenatis egestas non, consectetur vel lectus. Vivamus sagittis cursus sapien, vitae aliquet massa lobortis vitae. Fusce eleifend nulla sit amet tellus tincidunt ut molestie metus auctor. Cras congue pretium turpis, vitae accumsan metus fermentum quis. Curabitur vitae dui lectus, id dapibus diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur vitae nisi in elit rhoncus aliquam. Quisque dolor tortor, dictum id auctor non, dapibus a ante.'
//                deploymentState = 'Deployed'
//                requestedDeploymentDate = new Date()
//                completedDeploymentDate = new Date()
//                moduleDeployments = [
//                        adminPortletModule210DeploymentIntegration(ModuleDeployment) {
//                            applicationDeployment: ref('portalRelease210DeploymentIntegration')
//                            moduleRelease: ref('adminPortletModuleRelease210')
//                        },
//                        userPortletModule210DeploymentIntegration(ModuleRelease) {
//                            applicationDeployment: ref('portalRelease210DeploymentIntegration')
//                            moduleRelease: ref('userPortletModuleRelease210')
//                        }
//                ]
//            }

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

