package cn.bjjoy.web.bms.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 创建consumer需要的messageListener
 * @author bjjoy
 * @date 2018/2/4
 **/
public class MqMessageListener implements MessageListenerConcurrently{

    private MqMessageProcessor mqMessageProcessor;

    public void setMqMessageProcessor(MqMessageProcessor mqMessageProcessor) {
        this.mqMessageProcessor = mqMessageProcessor;
    }

    public MqMessageListener(MqMessageProcessor mqMessageProcessor) {
        this.mqMessageProcessor = mqMessageProcessor;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs){
            boolean result = mqMessageProcessor.handleMessage(msg);
            if (!result){
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
