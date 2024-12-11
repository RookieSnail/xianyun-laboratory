package com.xianyun.laboratory.converts;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

/**
 * 一句话描述一下类的用途
 *
 * @Author He Bingxing
 * @Date 2024-12-11
 */
public class ConvertString extends SimpleArgumentConverter {
	@Override
	protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
		return String.valueOf(source);
	}
}
