databaseChangeLog = {

	changeSet(author: "shaun (generated)", id: "1326468103517-1") {
		createTable(tableName: "acl_class") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "class", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-2") {
		createTable(tableName: "acl_entry") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "ace_order", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "acl_object_identity", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "audit_failure", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "audit_success", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "granting", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "mask", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "sid", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-3") {
		createTable(tableName: "acl_object_identity") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "object_id_class", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "entries_inheriting", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "object_id_identity", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "owner_sid", type: "BIGINT")

			column(name: "parent_object", type: "BIGINT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-4") {
		createTable(tableName: "acl_sid") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "principal", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "sid", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-5") {
		createTable(tableName: "application") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "deploy_instructions", type: "LONGTEXT")

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "source_control_path", type: "VARCHAR(200)")

			column(name: "source_control_repository_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "system_id", type: "BIGINT")

			column(name: "type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-6") {
		createTable(tableName: "application_deployment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_release_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "completed_deployment_date", type: "DATETIME")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "deployment_instructions", type: "LONGTEXT")

			column(name: "deployment_state", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "requested_deployment_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "system_environment_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-7") {
		createTable(tableName: "application_release") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "build_path", type: "VARCHAR(100)")

			column(name: "change_log", type: "LONGTEXT")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "release_number", type: "VARCHAR(20)") {
				constraints(nullable: "false")
			}

			column(name: "release_state", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "test_state_id", type: "BIGINT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-8") {
		createTable(tableName: "application_release_test_state") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-9") {
		createTable(tableName: "application_role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "source_control_user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-10") {
		createTable(tableName: "application_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-11") {
		createTable(tableName: "module") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "deploy_instructions", type: "LONGTEXT")

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-12") {
		createTable(tableName: "module_deployment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_deployment_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "deployment_instructions", type: "LONGTEXT")

			column(name: "deployment_state", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "module_release_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "test_state_id", type: "BIGINT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-13") {
		createTable(tableName: "module_deployment_test_state") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-14") {
		createTable(tableName: "module_release") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "application_release_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "module_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-15") {
		createTable(tableName: "module_system_component") {
			column(name: "module_system_components_id", type: "BIGINT")

			column(name: "system_component_id", type: "BIGINT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-16") {
		createTable(tableName: "module_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-17") {
		createTable(tableName: "project") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "category_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-18") {
		createTable(tableName: "project_category") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-19") {
		createTable(tableName: "registration_code") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-20") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-21") {
		createTable(tableName: "source_control_repository") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "path", type: "VARCHAR(200)") {
				constraints(nullable: "false")
			}

			column(name: "server_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-22") {
		createTable(tableName: "source_control_role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-23") {
		createTable(tableName: "source_control_server") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "VARCHAR(200)")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-24") {
		createTable(tableName: "source_control_user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "server_id", type: "BIGINT")

			column(name: "user_id", type: "BIGINT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-25") {
		createTable(tableName: "system") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-26") {
		createTable(tableName: "system_component") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "system_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-27") {
		createTable(tableName: "system_environment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(4000)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(50)") {
				constraints(nullable: "false")
			}

			column(name: "system_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "environments_idx", type: "INT")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-28") {
		createTable(tableName: "test_report") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-29") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-30") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-31") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-32") {
		createIndex(indexName: "acl_object_identity", tableName: "acl_entry", unique: "true") {
			column(name: "acl_object_identity")

			column(name: "ace_order")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-33") {
		createIndex(indexName: "object_id_class", tableName: "acl_object_identity", unique: "true") {
			column(name: "object_id_class")

			column(name: "object_id_identity")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-34") {
		createIndex(indexName: "sid", tableName: "acl_sid", unique: "true") {
			column(name: "sid")

			column(name: "principal")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-35") {
		createIndex(indexName: "name", tableName: "application_release_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-36") {
		createIndex(indexName: "name", tableName: "application_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-37") {
		createIndex(indexName: "name", tableName: "module_deployment_test_state", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-38") {
		createIndex(indexName: "name", tableName: "module_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-39") {
		createIndex(indexName: "name", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-40") {
		createIndex(indexName: "name", tableName: "project_category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-41") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-42") {
		createIndex(indexName: "name", tableName: "source_control_role", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-43") {
		createIndex(indexName: "name", tableName: "source_control_server", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-44") {
		createIndex(indexName: "name", tableName: "source_control_user", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-45") {
		createIndex(indexName: "name", tableName: "system", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-46") {
		createIndex(indexName: "name", tableName: "system_component", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-47") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-48") {
		addForeignKeyConstraint(baseColumnNames: "acl_object_identity", baseTableName: "acl_entry", baseTableSchemaName: "carmdev", constraintName: "FK5302D47DB0D9DC4D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-49") {
		addForeignKeyConstraint(baseColumnNames: "sid", baseTableName: "acl_entry", baseTableSchemaName: "carmdev", constraintName: "FK5302D47D8FDB88D5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "acl_sid", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-50") {
		addForeignKeyConstraint(baseColumnNames: "object_id_class", baseTableName: "acl_object_identity", baseTableSchemaName: "carmdev", constraintName: "FK2A2BB00970422CC5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "acl_class", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-51") {
		addForeignKeyConstraint(baseColumnNames: "owner_sid", baseTableName: "acl_object_identity", baseTableSchemaName: "carmdev", constraintName: "FK2A2BB00990EC1949", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "acl_sid", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-52") {
		addForeignKeyConstraint(baseColumnNames: "parent_object", baseTableName: "acl_object_identity", baseTableSchemaName: "carmdev", constraintName: "FK2A2BB009A50290B8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "acl_object_identity", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-53") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "application", baseTableSchemaName: "carmdev", constraintName: "FK5CA40550D405E925", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "project", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-54") {
		addForeignKeyConstraint(baseColumnNames: "source_control_repository_id", baseTableName: "application", baseTableSchemaName: "carmdev", constraintName: "FK5CA405509CD63F61", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "source_control_repository", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-55") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "application", baseTableSchemaName: "carmdev", constraintName: "FK5CA40550BECBA7EF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "system", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-56") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "application", baseTableSchemaName: "carmdev", constraintName: "FK5CA4055032077FF5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application_type", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-57") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "application_deployment", baseTableSchemaName: "carmdev", constraintName: "FKA913991486A0584E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application_release", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-58") {
		addForeignKeyConstraint(baseColumnNames: "system_environment_id", baseTableName: "application_deployment", baseTableSchemaName: "carmdev", constraintName: "FKA91399143DDC786", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "system_environment", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-59") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_release", baseTableSchemaName: "carmdev", constraintName: "FKF1C284183F5AAC45", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-60") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "application_release", baseTableSchemaName: "carmdev", constraintName: "FKF1C284182BB5E369", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application_release_test_state", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-61") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "application_role", baseTableSchemaName: "carmdev", constraintName: "FKB06053F5AAC45", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-62") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "application_role", baseTableSchemaName: "carmdev", constraintName: "FKB06055DCD227", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "source_control_role", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-63") {
		addForeignKeyConstraint(baseColumnNames: "source_control_user_id", baseTableName: "application_role", baseTableSchemaName: "carmdev", constraintName: "FKB060589C21D01", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "source_control_user", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-64") {
		addForeignKeyConstraint(baseColumnNames: "application_id", baseTableName: "module", baseTableSchemaName: "carmdev", constraintName: "FKC04BA66C3F5AAC45", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-65") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "module", baseTableSchemaName: "carmdev", constraintName: "FKC04BA66CA3133A1B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module_type", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-66") {
		addForeignKeyConstraint(baseColumnNames: "application_deployment_id", baseTableName: "module_deployment", baseTableSchemaName: "carmdev", constraintName: "FKD77B6E789EBB9326", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application_deployment", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-67") {
		addForeignKeyConstraint(baseColumnNames: "module_release_id", baseTableName: "module_deployment", baseTableSchemaName: "carmdev", constraintName: "FKD77B6E78F8BB79CC", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module_release", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-68") {
		addForeignKeyConstraint(baseColumnNames: "test_state_id", baseTableName: "module_deployment", baseTableSchemaName: "carmdev", constraintName: "FKD77B6E78E6B5D5AF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module_deployment_test_state", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-69") {
		addForeignKeyConstraint(baseColumnNames: "application_release_id", baseTableName: "module_release", baseTableSchemaName: "carmdev", constraintName: "FKFFB5493486A0584E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "application_release", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-70") {
		addForeignKeyConstraint(baseColumnNames: "module_id", baseTableName: "module_release", baseTableSchemaName: "carmdev", constraintName: "FKFFB54934142AE2CF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-71") {
		addForeignKeyConstraint(baseColumnNames: "module_system_components_id", baseTableName: "module_system_component", baseTableSchemaName: "carmdev", constraintName: "FKDCCBC60173B03C8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-72") {
		addForeignKeyConstraint(baseColumnNames: "system_component_id", baseTableName: "module_system_component", baseTableSchemaName: "carmdev", constraintName: "FKDCCBC60183CA246", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "system_component", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-73") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "project", baseTableSchemaName: "carmdev", constraintName: "FKED904B19F681AE3E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "project_category", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-74") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_repository", baseTableSchemaName: "carmdev", constraintName: "FKA0A83A10E354EBC7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "source_control_server", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-75") {
		addForeignKeyConstraint(baseColumnNames: "server_id", baseTableName: "source_control_user", baseTableSchemaName: "carmdev", constraintName: "FKEDA05711E354EBC7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "source_control_server", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-76") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "source_control_user", baseTableSchemaName: "carmdev", constraintName: "FKEDA05711AA846DD3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-77") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_component", baseTableSchemaName: "carmdev", constraintName: "FK1982DAEDBECBA7EF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "system", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-78") {
		addForeignKeyConstraint(baseColumnNames: "system_id", baseTableName: "system_environment", baseTableSchemaName: "carmdev", constraintName: "FKF9DD3403BECBA7EF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "system", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-79") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "carmdev", constraintName: "FK143BF46A559A9F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}

	changeSet(author: "shaun (generated)", id: "1326468103517-80") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "carmdev", constraintName: "FK143BF46AAA846DD3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "carmdev", referencesUniqueColumn: "false")
	}
}
