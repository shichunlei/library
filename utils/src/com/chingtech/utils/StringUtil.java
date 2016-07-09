package com.chingtech.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

/**
 * 
 * 页面说明：字符串验证工具类
 * 
 * @author 师春雷
 * @date 2015-8-11
 * @version 1.0
 * 
 */
public class StringUtil {

	private StringUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str.trim());
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean _isNotEmpty(String str) {
		if (isNotEmpty(str) && !"0".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean __isEmpty(String str) {
		if ("-1".equals(str) || isEmpty(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证邮箱的合法性
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {

		if (email.contains("@")) {
			Pattern pattern = Pattern
					.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 两次密码输入相符性验证
	 * 
	 * 
	 * @param pwd
	 * @param pwdAgain
	 * @return
	 */
	public static boolean pwdVal(String pwd, String pwdAgain) {
		if (pwd.equals(pwdAgain))
			return true;
		else {
			return false;
		}
	}

	/**
	 * 将double型的金钱数转换成两位小数的String型
	 * 
	 * @param money
	 * @return
	 */
	public static String doubleToString(double money) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(money);
	}

	/**
	 * 将小数转换成百分数
	 * 
	 * @param decimal
	 * @return
	 */
	public static String doubleToPercentage(double decimal) {
		DecimalFormat df = new DecimalFormat("0.0%");
		return df.format(decimal);
	}

	/**
	 * 
	 * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
	 * 
	 * @param str
	 *            无逗号的数字
	 * 
	 * @return 加上逗号的数字
	 */

	public static String addComma(String str) {

		// 将传进数字反转
		String reverseStr = new StringBuilder(str).reverse().toString();

		String strTemp = "";

		for (int i = 0; i < reverseStr.length(); i++) {
			if (i * 3 + 3 > reverseStr.length()) {
				strTemp += reverseStr.substring(i * 3, reverseStr.length());
				break;
			}

			strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
		}

		// 将[789,456,] 中最后一个[,]去除
		if (strTemp.endsWith(",")) {
			strTemp = strTemp.substring(0, strTemp.length() - 1);
		}

		// 将数字重新反转
		String resultStr = new StringBuilder(strTemp).reverse().toString();
		return resultStr;
	}

	/**
	 * MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5Encode(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @param token
	 * @return
	 */
	public static String encrypt(String password, String token) {
		// 截取token第6位开始的6位字符串
		String _token = token.substring(6, 12);
		// 密码MD5加密
		String md5Password = MD5Encode(password);
		// 拼接字符串
		String new_password = _token + md5Password;
		// 将拼接后的字符串转换成char[]
		char cPwd[] = new_password.toCharArray();
		// 得到当前日期
		int iTime = Integer.valueOf(TimeUtil.now_time());
		// 当前日期与255求余
		int s = iTime % 255;

		StringBuilder completeString = new StringBuilder();
		// 将求余后的值与char[]与或操作
		for (int i = 0; i < cPwd.length; i++) {
			char temp = (char) (cPwd[i] ^ s);
			completeString.append(Integer.toHexString((int) temp));
		}

		return completeString.toString();
	}

	public static int getUserId(Context context) {
		return (Integer) SPUtil.get(context, "user_id", 0, "LOGIN_DATE");
	}

	public static void setUserId(Context context, int user_id) {
		SPUtil.put(context, "user_id", user_id, "LOGIN_DATE");
	}

	public static String getToken(Context context) {
		return (String) SPUtil.get(context, "token", "", "LOGIN_DATE");
	}

	public static void setToken(Context context, String token) {
		SPUtil.put(context, "token", token, "LOGIN_DATE");
	}

	public static int getCustomerId(Context context) {
		return (Integer) SPUtil.get(context, "customer_id", 0, "LOGIN_DATE");
	}

	public static void setCustomerId(Context context, int customer_id) {
		SPUtil.put(context, "customer_id", customer_id, "LOGIN_DATE");
	}

	public static String getName(Context context) {
		return (String) SPUtil.get(context, "name", "", "LOGIN_DATE");
	}

	public static void setName(Context context, String name) {
		if (isNotEmpty(name)) {
			SPUtil.put(context, "name", name, "LOGIN_DATE");
		}
	}

	public static String getHeadPic(Context context) {
		return (String) SPUtil.get(context, "head_pic", "", "LOGIN_DATE");
	}

	public static void setHeadPic(Context context, String head_pic) {
		if (isNotEmpty(head_pic)) {
			SPUtil.put(context, "head_pic", head_pic, "LOGIN_DATE");
		}
	}
}
