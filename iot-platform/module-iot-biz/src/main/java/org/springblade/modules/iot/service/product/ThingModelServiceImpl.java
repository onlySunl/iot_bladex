
package org.springblade.modules.iot.service.product;

import cn.hutool.core.util.ObjectUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.IDbStructureData;
import org.springblade.modules.iot.api.device.service.RemoteIotDeviceService;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.product.vo.IotThingModelSaveReqVO;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelSaveReqVO;
import org.springblade.modules.iot.convert.ThingModelConvert;
import org.springblade.modules.iot.entity.ThingModelDO;
import org.springblade.modules.iot.dal.mysql.thingmodel.ThingModelMapper;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Validated
public class ThingModelServiceImpl implements ThingModelService {

    @Resource
    private ThingModelMapper thingModelMapper;

    @Resource
    private IDbStructureData dbStructureData;

    @Resource
    private RemoteIotDeviceService deviceApi;

    @Override
    @CacheEvict(cacheNames = RedisKeyConstants.THING_MODEL, key = "#updateReqVO.productKey")
    public void saveThingModel(ThingModelSaveReqVO updateReqVO) {
        String productKey = updateReqVO.getProductKey();
        ThingModel oldData = getThingModelByProductKey(productKey);

        ThingModel thingModel = new ThingModel(1L, productKey, JsonUtils.parseObject(updateReqVO.getModel(), ThingModel.Model.class));
        normalizeThingModel(thingModel);
        validateThingModel(thingModel);

        if (ObjectUtil.isNull(oldData)) {
            dbStructureData.defineThingModel(thingModel);
            IotThingModelSaveReqVO updateObj = BeanUtils.toBean(updateReqVO, IotThingModelSaveReqVO.class);
            updateObj.setModel(JsonUtils.toJsonString(thingModel.getModel()));
            thingModelMapper.insert(BeanUtils.toBean(updateObj, ThingModelDO.class));
        } else {
            thingModel.setId(oldData.getId());
            dbStructureData.updateThingModel(thingModel);
            IotThingModelSaveReqVO updateObj = BeanUtils.toBean(updateReqVO, IotThingModelSaveReqVO.class);
            updateObj.setId(oldData.getId());
            updateObj.setModel(JsonUtils.toJsonString(thingModel.getModel()));
            validateThingModelExists(updateObj.getId());
            thingModelMapper.updateById(BeanUtils.toBean(updateObj, ThingModelDO.class));
            deviceApi.clearPropertiesCache(productKey);
        }
    }

    @Override
    public ThingModel getThingModel(Long id) {
        ThingModelDO thingModelDO = thingModelMapper.selectById(id);
        ThingModel convert = ThingModelConvert.INSTANCE.convert(thingModelDO);

        if (thingModelDO != null && convert != null) {
            convert.setModel(JsonUtils.parseObject(thingModelDO.getModel(), ThingModel.Model.class));
        }
        return convert;
    }

    @Override
    public ThingModel getThingModelByProductKey(String productKey) {
        ThingModelDO thingModelDO = thingModelMapper.selectOne(ThingModelDO::getProductKey, productKey);
        ThingModel convert = ThingModelConvert.INSTANCE.convert(thingModelDO);

        if (thingModelDO != null && convert != null) {
            convert.setModel(JsonUtils.parseObject(thingModelDO.getModel(), ThingModel.Model.class));
        }
        return convert;
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.THING_MODEL, key = "#productKey", unless = "#result == null")
    public ThingModel getThingModelByProductKeyFromCache(String productKey) {
        return getThingModelByProductKey(productKey);
    }

