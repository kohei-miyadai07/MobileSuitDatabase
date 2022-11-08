-- Project Name : MSDB_ER図_Ver2
-- Date/Time    : 2022/08/11 9:29:08
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

-- 能力
--* RestoreFromTempTable
create table Ability (
  ms_id VARCHAR(8) not null
  , feature_id VARCHAR(8) not null
  , detail TEXT
  , constraint Ability_PKC primary key (ms_id,feature_id)
) ;

-- 機能
--* RestoreFromTempTable
create table Feature (
  feature_id VARCHAR(8) not null
  , feature_name VARCHAR(90) not null
  , detail TEXT
  , constraint Feature_PKC primary key (feature_id)
) ;

-- 登場作品
--* RestoreFromTempTable
create table Works (
  ms_id VARCHAR(8) not null
  , opus_id VARCHAR(8) not null
  , detail TEXT
  , constraint Works_PKC primary key (ms_id,opus_id)
) ;

-- 作品
--* RestoreFromTempTable
create table Opus (
  opus_id VARCHAR(8) not null
  , opus_name VARCHAR(90) not null
  , detail TEXT
  , constraint Opus_PKC primary key (opus_id)
) ;

-- 運用母艦
--* RestoreFromTempTable
create table Operation_Carrier (
  ms_id VARCHAR(8) not null
  , ship_id VARCHAR(8) not null
  , detail TEXT
  , constraint Operation_Carrier_PKC primary key (ms_id,ship_id)
) ;

-- 艦船
--* RestoreFromTempTable
create table Ship (
  ship_id VARCHAR(8) not null
  , shop_name VARCHAR(90) not null
  , detail TEXT
  , constraint Ship_PKC primary key (ship_id)
) ;

-- 搭乗者
--* RestoreFromTempTable
create table Passenger (
  ms_id VARCHAR(8) not null
  , pilot_id VARCHAR(8) not null
  , detail TEXT
  , constraint Passenger_PKC primary key (ms_id,pilot_id)
) ;

-- パイロット
--* RestoreFromTempTable
create table Pilot (
  pilot_id VARCHAR(8) not null
  , pilot_name VARCHAR(90) not null
  , detail TEXT
  , constraint Pilot_PKC primary key (pilot_id)
) ;

-- 所属
--* RestoreFromTempTable
create table Belonging (
  ms_id VARCHAR(8) not null
  , team_id VARCHAR(8) not null
  , detail TEXT
  , constraint Belonging_PKC primary key (ms_id,team_id)
) ;

-- チーム
--* RestoreFromTempTable
create table Team (
  team_id VARCHAR(8) not null
  , team_name VARCHAR(90) not null
  , detail TEXT
  , constraint Team_PKC primary key (team_id)
) ;

-- 開発者
--* RestoreFromTempTable
create table Developer (
  ms_id VARCHAR(8) not null
  , person_id VARCHAR(8) not null
  , detail TEXT
  , constraint Developer_PKC primary key (ms_id,person_id)
) ;

-- 人物
--* RestoreFromTempTable
create table Person (
  person_id VARCHAR(8) not null
  , person_name VARCHAR(90) not null
  , detail TEXT
  , constraint Person_PKC primary key (person_id)
) ;

-- 開発拠点
--* RestoreFromTempTable
create table Development_Base (
  ms_id VARCHAR(8) not null
  , base_id VARCHAR(8) not null
  , detail TEXT
  , constraint Development_Base_PKC primary key (ms_id,base_id)
) ;

-- 拠点
--* RestoreFromTempTable
create table Base (
  base_id VARCHAR(8) not null
  , base_name VARCHAR(90) not null
  , detail TEXT
  , constraint Base_PKC primary key (base_id)
) ;

-- 開発組織
--* RestoreFromTempTable
create table Development_Org (
  ms_id VARCHAR(8) not null
  , org_id VARCHAR(8) not null
  , detail TEXT
  , constraint Development_Org_PKC primary key (ms_id,org_id)
) ;

-- 組織
--* RestoreFromTempTable
create table Organization (
  org_id VARCHAR(8) not null
  , org_name VARCHAR(90) not null
  , detail TEXT
  , constraint Organization_PKC primary key (org_id)
) ;

-- カテゴライズ
--* RestoreFromTempTable
create table Categorize (
  ms_id VARCHAR(8) not null
  , class_id VARCHAR(8) not null
  , detail TEXT
  , constraint Categorize_PKC primary key (ms_id,class_id)
) ;

-- 分類
--* RestoreFromTempTable
create table CLASS (
  class_id VARCHAR(8) not null
  , class_name VARCHAR(90) not null
  , detail TEXT
  , constraint CLASS_PKC primary key (class_id)
) ;

-- 装備
--* RestoreFromTempTable
create table Equipment (
  ms_id VARCHAR(8) not null
  , arms_id VARCHAR(8) not null
  , number_equipment INTEGER
  , detail TEXT
  , constraint Equipment_PKC primary key (ms_id,arms_id)
) ;

