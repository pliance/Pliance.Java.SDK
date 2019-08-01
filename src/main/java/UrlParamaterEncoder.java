import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UrlParamaterEncoder {
	public static String Encode(Object obj)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if (obj == null) {
			return "";
		}

		Map<String, Object> map = new HashMap<String, Object>();

		UrlEncode(map, null, obj, null);

		if (map.size() == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();

		builder.append("?");

		boolean first = true;

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (!first) {
				builder.append("&");
			}

			builder.append(HtmlEncode(entry.getKey()));
			builder.append("=");
			builder.append(HtmlEncode(entry.getValue().toString()));
			first = false;
		}

		return builder.toString();
	}

	private static String HtmlEncode(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	private static boolean IsPrimitive(Class type) {
		return type == Double.class || type == Float.class || type == Long.class || type == Integer.class
				|| type == Short.class || type == Character.class || type == Byte.class || type == Boolean.class
				|| type == String.class;
	}

	private static void UrlEncode(Map<String, Object> map, String path, Object obj, Field prop)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		System.out.println("Path: " + path);

		if (obj == null) {
			return;
		}

		if (prop != null && IsPrimitive(obj.getClass())) {
			map.put(path, obj);
		}
        else if (prop!= null && obj instanceof Collection)
        {
            int index = 0;
            Collection<?> collection = (Collection<?>)obj;
            
            for (Object element : collection)
            {
            	Box box = new Box(element);
            	
            	UrlEncode(map, String.format("%s[%d]", index++), element, box.getClass().getField("Item"));
            }
        }
		else {
			for (Field field : obj.getClass().getDeclaredFields()) {
				String name = field.getName();
				int modifiers = field.getModifiers();

				if (!Modifier.isPublic(modifiers)) {
					continue;
				}

				if (path != null) {
					name = path + "." + name;
				}

				Object value = field.get(obj);

				if (value == null) {
					continue;
				}

				UrlEncode(map, name, value, field);
			}
		}
	}
}
