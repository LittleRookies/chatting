package com.demo.chatting.chattingbean.enem;

public enum TypeEnum {
    A("A", "directExchange", "receiveA", "receiveQueueA"),
    B("B", "directExchange", "receiveB", "receiveQueueB");
    private final String type;
    private final String exchange;
    private final String bind;
    private final String queue;

    TypeEnum(String type, String exchange, String bind, String queue) {
        this.type = type;
        this.exchange = exchange;
        this.bind = bind;
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public String getBind() {
        return bind;
    }

    public String getQueue() {
        return queue;
    }

    public String getType() {
        return type;
    }

    public static TypeEnum getByType(String type) {
        for (TypeEnum types : TypeEnum.values()) {
            if (types.getType().equals(type)) {
                return types;
            }
        }
        return null;
    }

}
