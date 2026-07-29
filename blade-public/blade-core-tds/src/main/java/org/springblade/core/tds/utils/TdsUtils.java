package org.springblade.core.tds.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.basic.context.ContextConstants;
import org.springblade.basic.exception.BizException;
import org.springblade.core.tds.enumeration.TdDataTypeEnum;
import org.springblade.core.tds.model.Fields;
import org.springblade.core.tds.model.FieldsVO;
import org.springblade.core.tds.model.SuperTableDTO;
import org.springblade.core.tds.model.TableDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 处理超级表和子表
 */
@Slf4j
public class TdsUtils {

    // 创建一个静态集合
    private static List<FieldsVO> defaultList;
    private static List<FieldsVO> defaultTagList;
    // 排除类型
    private static List<String> excludeTypeList;

    // 静态代码块，在类加载时只执行一次
    static {
        // 预置必备字段
        defaultList = new ArrayList<>();
        defaultTagList = new ArrayList<>();
        excludeTypeList = new ArrayList<>();
        excludeTypeList.addAll(Arrays.asList("TINYINT", "SMALLINT", "INT", "BIGINT"));
        defaultList.add(new FieldsVO("ts", TdDataTypeEnum.TIMESTAMP.getDataType()));
        defaultList.add(new FieldsVO("event_time", TdDataTypeEnum.TIMESTAMP.getDataType()));
        defaultTagList.add(new FieldsVO("device_identificaiton", TdDataTypeEnum.BINARY.getDataType(), 64));
    }


    /**
     * 处理超级表
     *
     * @param obj
     * @return
     */
    public static Map<String, SuperTableDTO> handleSuperTable(JSONObject obj) {
        Map<String, SuperTableDTO> superTableDtoHashMap = new HashMap<>();
        if (Objects.isNull(obj)) {
            return superTableDtoHashMap;
        }
        // 产品标识
        String productIdentification = Optional.ofNullable(obj.getStr("productIdentification"))
                .orElseThrow(() -> new BizException("productIdentification cannot be null"));

        // 产品类型
        String productType = Optional.ofNullable(obj.getStr("productType")).orElseThrow(() -> new BizException("productType cannot be null"));

        // 获取服务
        List<JSONObject> serviceList = Optional.ofNullable(obj.getBeanList("serviceList", JSONObject.class))
                .orElseThrow(() -> new BizException("serviceList cannot be null"));

        for (int i = 0; i < serviceList.size(); i++) {
            // 获取每个服务信息
            JSONObject serviceObject = Optional.ofNullable(serviceList.get(i)).orElseThrow(() -> new BizException("serviceObject cannot be null"));
            // 获取服务编码
            String serviceCode = Optional.ofNullable(serviceObject.getStr("serviceCode")).orElseThrow(() -> new BizException("serviceCode cannot be null"));
            // 超级表名称
            String superTableName = superTableName(productType, productIdentification, serviceCode);
            // 获取服务属性
            List<JSONObject> propertyList = Optional.ofNullable(serviceObject.getBeanList("propertyList", JSONObject.class))
                    .orElseThrow(() -> new BizException("propertyList cannot be null"));
            // 定义字段结构
            List<FieldsVO> schemaFieldsVOList = BeanUtil.copyToList(defaultList, FieldsVO.class);
            FieldsVO fieldsVo = null;
            for (int j = 0; j < propertyList.size(); j++) {
                JSONObject serviceData = propertyList.get(j);
                fieldsVo = new FieldsVO();
                fieldsVo.setFieldName(serviceData.getStr("propertyCode"));
                fieldsVo.setDataType(TdDataTypeEnum.valueOfByDataType(serviceData.getStr("datatype")).getDataType());
                boolean exists = excludeTypeList.stream().anyMatch(str -> str.equalsIgnoreCase(serviceData.getStr("datatype")));
                if (!exists) {
                    fieldsVo.setSize(serviceData.getInt("maxlength"));
                }
                schemaFieldsVOList.add(fieldsVo);
            }
            // tags
            List<FieldsVO> tagsSchemaFieldsVOList = BeanUtil.copyToList(defaultTagList, FieldsVO.class);
            SuperTableDTO superTableDto = new SuperTableDTO(FieldsVO.toFieldsList(schemaFieldsVOList),
                    FieldsVO.toFieldsList(tagsSchemaFieldsVOList), null, null, superTableName);
            superTableDtoHashMap.put(superTableName, superTableDto);
        }
        return superTableDtoHashMap;
    }

