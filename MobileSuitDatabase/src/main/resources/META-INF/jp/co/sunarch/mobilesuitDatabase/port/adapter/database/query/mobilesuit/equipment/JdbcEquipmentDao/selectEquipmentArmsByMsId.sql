select
    e.ms_id as ms_id,
    e.arms_id as arms_id,
    a.arms_name as arms_name,
    e.number_equipment as number_equipment,
    e.detail as detail,
    e.insert_date as insert_date,
    e.update_date as update_date,
    e.version as version
from
    Equipment e
    inner join Arms a on e.arms_id = a.arms_id
where
    e.ms_id =/*msId*/'99'