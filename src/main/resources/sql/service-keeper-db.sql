/*
 Navicat Premium Data Transfer

 Source Server         : service-keeper
 Source Server Type    : SQLite
 Source Server Version : 3008008
 Source Database       : main

 Target Server Type    : SQLite
 Target Server Version : 3008008
 File Encoding         : utf-8

 Date: 04/23/2019 14:51:48 PM
*/

PRAGMA foreign_keys = false;

-- ----------------------------
--  Table structure for deploy_log
-- ----------------------------
DROP TABLE IF EXISTS "deploy_log";
CREATE TABLE "deploy_log" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"service_name"  TEXT,
"service_version"  TEXT,
"ip"  TEXT,
"deploy_time"  TEXT,
"operator"  TEXT
);

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
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("server", '5');

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
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("server_service_mapping", '48');

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
INSERT INTO "main".sqlite_sequence (name, seq) VALUES ("service", '22');

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
