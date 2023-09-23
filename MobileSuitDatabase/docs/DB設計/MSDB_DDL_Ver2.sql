-- Project Name : MSDB_ER図_Ver2
-- Date/Time    : 2023/09/17 10:58:18
-- Author       : km57r
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
-- * RestoreFromTempTable
create table Ability (
  ms_id VARCHAR(90) not null
  , feature_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Ability_PKC primary key (ms_id,feature_id)
) ;

-- 機能
-- * RestoreFromTempTable
create table Feature (
  feature_id VARCHAR(90) not null
  , feature_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Feature_PKC primary key (feature_id)
) ;

-- 登場作品
-- * RestoreFromTempTable
create table Works (
  ms_id VARCHAR(90) not null
  , opus_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Works_PKC primary key (ms_id,opus_id)
) ;

-- 作品
-- * RestoreFromTempTable
create table Opus (
  opus_id VARCHAR(90) not null
  , opus_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Opus_PKC primary key (opus_id)
) ;

-- 運用母艦
-- * RestoreFromTempTable
create table Operation_Carrier (
  ms_id VARCHAR(90) not null
  , ship_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Operation_Carrier_PKC primary key (ms_id,ship_id)
) ;

-- 艦船
-- * RestoreFromTempTable
create table Ship (
  ship_id VARCHAR(90) not null
  , shop_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Ship_PKC primary key (ship_id)
) ;

-- 搭乗者
-- * RestoreFromTempTable
create table Passenger (
  ms_id VARCHAR(90) not null
  , pilot_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Passenger_PKC primary key (ms_id,pilot_id)
) ;

-- パイロット
-- * RestoreFromTempTable
create table Pilot (
  pilot_id VARCHAR(90) not null
  , pilot_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Pilot_PKC primary key (pilot_id)
) ;

-- 所属
-- * RestoreFromTempTable
create table Belonging (
  ms_id VARCHAR(90) not null
  , team_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Belonging_PKC primary key (ms_id,team_id)
) ;

-- チーム
-- * RestoreFromTempTable
create table Team (
  team_id VARCHAR(90) not null
  , team_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Team_PKC primary key (team_id)
) ;

-- 開発者
-- * RestoreFromTempTable
create table Developer (
  ms_id VARCHAR(90) not null
  , person_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Developer_PKC primary key (ms_id,person_id)
) ;

-- 人物
-- * RestoreFromTempTable
create table Person (
  person_id VARCHAR(90) not null
  , person_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Person_PKC primary key (person_id)
) ;

-- 開発拠点
-- * RestoreFromTempTable
create table Development_Base (
  ms_id VARCHAR(90) not null
  , base_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Development_Base_PKC primary key (ms_id,base_id)
) ;

-- 拠点
-- * RestoreFromTempTable
create table Base (
  base_id VARCHAR(90) not null
  , base_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Base_PKC primary key (base_id)
) ;

-- 開発組織
-- * RestoreFromTempTable
create table Development_Org (
  ms_id VARCHAR(90) not null
  , org_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Development_Org_PKC primary key (ms_id,org_id)
) ;

-- 組織
-- * RestoreFromTempTable
create table Organization (
  org_id VARCHAR(90) not null
  , org_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Organization_PKC primary key (org_id)
) ;

-- カテゴライズ
-- * RestoreFromTempTable
create table Categorize (
  ms_id VARCHAR(90) not null
  , class_id VARCHAR(90) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Categorize_PKC primary key (ms_id,class_id)
) ;

-- 分類
-- * RestoreFromTempTable
create table Class (
  class_id VARCHAR(90) not null
  , class_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Class_PKC primary key (class_id)
) ;

-- 装備
-- * RestoreFromTempTable
create table Equipment (
  ms_id VARCHAR(90) not null
  , arms_id VARCHAR(90) not null
  , number_equipment INTEGER
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Equipment_PKC primary key (ms_id,arms_id)
) ;

-- 武器
-- * RestoreFromTempTable
create table Arms (
  arms_id VARCHAR(90) not null
  , arms_name VARCHAR(300) not null
  , detail TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint Arms_PKC primary key (arms_id)
) ;

