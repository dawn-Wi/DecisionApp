package com.dawn.decisionApp;

public enum util {
    kakaoKey("824bd5644947b7a1d8f98c8f0037f9ed"),

    naverClientId("yYOf8JMgh876A7kRQDMI"),
    naverKey("52nb8l1Dd6");

    private final String value;

    util(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
