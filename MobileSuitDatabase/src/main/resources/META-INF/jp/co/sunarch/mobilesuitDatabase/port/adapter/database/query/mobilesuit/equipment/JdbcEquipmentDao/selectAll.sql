select
    e.ms_id,
    m.ms_name,
    e.arms_id,
    a.arms_name,
    e.number_equipment,
    e.detail,
    e.insert_date,
    e.update_date,
    e.version
from
    Equipment e
    inner join MobileSuit m on e.ms_id = m.ms_id
    inner join Arms a on e.arms_id = a.arms_id
order by
    m.ms_name,
    a.arms_name