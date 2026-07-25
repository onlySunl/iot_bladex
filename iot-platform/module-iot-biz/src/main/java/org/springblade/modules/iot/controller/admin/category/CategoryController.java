package org.springblade.modules.iot.controller.admin.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.category.vo.Category;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryListReqVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryRespVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategorySaveReqVO;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.category.ICategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;


@Tag(name = "管理后台 - IOT产品分类")
@RestController
@RequestMapping("/eiot/category")
@Validated
public class CategoryController extends BladeController {

    @Resource
    private ICategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "创建IOT产品分类")
    public R<Long> createCategory(@Valid @RequestBody CategorySaveReqVO createReqVO) {
        return data(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新IOT产品分类")
    public R<Boolean> updateCategory(@Valid @RequestBody CategorySaveReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除IOT产品分类")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得IOT产品分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<CategoryRespVO> getCategory(@RequestParam("id") Long id) {
        Category category = categoryService.getCategory(id);
        return data(BeanUtils.toBean(category, CategoryRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得IOT产品分类列表")
    public R<List<CategoryRespVO>> getCategoryList(@Valid CategoryListReqVO listReqVO) {
        List<Category> list = categoryService.getCategoryList(listReqVO);
        return data(BeanUtils.toBean(list, CategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出IOT产品分类 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCategoryExcel(@Valid CategoryListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<Category> list = categoryService.getCategoryList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "IOT产品分类.xls", "数据", CategoryRespVO.class,
                        BeanUtils.toBean(list, CategoryRespVO.class));
    }

}
