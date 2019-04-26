/*
 Navicat Premium Data Transfer

 Source Server         : service-keeper
 Source Server Type    : SQLite
 Source Server Version : 3008008
 Source Database       : main

 Target Server Type    : SQLite
 Target Server Version : 3008008
 File Encoding         : utf-8

 Date: 04/26/2019 16:34:39 PM
*/

PRAGMA foreign_keys = false;

-- ----------------------------
--  Table structure for _deploy_log_old_20190425
-- ----------------------------
DROP TABLE IF EXISTS "_deploy_log_old_20190425";
CREATE TABLE "_deploy_log_old_20190425" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"service_name"  TEXT,
"service_version"  TEXT,
"ip"  TEXT,
"deploy_time"  TEXT,
"operator"  TEXT
);

-- ----------------------------
--  Table structure for _deploy_log_old_20190425_1
-- ----------------------------
DROP TABLE IF EXISTS "_deploy_log_old_20190425_1";
CREATE TABLE "_deploy_log_old_20190425_1" (
	 "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	 "service_id" TEXT,
	 "server_id" TEXT,
	 "log_file_path" TEXT,
	 "create_time" TEXT,
	 "operator" TEXT
);
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("_deploy_log_old_20190425_1", '0');

-- ----------------------------
--  Table structure for deployment_log
-- ----------------------------
DROP TABLE IF EXISTS "deployment_log";
CREATE TABLE "deployment_log" (
	 "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	 "service_id" TEXT,
	 "server_id" TEXT,
	 "operation" TEXT,
	 "log_file_path" TEXT,
	 "create_time" TEXT,
	 "operator" TEXT
);
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("deployment_log", '21');

-- ----------------------------
--  Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS "server";
CREATE TABLE "server" (
	 "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	 "name" TEXT,
	 "ip" TEXT,
	 "port" TEXT,
	 "protocol" TEXT,
	 "user" TEXT,
	 "password" TEXT
);
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("server", '8');

-- ----------------------------
--  Table structure for server_service_mapping
-- ----------------------------
DROP TABLE IF EXISTS "server_service_mapping";
CREATE TABLE "server_service_mapping" (
	 "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	 "server_id" INTEGER,
	 "service_id" INTEGER,
	 "service_status" TEXT,
	 "create_time" TEXT,
	 "update_time" TEXT,
	 "operator" TEXT
);
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("server_service_mapping", '71');

-- ----------------------------
--  Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS "service";
CREATE TABLE "service" (
	 "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	 "name" TEXT,
	 "port" TEXT,
	 "docker_image_name" TEXT,
	 "docker_image_tag" TEXT,
	 "docker_container_name" TEXT,
	 "docker_pull_command" TEXT,
	 "docker_run_command" TEXT,
	 "docker_start_command" TEXT,
	 "docker_stop_command" TEXT,
	 "docker_rm_command" TEXT,
	 "docker_restart_command" TEXT,
	 "status" TEXT,
	 "create_time" TEXT,
	 "update_time" TEXT
);
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("service", '25');

-- ----------------------------
--  Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"password"  TEXT,
"create_time"  TEXT,
"last_sign_in_time"  TEXT,
"last_sign_in_ip"  TEXT
);

PRAGMA foreign_keys = true;
