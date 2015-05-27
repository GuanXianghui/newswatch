package com.newswatch.interfaces;

/**
 * 抓取内容基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface GrabContentInterface extends BaseInterface {
    /**
     * 采集顺序：1抬头，2时间，3来源，4作者，5内容
     */
    public static final int ORDER_INDEX_TITLE = 1;
    public static final int ORDER_INDEX_TIME = 2;
    public static final int ORDER_INDEX_SOURCE = 3;
    public static final int ORDER_INDEX_AUTHOR = 4;
    public static final int ORDER_INDEX_CONTENT = 5;
}
