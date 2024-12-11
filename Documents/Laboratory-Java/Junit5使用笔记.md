# Junit5使用笔记
***
demo源码参见：laboratory-java

## 依赖包
***
* 简单粗暴
  * org.junit.jupiter:junit-jupiter
  * org.junit.platform:junit-platform-launcher(添加此依赖可直接运行单元测试类以及方法)
* 精细定制
  * org.junit.jupiter:junit-jupiter-api
  * org.junit.jupiter:junit-jupiter-engine
  * org.junit.jupiter:junit-jupiter-params(参数化测试)
  * org.junit.platform:junit-platform-launcher(添加此依赖可直接运行单元测试类以及方法)

## pom文件配置
***
```
<!--  单元测试  -->
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.11.3</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.junit.platform</groupId>
  <artifactId>junit-platform-launcher</artifactId>
  <version>1.11.3</version>
  <scope>test</scope>
</dependency>
```

## 样例代码
***
```
package com.xianyun.laboratory;

import com.xianyun.laboratory.converts.ConvertString;
import com.xianyun.laboratory.enums.TestEnum;
import com.xianyun.laboratory.providers.arguments.CustomArgumentsProvider;
import com.xianyun.laboratory.utils.image.ImageMergeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class ImageMergeUtilsTests {
	
	/**
	 * 基本使用
	 */
	@Test
	public void contextLoads() {
		Assertions.assertTrue(ImageMergeUtils.isEmpty("1"));
	}
	
	/**
	 * 基本类型的参数数组
	 */
	@ParameterizedTest
	@ValueSource(strings = {"111", "", "null"})
	public void contextLoadParamsBasic(String str) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(str));
	}
	
	/**
	 * 自动类型转换
	 * 无法转换有异常：ParameterResolutionException
	 */
	@ParameterizedTest
	@ValueSource(strings = {"111", "", "2"})
	public void contextLoadParamsBasic2(int str) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(String.valueOf(str)));
	}
	
	/**
	 * 自动类型转换
	 * 自定义类型转换
	 */
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	public void contextLoadParamsConvert(@ConvertWith(ConvertString.class) String str) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(str));
	}
	
	/**
	 * 提供多个参数，以 CSV 的形式传递。
	 */
	@ParameterizedTest
	@CsvSource({"apple, 5", "banana, 6", "cherry, 6"})
	public void contextLoadParamsCSV(String fruit, int price) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(fruit));
	}
	
	/**
	 * 从 CSV 文件中读取参数。
	 * 文件路径：相对于 src/test/resources。
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/fruits.csv", numLinesToSkip = 1)
	void contextLoadParamsCSVFile(String fruit, int length) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(fruit));
	}
	
	/**
	 * 用于枚举类型的测试。
	 */
	@ParameterizedTest
	@EnumSource(TestEnum.class)
	void testEnum(TestEnum actual) {
		Assertions.assertEquals("C", actual.name());
	}
	
	/**
	 * 从工厂方法动态生成参数。
	 */
	@ParameterizedTest
	@MethodSource("provideStringsForTesting")
	void testWithMethodSource(String input) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(input));
	}
	public static Stream<String> provideStringsForTesting() {
		return Stream.of("test1", "test2", "test3");
	}
	
	/**
	 * 自定义参数提供器。一个参数
	 */
	@ParameterizedTest
	@ArgumentsSource(CustomArgumentsProvider.class)
	void testWithCustomArgumentsProvider(String fruit) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(fruit));
	}
	
	/**
	 * 自定义参数提供器。两个参数
	 */
	@ParameterizedTest
	@ArgumentsSource(CustomArgumentsProvider.class)
	void testWithCustomArgumentsProvider(String fruit, int price) {
		Assertions.assertTrue(ImageMergeUtils.isEmpty(fruit));
	}
	
}

```