package com.abhi.spring.security.demo.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.abhi.spring.security.demo.domain.view.AssetView;

@Component
public class AssetValidator implements Validator<AssetView> {
	private static final Logger logger = LoggerFactory.getLogger(AssetValidator.class);

	@Override
	public boolean validate(AssetView assetView) {
		Errors errors = new BeanPropertyBindingResult(assetView, assetView.getClass().getName());
		ValidationUtils.rejectIfEmpty(errors, "name", "asset.name.empty");
		logger.debug("AssetValidator.validate Asset name empty");
		return false;
	}
}