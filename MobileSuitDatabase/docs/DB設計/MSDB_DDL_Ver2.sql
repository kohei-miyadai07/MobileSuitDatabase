-- Project Name : MSDB_ER�}_Ver2
-- Date/Time    : 2022/08/11 9:29:08
-- Author       : mk26s
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << ���ӁI�I >>
  BackupToTempTable, RestoreFromTempTable�^�����߂��t������Ă��܂��B
  ����ɂ��Adrop table, create table ����f�[�^���c��܂��B
  ���̋@�\�͈ꎞ�I�� $$TableName �̂悤�Ȉꎞ�e�[�u�����쐬���܂��B
  ���̋@�\�� A5:SQL Mk-2�ł̂ݗL���ł��邱�Ƃɒ��ӂ��Ă��������B
*/

-- �\��
--* RestoreFromTempTable
create table Ability (
  ms_id VARCHAR(8) not null
  , feature_id VARCHAR(8) not null
  , detail TEXT
  , constraint Ability_PKC primary key (ms_id,feature_id)
) ;

-- �@�\
--* RestoreFromTempTable
create table Feature (
  feature_id VARCHAR(8) not null
  , feature_name VARCHAR(90) not null
  , detail TEXT
  , constraint Feature_PKC primary key (feature_id)
) ;

-- �o���i
--* RestoreFromTempTable
create table Works (
  ms_id VARCHAR(8) not null
  , opus_id VARCHAR(8) not null
  , detail TEXT
  , constraint Works_PKC primary key (ms_id,opus_id)
) ;

-- ��i
--* RestoreFromTempTable
create table Opus (
  opus_id VARCHAR(8) not null
  , opus_name VARCHAR(90) not null
  , detail TEXT
  , constraint Opus_PKC primary key (opus_id)
) ;

-- �^�p���
--* RestoreFromTempTable
create table Operation_Carrier (
  ms_id VARCHAR(8) not null
  , ship_id VARCHAR(8) not null
  , detail TEXT
  , constraint Operation_Carrier_PKC primary key (ms_id,ship_id)
) ;

-- �͑D
--* RestoreFromTempTable
create table Ship (
  ship_id VARCHAR(8) not null
  , shop_name VARCHAR(90) not null
  , detail TEXT
  , constraint Ship_PKC primary key (ship_id)
) ;

-- �����
--* RestoreFromTempTable
create table Passenger (
  ms_id VARCHAR(8) not null
  , pilot_id VARCHAR(8) not null
  , detail TEXT
  , constraint Passenger_PKC primary key (ms_id,pilot_id)
) ;

-- �p�C���b�g
--* RestoreFromTempTable
create table Pilot (
  pilot_id VARCHAR(8) not null
  , pilot_name VARCHAR(90) not null
  , detail TEXT
  , constraint Pilot_PKC primary key (pilot_id)
) ;

-- ����
--* RestoreFromTempTable
create table Belonging (
  ms_id VARCHAR(8) not null
  , team_id VARCHAR(8) not null
  , detail TEXT
  , constraint Belonging_PKC primary key (ms_id,team_id)
) ;

-- �`�[��
--* RestoreFromTempTable
create table Team (
  team_id VARCHAR(8) not null
  , team_name VARCHAR(90) not null
  , detail TEXT
  , constraint Team_PKC primary key (team_id)
) ;

-- �J����
--* RestoreFromTempTable
create table Developer (
  ms_id VARCHAR(8) not null
  , person_id VARCHAR(8) not null
  , detail TEXT
  , constraint Developer_PKC primary key (ms_id,person_id)
) ;

-- �l��
--* RestoreFromTempTable
create table Person (
  person_id VARCHAR(8) not null
  , person_name VARCHAR(90) not null
  , detail TEXT
  , constraint Person_PKC primary key (person_id)
) ;

-- �J�����_
--* RestoreFromTempTable
create table Development_Base (
  ms_id VARCHAR(8) not null
  , base_id VARCHAR(8) not null
  , detail TEXT
  , constraint Development_Base_PKC primary key (ms_id,base_id)
) ;

-- ���_
--* RestoreFromTempTable
create table Base (
  base_id VARCHAR(8) not null
  , base_name VARCHAR(90) not null
  , detail TEXT
  , constraint Base_PKC primary key (base_id)
) ;

-- �J���g�D
--* RestoreFromTempTable
create table Development_Org (
  ms_id VARCHAR(8) not null
  , org_id VARCHAR(8) not null
  , detail TEXT
  , constraint Development_Org_PKC primary key (ms_id,org_id)
) ;

-- �g�D
--* RestoreFromTempTable
create table Organization (
  org_id VARCHAR(8) not null
  , org_name VARCHAR(90) not null
  , detail TEXT
  , constraint Organization_PKC primary key (org_id)
) ;

-- �J�e�S���C�Y
--* RestoreFromTempTable
create table Categorize (
  ms_id VARCHAR(8) not null
  , class_id VARCHAR(8) not null
  , detail TEXT
  , constraint Categorize_PKC primary key (ms_id,class_id)
) ;

-- ����
--* RestoreFromTempTable
create table CLASS (
  class_id VARCHAR(8) not null
  , class_name VARCHAR(90) not null
  , detail TEXT
  , constraint CLASS_PKC primary key (class_id)
) ;

-- ����
--* RestoreFromTempTable
create table Equipment (
  ms_id VARCHAR(8) not null
  , arms_id VARCHAR(8) not null
  , number_equipment INTEGER
  , detail TEXT
  , constraint Equipment_PKC primary key (ms_id,arms_id)
) ;

-- ����
--* RestoreFromTempTable
create table Arms (
  arms_id VARCHAR(8) not null
  , arms_name VARCHAR(90) not null
  , detail TEXT
  , constraint Arms_PKC primary key (arms_id)
) ;

-- ���r���X�[�c
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

comment on table Ability is '�\��';
comment on column Ability.ms_id is '�@��ID';
comment on column Ability.feature_id is '�@�\ID';
comment on column Ability.detail is '�ڍ�';

comment on table Feature is '�@�\';
comment on column Feature.feature_id is '�@�\ID';
comment on column Feature.feature_name is '�@�\��';
comment on column Feature.detail is '�ڍ�';

comment on table Works is '�o���i';
comment on column Works.ms_id is '�@��ID';
comment on column Works.opus_id is '��iID';
comment on column Works.detail is '�ڍ�';

comment on table Opus is '��i';
comment on column Opus.opus_id is '��iID';
comment on column Opus.opus_name is '��i��';
comment on column Opus.detail is '�ڍ�';

comment on table Operation_Carrier is '�^�p���';
comment on column Operation_Carrier.ms_id is '�@��ID';
comment on column Operation_Carrier.ship_id is '�͑DID';
comment on column Operation_Carrier.detail is '�ڍ�';

comment on table Ship is '�͑D';
comment on column Ship.ship_id is '�͑DID';
comment on column Ship.shop_name is '�͑D��';
comment on column Ship.detail is '�ڍ�';

comment on table Passenger is '�����';
comment on column Passenger.ms_id is '�@��ID';
comment on column Passenger.pilot_id is '�p�C���b�gID';
comment on column Passenger.detail is '�ڍ�';

comment on table Pilot is '�p�C���b�g';
comment on column Pilot.pilot_id is '�p�C���b�gID';
comment on column Pilot.pilot_name is '�p�C���b�g��';
comment on column Pilot.detail is '�ڍ�';

comment on table Belonging is '����';
comment on column Belonging.ms_id is '�@��ID';
comment on column Belonging.team_id is '�`�[��ID';
comment on column Belonging.detail is '�ڍ�';

comment on table Team is '�`�[��';
comment on column Team.team_id is '�`�[��ID';
comment on column Team.team_name is '�`�[����';
comment on column Team.detail is '�ڍ�';

comment on table Developer is '�J����';
comment on column Developer.ms_id is '�@��ID';
comment on column Developer.person_id is '�l��ID';
comment on column Developer.detail is '�ڍ�';

comment on table Person is '�l��';
comment on column Person.person_id is '�l��ID';
comment on column Person.person_name is '�l����';
comment on column Person.detail is '�ڍ�';

comment on table Development_Base is '�J�����_';
comment on column Development_Base.ms_id is '�@��ID';
comment on column Development_Base.base_id is '���_ID';
comment on column Development_Base.detail is '�ڍ�';

comment on table Base is '���_';
comment on column Base.base_id is '���_ID';
comment on column Base.base_name is '���_��';
comment on column Base.detail is '�ڍ�';

comment on table Development_Org is '�J���g�D';
comment on column Development_Org.ms_id is '�@��ID';
comment on column Development_Org.org_id is '�g�DID';
comment on column Development_Org.detail is '�ڍ�';

comment on table Organization is '�g�D';
comment on column Organization.org_id is '�g�DID';
comment on column Organization.org_name is '�g�D��';
comment on column Organization.detail is '�ڍ�';

comment on table Categorize is '�J�e�S���C�Y';
comment on column Categorize.ms_id is '�@��ID';
comment on column Categorize.class_id is '����ID';
comment on column Categorize.detail is '�ڍ�';

comment on table CLASS is '����';
comment on column CLASS.class_id is '����ID';
comment on column CLASS.class_name is '���ޖ�';
comment on column CLASS.detail is '�ڍ�';

comment on table Equipment is '����';
comment on column Equipment.ms_id is '�@��ID';
comment on column Equipment.arms_id is '����ID';
comment on column Equipment.number_equipment is '������';
comment on column Equipment.detail is '�ڍ�';

comment on table Arms is '����';
comment on column Arms.arms_id is '����ID';
comment on column Arms.arms_name is '���햼';
comment on column Arms.detail is '�ڍ�';

comment on table MobileSuit is '���r���X�[�c';
comment on column MobileSuit.ms_id is '�@��ID';
comment on column MobileSuit.model_number is '�^���ԍ�';
comment on column MobileSuit.ms_name is '�@�̖�';
comment on column MobileSuit.ms_url is '�@�̎ʐ^URL';
comment on column MobileSuit.head_height is '������';
comment on column MobileSuit.overall_height is '�S��';
comment on column MobileSuit.weight is '�{�̏d��';
comment on column MobileSuit.total_weight is '�S���d��';
comment on column MobileSuit.power_source is '���͌�';
comment on column MobileSuit.material is '���b�ގ�';
comment on column MobileSuit.effective_sensor_radius is '�Z���T�[�L�����a';
comment on column MobileSuit.generator_output is '�W�F�l���[�^�[�o��';
comment on column MobileSuit.total_thrusters_output is '�X���X�^�[������';
comment on column MobileSuit.ms_overview is '�@�̊T�v';
comment on column MobileSuit.action is '����';
comment on column MobileSuit.insert_date is '�o�^����';
comment on column MobileSuit.update_date is '�X�V����';
comment on column MobileSuit.version is '�o�[�W����';

