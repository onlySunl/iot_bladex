/**
 * BladeX Secure 扩展：Feign 内部调用跳过 Token 校验
 * <p>
 * 识别 Feign 请求中的超级令牌（Blade-Auth: ak-xxx）或 X-Feign-Internal 标识头，
 * 跳过 TokenInterceptor 权限校验。
 * 通过实现 ISecureHandler 接口注册到 SecureConfiguration 中生效。
 * </p>
 */
package org.springblade.common.secure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.handler.ISecureHandler;
import org.springblade.core.secure.handler.BladeSecureHandler;
import org.springblade.core.secure.interceptor.TokenInterceptor;
import org.springblade.core.secure.nonce.NonceStore;
import org.springblade.core.secure.props.AuthSecure;
import org.springblade.core.secure.props.BasicSecure;
import org.springblade.core.secure.props.BladeSecureProperties;
import org.springblade.core.secure.props.SignSecure;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * Feign 内部调用安全处理器
 * <p>
 * 扩展 BladeX 默认的 BladeSecureHandler，在 Token 校验前检查是否为 Feign 内部调用。
 * 支持两种内部调用识别方式：
 * 1. 超级令牌模式：Blade-Auth 头以 ak- 开头
 * 2. 兼容旧模式：X-Feign-Internal: true
 * </p>
 */
@Slf4j
@Component
public class FeignInternalSecureHandler implements ISecureHandler {

	public static final String FEIGN_INTERNAL_HEADER = "X-Feign-Internal";
	public static final String FEIGN_INTERNAL_VALUE = "true";

	private static final String SUPER_TOKEN_PREFIX = "ak-";

	private final ISecureHandler delegate = new BladeSecureHandler();

	@Override
	public HandlerInterceptor tokenInterceptor(BladeSecureProperties secureProperties) {
		TokenInterceptor nativeInterceptor = new TokenInterceptor(secureProperties);

		return new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
									 Object handler) throws Exception {
				// 超级令牌模式
				String bladeAuth = request.getHeader("Blade-Auth");
				boolean isSuperToken = bladeAuth != null && bladeAuth.startsWith(SUPER_TOKEN_PREFIX);
				// 兼容旧模式
				boolean isFeignInternal = FEIGN_INTERNAL_VALUE.equals(request.getHeader(FEIGN_INTERNAL_HEADER));

				if (isSuperToken || isFeignInternal) {
					if (log.isDebugEnabled()) {
						log.debug("[Secure] Feign内部调用({})，跳过Token校验: {} {}",
							isSuperToken ? "超级令牌" : "内部标识", request.getMethod(), request.getRequestURI());
					}
					return true;
				}
				return nativeInterceptor.preHandle(request, response, handler);
			}
		};
	}

	@Override
	public HandlerInterceptor authInterceptor(BladeSecureProperties secureProperties, List<AuthSecure> authSecures) {
		return delegate.authInterceptor(secureProperties, authSecures);
	}

	@Override
	public HandlerInterceptor basicInterceptor(List<BasicSecure> basicSecures) {
		return delegate.basicInterceptor(basicSecures);
	}

	@Override
	public HandlerInterceptor signInterceptor(List<SignSecure> signSecures, NonceStore nonceStore) {
		return delegate.signInterceptor(signSecures, nonceStore);
	}

	@Override
	public HandlerInterceptor clientInterceptor(String clientCode) {
		return delegate.clientInterceptor(clientCode);
	}
}
