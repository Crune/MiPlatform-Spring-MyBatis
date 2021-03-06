package com.jlab.mi.platform;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.tobesoft.platform.PlatformResponse;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.VariableList;

@Component("miView")
public class MiVR extends AbstractView {

	protected Log log = LogFactory.getLog(this.getClass());

	protected VariableList miVariableList = new VariableList();
	protected DatasetList miDatasetList = new DatasetList();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PlatformData platformData = new PlatformData(miVariableList, miDatasetList);

		this.setMiResultMessage((String) model.get("MiResultCode"), (String) model.get("MiResultMsg"));

		Object vo = model.get("MiDTO");
		String dsName = (String) model.get("OutDsName");
		if (dsName == null) {
			dsName = "ds_output";
		}
		Dataset dataset = new Dataset(dsName);

		if (vo != null) {
			if (java.util.List.class.isAssignableFrom(vo.getClass())) {
				
				// 결과가 LIST 형태로 넘어왔을 경우
				dataset = Mi.list2ds(dsName, (java.util.List) vo);
				miDatasetList.addDataset(dataset);
				
			} else if (java.util.Map.class.isAssignableFrom(vo.getClass())) {
				
				// 결과가 MAP 형태로 넘어왔을 경우
				Map<String, List> map = ((Map) vo);
				for (String key : map.keySet()) {
					dataset = Mi.list2ds(key, (java.util.List) map.get(key));
					miDatasetList.addDataset(dataset);
				}
				
			} else {
				
				// 결과가 VO 형태로 넘어왔을 경우
				dataset = Mi.vo2ds(dsName, vo);
				miDatasetList.addDataset(dataset);
				
			}
			log.info("ResMiData: "+vo);
		}
		try {

			new PlatformResponse(response).sendData(platformData);

		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("Exception occurred while writing xml to MiPlatform Stream.", ex);
			}
			throw new Exception();
		}

	}
	
	public void setMiResultMessage(String code, String Msg) {
		miVariableList.addStr("ErrorCode", code);
		miVariableList.addStr("ErrorMsg", Msg);
	}

}
