-- Project Name : noname
-- Date/Time    : 2022/07/24 10:19:19
-- Author       : mk26s
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- カテゴライズ
--* RestoreFromTempTable
create table Categorice (
  grouping_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint Categorice_PKC primary key (grouping_id,ms_id)
) ;

-- 運用組織
--* RestoreFromTempTable
create table Unit (
  org_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint Unit_PKC primary key (org_id,ms_id)
) ;

-- 装備
--* RestoreFromTempTable
create table Equipment (
  arms_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , number_equipment INTEGER
  , constraint Equipment_PKC primary key (arms_id,ms_id)
) ;

-- 能力
--* RestoreFromTempTable
create table Ability (
  func_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint Ability_PKC primary key (func_id,ms_id)
) ;

-- 登場作品
--* RestoreFromTempTable
create table AppearanceWork (
  opus_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint AppearanceWork_PKC primary key (opus_id,ms_id)
) ;

-- 運用母艦
--* RestoreFromTempTable
create table OperationMotherShip (
  ship_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint OperationMotherShip_PKC primary key (ship_id,ms_id)
) ;

-- パイロット
--* RestoreFromTempTable
create table Pilot (
  person_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint Pilot_PKC primary key (person_id,ms_id)
) ;

-- 開発者
--* RestoreFromTempTable
create table Developer (
  person_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint Developer_PKC primary key (person_id,ms_id)
) ;

-- 開発拠点
--* RestoreFromTempTable
create table DevBase (
  base_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint DevBase_PKC primary key (base_id,ms_id)
) ;

-- 開発組織
--* RestoreFromTempTable
create table DevelopOrg (
  org_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , constraint DevelopOrg_PKC primary key (org_id,ms_id)
) ;

-- 武器
--* RestoreFromTempTable
create table Arms (
  arms_id VARCHAR(8) not null
  , arms_name VARCHAR(90)
  , detail TEXT
  , constraint Arms_PKC primary key (arms_id)
) ;

-- 機能
--* RestoreFromTempTable
create table Function (
  func_id VARCHAR(8) not null
  , func_name VARCHAR(90)
  , detail TEXT
  , constraint Function_PKC primary key (func_id)
) ;

-- 作品
--* RestoreFromTempTable
create table Opus (
  opus_id VARCHAR(8) not null
  , opus_name VARCHAR(90) not null
  , detail text
  , constraint Opus_PKC primary key (opus_id)
) ;

-- 艦船
--* RestoreFromTempTable
create table Ship (
  ship_id VARCHAR(8) not null
  , ship_name VARCHAR(90)
  , detail TEXT
  , constraint Ship_PKC primary key (ship_id)
) ;

-- 人物
--* RestoreFromTempTable
create table Person (
  person_id VARCHAR(8) not null
  , parson_name VARCHAR(90)
  , detail TEXT
  , constraint Person_PKC primary key (person_id)
) ;

-- 拠点
--* RestoreFromTempTable
create table Base (
  base_id VARCHAR(8) not null
  , base_name VARCHAR(90)
  , detail TEXT
  , constraint Base_PKC primary key (base_id)
) ;

-- 組織
--* RestoreFromTempTable
create table Organization (
  org_id VARCHAR(8) not null
  , org_name VARCHAR(90)
  , detail TEXT
  , constraint Organization_PKC primary key (org_id)
) ;

-- 分類
--* RestoreFromTempTable
create table Grouping (
  grouping_id VARCHAR(8) not null
  , grouping_name VARCHAR(90) not null
  , detail text
  , constraint Grouping_PKC primary key (grouping_id)
) ;

