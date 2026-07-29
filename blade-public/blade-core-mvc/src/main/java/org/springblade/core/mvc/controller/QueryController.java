package org.springblade.core.mvc.controller;

import cn.hutool.core.collection.CollUtil;
import org.springblade.basic.base.R;
import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.basic.utils.BeanPlusUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询Controller
 *
 * @param <Entity>    实体
 * @param <Id>        主键
 * @param <PageQuery> 分页参数
 * @param <ResultVO>  实体返回VO
 * @author mqttsnet
 * @date 2023年03月07日22:06:35
 */
public interface QueryController<Id extends Serializable, Entity extends SuperEntity<Id>, PageQuery, ResultVO>
        extends PageController<Id, Entity, PageQuery, ResultVO> {

    /**
     * 单体查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Parameters({
            @Parameter(name = "id", description = "主键", schema = @Schema(type = "long"), in = ParameterIn.PATH),
    })
    @Operation(summary = "单体查询", description = "单体查询")
    @GetMapping("/{id:[0-9]+}")
    @WebLog("'查询:' + #id")
    default R<ResultVO> get(@PathVariable Id id) {
        Entity entity = getSuperService().getById(id);
        ResultVO resultVO = BeanPlusUtil.toBean(entity, getResultVOClass());
        EchoService echoService = getEchoService();
        if (echoService != null) {
            echoService.action(resultVO);
        }
        return success(resultVO);
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Parameters({@Parameter(name = "id", description = "主键", schema = @Schema(type = "long"), in = ParameterIn.QUERY),})
    @Operation(summary = "查询单体详情")
    @GetMapping("/detail")
    @WebLog("'查询:' + #id")
    default R<ResultVO> getDetail(@RequestParam("id") Id id) {
        Entity entity = getSuperService().getById(id);
        ResultVO resultVO = BeanPlusUtil.toBean(entity, getResultVOClass());
        EchoService echoService = getEchoService();
        if (echoService != null) {
            echoService.action(resultVO);
        }
        return success(resultVO);
    }

    /**
     * 批量查询
     *
     * @param data 批量查询
     * @return 查询结果
     */
    @Operation(summary = "批量查询", description = "批量查询")
    @PostMapping("/query")
    @WebLog("批量查询")
    default R<List<ResultVO>> query(@RequestBody PageQuery data) {
        Entity entity = BeanPlusUtil.toBean(data, getEntityClass());
        QueryWrap<Entity> wrapper = Wraps.q(entity);
        List<Entity> list = getSuperService().list(wrapper);
        return success(BeanPlusUtil.toBeanList(list, getResultVOClass()));
    }


    /**
     * 根据ids批量查询
     *
     * @param ids 根据ids批量查询
     * @return 查询结果
     */
    @Operation(summary = "根据ids批量查询", description = "根据ids批量查询")
    @PostMapping({"/queryIds", "/findByIds"})
    @WebLog("根据ids批量查询")
    default R<List<ResultVO>> queryIds(@RequestBody @NotEmpty(message = "ID集合不能为空") List<Id> ids) {
        if (CollUtil.isEmpty(ids)) {
            return R.success(Collections.emptyList());
        }
        List<Entity> entities = getSuperService().listByIds(ids.stream().distinct().collect(Collectors.toList()));
        List<ResultVO> resultVOList = BeanPlusUtil.toBeanList(entities, getResultVOClass());
        EchoService echoService = getEchoService();
        if (echoService != null && !resultVOList.isEmpty()) {
            echoService.action(resultVOList);
        }
        return success(resultVOList);
    }

}
