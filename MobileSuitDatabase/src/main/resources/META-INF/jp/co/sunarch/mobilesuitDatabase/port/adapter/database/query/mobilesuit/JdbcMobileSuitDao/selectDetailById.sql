select
    ms_id
    , model_number
    , ms_name
    , ms_url
    , head_height
    , overall_height
    , weight
    , total_weight
    , power_source
    , material
    , effective_sensor_radius
    , generator_output
    , total_thrusters_output
    , ms_overview
    , action
    , insert_date
    , update_date
    , version 
from
    MobileSuit 
where
    ms_id = /* msId */'99'

