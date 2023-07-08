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
/*%if criteria.msName != null */
    m.ms_name = /* criteria.msName */'99'
/*%end*/
/*%if criteria.armsName != null */
    and a.arms_name = /* criteria.armsName */'99'
/*%end*/

