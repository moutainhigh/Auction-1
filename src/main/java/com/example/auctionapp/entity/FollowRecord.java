package com.example.auctionapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账户
 * </p>
 *
 * @author 孔邹祥
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FollowRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户类型 1:app客户关注
     */
    @TableField("type")
    private Integer type;

    /**
     * 客户id
     */
    @TableField("customer_id")
    private Integer customerId;

    /**
     * 拍品id
     */
    @TableField("good_id")
    private Integer goodId;

    /**
     * 关注标志 1：关注 2：取消关注
     */
    @TableField("follow_flag")
    private Integer followFlag;

    /**
     * 账户状态 0：失效 1：生效
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注字段
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 乐观锁标志
     */
    @TableField("version")
    private Integer version;


}
