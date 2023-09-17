select
    arms_id,
    arms_name,
    detail,
    insert_date,
    update_date,
    version
from
    Arms
where
    /*%if criteria.armsName != null */
    arms_name =/* criteria.armsName */'99'
    /*%end*/