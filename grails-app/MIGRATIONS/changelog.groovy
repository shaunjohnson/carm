databaseChangeLog = {

    changeSet(author: "spjohnso (generated)", id: "1327943560165-1") {
        createTable(tableName: "acl_class") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_classPK")
            }

            column(name: "class", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-2") {
        createTable(tableName: "acl_entry") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_entryPK")
            }

            column(name: "ace_order", type: "integer") {
                constraints(nullable: "false")
            }

            column(name: "acl_object_identity", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "audit_failure", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "audit_success", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "granting", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "mask", type: "integer") {
                constraints(nullable: "false")
            }

            column(name: "sid", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-3") {
        createTable(tableName: "acl_object_identity") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_object_idPK")
            }

            column(name: "object_id_class", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "entries_inheriting", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "object_id_identity", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "owner_sid", type: "bigint")

            column(name: "parent_object", type: "bigint")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-4") {
        createTable(tableName: "acl_sid") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_sidPK")
            }

            column(name: "principal", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "sid", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-5") {
        createTable(tableName: "activity_trace") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "activity_tracPK")
            }

            column(name: "action", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "date_occurred", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "objectId", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "objectName", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "objectType", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "oid", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-6") {
        createTable(tableName: "application") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "applicationPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "deploy_instructions", type: "longtext")

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "source_control_path", type: "varchar(200)")

            column(name: "source_control_repository_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "system_id", type: "bigint")

            column(name: "type_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-7") {
        createTable(tableName: "application_deployment") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_dPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_release_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "completed_deployment_date", type: "datetime")

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "deployment_instructions", type: "longtext")

            column(name: "deployment_state", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "sys_environment_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "requested_deployment_date", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-8") {
        createTable(tableName: "application_release") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_rPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "build_path", type: "varchar(100)")

            column(name: "change_log", type: "longtext")

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "release_number", type: "varchar(20)") {
                constraints(nullable: "false")
            }

            column(name: "release_state", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "test_state_id", type: "bigint")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-9") {
        createTable(tableName: "application_release_history") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_rPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_release_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "comments", type: "longtext")

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "summary", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-10") {
        createTable(tableName: "application_release_test_state") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_rPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-11") {
        createTable(tableName: "application_role") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_rPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "source_control_user_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-12") {
        createTable(tableName: "application_type") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "application_tPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-13") {
        createTable(tableName: "module") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "modulePK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "deploy_instructions", type: "longtext")

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "type_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-14") {
        createTable(tableName: "module_deployment") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "module_deployPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_deployment_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "deployment_instructions", type: "longtext")

            column(name: "deployment_state", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "module_release_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "test_state_id", type: "bigint")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-15") {
        createTable(tableName: "module_deployment_test_state") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "module_deployPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-16") {
        createTable(tableName: "module_release") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "module_releasPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_release_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "module_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-17") {
        createTable(tableName: "module_system_component") {
            column(name: "module_system_components_id", type: "bigint")

            column(name: "system_component_id", type: "bigint")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-18") {
        createTable(tableName: "module_type") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "module_typePK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-19") {
        createTable(tableName: "project") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "projectPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-20") {
        createTable(tableName: "project_category") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "project_categPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-21") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-22") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "varchar(255)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-23") {
        createTable(tableName: "source_control_repository") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "source_controPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "path", type: "varchar(200)") {
                constraints(nullable: "false")
            }

            column(name: "server_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-24") {
        createTable(tableName: "source_control_role") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "source_controPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-25") {
        createTable(tableName: "source_control_server") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "source_controPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "type", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "url", type: "varchar(200)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-26") {
        createTable(tableName: "source_control_user") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "source_controPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "server_id", type: "bigint")

            column(name: "user_id", type: "bigint")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-27") {
        createTable(tableName: "system") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "systemPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-28") {
        createTable(tableName: "system_component") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "system_componPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "system_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-29") {
        createTable(tableName: "system_environment") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "system_enviroPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "system_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "environments_idx", type: "integer")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-30") {
        createTable(tableName: "test_report") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "test_reportPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-31") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-32") {
        createTable(tableName: "user_role") {
            column(name: "role_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-33") {
        addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-34") {
        createIndex(indexName: "FK5302D47D8FDB88D5", tableName: "acl_entry") {
            column(name: "sid")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-35") {
        createIndex(indexName: "FK5302D47DB0D9DC4D", tableName: "acl_entry") {
            column(name: "acl_object_identity")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-36") {
        createIndex(indexName: "unique-ace_order", tableName: "acl_entry") {
            column(name: "acl_object_identity")

            column(name: "ace_order")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-37") {
        createIndex(indexName: "FK2A2BB00970422CC5", tableName: "acl_object_identity") {
            column(name: "object_id_class")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-38") {
        createIndex(indexName: "FK2A2BB00990EC1949", tableName: "acl_object_identity") {
            column(name: "owner_sid")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-39") {
        createIndex(indexName: "FK2A2BB009A50290B8", tableName: "acl_object_identity") {
            column(name: "parent_object")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-40") {
        createIndex(indexName: "unique-object_id_identity", tableName: "acl_object_identity") {
            column(name: "object_id_class")

            column(name: "object_id_identity")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-41") {
        createIndex(indexName: "unique-principal", tableName: "acl_sid") {
            column(name: "sid")

            column(name: "principal")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-42") {
        createIndex(indexName: "FK5CA4055032077FF5", tableName: "application") {
            column(name: "type_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-43") {
        createIndex(indexName: "FK5CA405509CD63F61", tableName: "application") {
            column(name: "source_control_repository_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-44") {
        createIndex(indexName: "FK5CA40550BECBA7EF", tableName: "application") {
            column(name: "system_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-45") {
        createIndex(indexName: "FK5CA40550D405E925", tableName: "application") {
            column(name: "project_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-46") {
        createIndex(indexName: "FKA913991433784F76", tableName: "application_deployment") {
            column(name: "environment_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-47") {
        createIndex(indexName: "FKA913991486A0584E", tableName: "application_deployment") {
            column(name: "application_release_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-48") {
        createIndex(indexName: "FKF1C284182BB5E369", tableName: "application_release") {
            column(name: "test_state_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-49") {
        createIndex(indexName: "FKF1C284183F5AAC45", tableName: "application_release") {
            column(name: "application_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-50") {
        createIndex(indexName: "FK338776D86A0584E", tableName: "application_release_history") {
            column(name: "application_release_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-51") {
        createIndex(indexName: "name_unique_1327943560056", tableName: "application_release_test_state", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-52") {
        createIndex(indexName: "FKB06053F5AAC45", tableName: "application_role") {
            column(name: "application_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-53") {
        createIndex(indexName: "FKB06055DCD227", tableName: "application_role") {
            column(name: "role_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-54") {
        createIndex(indexName: "FKB060589C21D01", tableName: "application_role") {
            column(name: "source_control_user_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-55") {
        createIndex(indexName: "name_unique_1327943560072", tableName: "application_type", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-56") {
        createIndex(indexName: "FKC04BA66C3F5AAC45", tableName: "module") {
            column(name: "application_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-57") {
        createIndex(indexName: "FKC04BA66CA3133A1B", tableName: "module") {
            column(name: "type_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-58") {
        createIndex(indexName: "FKD77B6E789EBB9326", tableName: "module_deployment") {
            column(name: "application_deployment_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-59") {
        createIndex(indexName: "FKD77B6E78E6B5D5AF", tableName: "module_deployment") {
            column(name: "test_state_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-60") {
        createIndex(indexName: "FKD77B6E78F8BB79CC", tableName: "module_deployment") {
            column(name: "module_release_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-61") {
        createIndex(indexName: "name_unique_1327943560072", tableName: "module_deployment_test_state", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-62") {
        createIndex(indexName: "FKFFB54934142AE2CF", tableName: "module_release") {
            column(name: "module_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-63") {
        createIndex(indexName: "FKFFB5493486A0584E", tableName: "module_release") {
            column(name: "application_release_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-64") {
        createIndex(indexName: "FKDCCBC60173B03C8", tableName: "module_system_component") {
            column(name: "module_system_components_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-65") {
        createIndex(indexName: "FKDCCBC60183CA246", tableName: "module_system_component") {
            column(name: "system_component_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-66") {
        createIndex(indexName: "name_unique_1327943560072", tableName: "module_type", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-67") {
        createIndex(indexName: "FKED904B19F681AE3E", tableName: "project") {
            column(name: "category_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-68") {
        createIndex(indexName: "name_unique_1327943560072", tableName: "project", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-69") {
        createIndex(indexName: "name_unique_1327943560072", tableName: "project_category", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-70") {
        createIndex(indexName: "authority_unique_1327943560072", tableName: "role", unique: "true") {
            column(name: "authority")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-71") {
        createIndex(indexName: "FKA0A83A10E354EBC7", tableName: "source_control_repository") {
            column(name: "server_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-72") {
        createIndex(indexName: "name_unique_1327943560087", tableName: "source_control_role", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-73") {
        createIndex(indexName: "name_unique_1327943560087", tableName: "source_control_server", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-74") {
        createIndex(indexName: "FKEDA05711AA846DD3", tableName: "source_control_user") {
            column(name: "user_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-75") {
        createIndex(indexName: "FKEDA05711E354EBC7", tableName: "source_control_user") {
            column(name: "server_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-76") {
        createIndex(indexName: "name_unique_1327943560087", tableName: "source_control_user", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-77") {
        createIndex(indexName: "name_unique_1327943560087", tableName: "system", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-78") {
        createIndex(indexName: "FK1982DAEDBECBA7EF", tableName: "system_component") {
            column(name: "system_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-79") {
        createIndex(indexName: "name_unique_1327943560087", tableName: "system_component", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-80") {
        createIndex(indexName: "FKF9DD3403BECBA7EF", tableName: "system_environment") {
            column(name: "system_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-81") {
        createIndex(indexName: "username_unique_1327943560087", tableName: "user", unique: "true") {
            column(name: "username")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-82") {
        createIndex(indexName: "FK143BF46A559A9F3", tableName: "user_role") {
            column(name: "role_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-83") {
        createIndex(indexName: "FK143BF46AAA846DD3", tableName: "user_role") {
            column(name: "user_id")
        }
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-84") {
        addForeignKeyConstraint(baseColumnNames: "acl_object_identity", baseTableName: "acl_entry", constraintName: "FK5302D47DB0D9DC4D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-85") {
        addForeignKeyConstraint(baseColumnNames: "sid", baseTableName: "acl_entry", constraintName: "FK5302D47D8FDB88D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-86") {
        addForeignKeyConstraint(baseColumnNames: "object_id_class", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00970422CC5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_class", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-87") {
        addForeignKeyConstraint(baseColumnNames: "owner_sid", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00990EC1949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-88") {
        addForeignKeyConstraint(baseColumnNames: "parent_object", baseTableName: "acl_object_identity", constraintName: "FK2A2BB009A50290B8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-89") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "application", constraintName: "FK5CA40550D405E925", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-90") {
        addForeignKeyConstraint(baseColumnNames: "source_control_repository_id", baseTableName: "application", constraintName: "FK5CA405509CD63F61", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_repository", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-91") {
        addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "application", constraintName: "FK5CA40550BECBA7EF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-92") {
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "application", constraintName: "FK5CA4055032077FF5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_type", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-93") {
        addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_deployment", constraintName: "FKA913991486A0584E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-94") {
        addForeignKeyConstraint(baseColumnNames: "environment_id", baseTableName: "application_deployment", constraintName: "FKA913991433784F76", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_environment", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-95") {
        addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_release", constraintName: "FKF1C284183F5AAC45", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-96") {
        addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "application_release", constraintName: "FKF1C284182BB5E369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release_test_state", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-97") {
        addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_release_history", constraintName: "FK338776D86A0584E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-98") {
        addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_role", constraintName: "FKB06053F5AAC45", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-99") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "application_role", constraintName: "FKB06055DCD227", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_role", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-100") {
        addForeignKeyConstraint(baseColumnNames: "source_control_user_id", baseTableName: "application_role", constraintName: "FKB060589C21D01", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_user", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-101") {
        addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "module", constraintName: "FKC04BA66C3F5AAC45", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-102") {
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "module", constraintName: "FKC04BA66CA3133A1B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_type", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-103") {
        addForeignKeyConstraint(baseColumnNames: "application_deployment_id", baseTableName: "module_deployment", constraintName: "FKD77B6E789EBB9326", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_deployment", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-104") {
        addForeignKeyConstraint(baseColumnNames: "module_release_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78F8BB79CC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_release", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-105") {
        addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78E6B5D5AF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_deployment_test_state", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-106") {
        addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "module_release", constraintName: "FKFFB5493486A0584E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-107") {
        addForeignKeyConstraint(baseColumnNames: "module_id", baseTableName: "module_release", constraintName: "FKFFB54934142AE2CF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-108") {
        addForeignKeyConstraint(baseColumnNames: "module_system_components_id", baseTableName: "module_system_component", constraintName: "FKDCCBC60173B03C8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-109") {
        addForeignKeyConstraint(baseColumnNames: "system_component_id", baseTableName: "module_system_component", constraintName: "FKDCCBC60183CA246", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_component", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-110") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "project", constraintName: "FKED904B19F681AE3E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project_category", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-111") {
        addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_repository", constraintName: "FKA0A83A10E354EBC7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-112") {
        addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_user", constraintName: "FKEDA05711E354EBC7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-113") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "source_control_user", constraintName: "FKEDA05711AA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-114") {
        addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_component", constraintName: "FK1982DAEDBECBA7EF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-115") {
        addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_environment", constraintName: "FKF9DD3403BECBA7EF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-116") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A559A9F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
    }

    changeSet(author: "spjohnso (generated)", id: "1327943560165-117") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
    }
}
