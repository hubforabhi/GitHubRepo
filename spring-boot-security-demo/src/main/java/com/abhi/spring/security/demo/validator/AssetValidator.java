package com.abhi.spring.security.demo.validator;

import org.springframework.stereotype.Component;

import com.abhi.spring.security.demo.domain.view.AssetView;

@Component
public class AssetValidator implements Validator<AssetView> {
	@Override
	public boolean validate(AssetView t) {
		return false;
	}
}
