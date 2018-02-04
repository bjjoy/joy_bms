package cn.bjjoy.web.bms.rocketmq.service;

import cn.bjjoy.web.bms.rocketmq.MqMessageProcessor;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * 具体消费信息处理过程，例更新数据库
 * @author bjjoy
 * @date 2018/2/4
 **/
@Service
public class UserMqMessageService implements MqMessageProcessor {

    @Override
    public boolean handleMessage(MessageExt messageExt) {
        try {
            System.out.println("consumer-service==============");
            System.out.println(new String(messageExt.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
