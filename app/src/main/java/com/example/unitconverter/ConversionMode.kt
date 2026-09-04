package com.example.unitconverter

enum class ConversionMode(
    val displayName: String,
    val fromUnit: String,
    val toUnit: String,
    val convert: (Double) -> Double
) {
    KG_TO_LB("kg to lb", "kg", "lb", { kg -> kg * 2.20462 }),
    LB_TO_KG("lb to kg", "lb", "kg", { lb -> lb / 2.20462 }),
    KMH_TO_KT("km/h to kt", "km/h", "kt", { kmh -> kmh * 0.539957 }),
    KT_TO_KMH("kt to km/h", "kt", "km/h", { kt -> kt * 1.852 }),
    KM_TO_NM("km to NM", "km", "NM", { km -> km * 0.539957 }),
    NM_TO_KM("NM to km", "NM", "km", { nm -> nm * 1.852 }),
    GAL_TO_L("gal to L", "gal", "L", { gal -> gal * 3.78541 }),
    L_TO_GAL("L to gal", "L", "gal", { l -> l / 3.78541 }),
    FT_TO_M("ft to m", "ft", "m", { ft -> ft * 0.3048 }),
    M_TO_FT("m to ft", "m", "ft", { m -> m / 0.3048 })
}
