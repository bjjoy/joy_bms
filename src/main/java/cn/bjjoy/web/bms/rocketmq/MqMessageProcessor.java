package cn.bjjoy.web.bms.rocketmq;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author bjjoy
 * @date 2018/2/4
 **/
public interface MqMessageProcessor {

    /**
     * 处理消息的接口
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);
}
