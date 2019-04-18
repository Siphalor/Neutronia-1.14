package team.hollow.neutronia.event_system;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Reflect {
    public static Class getInnerClass(Class clazz, String name) {
        Class[] declaredClasses = clazz.getDeclaredClasses();
        for (Class c : declaredClasses) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public static Object constructClass(Class clazz, Object... args) {
        Constructor<?>[] cap = clazz.getDeclaredConstructors();
        for (Constructor<?> c : cap) {
            Class<?>[] types = c.getParameterTypes();
            boolean match = true;
            for (int t = 0; t < types.length; t++) {
                Class<?> c_type = types[t];
                if (args[t] == null)
                    continue;
                if (c_type.isPrimitive()) {
                    try {
                        if (args[t].getClass().getField("TYPE").get(null).equals(c_type))
                            continue;
                    } catch (Exception ignored) {}
                    match = false;
                    break;
                }
                if (!(c_type.isAssignableFrom(args[t].getClass()))) {
                    match = false;
                    break;
                }
            }
            if (match) {
                c.setAccessible(true);
                try {
                    return c.newInstance(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	/*public static Object getMemberByType(Class field_class, Class object_class)
	{
		return getMemberByType(field_class, object_class, null);
	}*/

    public static Field getFieldByType(Class field_class, Class object_class) {
        Field[] fields = object_class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().equals(field_class)) {
                return f;
            }
        }
        return null;
    }


    public static Object getMemberByType(Class field_class, Class object_class, Object object) {
        Field f = getFieldByType(field_class, object_class);
        try {
            if (f != null) {
                return f.get(object);
            }
        } catch (Exception ignored) {
        }
        return null;
		
		/*Field[] fields =object_class.getDeclaredFields();
		for(int i =0; i < fields.length; i++)
		{
			Field f = fields[i];
			f.setAccessible(true);
			if(f.getType().equals(field_class))
			{
				try
				{
					/*if (Modifier.isStatic(f.getModifiers()))
					{
						return f.get(null);
					}else*/
					/*{
						return f.get(object);
					}
				} catch (Exception e)
				{
				}
			}
		}
		return null;*/
    }


    public static Object[] getMemberArrayByType(Class type, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().isArray()) {
                Class ofArray = f.getType().getComponentType();
                if (ofArray.equals(type)) {
                    try {
                        return (Object[]) f.get(object);
                    } catch (Exception ignored) {

                    }
                }
            }
        }
        return null;
    }
}
