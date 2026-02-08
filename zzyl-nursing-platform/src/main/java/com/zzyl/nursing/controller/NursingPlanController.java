package com.zzyl.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.service.INursingPlanService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;
// 添加Swagger相关导入
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理计划Controller
 * 
 * @author ruoyi
 * @date 2026-02-08
 */
@Api(tags = "护理计划管理")
@RestController
@RequestMapping("/nursing/plan")
public class NursingPlanController extends BaseController
{
    @Autowired
    private INursingPlanService nursingPlanService;

    /**
     * 查询护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询护理计划列表", notes = "根据条件分页查询护理计划列表")
    public TableDataInfo list(
            @ApiParam(value = "护理计划查询条件对象", required = false) NursingPlan nursingPlan)
    {
        startPage();
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        return getDataTable(list);
    }

    /**
     * 导出护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:export')")
    @Log(title = "护理计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出护理计划列表", notes = "根据条件导出护理计划数据到Excel文件")
    public void export(
            @ApiParam(value = "HTTP响应对象，用于输出Excel文件", required = true) HttpServletResponse response,
            @ApiParam(value = "护理计划查询条件对象", required = false) NursingPlan nursingPlan)
    {
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        ExcelUtil<NursingPlan> util = new ExcelUtil<NursingPlan>(NursingPlan.class);
        util.exportExcel(response, list, "护理计划数据");
    }

    /**
     * 获取护理计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取护理计划详细信息", notes = "根据ID获取护理计划的详细信息")
    public AjaxResult getInfo(
            @ApiParam(value = "护理计划ID", required = true) @PathVariable("id") Long id)
    {
        return success(nursingPlanService.selectNursingPlanById(id));
    }

    /**
     * 新增护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:add')")
    @Log(title = "护理计划", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增护理计划", notes = "创建新的护理计划记录")
    public AjaxResult add(
            @ApiParam(value = "护理计划对象", required = true) @RequestBody NursingPlan nursingPlan)
    {
        return toAjax(nursingPlanService.insertNursingPlan(nursingPlan));
    }

    /**
     * 修改护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:edit')")
    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改护理计划", notes = "更新现有护理计划的信息")
    public AjaxResult edit(
            @ApiParam(value = "护理计划对象", required = true) @RequestBody NursingPlan nursingPlan)
    {
        return toAjax(nursingPlanService.updateNursingPlan(nursingPlan));
    }

    /**
     * 删除护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:remove')")
    @Log(title = "护理计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除护理计划", notes = "根据ID数组批量删除护理计划记录")
    public AjaxResult remove(
            @ApiParam(value = "护理计划ID数组", required = true) @PathVariable Long[] ids)
    {
        return toAjax(nursingPlanService.deleteNursingPlanByIds(ids));
    }
}