package org.springblade.modules.iot.service.script.infra.groovy

productInfo.setId(100);
println "hello world.";
println(productInfo);
if (productInfo.getId() == 100) {
    productInfo.setId(500);
}