    /**
     * 处理子表
     *
     * @param obj
     * @return
     */
    public static Map<String, TableDTO> handleSubTable(JSONObject obj) {
        Map<String, TableDTO> tableDtoMap = new HashMap<>();
        if (Objects.isNull(obj)) {
            return tableDtoMap;
        }
        // 产品标识
        String productIdentification = Optional.ofNullable(obj.getStr("productIdentification"))
                .orElseThrow(() -> new BizException("productIdentification cannot be null"));
        // 产品类型
        String productType = Optional.ofNullable(obj.getStr("productType")).orElseThrow(() -> new BizException("productType cannot be null"));
        // 设备标识集合
        List<String> deviceList = Optional.ofNullable(obj.getBeanList("devices", String.class)).orElseThrow(() -> new BizException("devices cannot be null"));
        // 获取服务集合
        List<String> serviceList = Optional.ofNullable(obj.getBeanList("serviceList", String.class))
                .orElseThrow(() -> new BizException("serviceList cannot be null"));
        for (int i = 0; i < serviceList.size(); i++) {
            // 获取每个服务信息
            String serviceStr = serviceList.get(i);
            // 超级表名称
            String superTableName = superTableName(productType, productIdentification, serviceStr);
            Fields fields;
            TableDTO tableDto;
            for (int j = 0; j < deviceList.size(); j++) {
                // 获取每个设备
                String deviceStr = deviceList.get(j);
                // 子表名称
                String subTableName = subTableName(superTableName, deviceStr);
                // Tag的处理
                List<Fields> tagsFieldValues = new ArrayList<>();
                // 字段
                fields = new Fields();
                fields.setFieldValue(deviceStr);
                tagsFieldValues.add(fields);
                // 子实体
                tableDto = new TableDTO();
                tableDto.setTableName(subTableName);
                tableDto.setSuperTableName(superTableName);
                tableDto.setTagsFieldValues(tagsFieldValues);
                tableDtoMap.put(subTableName, tableDto);
            }
        }
        return tableDtoMap;
    }

    /**
     * 处理产品服务定义
     *
     * @param o
     * @return
     */
    public static String handleProductService(Object o) {
        JSONObject jsonObject = JSONUtil.parseObj(o);
        String productType = jsonObject.getStr("productType");
        String productIdentification = jsonObject.getStr("productIdentification");
        String serviceCode = jsonObject.getStr("serviceCode");
        return superTableName(productType, productIdentification, serviceCode);
    }

    /**
     * 处理产品属性定义
     *
     * @param o
     * @return
     */
    public static Map<String, Fields> handleProductProperty(Object o) {
        JSONObject jsonObject = JSONUtil.parseObj(o);
        String productType = jsonObject.getStr("productType");
        String productIdentification = jsonObject.getStr("productIdentification");
        String serviceCode = jsonObject.getStr("serviceCode");
        Map<String, Fields> map = new HashMap<>(5);
        FieldsVO fieldsVo = new FieldsVO();
        fieldsVo.setFieldName(jsonObject.getStr("propertyCode"));
        fieldsVo.setDataType(TdDataTypeEnum.valueOfByDataType(jsonObject.getStr("datatype")).getDataType());
        boolean exists = excludeTypeList.stream().anyMatch(str -> str.equalsIgnoreCase(jsonObject.getStr("datatype")));
        if (!exists) {
            fieldsVo.setSize(jsonObject.getInt("maxlength"));
        }
        map.put(superTableName(productType, productIdentification, serviceCode), FieldsVO.toFields(fieldsVo));
        return map;
    }


    /**
     * 拼接超级表名称
     *
     * @param productType
     * @param productIdentification
     * @param serviceCode
     * @return
     */
    public static String superTableName(String productType, String productIdentification, String serviceCode) {
        StringBuilder tableNameBuilder = new StringBuilder();
        tableNameBuilder.append(productType)
                .append(ContextConstants.UNDERLINE)
                .append(productIdentification)
                .append(ContextConstants.UNDERLINE)
                .append(serviceCode);
        return tableNameBuilder.toString();
    }

    /**
     * 拼接子表名称
     *
     * @param superTableName
     * @param deviceIdentificaiton
     * @return
     */
    public static String subTableName(String superTableName, String deviceIdentificaiton) {
        return superTableName + "_" + deviceIdentificaiton;
    }


}




