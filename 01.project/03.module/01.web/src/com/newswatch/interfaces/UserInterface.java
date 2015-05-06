package com.newswatch.interfaces;

/**
 * 用户实体基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface UserInterface extends BaseInterface {
    /**
     * 用户类型  1 普通用户 2 管理员
     */
    public static final int USER_TYPE_NORMAL = 1;
    public static final int USER_TYPE_ADMIN = 2;
}