-- モビルスーツ
-- * RestoreFromTempTable
create table MobileSuit (
  ms_id VARCHAR(90) not null
  , model_number VARCHAR(300) not null
  , ms_name VARCHAR(300) not null
  , ms_url TEXT not null
  , head_height DECIMAL(5,2)
  , overall_height DECIMAL(5,2)
  , weight DECIMAL(5,2)
  , total_weight DECIMAL(5,2)
  , power_source VARCHAR(300)
  , material VARCHAR(300)
  , effective_sensor_radius BIGINT
  , generator_output BIGINT
  , total_thrusters_output BIGINT
  , ms_overview TEXT
  , action TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version integer not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;

alter table Ability
  add constraint Ability_FK1 foreign key (feature_id) references Feature(feature_id)
  on delete cascade;

alter table Ability
  add constraint Ability_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Works
  add constraint Works_FK1 foreign key (opus_id) references Opus(opus_id)
  on delete cascade;

alter table Works
  add constraint Works_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Operation_Carrier
  add constraint Operation_Carrier_FK1 foreign key (ship_id) references Ship(ship_id)
  on delete cascade;

alter table Operation_Carrier
  add constraint Operation_Carrier_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Passenger
  add constraint Passenger_FK1 foreign key (pilot_id) references Pilot(pilot_id)
  on delete cascade;

alter table Passenger
  add constraint Passenger_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Belonging
  add constraint Belonging_FK1 foreign key (team_id) references Team(team_id)
  on delete cascade;

alter table Belonging
  add constraint Belonging_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Developer
  add constraint Developer_FK1 foreign key (person_id) references Person(person_id)
  on delete cascade;

alter table Developer
  add constraint Developer_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Development_Base
  add constraint Development_Base_FK1 foreign key (base_id) references Base(base_id)
  on delete cascade;

alter table Development_Base
  add constraint Development_Base_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Development_Org
  add constraint Development_Org_FK1 foreign key (org_id) references Organization(org_id)
  on delete cascade;

alter table Development_Org
  add constraint Development_Org_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Categorize
  add constraint Categorize_FK1 foreign key (class_id) references Class(class_id)
  on delete cascade;

alter table Categorize
  add constraint Categorize_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

alter table Equipment
  add constraint Equipment_FK1 foreign key (arms_id) references Arms(arms_id)
  on delete cascade;

alter table Equipment
  add constraint Equipment_FK2 foreign key (ms_id) references MobileSuit(ms_id)
  on delete cascade;

comment on table Ability is '能力';
comment on column Ability.ms_id is '機体ID';
comment on column Ability.feature_id is '機能ID';
comment on column Ability.detail is '詳細';
comment on column Ability.insert_date is '登録日時';
comment on column Ability.update_date is '更新日時';
comment on column Ability.version is 'バージョン';

comment on table Feature is '機能';
comment on column Feature.feature_id is '機能ID';
comment on column Feature.feature_name is '機能名';
comment on column Feature.detail is '詳細';
comment on column Feature.insert_date is '登録日時';
comment on column Feature.update_date is '更新日時';
comment on column Feature.version is 'バージョン';

comment on table Works is '登場作品';
comment on column Works.ms_id is '機体ID';
comment on column Works.opus_id is '作品ID';
comment on column Works.detail is '詳細';
comment on column Works.insert_date is '登録日時';
comment on column Works.update_date is '更新日時';
comment on column Works.version is 'バージョン';

comment on table Opus is '作品';
comment on column Opus.opus_id is '作品ID';
comment on column Opus.opus_name is '作品名';
comment on column Opus.detail is '詳細';
comment on column Opus.insert_date is '登録日時';
comment on column Opus.update_date is '更新日時';
comment on column Opus.version is 'バージョン';

comment on table Operation_Carrier is '運用母艦';
comment on column Operation_Carrier.ms_id is '機体ID';
comment on column Operation_Carrier.ship_id is '艦船ID';
comment on column Operation_Carrier.detail is '詳細';
comment on column Operation_Carrier.insert_date is '登録日時';
comment on column Operation_Carrier.update_date is '更新日時';
comment on column Operation_Carrier.version is 'バージョン';

comment on table Ship is '艦船';
comment on column Ship.ship_id is '艦船ID';
comment on column Ship.shop_name is '艦船名';
comment on column Ship.detail is '詳細';
comment on column Ship.insert_date is '登録日時';
comment on column Ship.update_date is '更新日時';
comment on column Ship.version is 'バージョン';

comment on table Passenger is '搭乗者';
comment on column Passenger.ms_id is '機体ID';
comment on column Passenger.pilot_id is 'パイロットID';
comment on column Passenger.detail is '詳細';
comment on column Passenger.insert_date is '登録日時';
comment on column Passenger.update_date is '更新日時';
comment on column Passenger.version is 'バージョン';

comment on table Pilot is 'パイロット';
comment on column Pilot.pilot_id is 'パイロットID';
comment on column Pilot.pilot_name is 'パイロット名';
comment on column Pilot.detail is '詳細';
comment on column Pilot.insert_date is '登録日時';
comment on column Pilot.update_date is '更新日時';
comment on column Pilot.version is 'バージョン';

comment on table Belonging is '所属';
comment on column Belonging.ms_id is '機体ID';
comment on column Belonging.team_id is 'チームID';
comment on column Belonging.detail is '詳細';
comment on column Belonging.insert_date is '登録日時';
comment on column Belonging.update_date is '更新日時';
comment on column Belonging.version is 'バージョン';

comment on table Team is 'チーム';
comment on column Team.team_id is 'チームID';
comment on column Team.team_name is 'チーム名';
comment on column Team.detail is '詳細';
comment on column Team.insert_date is '登録日時';
comment on column Team.update_date is '更新日時';
comment on column Team.version is 'バージョン';

comment on table Developer is '開発者';
comment on column Developer.ms_id is '機体ID';
comment on column Developer.person_id is '人物ID';
comment on column Developer.detail is '詳細';
comment on column Developer.insert_date is '登録日時';
comment on column Developer.update_date is '更新日時';
comment on column Developer.version is 'バージョン';

comment on table Person is '人物';
comment on column Person.person_id is '人物ID';
comment on column Person.person_name is '人物名';
comment on column Person.detail is '詳細';
comment on column Person.insert_date is '登録日時';
comment on column Person.update_date is '更新日時';
comment on column Person.version is 'バージョン';

comment on table Development_Base is '開発拠点';
comment on column Development_Base.ms_id is '機体ID';
comment on column Development_Base.base_id is '拠点ID';
comment on column Development_Base.detail is '詳細';
comment on column Development_Base.insert_date is '登録日時';
comment on column Development_Base.update_date is '更新日時';
comment on column Development_Base.version is 'バージョン';

comment on table Base is '拠点';
comment on column Base.base_id is '拠点ID';
comment on column Base.base_name is '拠点名';
comment on column Base.detail is '詳細';
comment on column Base.insert_date is '登録日時';
comment on column Base.update_date is '更新日時';
comment on column Base.version is 'バージョン';

comment on table Development_Org is '開発組織';
comment on column Development_Org.ms_id is '機体ID';
comment on column Development_Org.org_id is '組織ID';
comment on column Development_Org.detail is '詳細';
comment on column Development_Org.insert_date is '登録日時';
comment on column Development_Org.update_date is '更新日時';
comment on column Development_Org.version is 'バージョン';

comment on table Organization is '組織';
comment on column Organization.org_id is '組織ID';
comment on column Organization.org_name is '組織名';
comment on column Organization.detail is '詳細';
comment on column Organization.insert_date is '登録日時';
comment on column Organization.update_date is '更新日時';
comment on column Organization.version is 'バージョン';

comment on table Categorize is 'カテゴライズ';
comment on column Categorize.ms_id is '機体ID';
comment on column Categorize.class_id is '分類ID';
comment on column Categorize.detail is '詳細';
comment on column Categorize.insert_date is '登録日時';
comment on column Categorize.update_date is '更新日時';
comment on column Categorize.version is 'バージョン';

comment on table Class is '分類';
comment on column Class.class_id is '分類ID';
comment on column Class.class_name is '分類名';
comment on column Class.detail is '詳細';
comment on column Class.insert_date is '登録日時';
comment on column Class.update_date is '更新日時';
comment on column Class.version is 'バージョン';

comment on table Equipment is '装備';
comment on column Equipment.ms_id is '機体ID';
comment on column Equipment.arms_id is '武器ID';
comment on column Equipment.number_equipment is '装備数';
comment on column Equipment.detail is '詳細';
comment on column Equipment.insert_date is '登録日時';
comment on column Equipment.update_date is '更新日時';
comment on column Equipment.version is 'バージョン';

comment on table Arms is '武器';
comment on column Arms.arms_id is '武器ID';
comment on column Arms.arms_name is '武器名';
comment on column Arms.detail is '詳細';
comment on column Arms.insert_date is '登録日時';
comment on column Arms.update_date is '更新日時';
comment on column Arms.version is 'バージョン';

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

