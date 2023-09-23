-- 装備
alter table Equipment add insert_date TIMESTAMP not null;

alter table Equipment add update_date TIMESTAMP not null;

alter table Equipment add version integer not null;

comment on column Equipment.insert_date is '登録日時';

comment on column Equipment.update_date is '更新日時';

comment on column Equipment.version is 'バージョン';

-- 武器
alter table Arms add insert_date TIMESTAMP not null;

alter table Arms add update_date TIMESTAMP not null;

alter table Arms add version integer not null;

comment on column Arms.insert_date is '登録日時';

comment on column Arms.update_date is '更新日時';

comment on column Arms.version is 'バージョン';
