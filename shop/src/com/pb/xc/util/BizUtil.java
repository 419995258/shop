package com.pb.xc.util;

import java.util.Random;

public class BizUtil {
	/**
	 * 服务项目类型
	 */
	public static final String DIC_TYPE1 = "service";
	
	/**
	 * 专业技能类型
	 */
	public static final String DIC_TYPE2 = "skill";
	
	/**
	 * 用户类型 - 普通用户
	 */
	public static final Integer USER_TYPE1 = 1;
	
	/**
	 * 用户类型 - 志愿者用户
	 */
	public static final Integer USER_TYPE2 = 2;
	
	/**
	 * 捐助类型 - 患儿
	 */
	public static final Integer CONTRIBUTION_TYPE1 = 1;
	
	/**
	 * 捐助类型 - 志愿者
	 */
	public static final Integer CONTRIBUTION_TYPE2 = 2;
	
	/**
	 * 完成注册状态 待审核
	 */
	public static final String WCZC_STYLE1 = "1";
	
	/**
	 * 完成注册状态 审核中
	 */
	public static final String WCZC_STYLE2 = "2";
	
	/**
	 * 完成注册状态 审核驳回
	 */
	public static final String WCZC_STYLE3 = "3";
	
	/**
	 * 完成注册状态 已审核
	 */
	public static final String WCZC_STYLE4 = "4";
	
	/**
	 * 完成注册状态 作废
	 */
	public static final String WCZC_STYLE5 = "5";
	
	/**
	 * 身份类型 组织管理员
	 */
	public static final Integer MANAGER_TYPE1 = 1;

	/**
	 * 身份类型 组织负责人
	 */
	public static final Integer MANAGER_TYPE2 = 2;
	
	/**
	 * 身份类型 成员
	 */
	public static final Integer MANAGER_TYPE3 = 3;
	
	/**
	 * 身份类型 出纳
	 */
	public static final Integer MANAGER_TYPE4 = 4;
	
	/**
	 * 身份类型 会计
	 */
	public static final Integer MANAGER_TYPE5 = 5;
	
	/**
	 * 身份类型 待审核
	 */
	public static final Integer MANAGER_TYPE6 = 6;
	
	/**
	 * 项目负责人
	 */
	public static final Integer PROJECT_MANAGE_TYPE1 = 1;
	
	/**
	 * 项目管理员
	 */
	public static final Integer PROJECT_MANAGE_TYPE2 = 2;
	
	/**
	 * 项目成员
	 */
	public static final Integer PROJECT_MANAGE_TYPE0 = 0;
	
	/**
	 * 审批状态 未处理
	 */
	public static final Integer SP_TYPE0 = 0;
	
	/**
	 * 审批状态 待处理
	 */
	public static final Integer SP_TYPE1 = 1;
	
	/**
	 * 审批状态 同意
	 */
	public static final Integer SP_TYPE2 = 2;
	
	/**
	 * 审批状态 拒绝
	 */
	public static final Integer SP_TYPE3 = 3;
	
	/**
	 * 审批主流程 待审批
	 */
	public static final Integer SP_Z_TYPE0 = 0;
	
	/**
	 * 审批主流程 通过
	 */
	public static final Integer SP_Z_TYPE1 = 1;
	
	/**
	 * 审批主流程 驳回
	 */
	public static final Integer SP_Z_TYPE2 = 2;
	
	/**
	 * 还款状态 未还款
	 */
	public static final Integer REPAYMENT_TYPE0 = 0;
	
	/**
	 * 还款状态 已还款
	 */
	public static final Integer REPAYMENT_TYPE1 = 1;
	
	private BizUtil() {
	}
	
	public static String buildLikeName(String name) {
		return "%" + name.trim() + "%";
	}
	
	public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
	
	/**
	 * 生成指定位数字符产编码
	 * @param Len
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public static String contrastOrgCode2(int Len) throws NumberFormatException, Exception {
		String[] baseString={"0","1","2","3",
                "4","5","6","7","8","9",
                "a","b","c","d","e",
                "f","g","h","i","j",
                "k","l","m","n","o",
                "p","q","r","s","t",
                "u","v","w","x","y",
                "z","A","B","C","D",
                "E","F","G","H","I",
                "J","K","L","M","N",
                "O","P","Q","R","S",
                "T","U","V","W","X","Y","Z"};
        Random random = new Random();
        int length=baseString.length;
        String randomString="";
        for(int i=0;i<length;i++){
            randomString+=baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr="";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length()-1));
        }
        return resultStr;
	}
}
