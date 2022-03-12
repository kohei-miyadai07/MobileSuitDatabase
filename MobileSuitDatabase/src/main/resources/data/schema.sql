--モビルスーツ
create table MobileSuit (
  ms_id VARCHAR(8) not null
  , model_number VARCHAR(50) not null
  , ms_name VARCHAR(90) not null
  , ms_url VARCHAR(100) not null
  , head_height DECIMAL(5,2)
  , weight DECIMAL(5,2)
  , total_weight DECIMAL(5,2)
  , power_source VARCHAR(90)
  , material VARCHAR(90)
  , generator_output BIGINT
  , total_thrusters_output BIGINT
  , ms_overview VARCHAR(2000)
  , action VARCHAR(2000)
  , insert_date TIMESTAMP not null
  , update_date TIMESTAMP not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;


--武装
create table Armed (
  armed_id VARCHAR(8) not null
  , armed_name VARCHAR(90) not null
  , armed_explanation VARCHAR(2000)
  , constraint Armed_PKC primary key (armed_id)
) ;


--装備
create table Equipment (
  equipment_id VARCHAR(8) not null
  , ms_id VARCHAR(8) not null
  , armed_id VARCHAR(8) not null
  , number_equipment INTEGER
  , constraint Equipment_PKC primary key (equipment_id)
) ;

alter table Equipment
  add constraint Equipment_FK1 foreign key (armed_id) references Armed(armed_id);

alter table Equipment
  add constraint Equipment_FK2 foreign key (ms_id) references MobileSuit(ms_id);
