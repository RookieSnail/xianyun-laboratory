package com.xianyun.laboratory.providers.arguments;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * 一句话描述一下类的用途
 *
 * @Author He Bingxing
 * @Date 2024-12-11
 */
public class CustomArgumentsProvider implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
		// 一个参数
		return Stream.of(Arguments.of(""), Arguments.of("banana"));
		//多个参数
//		return Stream.of(Arguments.of("",1), Arguments.of("banana",2));
	}
}
