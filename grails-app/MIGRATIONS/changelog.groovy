databaseChangeLog = {

	changeSet(author: "spjohnso (generated)", id: "1330979409995-1") {
		createTable(tableName: "acl_class") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_classPK")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-2") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-3") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-4") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-5") {
		createTable(tableName: "activity_trace") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "activity_tracPK")
			}

			column(name: "action", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_occurred", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "object_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "object_name", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "object_type", type: "varchar(100)") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-6") {
		createTable(tableName: "application") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "applicationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "build_instructions", type: "longtext")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deploy_instructions", type: "longtext")

			column(name: "description", type: "varchar(4000)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-7") {
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

            column(name: "assigned_to_id", type: "bigint")

			column(name: "completed_deployment_date", type: "datetime")

            column(name: "date_assigned", type: "datetime")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

            column(name: "date_submitted", type: "datetime")

            column(name: "date_tested", type: "datetime")

			column(name: "deployment_instructions", type: "longtext")

			column(name: "deployment_state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "requested_deployment_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "sys_environment_id", type: "bigint") {
				constraints(nullable: "false")
			}

            column(name: "submitted_by_id", type: "bigint")

            column(name: "test_state_id", type: "bigint")

            column(name: "tested_by_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-8") {
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

			column(name: "assigned_to_id", type: "bigint")

			column(name: "build_instructions", type: "longtext")

			column(name: "build_path", type: "varchar(100)")

			column(name: "change_log", type: "longtext")

			column(name: "date_assigned", type: "datetime")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "date_submitted", type: "datetime")

			column(name: "date_tested", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "release_number", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "release_state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "submitted_by_id", type: "bigint")

			column(name: "test_state_id", type: "bigint")

			column(name: "tested_by_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-9") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-10") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-11") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-12") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-13") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-14") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-15") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-16") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-17") {
		createTable(tableName: "module_system_server") {
			column(name: "module_system_servers_id", type: "bigint")

			column(name: "system_server_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-18") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-19") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-20") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-21") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-22") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-23") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-24") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-25") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-26") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-27") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-28") {
		createTable(tableName: "system_server") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "system_serverPK")
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-29") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-30") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-31") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-32") {
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

	changeSet(author: "spjohnso (generated)", id: "1330979409995-33") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-34") {
		createIndex(indexName: "FK5302D47D8FDB88D5", tableName: "acl_entry") {
			column(name: "sid")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-35") {
		createIndex(indexName: "FK5302D47DB0D9DC4D", tableName: "acl_entry") {
			column(name: "acl_object_identity")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-36") {
		createIndex(indexName: "unique-ace_order", tableName: "acl_entry") {
			column(name: "acl_object_identity")

			column(name: "ace_order")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-37") {
		createIndex(indexName: "FK2A2BB00970422CC5", tableName: "acl_object_identity") {
			column(name: "object_id_class")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-38") {
		createIndex(indexName: "FK2A2BB00990EC1949", tableName: "acl_object_identity") {
			column(name: "owner_sid")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-39") {
		createIndex(indexName: "FK2A2BB009A50290B8", tableName: "acl_object_identity") {
			column(name: "parent_object")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-40") {
		createIndex(indexName: "unique-object_id_identity", tableName: "acl_object_identity") {
			column(name: "object_id_class")

			column(name: "object_id_identity")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-41") {
		createIndex(indexName: "unique-principal", tableName: "acl_sid") {
			column(name: "sid")

			column(name: "principal")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-42") {
		createIndex(indexName: "FK5CA405504EE68204", tableName: "application") {
			column(name: "system_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-43") {
		createIndex(indexName: "FK5CA40550553CC050", tableName: "application") {
			column(name: "project_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-44") {
		createIndex(indexName: "FK5CA40550AAFC5AD7", tableName: "application") {
			column(name: "type_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-45") {
		createIndex(indexName: "FK5CA40550DD797715", tableName: "application") {
			column(name: "source_control_repository_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-46") {
		createIndex(indexName: "FKA9139914724961F3", tableName: "application_deployment") {
			column(name: "sys_environment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-47") {
		createIndex(indexName: "FKA9139914C7A2D895", tableName: "application_deployment") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-48") {
		createIndex(indexName: "FKF1C284181F99D583", tableName: "application_release") {
			column(name: "submitted_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-49") {
		createIndex(indexName: "FKF1C284182782C0B9", tableName: "application_release") {
			column(name: "tested_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-50") {
		createIndex(indexName: "FKF1C2841841366B72", tableName: "application_release") {
			column(name: "assigned_to_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-51") {
		createIndex(indexName: "FKF1C2841865E1C302", tableName: "application_release") {
			column(name: "test_state_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-52") {
		createIndex(indexName: "FKF1C28418B0244827", tableName: "application_release") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-53") {
		createIndex(indexName: "FK338776DC7A2D895", tableName: "application_release_history") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-54") {
		createIndex(indexName: "name_unique_1330979409901", tableName: "application_release_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-55") {
		createIndex(indexName: "FKB06057060E0DB", tableName: "application_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-56") {
		createIndex(indexName: "FKB0605B0244827", tableName: "application_role") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-57") {
		createIndex(indexName: "FKB0605F4462BB5", tableName: "application_role") {
			column(name: "source_control_user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-58") {
		createIndex(indexName: "name_unique_1330979409901", tableName: "application_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-59") {
		createIndex(indexName: "FKC04BA66CB0244827", tableName: "module") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-60") {
		createIndex(indexName: "FKC04BA66CC9B5B553", tableName: "module") {
			column(name: "type_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-61") {
		createIndex(indexName: "FKD77B6E78209DA925", tableName: "module_deployment") {
			column(name: "module_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-62") {
		createIndex(indexName: "FKD77B6E789CDC5E07", tableName: "module_deployment") {
			column(name: "application_deployment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-63") {
		createIndex(indexName: "FKD77B6E78FFC44810", tableName: "module_deployment") {
			column(name: "test_state_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-64") {
		createIndex(indexName: "name_unique_1330979409917", tableName: "module_deployment_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-65") {
		createIndex(indexName: "FKFFB54934C7A2D895", tableName: "module_release") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-66") {
		createIndex(indexName: "FKFFB54934C9FBBA07", tableName: "module_release") {
			column(name: "module_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-67") {
		createIndex(indexName: "FKDCCBC6099BD4FD1", tableName: "module_system_server") {
			column(name: "system_server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-68") {
		createIndex(indexName: "FKDCCBC60CD0BDB00", tableName: "module_system_server") {
			column(name: "module_system_servers_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-69") {
		createIndex(indexName: "name_unique_1330979409917", tableName: "module_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-70") {
		createIndex(indexName: "FKED904B1942702A69", tableName: "project") {
			column(name: "category_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-71") {
		createIndex(indexName: "name_unique_1330979409917", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-72") {
		createIndex(indexName: "name_unique_1330979409917", tableName: "project_category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-73") {
		createIndex(indexName: "authority_unique_1330979409917", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-74") {
		createIndex(indexName: "FKA0A83A10BD101D7B", tableName: "source_control_repository") {
			column(name: "server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-75") {
		createIndex(indexName: "name_unique_1330979409932", tableName: "source_control_role", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-76") {
		createIndex(indexName: "name_unique_1330979409932", tableName: "source_control_server", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-77") {
		createIndex(indexName: "FKEDA05711AA846DD3", tableName: "source_control_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-78") {
		createIndex(indexName: "FKEDA05711BD101D7B", tableName: "source_control_user") {
			column(name: "server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-79") {
		createIndex(indexName: "name_unique_1330979409932", tableName: "source_control_user", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-80") {
		createIndex(indexName: "name_unique_1330979409932", tableName: "system", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-81") {
		createIndex(indexName: "FK1982DAED4EE68204", tableName: "system_server") {
			column(name: "system_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-82") {
		createIndex(indexName: "name_unique_1330979409932", tableName: "system_server", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-83") {
		createIndex(indexName: "FKF9DD34034EE68204", tableName: "system_environment") {
			column(name: "system_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-84") {
		createIndex(indexName: "username_unique_1330979409932", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-85") {
		createIndex(indexName: "FK143BF46A559A9F3", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-86") {
		createIndex(indexName: "FK143BF46AAA846DD3", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-87") {
		addForeignKeyConstraint(baseColumnNames: "acl_object_identity", baseTableName: "acl_entry", constraintName: "FK5302D47DB0D9DC4D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-88") {
		addForeignKeyConstraint(baseColumnNames: "sid", baseTableName: "acl_entry", constraintName: "FK5302D47D8FDB88D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-89") {
		addForeignKeyConstraint(baseColumnNames: "object_id_class", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00970422CC5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_class", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-90") {
		addForeignKeyConstraint(baseColumnNames: "owner_sid", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00990EC1949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-91") {
		addForeignKeyConstraint(baseColumnNames: "parent_object", baseTableName: "acl_object_identity", constraintName: "FK2A2BB009A50290B8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-92") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "application", constraintName: "FK5CA40550553CC050", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-93") {
		addForeignKeyConstraint(baseColumnNames: "source_control_repository_id", baseTableName: "application", constraintName: "FK5CA40550DD797715", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_repository", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-94") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "application", constraintName: "FK5CA405504EE68204", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-95") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "application", constraintName: "FK5CA40550AAFC5AD7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-96") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_deployment", constraintName: "FKA9139914C7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-97") {
		addForeignKeyConstraint(baseColumnNames: "sys_environment_id", baseTableName: "application_deployment", constraintName: "FKA9139914724961F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_environment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-98") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_release", constraintName: "FKF1C28418B0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-99") {
		addForeignKeyConstraint(baseColumnNames: "assigned_to_id", baseTableName: "application_release", constraintName: "FKF1C2841841366B72", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-100") {
		addForeignKeyConstraint(baseColumnNames: "submitted_by_id", baseTableName: "application_release", constraintName: "FKF1C284181F99D583", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-101") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "application_release", constraintName: "FKF1C2841865E1C302", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release_test_state", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-102") {
		addForeignKeyConstraint(baseColumnNames: "tested_by_id", baseTableName: "application_release", constraintName: "FKF1C284182782C0B9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-103") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_release_history", constraintName: "FK338776DC7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-104") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_role", constraintName: "FKB0605B0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-105") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "application_role", constraintName: "FKB06057060E0DB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_role", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-106") {
		addForeignKeyConstraint(baseColumnNames: "source_control_user_id", baseTableName: "application_role", constraintName: "FKB0605F4462BB5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-107") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "module", constraintName: "FKC04BA66CB0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-108") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "module", constraintName: "FKC04BA66CC9B5B553", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-109") {
		addForeignKeyConstraint(baseColumnNames: "application_deployment_id", baseTableName: "module_deployment", constraintName: "FKD77B6E789CDC5E07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_deployment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-110") {
		addForeignKeyConstraint(baseColumnNames: "module_release_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78209DA925", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-111") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78FFC44810", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_deployment_test_state", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-112") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "module_release", constraintName: "FKFFB54934C7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-113") {
		addForeignKeyConstraint(baseColumnNames: "module_id", baseTableName: "module_release", constraintName: "FKFFB54934C9FBBA07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-114") {
		addForeignKeyConstraint(baseColumnNames: "module_system_servers_id", baseTableName: "module_system_server", constraintName: "FKDCCBC60CD0BDB00", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-115") {
		addForeignKeyConstraint(baseColumnNames: "system_server_id", baseTableName: "module_system_server", constraintName: "FKDCCBC6099BD4FD1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-116") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "project", constraintName: "FKED904B1942702A69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-117") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_repository", constraintName: "FKA0A83A10BD101D7B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-118") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_user", constraintName: "FKEDA05711BD101D7B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-119") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "source_control_user", constraintName: "FKEDA05711AA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-120") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_server", constraintName: "FK1982DAED4EE68204", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-121") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_environment", constraintName: "FKF9DD34034EE68204", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-122") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A559A9F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1330979409995-123") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
