databaseChangeLog = {

	changeSet(author: "spjohnso (generated)", id: "1337949374168-1") {
		createTable(tableName: "acl_class") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "acl_classPK")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-2") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-3") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-4") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-5") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-6") {
		createTable(tableName: "application") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "applicationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "binaries_path", type: "varchar(200)") {
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
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "source_control_path", type: "varchar(200)")

			column(name: "source_control_repository_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sys_environment_id", type: "bigint")

			column(name: "type_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-7") {
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

			column(name: "deployment_environment_id", type: "bigint") {
				constraints(nullable: "false")
			}

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

			column(name: "submitted_by_id", type: "bigint")

			column(name: "tested_by_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-8") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-9") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-10") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-11") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-12") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-13") {
		createTable(tableName: "favorite") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "favoritePK")
			}

			column(name: "application_id", type: "bigint")

			column(name: "project_id", type: "bigint")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-14") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-15") {
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

			column(name: "deployment_state", type: "varchar(255)") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-16") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-17") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-18") {
		createTable(tableName: "module_system_server") {
			column(name: "module_system_servers_id", type: "bigint")

			column(name: "system_server_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-19") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-20") {
		createTable(tableName: "notification") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notificationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "email_address", type: "varchar(255)")

			column(name: "notification_event", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "notification_scheme_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "recipient_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-21") {
		createTable(tableName: "notification_scheme") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notification_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(4000)")

			column(name: "name", type: "varchar(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-22") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-23") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-24") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-25") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-26") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-27") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-28") {
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
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(200)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-29") {
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
				constraints(nullable: "false")
			}

			column(name: "server_id", type: "bigint")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-30") {
		createTable(tableName: "system_deployment_environment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "system_deployPK")
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

			column(name: "sys_environment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "environments_idx", type: "integer")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-31") {
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
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-32") {
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
				constraints(nullable: "false")
			}

			column(name: "sys_environment_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-33") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-34") {
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

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)") {
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
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-35") {
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

	changeSet(author: "spjohnso (generated)", id: "1337949374168-36") {
		createTable(tableName: "watch") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "watchPK")
			}

			column(name: "application_id", type: "bigint")

			column(name: "project_id", type: "bigint")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-37") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-38") {
		addForeignKeyConstraint(baseColumnNames: "acl_object_identity", baseTableName: "acl_entry", constraintName: "FK5302D47DB0D9DC4D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-39") {
		addForeignKeyConstraint(baseColumnNames: "sid", baseTableName: "acl_entry", constraintName: "FK5302D47D8FDB88D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-40") {
		addForeignKeyConstraint(baseColumnNames: "object_id_class", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00970422CC5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_class", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-41") {
		addForeignKeyConstraint(baseColumnNames: "owner_sid", baseTableName: "acl_object_identity", constraintName: "FK2A2BB00990EC1949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_sid", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-42") {
		addForeignKeyConstraint(baseColumnNames: "parent_object", baseTableName: "acl_object_identity", constraintName: "FK2A2BB009A50290B8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-43") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "application", constraintName: "FK5CA40550553CC050", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-44") {
		addForeignKeyConstraint(baseColumnNames: "source_control_repository_id", baseTableName: "application", constraintName: "FK5CA40550DD797715", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_repository", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-45") {
		addForeignKeyConstraint(baseColumnNames: "sys_environment_id", baseTableName: "application", constraintName: "FK5CA40550724961F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_environment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-46") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "application", constraintName: "FK5CA40550AAFC5AD7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-47") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_deployment", constraintName: "FKA9139914C7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-48") {
		addForeignKeyConstraint(baseColumnNames: "assigned_to_id", baseTableName: "application_deployment", constraintName: "FKA913991441366B72", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-49") {
		addForeignKeyConstraint(baseColumnNames: "deployment_environment_id", baseTableName: "application_deployment", constraintName: "FKA913991445BF9236", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_deployment_environment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-50") {
		addForeignKeyConstraint(baseColumnNames: "submitted_by_id", baseTableName: "application_deployment", constraintName: "FKA91399141F99D583", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-51") {
		addForeignKeyConstraint(baseColumnNames: "tested_by_id", baseTableName: "application_deployment", constraintName: "FKA91399142782C0B9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-52") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_release", constraintName: "FKF1C28418B0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-53") {
		addForeignKeyConstraint(baseColumnNames: "assigned_to_id", baseTableName: "application_release", constraintName: "FKF1C2841841366B72", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-54") {
		addForeignKeyConstraint(baseColumnNames: "submitted_by_id", baseTableName: "application_release", constraintName: "FKF1C284181F99D583", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-55") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "application_release", constraintName: "FKF1C2841865E1C302", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release_test_state", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-56") {
		addForeignKeyConstraint(baseColumnNames: "tested_by_id", baseTableName: "application_release", constraintName: "FKF1C284182782C0B9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-57") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_release_history", constraintName: "FK338776DC7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-58") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_role", constraintName: "FKB0605B0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-59") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "application_role", constraintName: "FKB06057060E0DB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_role", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-60") {
		addForeignKeyConstraint(baseColumnNames: "source_control_user_id", baseTableName: "application_role", constraintName: "FKB0605F4462BB5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-61") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "favorite", constraintName: "FK3EA1C99CB0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-62") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "favorite", constraintName: "FK3EA1C99C553CC050", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-63") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "favorite", constraintName: "FK3EA1C99CAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-64") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "module", constraintName: "FKC04BA66CB0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-65") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "module", constraintName: "FKC04BA66CC9B5B553", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-66") {
		addForeignKeyConstraint(baseColumnNames: "application_deployment_id", baseTableName: "module_deployment", constraintName: "FKD77B6E789CDC5E07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_deployment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-67") {
		addForeignKeyConstraint(baseColumnNames: "module_release_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78209DA925", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-68") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "module_deployment", constraintName: "FKD77B6E78FFC44810", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module_deployment_test_state", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-69") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "module_release", constraintName: "FKFFB54934C7A2D895", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application_release", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-70") {
		addForeignKeyConstraint(baseColumnNames: "module_id", baseTableName: "module_release", constraintName: "FKFFB54934C9FBBA07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-71") {
		addForeignKeyConstraint(baseColumnNames: "module_system_servers_id", baseTableName: "module_system_server", constraintName: "FK62F882C0802D7160", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "module", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-72") {
		addForeignKeyConstraint(baseColumnNames: "system_server_id", baseTableName: "module_system_server", constraintName: "FK62F882C0EFBCCF03", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-73") {
		addForeignKeyConstraint(baseColumnNames: "notification_scheme_id", baseTableName: "notification", constraintName: "FK237A88EB4670A53F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_scheme", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-74") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification", constraintName: "FK237A88EBAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-75") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "project", constraintName: "FKED904B1942702A69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-76") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_repository", constraintName: "FKA0A83A10BD101D7B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-77") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_user", constraintName: "FKEDA05711BD101D7B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "source_control_server", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-78") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "source_control_user", constraintName: "FKEDA05711AA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-79") {
		addForeignKeyConstraint(baseColumnNames: "sys_environment_id", baseTableName: "system_deployment_environment", constraintName: "FK63A9B3A9724961F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_environment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-80") {
		addForeignKeyConstraint(baseColumnNames: "sys_environment_id", baseTableName: "system_server", constraintName: "FK8128B753724961F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "system_environment", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-81") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A559A9F3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-82") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-83") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "watch", constraintName: "FK6BAC4CFB0244827", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "application", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-84") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "watch", constraintName: "FK6BAC4CF553CC050", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-85") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "watch", constraintName: "FK6BAC4CFAA846DD3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-86") {
		createIndex(indexName: "FK5302D47D8FDB88D5", tableName: "acl_entry") {
			column(name: "sid")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-87") {
		createIndex(indexName: "FK5302D47DB0D9DC4D", tableName: "acl_entry") {
			column(name: "acl_object_identity")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-88") {
		createIndex(indexName: "unique-ace_order", tableName: "acl_entry") {
			column(name: "acl_object_identity")

			column(name: "ace_order")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-89") {
		createIndex(indexName: "FK2A2BB00970422CC5", tableName: "acl_object_identity") {
			column(name: "object_id_class")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-90") {
		createIndex(indexName: "FK2A2BB00990EC1949", tableName: "acl_object_identity") {
			column(name: "owner_sid")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-91") {
		createIndex(indexName: "FK2A2BB009A50290B8", tableName: "acl_object_identity") {
			column(name: "parent_object")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-92") {
		createIndex(indexName: "unique-object_id_identity", tableName: "acl_object_identity") {
			column(name: "object_id_class")

			column(name: "object_id_identity")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-93") {
		createIndex(indexName: "unique-principal", tableName: "acl_sid") {
			column(name: "sid")

			column(name: "principal")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-94") {
		createIndex(indexName: "FK5CA40550553CC050", tableName: "application") {
			column(name: "project_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-95") {
		createIndex(indexName: "FK5CA40550724961F3", tableName: "application") {
			column(name: "sys_environment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-96") {
		createIndex(indexName: "FK5CA40550AAFC5AD7", tableName: "application") {
			column(name: "type_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-97") {
		createIndex(indexName: "FK5CA40550DD797715", tableName: "application") {
			column(name: "source_control_repository_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-98") {
		createIndex(indexName: "name_unique_1337949374043", tableName: "application", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-99") {
		createIndex(indexName: "unique-source_control_path", tableName: "application") {
			column(name: "source_control_repository_id")

			column(name: "source_control_path")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-100") {
		createIndex(indexName: "FKA91399141F99D583", tableName: "application_deployment") {
			column(name: "submitted_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-101") {
		createIndex(indexName: "FKA91399142782C0B9", tableName: "application_deployment") {
			column(name: "tested_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-102") {
		createIndex(indexName: "FKA913991441366B72", tableName: "application_deployment") {
			column(name: "assigned_to_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-103") {
		createIndex(indexName: "FKA913991445BF9236", tableName: "application_deployment") {
			column(name: "deployment_environment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-104") {
		createIndex(indexName: "FKA9139914C7A2D895", tableName: "application_deployment") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-105") {
		createIndex(indexName: "FKF1C284181F99D583", tableName: "application_release") {
			column(name: "submitted_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-106") {
		createIndex(indexName: "FKF1C284182782C0B9", tableName: "application_release") {
			column(name: "tested_by_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-107") {
		createIndex(indexName: "FKF1C2841841366B72", tableName: "application_release") {
			column(name: "assigned_to_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-108") {
		createIndex(indexName: "FKF1C2841865E1C302", tableName: "application_release") {
			column(name: "test_state_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-109") {
		createIndex(indexName: "FKF1C28418B0244827", tableName: "application_release") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-110") {
		createIndex(indexName: "unique-release_number", tableName: "application_release") {
			column(name: "application_id")

			column(name: "release_number")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-111") {
		createIndex(indexName: "FK338776DC7A2D895", tableName: "application_release_history") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-112") {
		createIndex(indexName: "name_unique_1337949374059", tableName: "application_release_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-113") {
		createIndex(indexName: "FKB06057060E0DB", tableName: "application_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-114") {
		createIndex(indexName: "FKB0605B0244827", tableName: "application_role") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-115") {
		createIndex(indexName: "FKB0605F4462BB5", tableName: "application_role") {
			column(name: "source_control_user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-116") {
		createIndex(indexName: "name_unique_1337949374059", tableName: "application_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-117") {
		createIndex(indexName: "FK3EA1C99C553CC050", tableName: "favorite") {
			column(name: "project_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-118") {
		createIndex(indexName: "FK3EA1C99CAA846DD3", tableName: "favorite") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-119") {
		createIndex(indexName: "FK3EA1C99CB0244827", tableName: "favorite") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-120") {
		createIndex(indexName: "FKC04BA66CB0244827", tableName: "module") {
			column(name: "application_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-121") {
		createIndex(indexName: "FKC04BA66CC9B5B553", tableName: "module") {
			column(name: "type_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-122") {
		createIndex(indexName: "FKD77B6E78209DA925", tableName: "module_deployment") {
			column(name: "module_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-123") {
		createIndex(indexName: "FKD77B6E789CDC5E07", tableName: "module_deployment") {
			column(name: "application_deployment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-124") {
		createIndex(indexName: "FKD77B6E78FFC44810", tableName: "module_deployment") {
			column(name: "test_state_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-125") {
		createIndex(indexName: "name_unique_1337949374059", tableName: "module_deployment_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-126") {
		createIndex(indexName: "FKFFB54934C7A2D895", tableName: "module_release") {
			column(name: "application_release_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-127") {
		createIndex(indexName: "FKFFB54934C9FBBA07", tableName: "module_release") {
			column(name: "module_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-128") {
		createIndex(indexName: "FK62F882C0802D7160", tableName: "module_system_server") {
			column(name: "module_system_servers_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-129") {
		createIndex(indexName: "FK62F882C0EFBCCF03", tableName: "module_system_server") {
			column(name: "system_server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-130") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "module_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-131") {
		createIndex(indexName: "FK237A88EB4670A53F", tableName: "notification") {
			column(name: "notification_scheme_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-132") {
		createIndex(indexName: "FK237A88EBAA846DD3", tableName: "notification") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-133") {
		createIndex(indexName: "FKED904B1942702A69", tableName: "project") {
			column(name: "category_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-134") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-135") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "project_category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-136") {
		createIndex(indexName: "authority_unique_1337949374075", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-137") {
		createIndex(indexName: "FKA0A83A10BD101D7B", tableName: "source_control_repository") {
			column(name: "server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-138") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "source_control_role", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-139") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "source_control_server", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-140") {
		createIndex(indexName: "FKEDA05711AA846DD3", tableName: "source_control_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-141") {
		createIndex(indexName: "FKEDA05711BD101D7B", tableName: "source_control_user") {
			column(name: "server_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-142") {
		createIndex(indexName: "name_unique_1337949374075", tableName: "source_control_user", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-143") {
		createIndex(indexName: "FK63A9B3A9724961F3", tableName: "system_deployment_environment") {
			column(name: "sys_environment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-144") {
		createIndex(indexName: "name_unique_1337949374090", tableName: "system_environment", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-145") {
		createIndex(indexName: "FK8128B753724961F3", tableName: "system_server") {
			column(name: "sys_environment_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-146") {
		createIndex(indexName: "name_unique_1337949374090", tableName: "system_server", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-147") {
		createIndex(indexName: "username_unique_1337949374090", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-148") {
		createIndex(indexName: "FK143BF46A559A9F3", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-149") {
		createIndex(indexName: "FK143BF46AAA846DD3", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-150") {
		createIndex(indexName: "FK6BAC4CF553CC050", tableName: "watch") {
			column(name: "project_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-151") {
		createIndex(indexName: "FK6BAC4CFAA846DD3", tableName: "watch") {
			column(name: "user_id")
		}
	}

	changeSet(author: "spjohnso (generated)", id: "1337949374168-152") {
		createIndex(indexName: "FK6BAC4CFB0244827", tableName: "watch") {
			column(name: "application_id")
		}
	}
}
