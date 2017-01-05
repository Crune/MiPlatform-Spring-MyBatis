package com.jlab.mi.platform;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tobesoft.platform.PlatformRequest;

public class MiAR implements HandlerMethodArgumentResolver {

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return MiDTO.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

		MiDTO dto = new MiDTO();
		PlatformRequest platformRequest = null;

		try {
			platformRequest = new PlatformRequest(request);
			platformRequest.receiveData();
		} catch (IOException e) {
			e.getStackTrace();
		}

		dto.setVariableList(platformRequest.getVariableList());
		dto.setDatasetList(platformRequest.getDatasetList());

		log.info("ReqMiData: "+dto);
		
		return dto;
	}
}