-- モビルスーツ
--* RestoreFromTempTable
create table MobileSuit (
  ms_id VARCHAR(8) not null
  , model_number VARCHAR(50) not null
  , ms_name VARCHAR(90) not null
  , ms_url VARCHAR(100) not null
  , head_height DECIMAL(5,2)
  , overall_height DECIMAL(5,2)
  , weight DECIMAL(5,2)
  , total_weight DECIMAL(5,2)
  , power_source VARCHAR(90)
  , material VARCHAR(90)
  , sensor_effective_radius BIGINT
  , generator_output BIGINT
  , total_thrusters_output BIGINT
  , ms_overview VARCHAR(2000)
  , action VARCHAR(2000)
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version VARCHAR(3) not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;

comment on table Categorice is 'カテゴライズ';
comment on column Categorice.grouping_id is '分類ID';
comment on column Categorice.ms_id is '機体ID';
comment on column Categorice.detail is '詳細';

comment on table Unit is '運用組織';
comment on column Unit.org_id is '組織ID';
comment on column Unit.ms_id is '機体ID';
comment on column Unit.detail is '詳細';

comment on table Equipment is '装備';
comment on column Equipment.arms_id is '武器ID';
comment on column Equipment.ms_id is '機体ID';
comment on column Equipment.detail is '詳細';
comment on column Equipment.number_equipment is '装備数';

comment on table Ability is '能力';
comment on column Ability.func_id is '機能ID';
comment on column Ability.ms_id is '機体ID';
comment on column Ability.detail is '詳細';

comment on table AppearanceWork is '登場作品';
comment on column AppearanceWork.opus_id is '作品ID';
comment on column AppearanceWork.ms_id is '機体ID';
comment on column AppearanceWork.detail is '詳細';

comment on table OperationMotherShip is '運用母艦';
comment on column OperationMotherShip.ship_id is '艦船ID';
comment on column OperationMotherShip.ms_id is '機体ID';
comment on column OperationMotherShip.detail is '詳細';

comment on table Pilot is 'パイロット';
comment on column Pilot.person_id is '人物ID';
comment on column Pilot.ms_id is '機体ID';
comment on column Pilot.detail is '詳細';

comment on table Developer is '開発者';
comment on column Developer.person_id is '人物ID';
comment on column Developer.ms_id is '機体ID';
comment on column Developer.detail is '詳細';

comment on table DevBase is '開発拠点';
comment on column DevBase.base_id is '拠点ID';
comment on column DevBase.ms_id is '機体ID';
comment on column DevBase.detail is '詳細';

comment on table DevelopOrg is '開発組織';
comment on column DevelopOrg.org_id is '組織ID';
comment on column DevelopOrg.ms_id is '機体ID';
comment on column DevelopOrg.detail is '詳細';

comment on table Arms is '武器';
comment on column Arms.arms_id is '武器ID';
comment on column Arms.arms_name is '武器名';
comment on column Arms.detail is '詳細';

comment on table Function is '機能';
comment on column Function.func_id is '機能ID';
comment on column Function.func_name is '機能名';
comment on column Function.detail is '詳細';

comment on table Opus is '作品';
comment on column Opus.opus_id is '作品ID';
comment on column Opus.opus_name is '作品名';
comment on column Opus.detail is '詳細';

comment on table Ship is '艦船';
comment on column Ship.ship_id is '艦船ID';
comment on column Ship.ship_name is '艦船名';
comment on column Ship.detail is '詳細';

comment on table Person is '人物';
comment on column Person.person_id is '人物ID';
comment on column Person.parson_name is '人物名';
comment on column Person.detail is '詳細';

comment on table Base is '拠点';
comment on column Base.base_id is '拠点ID';
comment on column Base.base_name is '拠点名';
comment on column Base.detail is '詳細';

comment on table Organization is '組織';
comment on column Organization.org_id is '組織ID';
comment on column Organization.org_name is '組織名';
comment on column Organization.detail is '詳細';

comment on table Grouping is '分類';
comment on column Grouping.grouping_id is '分類ID';
comment on column Grouping.grouping_name is '分類名';
comment on column Grouping.detail is '詳細';

comment on table MobileSuit is 'モビルスーツ';
comment on column MobileSuit.ms_id is '機体ID';
comment on column MobileSuit.model_number is '型式番号';
comment on column MobileSuit.ms_name is '機体名';
comment on column MobileSuit.ms_url is '機体写真URL';
comment on column MobileSuit.head_height is '頭高頂';
comment on column MobileSuit.overall_height is '全高';
comment on column MobileSuit.weight is '本体重量';
comment on column MobileSuit.total_weight is '全備重量';
comment on column MobileSuit.power_source is '動力源';
comment on column MobileSuit.material is '装甲材質';
comment on column MobileSuit.sensor_effective_radius is 'センサー有効半径';
comment on column MobileSuit.generator_output is 'ジェネレーター出力';
comment on column MobileSuit.total_thrusters_output is 'スラスター総推力';
comment on column MobileSuit.ms_overview is '機体概要';
comment on column MobileSuit.action is '活躍';
comment on column MobileSuit.insert_date is '登録日時';
comment on column MobileSuit.update_date is '更新日時';
comment on column MobileSuit.version is 'バージョン';

