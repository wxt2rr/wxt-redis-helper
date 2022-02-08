package com.wangxt.redis.helper.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Expire {

	@Getter
	@AllArgsConstructor
	public enum EXPX {
		SECONDS("EX"),
		MILLISECONDS("PX");
		private final String expx;
	}

	@Getter
	@AllArgsConstructor
	public enum NXXX {
		NOTEXIST("NX"),
		EXIST("XX");
		private final String nxxx;
	}

	public static Expire s10 = new Expire(10); // 10秒钟
	public static Expire m1 = new Expire(60); // 1分钟
	public static Expire m3 = new Expire(60 * 3); // 3分钟
	public static Expire m5 = new Expire(60 * 5); // 5分钟
	public static Expire m10 = new Expire(60 * 10); // 10分钟
	public static Expire m30 = new Expire(60 * 30); // 30分钟
	public static Expire m31 = new Expire(60 * 31); // 31分钟
	public static Expire h1 = new Expire(60 * 60); // 1小时
	public static Expire h2 = new Expire(2 * 60 * 60);// 2 小时
	public static Expire h6 = new Expire(6 * 60 * 60);// 6 小时
	public static Expire h2_m1 = new Expire(121 * 60); // 2小时1分钟
	public static Expire d1 = new Expire(60 * 60 * 24); // 1天
	public static Expire d3 = new Expire(60 * 60 * 24 * 3); // 3天
	public static Expire d7 = new Expire(60 * 60 * 24 * 7); // 7天
	public static Expire d30 = new Expire(60 * 60 * 24 * 30); // 30天
	public static Expire y1 = new Expire(60 * 60 * 24 * 30 * 365); // 1年

	private long time = 0;
	private EXPX expx = null;

	private Expire(long time) {
		this(time, EXPX.SECONDS);
	}

	private Expire(long time, EXPX expx) {
		this.time = time;
		this.expx = expx;
	}

	public EXPX getEXPX() {
		return expx;
	}

	public long getTime() {
		return time;
	}

}
