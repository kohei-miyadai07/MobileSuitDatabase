select
    e.ms_id
    , m.ms_name
    , e.arms_id
    , a.arms_name
    , e.number_equipment
    , e.detail 
from
    Equipment e 
    inner join MobileSuit m 
        on e.ms_id = m.ms_id 
    inner join Arms a 
        on e.arms_id = a.arms_id 
where
    e.ms_id = /* msId */'99'
    and e.arms_id = /* armsId */'99'

