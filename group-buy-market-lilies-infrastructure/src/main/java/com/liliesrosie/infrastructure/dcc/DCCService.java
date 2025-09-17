package com.liliesrosie.infrastructure.dcc;


import com.liliesrosie.types.annotations.DCCValue;
import com.liliesrosie.types.common.Constants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-19 10:45
 */
@Service
public class DCCService {

    /**
     * 降级开关 0关闭、1开启
     */
    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    @DCCValue("whiteList:1")
    private String whiteList;

    @DCCValue("scBlacklist:s02c02")
    private String scBlacklist;

    @DCCValue("cacheSwitch:0")
    private String cacheOpenSwitch;

    /**
     * Cache switch: 0 means enabled, 1 means disabled.
     */
    public boolean isCacheOpenSwitch(){
        return "0".equals(cacheOpenSwitch);
    }

    public boolean isDowngradeSwitch() {
        return "1".equals(downgradeSwitch);
    }

    // 用户id是否属于 x%, x = cutRange
    public boolean isCutRange(String userId) {
        // 计算哈希码的绝对值
        int hashCode = Math.abs(userId.hashCode());

        // 获取最后两位
        int lastTwoDigits = hashCode % 100;

        // 判断是否在切量范围内
        if (lastTwoDigits <= Integer.parseInt(cutRange)) {
            return true;
        }

        return false;
    }

    private List<String> getWhiteList(){
        return Arrays.asList(whiteList.split(Constants.SPLIT));
    }

    public boolean isInWhiteList(String tagId){
        return this.getWhiteList().contains(tagId);
    }

    public boolean isSCBlackIntercept(String source, String channel){
        List<String> list = Arrays.asList(scBlacklist.split(Constants.SPLIT));
        return list.contains(source + channel);
    }
}