    private void validateThingModelExists(Long id) {
        if (thingModelMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }

    private void normalizeThingModel(ThingModel thingModel) {
        if (thingModel.getModel() == null) {
            thingModel.setModel(new ThingModel.Model());
        }
        if (thingModel.getModel().getProperties() == null) {
            thingModel.getModel().setProperties(new ArrayList<>());
        }
        if (thingModel.getModel().getServices() == null) {
            thingModel.getModel().setServices(new ArrayList<>());
        }
        if (thingModel.getModel().getEvents() == null) {
            thingModel.getModel().setEvents(new ArrayList<>());
        }
        thingModel.getModel().getProperties().forEach(this::normalizeProperty);
        thingModel.getModel().getServices().forEach(service -> {
            if (service.getInputData() == null) {
                service.setInputData(new ArrayList<>());
            }
            if (service.getOutputData() == null) {
                service.setOutputData(new ArrayList<>());
            }
            service.getInputData().forEach(this::normalizeParameter);
            service.getOutputData().forEach(this::normalizeParameter);
        });
        thingModel.getModel().getEvents().forEach(event -> {
            if (event.getOutputData() == null) {
                event.setOutputData(new ArrayList<>());
            }
            event.getOutputData().forEach(this::normalizeParameter);
        });
    }

    private void normalizeProperty(ThingModel.Property property) {
        if (property.getDataType() != null) {
            normalizeDataType(property.getDataType());
        }
    }

    private void normalizeParameter(ThingModel.Parameter parameter) {
        if (parameter.getDataType() != null) {
            normalizeDataType(parameter.getDataType());
        }
    }

    private void normalizeDataType(ThingModel.DataType dataType) {
        if (dataType == null) {
            return;
        }
        dataType.normalizedType();
        if (ThingModel.DataType.TYPE_OBJECT.equals(dataType.getType())) {
            dataType.getPropertySpecs().forEach(this::normalizeParameter);
        } else if (ThingModel.DataType.TYPE_ARRAY.equals(dataType.getType())) {
            normalizeDataType(dataType.getItemTypeSpec());
        }
    }

    private void validateThingModel(ThingModel thingModel) {
        validateUniqueIdentifiers(thingModel.getModel().getProperties(), "property");
        validateUniqueIdentifiers(thingModel.getModel().getServices(), "service");
        validateUniqueIdentifiers(thingModel.getModel().getEvents(), "event");
        thingModel.getModel().getProperties().forEach(property -> validateProperty(property, "property"));
        thingModel.getModel().getServices().forEach(service -> {
            validateIdentifier("service", service.getIdentifier());
            validateParameters(service.getInputData(), "service[" + service.getIdentifier() + "].inputData");
            validateParameters(service.getOutputData(), "service[" + service.getIdentifier() + "].outputData");
        });
        thingModel.getModel().getEvents().forEach(event -> {
            validateIdentifier("event", event.getIdentifier());
            validateParameters(event.getOutputData(), "event[" + event.getIdentifier() + "].outputData");
        });
    }

    private void validateUniqueIdentifiers(List<?> items, String scope) {
        Set<String> identifiers = new HashSet<>();
        for (Object item : items) {
            String identifier;
            if (item instanceof ThingModel.Property) {
                identifier = ((ThingModel.Property) item).getIdentifier();
            } else if (item instanceof ThingModel.Service) {
                identifier = ((ThingModel.Service) item).getIdentifier();
            } else if (item instanceof ThingModel.Event) {
                identifier = ((ThingModel.Event) item).getIdentifier();
            } else {
                continue;
            }
            if (!identifiers.add(identifier)) {
                throw new ServiceException(scope + " identifier duplicated: " + identifier);
            }
        }
    }

    private void validateProperty(ThingModel.Property property, String path) {
        validateIdentifier(path, property.getIdentifier());
        validateDataType(path + "[" + property.getIdentifier() + "]", property.getDataType());
    }

    private void validateParameters(List<ThingModel.Parameter> parameters, String path) {
        Set<String> identifiers = new HashSet<>();
        for (ThingModel.Parameter parameter : parameters) {
            validateIdentifier(path, parameter.getIdentifier());
            if (!identifiers.add(parameter.getIdentifier())) {
                throw new ServiceException(path + " duplicated identifier: " + parameter.getIdentifier());
            }
            validateDataType(path + "[" + parameter.getIdentifier() + "]", parameter.getDataType());
        }
    }

    private void validateIdentifier(String scope, String identifier) {
        if (StringUtils.isBlank(identifier)) {
            throw new ServiceException( scope + " identifier cannot be blank");
        }
        if (!identifier.matches("^[a-zA-Z][a-zA-Z0-9_:.\\-]*$")) {
            throw new ServiceException(scope + " identifier illegal: " + identifier);
        }
    }

    private void validateDataType(String path, ThingModel.DataType dataType) {
        if (dataType == null || StringUtils.isBlank(dataType.getType())) {
            throw new ServiceException(path + " dataType is required");
        }
        Map<String, Object> specs = dataType.getSpecMap();
        switch (dataType.normalizedType()) {
            case ThingModel.DataType.TYPE_BOOL:
            case ThingModel.DataType.TYPE_DATE:
            case ThingModel.DataType.TYPE_DATETIME:
            case ThingModel.DataType.TYPE_POSITION:
                return;
            case ThingModel.DataType.TYPE_ENUM:
                if (specs.isEmpty()) {
                    throw new ServiceException(path + " enum specs cannot be empty");
                }
                return;
            case ThingModel.DataType.TYPE_STRING:
            case ThingModel.DataType.TYPE_TEXT:
                validatePositiveLength(path, specs);
                return;
            case ThingModel.DataType.TYPE_INT32:
            case ThingModel.DataType.TYPE_INT64:
            case ThingModel.DataType.TYPE_FLOAT:
            case ThingModel.DataType.TYPE_DOUBLE:
                validateNumericRange(path, specs);
                return;
            case ThingModel.DataType.TYPE_OBJECT:
                List<ThingModel.Parameter> fields = dataType.getPropertySpecs();
                if (fields.isEmpty()) {
                    throw new ServiceException(path + " object type requires nested properties");
                }
                validateParameters(fields, path + ".properties");
                return;
            case ThingModel.DataType.TYPE_ARRAY:
                ThingModel.DataType itemType = dataType.getItemTypeSpec();
                if (itemType == null) {
                    throw new ServiceException(path + " array type requires itemType");
                }
                validateDataType(path + ".items", itemType);
                return;
            default:
                throw new ServiceException(path + " unsupported dataType: " + dataType.getType());
        }
    }

    private void validatePositiveLength(String path, Map<String, Object> specs) {
        Object length = specs.get("length");
        if (length == null || StringUtils.isBlank(String.valueOf(length))) {
            return;
        }
        try {
            int parsed = Integer.parseInt(String.valueOf(length));
            if (parsed <= 0) {
                throw new ServiceException(path + " length must be positive");
            }
        } catch (NumberFormatException ex) {
            throw new ServiceException(path + " length must be numeric");
        }
    }

    private void validateNumericRange(String path, Map<String, Object> specs) {
        BigDecimal min = toBigDecimal(specs.get("min"), path + ".min");
        BigDecimal max = toBigDecimal(specs.get("max"), path + ".max");
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new ServiceException( path + " min cannot exceed max");
        }
    }

    private BigDecimal toBigDecimal(Object value, String field) {
        if (value == null || StringUtils.isBlank(String.valueOf(value))) {
            return null;
        }
        try {
            return new BigDecimal(String.valueOf(value).trim());
        } catch (NumberFormatException ex) {
            throw new ServiceException(field + " must be numeric");
        }
    }
}
