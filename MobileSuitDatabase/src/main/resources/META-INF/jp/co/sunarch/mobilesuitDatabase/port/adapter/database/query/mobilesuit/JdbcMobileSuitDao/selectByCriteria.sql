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
/*%if criteria.modelNumber != null */
    model_number = /* criteria.modelNumber */'99'
/*%end*/
/*%if criteria.msName != null */
and
    ms_name = /* criteria.msName */'99'
/*%end*/
/*%if criteria.headHeightFrom != null */
and
    head_height >= /* criteria.headHeightFrom */99
/*%end*/
/*%if criteria.headHeightTo != null */
and
    head_height <= /* criteria.headHeightTo */99
/*%end*/
/*%if criteria.overallHeightFrom != null */
and
    overall_height >= /* criteria.overallHeightFrom */99
/*%end*/
/*%if criteria.overallHeightTo != null */
and
    overall_height <= /* criteria.overallHeightTo */99
/*%end*/
/*%if criteria.weightFrom != null */
and
    weight >= /* criteria.weightFrom */99
/*%end*/
/*%if criteria.weightTo != null */
and
    weight <= /* criteria.weightTo */99
/*%end*/
/*%if criteria.totalWeightFrom != null */
and
    total_weight >= /* criteria.totalWeightFrom */99
/*%end*/
/*%if criteria.totalWeightTo != null */
and
    total_weight <= /* criteria.totalWeightTo */99
/*%end*/
/*%if criteria.powerSource != null */
and
    power_source = /* criteria.powerSource */'99'
/*%end*/
/*%if criteria.material != null */
and
    material = /* criteria.material */'99'
/*%end*/
/*%if criteria.effectiveSensorRadiusFrom != null */
and
    effective_sensor_radius >= /* criteria.effectiveSensorRadiusFrom */99
/*%end*/
/*%if criteria.effectiveSensorRadiusTo != null */
and
    effective_sensor_radius <= /* criteria.effectiveSensorRadiusTo */99
/*%end*/
/*%if criteria.generatorOutputFrom != null */
and
    generator_output >= /* criteria.generatorOutputFrom */99
/*%end*/
/*%if criteria.generatorOutputTo != null */
and
    generator_output <= /* criteria.generatorOutputTo */99
/*%end*/
/*%if criteria.totalThrustersOutputFrom != null */
and
    total_thrusters_output >= /* criteria.totalThrustersOutputFrom */99
/*%end*/
/*%if criteria.totalThrustersOutputTo != null */
and
    total_thrusters_output <= /* criteria.totalThrustersOutputTo */99
/*%end*/
