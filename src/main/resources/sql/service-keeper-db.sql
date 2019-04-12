/*
Navicat SQLite Data Transfer

Source Server         : service-keeper
Source Server Version : 30808
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30808
File Encoding         : 65001

Date: 2019-04-12 18:31:19
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for command
-- ----------------------------
DROP TABLE IF EXISTS "main"."command";
CREATE TABLE "command" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"command"  TEXT,
"description"  TEXT,
"operation_type"  INTEGER,
"create_time"  TEXT,
"create_user"  TEXT,
"update_time"  TEXT,
"status"  TEXT
);

-- ----------------------------
-- Table structure for deploy_log
-- ----------------------------
DROP TABLE IF EXISTS "main"."deploy_log";
CREATE TABLE "deploy_log" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"service_name"  TEXT,
"service_version"  TEXT,
"ip"  TEXT,
"deploy_time"  TEXT,
"operator"  TEXT
);

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS "main"."server";
CREATE TABLE "server" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"ip"  TEXT,
"port"  TEXT,
"user"  TEXT,
"password"  TEXT
);

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS "main"."service";
CREATE TABLE "service" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"ip"  TEXT,
"port"  TEXT,
"docker_image_name"  TEXT,
"docker_image_tag"  TEXT,
"docker_container_name"  TEXT,
"docker_pull_command"  TEXT,
"docker_run_command"  TEXT,
"docker_start_command"  TEXT,
"docker_stop_command"  TEXT,
"docker_restart_command"  TEXT,
"status"  TEXT,
"create_time"  TEXT,
"update_time"  TEXT
);

-- ----------------------------
-- Table structure for service_command_mapping
-- ----------------------------
DROP TABLE IF EXISTS "main"."service_command_mapping";
CREATE TABLE "service_command_mapping" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"service_id"  INTEGER,
"command_id"  INTEGER,
"create_time"  TEXT,
"create_user"  TEXT,
"update_time"  TEXT
);

-- ----------------------------
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "main"."sqlite_sequence";
CREATE TABLE sqlite_sequence(name,seq);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "main"."user";
CREATE TABLE "user" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"password"  TEXT,
"create_time"  TEXT,
"last_sign_in_time"  TEXT,
"last_sign_in_ip"  TEXT
);
