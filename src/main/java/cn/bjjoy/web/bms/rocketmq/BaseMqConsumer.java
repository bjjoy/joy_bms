package cn.bjjoy.web.bms.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * 创建consumer基础类
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

    /**
     * 添加消费者监听消息
     * @param mqMessageProcessor
     * @return DefaultMQPushConsumer 返回值没有意义，主要是方法里边内容
     * @throws InterruptedException
     * @throws MQClientException
     */
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
