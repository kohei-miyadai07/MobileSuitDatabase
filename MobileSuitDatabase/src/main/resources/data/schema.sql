-- Project Name : noname
-- Date/Time    : 2022/06/16 7:33:40
-- Author       : mk26s
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- 武装
create table Armed (
  arms_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , detail TEXT
  , number_equipment INTEGER
  , constraint Armed_PKC primary key (arms_id,ms_id)
) ;

-- 武器
create table Arms (
  arms_id VARCHAR(8) not null
  , arms_name VARCHAR(90)
  , detail TEXT
  , constraint Arms_PKC primary key (arms_id)
) ;

-- モビルスーツ
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

comment on table Armed is '武装';
comment on column Armed.arms_id is '武器ID';
comment on column Armed.ms_id is '機体ID';
comment on column Armed.detail is '詳細';
comment on column Armed.number_equipment is '装備数';

comment on table arms is '武器';
comment on column arms.arms_id is '武器ID';
comment on column arms.arms_name is '武器名';
comment on column arms.detail is '詳細';

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