-- 武器
--* RestoreFromTempTable
create table Arms (
  arms_id VARCHAR(8) not null
  , arms_name VARCHAR(90) not null
  , detail TEXT
  , constraint Arms_PKC primary key (arms_id)
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
  , effective_sensor_radius BIGINT
  , generator_output BIGINT
  , total_thrusters_output BIGINT
  , ms_overview TEXT
  , action TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version VARCHAR(3)
  , constraint MobileSuit_PKC primary key (ms_id)
) ;

comment on table Ability is '能力';
comment on column Ability.ms_id is '機体ID';
comment on column Ability.feature_id is '機能ID';
comment on column Ability.detail is '詳細';

comment on table Feature is '機能';
comment on column Feature.feature_id is '機能ID';
comment on column Feature.feature_name is '機能名';
comment on column Feature.detail is '詳細';

comment on table Works is '登場作品';
comment on column Works.ms_id is '機体ID';
comment on column Works.opus_id is '作品ID';
comment on column Works.detail is '詳細';

comment on table Opus is '作品';
comment on column Opus.opus_id is '作品ID';
comment on column Opus.opus_name is '作品名';
comment on column Opus.detail is '詳細';

comment on table Operation_Carrier is '運用母艦';
comment on column Operation_Carrier.ms_id is '機体ID';
comment on column Operation_Carrier.ship_id is '艦船ID';
comment on column Operation_Carrier.detail is '詳細';

comment on table Ship is '艦船';
comment on column Ship.ship_id is '艦船ID';
comment on column Ship.shop_name is '艦船名';
comment on column Ship.detail is '詳細';

comment on table Passenger is '搭乗者';
comment on column Passenger.ms_id is '機体ID';
comment on column Passenger.pilot_id is 'パイロットID';
comment on column Passenger.detail is '詳細';

comment on table Pilot is 'パイロット';
comment on column Pilot.pilot_id is 'パイロットID';
comment on column Pilot.pilot_name is 'パイロット名';
comment on column Pilot.detail is '詳細';

comment on table Belonging is '所属';
comment on column Belonging.ms_id is '機体ID';
comment on column Belonging.team_id is 'チームID';
comment on column Belonging.detail is '詳細';

comment on table Team is 'チーム';
comment on column Team.team_id is 'チームID';
comment on column Team.team_name is 'チーム名';
comment on column Team.detail is '詳細';

comment on table Developer is '開発者';
comment on column Developer.ms_id is '機体ID';
comment on column Developer.person_id is '人物ID';
comment on column Developer.detail is '詳細';

comment on table Person is '人物';
comment on column Person.person_id is '人物ID';
comment on column Person.person_name is '人物名';
comment on column Person.detail is '詳細';

comment on table Development_Base is '開発拠点';
comment on column Development_Base.ms_id is '機体ID';
comment on column Development_Base.base_id is '拠点ID';
comment on column Development_Base.detail is '詳細';

comment on table Base is '拠点';
comment on column Base.base_id is '拠点ID';
comment on column Base.base_name is '拠点名';
comment on column Base.detail is '詳細';

comment on table Development_Org is '開発組織';
comment on column Development_Org.ms_id is '機体ID';
comment on column Development_Org.org_id is '組織ID';
comment on column Development_Org.detail is '詳細';

comment on table Organization is '組織';
comment on column Organization.org_id is '組織ID';
comment on column Organization.org_name is '組織名';
comment on column Organization.detail is '詳細';

comment on table Categorize is 'カテゴライズ';
comment on column Categorize.ms_id is '機体ID';
comment on column Categorize.class_id is '分類ID';
comment on column Categorize.detail is '詳細';

comment on table CLASS is '分類';
comment on column CLASS.class_id is '分類ID';
comment on column CLASS.class_name is '分類名';
comment on column CLASS.detail is '詳細';

comment on table Equipment is '装備';
comment on column Equipment.ms_id is '機体ID';
comment on column Equipment.arms_id is '武器ID';
comment on column Equipment.number_equipment is '装備数';
comment on column Equipment.detail is '詳細';

comment on table Arms is '武器';
comment on column Arms.arms_id is '武器ID';
comment on column Arms.arms_name is '武器名';
comment on column Arms.detail is '詳細';

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
comment on column MobileSuit.effective_sensor_radius is 'センサー有効半径';
comment on column MobileSuit.generator_output is 'ジェネレーター出力';
comment on column MobileSuit.total_thrusters_output is 'スラスター総推力';
comment on column MobileSuit.ms_overview is '機体概要';
comment on column MobileSuit.action is '活躍';
comment on column MobileSuit.insert_date is '登録日時';
comment on column MobileSuit.update_date is '更新日時';
comment on column MobileSuit.version is 'バージョン';

