package com.jlab.mi;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.jlab.mi.platform.Mi;
import com.jlab.mi.platform.MiDTO;


@Controller
public class EduController {

	@Autowired
	private EduService serv;

	@Resource(name = "miView")
	private View miView;
	
	@RequestMapping(value = "/base_sawon_sel.jsp")
	public ModelAndView base_sawon_sel(MiDTO in, String name) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			List<SawonDto> rst = serv.findSawon(name);
			Mi.setResult(mav, "ds_sawon", rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/base_sawon_tr.jsp")
	public ModelAndView base_sawon_tr(MiDTO in) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			// 입력된 수정사항 DB 반영 
			serv.modifySawon(in);
			
			// 수정된 결과물 반환
			List<SawonDto> rst = serv.findSawon("");
			
			Mi.setResult(mav, "ds_sawon", rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/base_code_sel.jsp")
	public ModelAndView base_code_sel(MiDTO in) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			@SuppressWarnings("unchecked")
			Map<String, List<?>> rst = serv.getInitCode();
			Mi.setResult(mav, rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		return mav;
	}
}
