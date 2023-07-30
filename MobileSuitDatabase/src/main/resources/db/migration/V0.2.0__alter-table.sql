-- 装備
alter table Equipment alter column ms_id type VARCHAR(90);

alter table Equipment alter column arms_id type VARCHAR(90);

-- 武器
alter table Arms alter column arms_id type VARCHAR(90);

alter table Arms alter column arms_name type VARCHAR(300);

-- モビルスーツ
alter table MobileSuit alter column ms_id type VARCHAR(90);

alter table MobileSuit alter column model_number type VARCHAR(300);

alter table MobileSuit alter column ms_name type VARCHAR(300);

alter table MobileSuit alter column ms_url type TEXT;

alter table MobileSuit alter column power_source type VARCHAR(300);

alter table MobileSuit alter column material type VARCHAR(300);

