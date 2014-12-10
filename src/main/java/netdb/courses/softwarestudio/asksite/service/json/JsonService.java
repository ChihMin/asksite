package netdb.courses.softwarestudio.asksite.service.json;

import com.alibaba.fastjson.JSON;

public class JsonService {

	public static String serialize(Object obj) {

		return JSON.toJSONString(obj);

	}

	public static <T> T deserialize(String str, Class<T> cls) {

		return JSON.parseObject(str, cls);

	}

}
