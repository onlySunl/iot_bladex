package org.springblade.modules.iot.common.enums;

enum Operator {
eq("=="),
not("!="),
gt(">"),
lt("<"),
gte(">="),
lte("<="),
like("like");

private final String symbol;

Object convert(String value) {
return value;
}
}