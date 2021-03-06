package com.example.auctionapp.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 计算类
 * @author mengjia
 * @date 2019/8/29
 * @version 1.0
 * @see
 */
@Slf4j
public class CalcUtils {
    public static final BigDecimal ZERO = new BigDecimal("0.00");
    //统一精度
    public static final int UTIL_SCALE = 2;
    //佣金比例
    public static final BigDecimal COMMISSION_PROPORTION = new BigDecimal("0.05");
    //基础系数
    public static final BigDecimal BADGE_COEFFICIENT_BASIC = new BigDecimal("1.00");
    //一级系数
    public static final BigDecimal BADGE_COEFFCIENT_LEVEL_1 =  new BigDecimal("1.01");
    //二级系数
    public static final BigDecimal BADGE_COEFFCIENT_LEVEL_2 =  new BigDecimal("1.02");
    //三级系数
    public static final BigDecimal BADGE_COEFFCIENT_LEVEL_3 =  new BigDecimal("1.03");
    //四级系数
    public static final BigDecimal BADGE_COEFFCIENT_LEVEL_4 =  new BigDecimal("1.04");
    //五级系数
    public static final BigDecimal BADGE_COEFFCIENT_LEVEL_5 =  new BigDecimal("1.05");
    //100拍卖值
    public static final BigDecimal COMMISSION_VALUE_100 = new BigDecimal("100.00");
    //100徽章值
    public static final BigDecimal BADGE_VALUE_100 = new BigDecimal("100.00");
    //200徽章值
    public static final BigDecimal BADGE_VALUE_200 = new BigDecimal("200.00");
    //300徽章值
    public static final BigDecimal BADGE_VALUE_300 = new BigDecimal("300.00");
    //400徽章值
    public static final BigDecimal BADGE_VALUE_400 = new BigDecimal("400.00");
    //500徽章值
    public static final BigDecimal BADGE_VALUE_500 = new BigDecimal("500.00");
    //600徽章值
    public static final BigDecimal BADGE_VALUE_600 = new BigDecimal("600.00");
    //700徽章值
    public static final BigDecimal BADGE_VALUE_700 = new BigDecimal("700.00");
    //800徽章值
    public static final BigDecimal BADGE_VALUE_800 = new BigDecimal("800.00");
    //900徽章值
    public static final BigDecimal BADGE_VALUE_900 = new BigDecimal("900.00");
    //1000徽章值
    public static final BigDecimal BADGE_VALUE_1000 = new BigDecimal("1000.00");
    //好友徽章等级与ID映射
    public static Map<BigDecimal,Integer> friendEmblemLevelIdMap;
    //贡献徽章等级与ID映射
    public static Map<BigDecimal,Integer> ctrbEmblemLevelIdMap;
    static{
        friendEmblemLevelIdMap = new HashMap<>();
        friendEmblemLevelIdMap.put(BADGE_COEFFICIENT_BASIC,11);
        friendEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_1,1);
        friendEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_2,2);
        friendEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_3,3);
        friendEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_4,4);
        friendEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_5,5);

        ctrbEmblemLevelIdMap = new HashMap<>();
        ctrbEmblemLevelIdMap.put(BADGE_COEFFICIENT_BASIC,12);
        ctrbEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_1,6);
        ctrbEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_2,7);
        ctrbEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_3,8);
        ctrbEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_4,9);
        ctrbEmblemLevelIdMap.put(BADGE_COEFFCIENT_LEVEL_5,10);
    }

    /**
     * @description 精度控制
     * @author mengjia
     * @date 2019/8/29
     * @param value 需进行精度控制的BigDecimal值
     * @param scale 精度值
     * @return java.math.BigDecimal 精度控制后的BigDecimal值
     * @throws
     **/
    public static BigDecimal precisionConvert(BigDecimal value,int scale){
        if(value == null){
            return null;
        }
        try{
            BigDecimal resultValue = value.setScale(scale,BigDecimal.ROUND_HALF_UP);
            return resultValue;
        }catch(NumberFormatException e){
            log.warn("" + e);
        }
        return null;
    }
    /**
     * @description 根据贡献徽章值得出加成系数1.00-1.05
     * @author mengjia
     * @date 2019/8/29
     * @param ctrbBadgeValue 贡献徽章值
     * @return java.math.BigDecimal 贡献徽章加成系数
     * @throws
     **/
    public static BigDecimal calcCtrbBadgeCoefficient(BigDecimal ctrbBadgeValue){
        if(ctrbBadgeValue.compareTo(ZERO) < 0){
            log.warn("输入值小于零");
            return null;
        }
        ctrbBadgeValue = precisionConvert(ctrbBadgeValue,UTIL_SCALE);
        if(ctrbBadgeValue.compareTo(ZERO) >= 0
                && ctrbBadgeValue.compareTo(BADGE_VALUE_100) < 0) {
            return BADGE_COEFFICIENT_BASIC;
        }
        if(ctrbBadgeValue.compareTo(BADGE_VALUE_100) >= 0
                && ctrbBadgeValue.compareTo(BADGE_VALUE_200) < 0){
            return BADGE_COEFFCIENT_LEVEL_1;
        }
        if(ctrbBadgeValue.compareTo(BADGE_VALUE_200) >= 0
                && ctrbBadgeValue.compareTo(BADGE_VALUE_300) < 0){
            return BADGE_COEFFCIENT_LEVEL_2;
        }
        if(ctrbBadgeValue.compareTo(BADGE_VALUE_300) >= 0
                && ctrbBadgeValue.compareTo(BADGE_VALUE_400) < 0){
            return BADGE_COEFFCIENT_LEVEL_3;
        }
        if(ctrbBadgeValue.compareTo(BADGE_VALUE_400) >= 0
                && ctrbBadgeValue.compareTo(BADGE_VALUE_500) < 0){
            return BADGE_COEFFCIENT_LEVEL_4;
        }else{
            return BADGE_COEFFCIENT_LEVEL_5;
        }
    }
    public static BigDecimal calcFriendBadgeCoefficient(BigDecimal friendBadgeValue){
        if(friendBadgeValue.compareTo(ZERO) < 0){
            log.warn("输入值小于零");
            return null;
        }
        friendBadgeValue = precisionConvert(friendBadgeValue,UTIL_SCALE);
        if(friendBadgeValue.compareTo(ZERO) >= 0
                && friendBadgeValue.compareTo(BADGE_VALUE_200) < 0){
            return BADGE_COEFFICIENT_BASIC;
        }
        if(friendBadgeValue.compareTo(BADGE_VALUE_200) >= 0
                && friendBadgeValue.compareTo(BADGE_VALUE_400) < 0){
            return BADGE_COEFFCIENT_LEVEL_1;
        }
        if(friendBadgeValue.compareTo(BADGE_VALUE_400) >= 0
                && friendBadgeValue.compareTo(BADGE_VALUE_600) < 0){
            return BADGE_COEFFCIENT_LEVEL_2;
        }
        if(friendBadgeValue.compareTo(BADGE_VALUE_600) >= 0
                && friendBadgeValue.compareTo(BADGE_VALUE_800) < 0){
            return BADGE_COEFFCIENT_LEVEL_3;
        }
        if(friendBadgeValue.compareTo(BADGE_VALUE_800) >= 0
                && friendBadgeValue.compareTo(BADGE_VALUE_1000) < 0){
            return BADGE_COEFFCIENT_LEVEL_4;
        }else{
            return BADGE_COEFFCIENT_LEVEL_5;
        }
    }
    /**
     * @description 根据应支付拍豆、贡献徽章系数、好友徽章系数和好友助力值计算拍卖值
     * 拍卖值基数 = 实际支付拍豆（佣金） + 赠豆 = 应支付拍豆
     * 拍卖值公式：拍卖值基数 +贡献徽章加成拍卖值 +好友徽章加成拍卖值 +好友徽章加成拍卖值 + 好友助力拍卖值
     * 贡献徽章加成拍卖值 = 拍卖值基数*(贡献徽章系数 - 1)
     * 如果是0级，但是拍卖值基数>100，那么大于100的部分贡献徽章系数是1.01
     * 好友徽章加成拍卖值 = 拍卖值基数*(好友徽章系数 -1)

     * @author mengjia
     * @date 2019/8/29
     * @param shouldPayBeans   应支付拍豆
     * @param ctrbBadgeCoefficient    贡献徽章系数
     * @param friendBadgeCoefficient  好友徽章系数
     * @param friendAsstValue   好友助力值
     * @return java.math.BigDecimal 满足要求时，返回计算结果，精度为2；否则返回null
     * @throws
     **/
    public static BigDecimal calcAuctionValue(BigDecimal shouldPayBeans,BigDecimal ctrbBadgeCoefficient,
                                              BigDecimal friendBadgeCoefficient,BigDecimal friendAsstValue){
        if(shouldPayBeans.compareTo(ZERO) <= 0
                || ctrbBadgeCoefficient.compareTo(BADGE_COEFFICIENT_BASIC) < 0
                || friendBadgeCoefficient.compareTo(BADGE_COEFFICIENT_BASIC) < 0
                || friendAsstValue.compareTo(ZERO) < 0){
            log.warn("输入参数有误。");
            return null;
        }
        BigDecimal auctionValue = ZERO;
        if(ctrbBadgeCoefficient.compareTo(BADGE_COEFFICIENT_BASIC) == 0
                && shouldPayBeans.compareTo(COMMISSION_VALUE_100) > 0) {
            //拍卖值基数大于100的贡献徽章系数加成
            BigDecimal temp1 = shouldPayBeans.subtract(COMMISSION_VALUE_100)
                    .multiply((BADGE_COEFFCIENT_LEVEL_1.subtract(BADGE_COEFFICIENT_BASIC)));
            //好友贡献徽章系数加成 + 好友助力值
            BigDecimal temp2 = shouldPayBeans.multiply((friendBadgeCoefficient.subtract(BADGE_COEFFICIENT_BASIC))).add(friendAsstValue);
            //拍卖值 = 拍卖值基数 + 拍卖值基数*(贡献徽章系数 - 1) + 拍卖值基数*(好友徽章系数 - 1)
            auctionValue = shouldPayBeans.add(temp1).add(temp2);
            log.info("拍卖值 = " + precisionConvert(auctionValue,UTIL_SCALE));
            return precisionConvert(auctionValue,UTIL_SCALE);
        }else{
            //拍卖值公式：拍卖值基数 +贡献徽章加成拍卖值 +好友徽章加成拍卖值 +好友徽章加成拍卖值 + 好友助力拍卖值
            auctionValue = shouldPayBeans.add(
                    shouldPayBeans.multiply((ctrbBadgeCoefficient.subtract(BADGE_COEFFICIENT_BASIC)))
                                        ).add(shouldPayBeans.multiply((friendBadgeCoefficient.subtract(BADGE_COEFFICIENT_BASIC)))
                                        ).add(friendAsstValue);
            log.info("拍卖值 = " + precisionConvert(auctionValue,UTIL_SCALE));
            return precisionConvert(auctionValue,UTIL_SCALE);
        }
    }

    /**
     * @description 根据用户应支付拍豆、贡献徽章系数、好友徽章系数和好友助力值计算拍卖值，其中好友助力值不填时，默认为0.00
     * @author mengjia
     * @date 2019/9/7
     * @param shouldPayBeans   应支付拍豆
     * @param ctrbBadgeCoefficient    贡献徽章系数
     * @param friendBadgeCoefficient  好友徽章系数
     * @return java.math.BigDecimal 满足要求时，返回计算结果，精度为2；否则返回null
     * @throws
     **/
    public static BigDecimal calcAuctionValue(BigDecimal shouldPayBeans,BigDecimal ctrbBadgeCoefficient,
                                              BigDecimal friendBadgeCoefficient){
        return calcAuctionValue(shouldPayBeans,ctrbBadgeCoefficient,friendBadgeCoefficient,new BigDecimal("0.00"));
    }
}
