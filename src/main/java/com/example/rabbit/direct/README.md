#RabbitMQ的生产者---消费者
1、此处的消息生产与消费，消费者与生产者绑定同一个队列，生产者负责往队列中发送消息，消费者负责从队列中取出消息去消费  
2、此处有三个相同的消费者同时监听当前队列glliu.sms20201205，如果没有添加channel.basicQos(int)参数的话（参数值得含义是每次当前消费者拉取多少条消息），则三个消费者会对当前队列轮询拉取消息。消息1->consumer1，消息2->consumer2，消息3->consumer3，如果添加了这个参数，则根据消费能力，消费完一次拉取过来的消息才能再去消息队列拉取消息（根据消费能力拉取消息）  