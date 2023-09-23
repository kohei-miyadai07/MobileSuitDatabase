select
    ms_id,
    arms_id,
    number_equipment,
    detail,
    insert_date,
    update_date,
    version
from
    Equipment
where
    ms_id =/* msId */'99'
    and arms_id =/* armsId */'99'
