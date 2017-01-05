package com.jlab.mi.platform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.springframework.web.servlet.ModelAndView;
import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;

public class Mi {

	public static void setResult(ModelAndView mav, String code, String msg) {
		setResult(mav, null, code, msg);
	}

	public static void setResult(ModelAndView mav, Object rst, String code, String msg) {
		mav.addObject("MiDTO", rst);
		mav.addObject("MiResultCode", code);
		mav.addObject("MiResultMsg", msg);
	}

	public static void setResult(ModelAndView mav, String dsName, Object rst, String code, String msg) {
		mav.addObject("MiDTO", rst);
		mav.addObject("OutDsName", dsName);
		mav.addObject("MiResultCode", code);
		mav.addObject("MiResultMsg", msg);
	}

	public static Dataset vo2ds(String dsName, Object vo) {
		Dataset dataset = new Dataset(dsName);
		appendVo(dataset, vo);
		return dataset;
	}

	public static void appendVo(Dataset dataset, Object vo) {
		Class<? extends Object> cls = vo.getClass();
		Field[] field = cls.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String cName = field[i].getName();

			if (!"serialVersionUID".equals(field[i].getName()) && dataset.getColumnIndex(cName) < 0)
				dataset.addColumn(field[i].getName(), ColumnInfo.COLTYPE_STRING, (short) 255);
		}
		int row = dataset.appendRow();
		for (int i = 0; i < field.length; i++) {
			if (!"serialVersionUID".equals(field[i].getName())) {
				String memberName = field[i].getName();
				String methodName = "get" + memberName.substring(0, 1).toUpperCase()
						+ memberName.substring(1, memberName.length());
				Method m;
				String ret = null;
				try {
					m = cls.getMethod(methodName);
					ret = (String) m.invoke(vo);
				} catch (Exception e) {
					e.printStackTrace();
				}

				dataset.setColumn(row, memberName, ret);
			}
		}
	}

	public static void inject(Object target, Object org)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = target.getClass().getMethods();

		Map<String, Method> getter = new HashMap<String, Method>();
		Map<String, Method> setter = new HashMap<String, Method>();

		for (Method m : methods) {
			if (m.getName().length() > 3) {
				String head = m.getName().substring(0, 3);
				String name = m.getName().substring(3);
				if (head.equals("set"))
					setter.put(name, m);
				if (head.equals("get"))
					getter.put(name, m);
			}
		}

		for (String curMethodName : setter.keySet()) {
			if (getter.containsKey(curMethodName)) {
				Object data = getter.get(curMethodName).invoke(org);
				setter.get(curMethodName).invoke(target, data);
			}
		}
	}

	public static Object map2vo(Map<?, ?> map, Class<? extends Object> cls) {
		Object rst = null;
		try {
			rst = cls.newInstance();
			Method[] methods = cls.getMethods();
			Map<String, Method> setter = new HashMap<String, Method>();
			for (Method m : methods) {
				if (m.getName().length() > 3) {
					String head = m.getName().substring(0, 3);
					String name = m.getName().substring(3).toLowerCase();
					if (head.equals("set"))
						setter.put(name, m);
				}
			}

			for (String curMethodName : setter.keySet()) {
				String data = (String) map.get(curMethodName);
				if (data != null) {
					setter.get(curMethodName).invoke(rst, data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rst;
	}
	
	public static List<?> crudSelect(String type, Map<?, ?> map, Class<? extends Object> cls) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Dataset list2ds(String dsName, List<?> list) {
		Dataset dataset = new Dataset(dsName);

		Iterator<Map<?, ?>> iterator = (Iterator<Map<?, ?>>) list.iterator();
		while (iterator.hasNext()) {
			appendVo(dataset, iterator.next());
		}

		return dataset;
	}
}
