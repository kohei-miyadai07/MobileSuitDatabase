-- Project Name : MSDB_ER
-- Date/Time    : 2022/02/06 0:42:31
-- Author       : 宮臺滉平
-- RDBMS Type   : Microsoft SQL Server 2005
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- 装備
--* RestoreFromTempTable
create table Equipment (
  equipment_id VARCHAR not null
  , ms_id VARCHAR not null
  , ms_name VARCHAR not null
  , armed_id VARCHAR not null
  , armed_name VARCHAR not null
  , number_equipment INTEGER
  , constraint Equipment_PKC primary key (equipment_id)
) ;

-- 武装
--* RestoreFromTempTable
create table Armed (
  armed_id VARCHAR not null
  , armed_name VARCHAR not null
  , armed_explanation VARCHAR
  , constraint Armed_PKC primary key (armed_id)
) ;

-- モビルスーツ
--* RestoreFromTempTable
create table MobileSuit (
  ms_id VARCHAR not null
  , model_number VARCHAR not null
  , ms_name VARCHAR not null
  , ms_url VARCHAR not null
  , head_height DECIMAL
  , weight DECIMAL
  , total_weight DECIMAL
  , power_source VARCHAR
  , material VARCHAR
  , generator_output INTEGER
  , total_thrusters_output INTEGER
  , ms_overview VARCHAR
  , action VARCHAR
  , insert_date DATETIME not null
  , update_date DATETIME not null
  , constraint MobileSuit_PKC primary key (ms_id)
) ;

execute sp_addextendedproperty N'MS_Description', N'装備', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', null, null;
execute sp_addextendedproperty N'MS_Description', N'装備ID', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'equipment_id';
execute sp_addextendedproperty N'MS_Description', N'機体ID', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'ms_id';
execute sp_addextendedproperty N'MS_Description', N'機体名', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'ms_name';
execute sp_addextendedproperty N'MS_Description', N'武装ID', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'armed_id';
execute sp_addextendedproperty N'MS_Description', N'武装名', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'armed_name';
execute sp_addextendedproperty N'MS_Description', N'装備数', N'SCHEMA', N'dbo', N'TABLE', N'Equipment', N'COLUMN', N'number_equipment';

execute sp_addextendedproperty N'MS_Description', N'武装', N'SCHEMA', N'dbo', N'TABLE', N'Armed', null, null;
execute sp_addextendedproperty N'MS_Description', N'武装ID', N'SCHEMA', N'dbo', N'TABLE', N'Armed', N'COLUMN', N'armed_id';
execute sp_addextendedproperty N'MS_Description', N'武装名', N'SCHEMA', N'dbo', N'TABLE', N'Armed', N'COLUMN', N'armed_name';
execute sp_addextendedproperty N'MS_Description', N'武装説明', N'SCHEMA', N'dbo', N'TABLE', N'Armed', N'COLUMN', N'armed_explanation';

execute sp_addextendedproperty N'MS_Description', N'モビルスーツ', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', null, null;
execute sp_addextendedproperty N'MS_Description', N'機体ID', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'ms_id';
execute sp_addextendedproperty N'MS_Description', N'型式番号', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'model_number';
execute sp_addextendedproperty N'MS_Description', N'機体名', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'ms_name';
execute sp_addextendedproperty N'MS_Description', N'機体写真URL', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'ms_url';
execute sp_addextendedproperty N'MS_Description', N'頭高頂', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'head_height';
execute sp_addextendedproperty N'MS_Description', N'本体重量', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'weight';
execute sp_addextendedproperty N'MS_Description', N'全備重量', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'total_weight';
execute sp_addextendedproperty N'MS_Description', N'動力源', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'power_source';
execute sp_addextendedproperty N'MS_Description', N'装甲材質', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'material';
execute sp_addextendedproperty N'MS_Description', N'ジェネレーター出力', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'generator_output';
execute sp_addextendedproperty N'MS_Description', N'スラスター総推力', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'total_thrusters_output';
execute sp_addextendedproperty N'MS_Description', N'機体概要', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'ms_overview';
execute sp_addextendedproperty N'MS_Description', N'活躍', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'action';
execute sp_addextendedproperty N'MS_Description', N'登録日時', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'insert_date';
execute sp_addextendedproperty N'MS_Description', N'更新日時', N'SCHEMA', N'dbo', N'TABLE', N'MobileSuit', N'COLUMN', N'update_date';

