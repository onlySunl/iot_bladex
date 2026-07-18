

package org.springblade.modules.iot.controller.admin.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.category.vo.Category;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryListReqVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryRespVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategorySaveReqVO;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.category.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springblade.modules.iot.common.entity.CommonResult.success;
import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;


@Tag(name = "管理后台 - IOT产品分类")
@RestController
@RequestMapping("/eiot/category")
@Validated
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "创建IOT产品分类")
    @PreAuthorize("@ss.hasPermission('iot:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody CategorySaveReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新IOT产品分类")
    @PreAuthorize("@ss.hasPermission('iot:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody CategorySaveReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除IOT产品分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得IOT产品分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:category:query')")
    public CommonResult<CategoryRespVO> getCategory(@RequestParam("id") Long id) {
        Category category = categoryService.getCategory(id);
        return success(BeanUtils.toBean(category, CategoryRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得IOT产品分类列表")
    @PreAuthorize("@ss.hasPermission('iot:category:query')")
    public CommonResult<List<CategoryRespVO>> getCategoryList(@Valid CategoryListReqVO listReqVO) {
        List<Category> list = categoryService.getCategoryList(listReqVO);
        return success(BeanUtils.toBean(list, CategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出IOT产品分类 Excel")
    @PreAuthorize("@ss.hasPermission('iot:category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCategoryExcel(@Valid CategoryListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<Category> list = categoryService.getCategoryList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "IOT产品分类.xls", "数据", CategoryRespVO.class,
                        BeanUtils.toBean(list, CategoryRespVO.class));
    }

}
