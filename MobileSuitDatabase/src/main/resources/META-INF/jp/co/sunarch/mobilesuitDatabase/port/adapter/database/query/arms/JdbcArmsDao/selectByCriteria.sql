select
    arms_id
    , arms_name
    , detail 
from
    Arms 
where
/*%if criteria.armsName != null */
    arms_name = /* criteria.armsName */'99'
/*%end*/

