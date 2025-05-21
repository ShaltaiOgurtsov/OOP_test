package Lab2;

import java.util.HashMap;
import java.util.Map;

public class Unit {
    String name;
    ConversionType conversionType;
    MeasurmentType measurmentType;
    double toBaseFactor;
    UnitConversionFormula toBaseFunction;
    UnitConversionFormula fromBaseFuncrion;

    public Unit (String name, MeasurmentType type, double factor){
        this.name = name;
        this.conversionType = ConversionType.LINEAR;
        this.measurmentType = type;
        this.toBaseFactor = factor;
    }

    public Unit (String name, MeasurmentType type, UnitConversionFormula toBaseFormula, UnitConversionFormula fromBaseFormula){
        this.name = name;
        this.measurmentType = type;
        this.conversionType = conversionType.FORMULA;
        this.toBaseFunction = toBaseFormula;
        this.fromBaseFuncrion = fromBaseFormula;
    }

    public static Map<String, Unit> units = new HashMap<>();
    
    static  {
        units.put("m", new Unit("m", MeasurmentType.DISTANCE, 1.0));
        units.put("cm", new Unit("cm", MeasurmentType.DISTANCE, 0.01));
        units.put("mm", new Unit("mm", MeasurmentType.DISTANCE, 0.001));
        units.put("km", new Unit("km", MeasurmentType.DISTANCE, 1000));
        units.put("μm", new Unit("μm", MeasurmentType.DISTANCE, 1e-6));

        units.put("s", new Unit("s", MeasurmentType.TIME, 1.0));
        units.put("ms", new Unit("ms", MeasurmentType.TIME, 0.001));
        units.put("min", new Unit("min", MeasurmentType.TIME, 60));
        units.put("h", new Unit("h", MeasurmentType.TIME, 3600));

        units.put("g", new Unit("g", MeasurmentType.MASS, 1.0));
        units.put("kg", new Unit("kg", MeasurmentType.MASS, 1000.0));
        units.put("mg", new Unit("mg", MeasurmentType.MASS, 0.001));
        units.put("μg", new Unit("μg", MeasurmentType.MASS, 1e-6));
        units.put("t", new Unit("t", MeasurmentType.MASS, 1e6));

        units.put("Pa", new Unit("Pa", MeasurmentType.PRESSURE, 1.0));
        units.put("kPa", new Unit("kPa", MeasurmentType.PRESSURE, 1000.0));
        units.put("MPa", new Unit("MPa", MeasurmentType.PRESSURE, 1e6));
        units.put("bar", new Unit("bar", MeasurmentType.PRESSURE, 100000.0));
        units.put("atm", new Unit("atm", MeasurmentType.PRESSURE, 101325.0));

        units.put("m²", new Unit("m²", MeasurmentType.AREA, 1.0));
        units.put("cm²", new Unit("cm²", MeasurmentType.AREA, 0.0001));
        units.put("mm²", new Unit("mm²", MeasurmentType.AREA, 1e-6));
        units.put("km²", new Unit("km²", MeasurmentType.AREA, 1e6));

        units.put("m/s", new Unit("m/s", MeasurmentType.SPEED, 1.0));
        units.put("km/h", new Unit("km/h", MeasurmentType.SPEED, 1000.0 / 3600));
        units.put("cm/s", new Unit("cm/s", MeasurmentType.SPEED, 0.01));

        units.put("J", new Unit("J", MeasurmentType.ENERGY, 1.0));
        units.put("kJ", new Unit("kJ", MeasurmentType.ENERGY, 1000.0));
        units.put("cal", new Unit("cal", MeasurmentType.ENERGY, 4.184));
        units.put("kcal", new Unit("kcal", MeasurmentType.ENERGY, 4184.0));




        units.put("day", new Unit("day", MeasurmentType.TIME,
        v -> v * 86400, v -> v / 86400));
        units.put("week", new Unit("week", MeasurmentType.TIME,
        v -> v * 604800, v -> v / 604800));

        units.put("lb", new Unit("lb", MeasurmentType.MASS,
        v -> v * 453.59237, v -> v / 453.59237));
        units.put("oz", new Unit("oz", MeasurmentType.MASS,
        v -> v * 28.3495, v -> v / 28.3495));

        units.put("psi", new Unit("psi", MeasurmentType.PRESSURE,
        v -> v * 6894.76, v -> v / 6894.76));
        units.put("mmHg", new Unit("mmHg", MeasurmentType.PRESSURE,
        v -> v * 133.322, v -> v / 133.322));

        units.put("in", new Unit("in", MeasurmentType.DISTANCE,
        v -> v * 0.0254, v -> v / 0.0254));
        units.put("ft", new Unit("ft", MeasurmentType.DISTANCE,
        v -> v * 0.3048, v -> v / 0.3048));

        units.put("acre", new Unit("acre", MeasurmentType.AREA,
        v -> v * 4046.86, v -> v / 4046.86));
        units.put("hectare", new Unit("hectare", MeasurmentType.AREA,
        v -> v * 10000.0, v -> v / 10000.0));

        units.put("gallon", new Unit("gallon", MeasurmentType.VOLUME,
        v -> v * 0.00378541, v -> v / 0.00378541)); 
        units.put("cup", new Unit("cup", MeasurmentType.VOLUME,
        v -> v * 0.000236588, v -> v / 0.000236588));   

        units.put("mph", new Unit("mph", MeasurmentType.SPEED,
        v -> v * 0.44704, v -> v / 0.44704));
        units.put("kn", new Unit("kn", MeasurmentType.SPEED,
        v -> v * 0.514444, v -> v / 0.514444));

        units.put("°C", new Unit("°C", MeasurmentType.TEMRATURE,
        v -> v + 273.15, v -> v - 273.15));
        units.put("°F", new Unit("°F", MeasurmentType.TEMRATURE,
        v -> (v + 459.67) * 5.0 / 9.0, v -> v * 9.0 / 5.0 - 459.67));

        units.put("BTU", new Unit("BTU", MeasurmentType.ENERGY,
        v -> v * 1055.06, v -> v / 1055.06));
        units.put("Wh", new Unit("Wh", MeasurmentType.ENERGY,
        v -> v * 3600.0, v -> v / 3600.0));
    }

    public static double Convert(double value, String fromUnitName, String toUnitName){
        Unit from = units.get(fromUnitName);
        Unit to = units.get(toUnitName);
        if (from.measurmentType != to.measurmentType){
            throw new IllegalArgumentException("Cannot convert between incompatible units: "
                                + fromUnitName + " and " + toUnitName);
        }
        double baseValue;

        if (from.conversionType == ConversionType.LINEAR){
            baseValue = value * from.toBaseFactor;
        }
        else{
            baseValue = from.toBaseFunction.Convert(value);
        }


        if (to.conversionType == ConversionType.LINEAR){
            return  baseValue / to.toBaseFactor;
        }
        else {
            return to.fromBaseFuncrion.Convert(baseValue);
        }
    }
}
