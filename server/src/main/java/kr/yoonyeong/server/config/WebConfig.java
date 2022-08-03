package kr.yoonyeong.server.config;

import kr.yoonyeong.server.resolver.TodoRequestDefaultResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author rival
 * @since 2022-08-03
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TodoRequestDefaultResolver todoRequestDefaultResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(todoRequestDefaultResolver);
    }
}
