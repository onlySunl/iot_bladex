package org.springblade.modules.iot.system.service.system.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.utils.ArgumentAssert;
import com.mqttsnet.basic.utils.CollHelper;
import com.mqttsnet.basic.utils.StrPool;
import com.mqttsnet.basic.utils.TreeUtil;
import org.springblade.modules.iot.common.constant.DefValConstants;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.system.DefArea;
import org.springblade.modules.iot.system.manager.system.DefAreaManager;
import org.springblade.modules.iot.system.service.system.DefAreaService;
import org.springblade.modules.iot.system.vo.query.system.DefAreaPageQuery;
import org.springblade.modules.iot.system.vo.save.system.DefAreaSaveVO;
import org.springblade.modules.iot.system.vo.update.system.DefAreaUpdateVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.DEFAULTS)
public class DefAreaServiceImpl extends SuperServiceImpl<DefAreaManager, Long, DefArea> implements DefAreaService {
    private static String toJson(List<DefArea> list) {
        List<DefArea> treeList = TreeUtil.buildTree(list);
        List<Map<String, Object>> jsonList = new ArrayList<>();
        mapTree(jsonList, treeList);
        return JSONObject.toJSONString(jsonList);
    }

    private static void mapTree(List<Map<String, Object>> jsonList, List<DefArea> treeList) {
        Map<String, Object> map;
        for (DefArea defArea : treeList) {
            map = new HashMap<>();
            map.put("value", String.valueOf(defArea.getId()));
            map.put("label", defArea.getName());

            if (CollUtil.isNotEmpty(defArea.getChildren())) {
                List<Map<String, Object>> childList = new ArrayList<>();
                mapTree(childList, defArea.getChildren());
                map.put("children", childList);
            }
            jsonList.add(map);
        }
    }

    @Override
    protected <SaveVO> DefArea saveBefore(SaveVO saveVO) {
        DefAreaSaveVO defAreaSaveVO = (DefAreaSaveVO) saveVO;
        ArgumentAssert.isFalse(check(defAreaSaveVO.getCode(), null), "编码不能重复");
        check(defAreaSaveVO.getCode(), null);
        DefArea defArea = super.saveBefore(defAreaSaveVO);
        fillArea(defArea);
        return defArea;
    }

    private void fillArea(DefArea defArea) {
        if (defArea.getParentId() == null || defArea.getParentId() <= 0) {
            defArea.setParentId(DefValConstants.PARENT_ID);
            defArea.setTreePath(DefValConstants.TREE_PATH_SPLIT);
            defArea.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            DefArea parent = superManager.getById(defArea.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级地区");
            defArea.setTreePath(StrPool.EMPTY);
            defArea.setTreeGrade(parent.getTreeGrade() + 1);
            defArea.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    protected <UpdateVO> DefArea updateBefore(UpdateVO updateVO) {
        DefAreaUpdateVO defAreaUpdateVO = (DefAreaUpdateVO) updateVO;
        DefArea defArea = super.updateBefore(defAreaUpdateVO);
        fillArea(defArea);
        return defArea;
    }

    @Override
    public <UpdateVO> DefArea updateAllBefore(UpdateVO updateVO) {
        DefAreaUpdateVO defAreaUpdateVO = (DefAreaUpdateVO) updateVO;
        DefArea defArea = super.updateAllBefore(defAreaUpdateVO);
        fillArea(defArea);
        return defArea;
    }

    @Override
    public List<DefArea> findTree(DefAreaPageQuery pageQuery) {
        if (pageQuery == null) {
            List<DefArea> allAreas = superManager.list();
            return TreeUtil.buildTree(allAreas);
        }

        if (StrUtil.isNotEmpty(pageQuery.getName())) {
            // 名称搜索逻辑
            return handleNameSearch(pageQuery);
        } else {
            QueryWrapper<DefArea> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(pageQuery.getParentId() != null, DefArea::getParentId, pageQuery.getParentId());
            queryWrapper.lambda().eq(StrUtil.isNotEmpty(pageQuery.getLevel()), DefArea::getLevel, pageQuery.getLevel());
            queryWrapper.lambda().eq(pageQuery.getState() != null, DefArea::getState, pageQuery.getState());
            List<DefArea> list = superManager.list(queryWrapper);
            return TreeUtil.buildTree(list);
        }
    }

    /**
     * 处理名称搜索
     * @param pageQuery 查询条件
     * @return 地区树结构
     */
    private List<DefArea> handleNameSearch(DefAreaPageQuery pageQuery) {
        List<DefArea> searchList = superManager.list(Wraps.<DefArea>lbQ().like(DefArea::getName, pageQuery.getName()));
        if (searchList.isEmpty()) {
            return searchList;
        }

        List<String> paths = CollHelper.split(searchList, DefArea::getTreePath, DefValConstants.TREE_PATH_SPLIT);
        if (paths.isEmpty()) {
            return searchList;
        }

        Collection<Long> parentIds = CollUtil.trans(paths, Convert::toLong);
        List<DefArea> parentAreas = superManager.list(Wraps.<DefArea>lbQ().in(DefArea::getId, parentIds));
        searchList.addAll(parentAreas);

        List<DefArea> mergedList = searchList.stream().distinct().toList();
        return TreeUtil.buildTree(mergedList);
    }


    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写地区编码");
        long count = superManager.count(Wraps.<DefArea>lbQ().eq(DefArea::getCode, code).ne(DefArea::getId, id));
        return count > 0;
    }

    @Override
    public List<DefArea> findLazyList(Long parentId) {
        return superManager.list(Wraps.<DefArea>lbQ().eq(DefArea::getParentId, parentId));
    }

    @Override
    public void downloadJson(Integer treeGrade, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        List<DefArea> list = superManager.list(
                Wraps.<DefArea>lbQ().le(DefArea::getTreeGrade, treeGrade).orderByAsc(DefArea::getSortValue)
        );
        long end = System.currentTimeMillis();
        log.info("查询时间: {}", end - start);
        down(toJson(list), response);
    }

    @SneakyThrows
    private void down(String text, HttpServletResponse response) {
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=cities.json");
        ServletOutputStream out = response.getOutputStream();

        IoUtil.write(out, StandardCharsets.UTF_8, true, text);
    }

}
