package cn.bjjoy.web.bms.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @author bjjoy
 * @date 2018/2/4
 **/
public abstract class BaseMqConsumer {

    /**
     * 消费组名称
     * @return
     */
    public abstract String getConsumerGroup();

    /**
     * NameSrvAddr
     * @return String
     */
    public abstract String getNameSrvAddr();

    /**
     * topic
     * @return
     */
    public abstract String getTopic();

    /**
     * tag或者subExpression
     * @return String
     */
    public abstract String getSubExpression();

    public DefaultMQPushConsumer getConsumer(MqMessageProcessor mqMessageProcessor) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(getConsumerGroup());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr(getNameSrvAddr());
        consumer.subscribe(getTopic(), getSubExpression());
        consumer.registerMessageListener(new MqMessageListener(mqMessageProcessor));
        consumer.start();
        return consumer;
    }
}
