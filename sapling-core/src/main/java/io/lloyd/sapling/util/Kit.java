package io.lloyd.sapling.util;

/**
 * 基础常量配置
 * 
 * @author LilyBlooper blooper@163.com
 * 
 */
public class Kit {

	/*-------------------------------------------
	|        常用状态                                                                                            |
	============================================*/
	public static final int NUM_ZERO = 0;
	public static final int NUM_ONE = 1;

	public static final int VALCODE_LEN = 6;

	public static final int NOT_DELETED = NUM_ZERO; // 未被删除
	public static final int DELETED = NUM_ONE; // 已删除

	public static final String STR_ZERO = "0";
	public static final String STR_ONE = "1";
	public static final String STR_ONE_HUNDRED = "100";
	public static final String STR_TWO_HUNDRED = "200";

	public static final String STATUS_VALID = STR_ONE;
	public static final String STATUS_INVALID = STR_ZERO;

	/*-------------------------------------------
	|        秒数                                                                                                    |
	============================================*/
	public static final int SECS_ONE_MINUTE = 1 * 60;
	public static final int SECS_AN_HOUR = SECS_ONE_MINUTE * 60;
	public static final int SECS_HALF_HOUR = SECS_ONE_MINUTE * 30;
	public static final int SECS_ONE_DAY = SECS_AN_HOUR * 24;
	public static final int SECS_ONE_WEEK = SECS_ONE_DAY * 7;
	public static final int SECS_ONE_YEAR = SECS_ONE_DAY * 365;

	/*-------------------------------------------
	|        分页相关信息                                                                                    |
	============================================*/
	public static final String INIT_PAGESIZE = "0";
	public static final String INIT_PAGENUM = "1";

	public static final String PAGE_NUM_1 = STR_ONE;
	public static final String PAGE_SIZE_100 = STR_ONE_HUNDRED;
	public static final String PAGE_SIZE_200 = STR_TWO_HUNDRED;

	/**
	 * 页面和每页个数是常见的一组参数，这里采用的是默认值。 适合前端插件 Bootstrap Table
	 */
	public static final String KEY_PAGENUM = "pageNum";
	public static final String KEY_PAGESIZE = "pageSize";

	/**
	 * 构造查询的orderBy参数，同样默认支持 Bootstrap Table
	 */
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	public static final String KEY_ORDER = "order";
	public static final String KEY_SORT = "sort";

	/*-------------------------------------------
	|        常用描述信息                                                                                    |
	============================================*/

	public static final String MSG_SUCCESS = "成功";
	public static final String MSG_FAILED = "失败";

	/*-------------------------------------------
	|        其他                                                                                                     |
	============================================*/

	public static final String DATE_PATTEN_COMPACT = "yyyyMMddHHmmss";
	public static final String DATE_PATTEN_YMDHMS = "yyyyMMddHHmmss";
	public static final String DATE_PATTEN_YMDHM = "yyyyMMddHHmm";
	public static final String DATE_PATTEN_YMDH = "yyyyMMddHH";
	public static final String DATE_PATTEN_YMD = "yyyyMMdd";
	public static final String DATE_PATTEN_YM = "yyyyMM";
	public static final String DATE_PATTEN_Y = "yyyy";
	public static final String STATIC_FILE_SUFFIX = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk";

}
