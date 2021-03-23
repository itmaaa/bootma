package cn.maaa.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.impl.PrimitiveConverter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * Enum转换单元测试
 */
public class EnumConvertTest {

	@Test
	public  void test03 () {
		/**
		 * The {@code int} value representing the {@code private}
		 * modifier.
		 */
		 final int PRIVATE          = 0x00000002;

		/**
		 * The {@code int} value representing the {@code final}
		 * modifier.
		 */
		 final int FINAL            = 0x00000010;


		 int number = 0x00000010;

		System.out.println(number & FINAL);
		System.out.println(number & PRIVATE);

		System.out.println(Integer.toBinaryString((Modifier.classModifiers())));
		System.out.println( Integer.toBinaryString(EnumConvertTest.class.getModifiers()));

		int modifiers = EnumConvertTest.class.getModifiers() & Modifier.classModifiers();
		System.out.println(Integer.toBinaryString(modifiers));

		if (modifiers != 0) {
			System.out.println(Modifier.toString(modifiers));
		}
	}


	@Test
	public void test04(){
        ArrayList<Integer> objects = new ArrayList();
        objects.add(1);
        objects.add(2);
        Object objects1 = objects;

        boolean instance = List.class.isInstance(objects1);
        System.out.println(instance);

        List cast = List.class.cast(objects1);
        System.out.println(cast);

        PrimitiveConverter primitiveConverter = new PrimitiveConverter(int.class);
        System.out.println(ClassUtil.getTypeArgument(primitiveConverter.getClass()));
        System.out.println(primitiveConverter.getTargetType());
    }

    @Test
    public  void test05(){
	    String[] arr = {"a","b","c"};
        System.out.println(arr.getClass().isArray());
        System.out.println(arr.getClass().getComponentType());
        System.out.println("============================");

        ArrayList<Integer> list = new ArrayList();
        System.out.println(list.getClass() instanceof Class);
        System.out.println(TypeUtil.getTypeArgument(list.getClass()));
        System.out.println(TypeUtil.getTypeArgument(list.getClass()) instanceof TypeVariable);
        System.out.println("============================");


        TypeReference<ArrayList<Integer>> typeReference = new TypeReference<ArrayList<Integer>>() {
        };
        System.out.println(typeReference.getType() instanceof ParameterizedType);
        System.out.println(((ParameterizedType)typeReference.getType()).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedType)typeReference.getType()).getRawType());
        System.out.println(((ParameterizedType)typeReference.getType()).getOwnerType());
        System.out.println("============================");



    }


	@Test
	public  void test06() throws NoSuchMethodException {
		//AtomicReference convert = Convert.convert(AtomicReference.class, 1);
		//System.out.println(convert.get());
		System.out.println(modifiersToInt(ModifierUtil.ModifierType.PUBLIC,ModifierUtil.ModifierType.PRIVATE));

		Method method = EnumConvertTest.class.getDeclaredMethod("ddd");
		System.out.println(ModifierUtil.hasModifier(method, ModifierUtil.ModifierType.PUBLIC,ModifierUtil.ModifierType.PROTECTED));
		System.out.println(ModifierUtil.hasModifier(method, ModifierUtil.ModifierType.PRIVATE));
	}
	private static void ddd() {
	}

	private static int modifiersToInt(ModifierUtil.ModifierType... modifierTypes) {
		int modifier = modifierTypes[0].getValue();
		for(int i = 1; i < modifierTypes.length; i++) {
			modifier &= modifierTypes[i].getValue();
		}
		return modifier;
	}

	class  A extends  AtomicReference<Integer>{

	}

	@Test
	public void test07(){
		ArrayList<Integer> list = new ArrayList<>();
         Integer[]arr = {1,2,3};
		List<Integer> list1 = Arrays.asList(arr);
		list1.add(0);
	}



}
