package com.cloud.askwalking.gateway.pipline.context.api;

import com.alibaba.cloud.dubbo.service.DubboGenericServiceFactory;
import com.cloud.askwalking.client.exception.ErrorCode;
import com.cloud.askwalking.core.constants.GatewayConstant;
import com.cloud.askwalking.core.context.GatewayInvokeContext;
import com.cloud.askwalking.gateway.pipline.AbstractGatewayContextHandler;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Set;

/**
 * @author niuzhiwei
 */
@Slf4j
public class BeforeContextHandler extends AbstractGatewayContextHandler implements ApplicationContextAware {

    private final Set<String> handleTypes = Sets.newHashSet(GatewayConstant.API);

    private ApplicationContext applicationContext;

    @Override
    public boolean handleGatewayInvoke(GatewayInvokeContext gatewayInvokeContext) {

        try {
            gatewayInvokeContext.setServiceFactory(this.applicationContext.getBean(DubboGenericServiceFactory.class));
            gatewayInvokeContext.preBuild();
        } catch (BeansException e) {
            log.error("[BeforeContextHandler] Exception in building Dubbo metadata：", e);
            return putDebugErrorResult(gatewayInvokeContext, ErrorCode.SYSTEM_ERROR);
        }

        return true;
    }

    @Override
    public Set<String> handleType() {
        return this.handleTypes;
    }

    @Override
    public int getOrder() {
        return 40;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
