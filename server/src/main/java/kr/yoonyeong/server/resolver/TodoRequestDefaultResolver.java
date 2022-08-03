package kr.yoonyeong.server.resolver;

import kr.yoonyeong.server.annotation.TodoRequestDefault;
import kr.yoonyeong.server.dto.TodoRequestDTO;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author rival
 * @since 2022-08-03
 */
@Component
public class TodoRequestDefaultResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(TodoRequestDefault.class) != null;
    }

    @Override
    public Object resolveArgument(@Nullable MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  @Nullable NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {

        try{

            String page = webRequest.getParameter("page");
            String size = webRequest.getParameter("size");

            if(page==null || size == null){
                return new TodoRequestDTO();
            }

            int p = Integer.parseInt(page);
            int s = Integer.parseInt(size);
            return new TodoRequestDTO(
                p < 1 ? 0 : p-1
                , Math.max(s, 10));
        }catch(Exception e){
            return new TodoRequestDTO();
        }

    }


}
