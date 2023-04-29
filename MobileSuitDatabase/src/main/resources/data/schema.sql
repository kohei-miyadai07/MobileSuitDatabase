-- 装備
create table Equipment (
  ms_id VARCHAR(8) not null
  , arms_id VARCHAR(8) not null
  , number_equipment INTEGER
  , detail TEXT
  , constraint Equipment_PKC primary key (ms_id,arms_id)
) ;

-- 武器
create table Arms (
  arms_id VARCHAR(8) not null
  , arms_name VARCHAR(90) not null
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
  , effective_sensor_radius BIGINT
  , generator_output BIGINT
  , total_thrusters_output BIGINT
  , ms_overview TEXT
  , action TEXT
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , version INTEGER not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;

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

