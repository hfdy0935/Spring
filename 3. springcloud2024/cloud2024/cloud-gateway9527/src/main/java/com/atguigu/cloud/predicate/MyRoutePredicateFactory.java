package com.atguigu.cloud.predicate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hf-dy
 * @date 2024/9/3 14:51
 */

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }


    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (userType == null) return false;
                return userType.equals(config.getUserType());
            }
        };
    }


    @Setter
    @Getter
    @Validated
    public static class Config {
        @NotEmpty
        private String userType;
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
