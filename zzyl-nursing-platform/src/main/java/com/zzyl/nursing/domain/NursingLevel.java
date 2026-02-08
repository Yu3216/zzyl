package com.zzyl.nursing.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理等级对象 nursing_level
 *
 * @author ruoyi
 * @date 2026-02-08
 */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class NursingLevel extends BaseEntity
        {
        private static final long serialVersionUID = 1L;

                /** 主键ID */
        private Long id;

                /** 等级名称 */
                @Excel(name = "等级名称")
        private String name;

                /** 护理计划ID */
                @Excel(name = "护理计划ID")
        private Long lplanId;

                /** 护理费用 */
                @Excel(name = "护理费用")
        private Double fee;

                /** 状态（0：禁用，1：启用） */
                @Excel(name = "状态", readConverterExp = "0=：禁用，1：启用")
        private Integer status;

                /** 等级说明 */
                @Excel(name = "等级说明")
        private String description;

        }