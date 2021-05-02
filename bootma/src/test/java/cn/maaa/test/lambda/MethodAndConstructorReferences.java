package cn.maaa.test.lambda;

import cn.hutool.core.io.LineHandler;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.VoidFunc0;
import cn.maaa.common.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/5/2 18:52
 */
public class MethodAndConstructorReferences{
    public static void main(String[] args) {

        LineHandler lineHandler = Integer::valueOf;
        Func1<Person, String> getFirstName = Person::getFirstName;

        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        //System.out.println(converted.getClass());   //class java.lang.Integer

        try {
            VoidFunc0 voidFunc0 = Person::new;
            voidFunc0.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

       /** PersonFactory<Person> personFactory = Person::new; 等价于
         PersonFactory<Person> personFactory = (firstName,lastName) ->  new Person(firstName,lastName );
         相当于创建了PersonFactory的匿名内部类实现接口create的逻辑为 return  new Person(firstName,lastName )*/
       // PersonFactory<Person> personFactory = (firstName,lastName) ->  new Person(firstName,lastName );
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);

       /* Person person = new Person();
        PersonFactory<Person> personFactory = person::showFirstName;
        String ma = personFactory.printFirstName("ma");
        System.out.println(ma);*/
    }
}


@Getter
@Setter
class Person {
    String firstName;
    String lastName;

    Person() {
        System.out.println("无参数Person实例化");
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String showFirstName(String firstName){
        return firstName+"@";
    }


    @Override
    public String toString(){
        return JsonUtils.beanToJson(this);
    }
}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);

   // String printFirstName(String firstName);
}

