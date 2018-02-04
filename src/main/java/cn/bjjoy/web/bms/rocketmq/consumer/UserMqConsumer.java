package cn.bjjoy.web.bms.rocketmq.consumer;

import cn.bjjoy.web.bms.rocketmq.BaseMqConsumer;
import cn.bjjoy.web.bms.rocketmq.service.UserMqMessageService;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bjjoy
 * @date 2018/2/3
 **/
@Configuration
@ConfigurationProperties(prefix = "joy.rocketmq")
public class UserMqConsumer extends BaseMqConsumer {

    private final String consumerGroup = "user_group";

    private final String nameSrvAddr = "127.0.0.1:9876";

    private final String topic = "user_topic";

    private final String subExpression = "TagA";

    @Autowired
    UserMqMessageService userMqMessageService;

    @Override
    public String getConsumerGroup() {
        return consumerGroup;
    }

    @Override
    public String getNameSrvAddr() {
        return nameSrvAddr;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getSubExpression() {
        return subExpression;
    }

    @Bean
    public DefaultMQPushConsumer getUserConsumer() throws MQClientException, InterruptedException {
        return super.getConsumer(userMqMessageService);
    }
}
