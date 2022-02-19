--モビルスーツ
create table MobileSuit (
  ms_id VARCHAR(7) not null
  , model_number VARCHAR(20) not null
  , ms_name VARCHAR(20) not null
  , ms_url VARCHAR(1000) not null
  , head_height DECIMAL(5,2)
  , weight DECIMAL(5,2)
  , total_weight DECIMAL(5,2)
  , power_source VARCHAR(90)
  , material VARCHAR(90)
  , generator_output INTEGER
  , total_thrusters_output INTEGER
  , ms_overview VARCHAR(2000)
  , action VARCHAR(2000)
  , insert_date DATETIME not null
  , update_date DATETIME not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;


--武装
create table Armed (
  armed_id VARCHAR(7) not null
  , armed_name VARCHAR(90) not null
  , armed_explanation VARCHAR(2000)
  , constraint Armed_PKC primary key (armed_id)
) ;


--装備
create table Equipment (
  equipment_id VARCHAR(7) not null
  , ms_id VARCHAR(7) not null
  , ms_name VARCHAR(20) not null
  , armed_id VARCHAR(7) not null
  , armed_name VARCHAR(90) not null
  , number_equipment INTEGER
  , constraint Equipment_PKC primary key (equipment_id)
) ;
