
package org.springblade.modules.iot.temporal.kw.dm;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FieldParser {

    private static final Map<String, String> TYPE_MAPPING = Collections.unmodifiableMap(new HashMap<String, String>() {{
        put("int", "INT4");
        put("int32", "INT8");
        put("long", "INT8");
        put("int64", "INT8");
        put("float", "FLOAT8");
        put("double", "FLOAT8");
        put("bool", "INT2");
        put("enum", "INT4");
        put("string", "NCHAR");
        put("text", "NCHAR");
        put("date", "NCHAR");
        put("datetime", "NCHAR");
        put("array", "NCHAR");
        put("object", "NCHAR");
        put("position", "NCHAR");
    }});

    public static KwField parse(ThingModel.Property property) {
        String fieldName = property.getIdentifier().toLowerCase();
        String fieldType = TYPE_MAPPING.get(property.getDataType().normalizedType());
        int length = -1;
        Object rawLength = property.getDataType().getSpecMap().get("length");
        if (rawLength != null) {
            length = Integer.parseInt(String.valueOf(rawLength));
        }
        if ("NCHAR".equals(fieldType) && length < 1) {
            length = 1024;
        }
        return new KwField(fieldName, fieldType, length);
    }

    public static List<KwField> parse(ThingModel thingModel) {
        return thingModel.getModel().getProperties().stream().map(FieldParser::parse).collect(Collectors.toList());
    }

    public static List<KwField> parse(List rows) {
        return (List<KwField>) rows.stream().map((rowValue) -> {
            List row = (List) rowValue;
            String type = row.get(1).toString().toUpperCase();
            return new KwField(
                    row.get(0).toString(),
                    type,
                    type.endsWith("CHAR") ? Integer.parseInt(row.get(2).toString()) : -1);
        }).collect(Collectors.toList());
    }

    public static String getFieldDefine(KwField field) {
        return field.getName() + " " + (field.getLength() > 0
                ? String.format("%s(%d)", field.getType(), field.getLength())
                : field.getType());
    }

    public static String getFieldTagDefine(KwField field) {
        return field.getName();
    }

    public static String getAlterFieldDefine(KwField field) {
        return field.getName() + " TYPE " + (field.getLength() > 0
                ? String.format("%s(%d)", field.getType(), field.getLength())
                : field.getType());
    }
}
