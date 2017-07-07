databaseChangeLog = {

    changeSet(author: "NAVER (generated)", id: "1499318051780-1") {
        createTable(tableName: "board") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "writer", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "TEXT") {
                constraints(nullable: "false")
            }

            column(name: "pub_date", type: "datetime(6)") {
                constraints(nullable: "false")
            }

            column(name: "author", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "NAVER (generated)", id: "1499318051780-2") {
        createTable(tableName: "board001") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "writer", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pub_date", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "author", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "NAVER (generated)", id: "1499318051780-3") {
        createTable(tableName: "content") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "image_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "mod_date", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pub_date", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "NAVER (generated)", id: "1499318051780-4") {
        createTable(tableName: "vehicle") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }
}
