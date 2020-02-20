package com.abhi.spring.security.demo.mapper;

import org.springframework.stereotype.Component;

import com.abhi.spring.security.demo.domain.Asset;
import com.abhi.spring.security.demo.domain.view.AssetView;

@Component
public class AssetMapper implements Mapper<Asset, AssetView> {
	
	@Override
	public AssetView mapFrom(Asset i) {
		return new AssetView();
	}

	@Override
	public Asset mapTo(AssetView o) {
		return new Asset();
	}
}
