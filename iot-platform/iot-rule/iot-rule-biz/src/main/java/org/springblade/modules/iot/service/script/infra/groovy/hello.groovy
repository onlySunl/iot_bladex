package com.mqttsnet.thinglinks.service.script.infra.groovy

productInfo.setId(100);
println "hello world.";
println(productInfo);
if (productInfo.getId() == 100) {
    productInfo.setId(500);
}