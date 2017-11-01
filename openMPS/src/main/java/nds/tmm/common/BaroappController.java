/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

package nds.tmm.common;

import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * @author dbongman
 *
 */
@Controller
public class BaroappController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaroappController.class);

	@RequestMapping(
			value = "/app/save", 
			method = RequestMethod.POST)
	public void params(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException
	{
		logger.debug("API /app/save");
		
		String filename = req.getParameter("filename");
		String startup = req.getParameter("startup");
		if( startup != null && filename == null )
		{
			logger.debug("Startup HTML Length : "+startup.length());
			logger.debug(startup);
			
			session.setAttribute("startup", startup);
			session.setAttribute("filename", null);
		}
		else if( filename != null )
		{
			String data = req.getParameter("data");
			session.setAttribute("filename", filename);
			logger.debug("File name : "+filename);
			logger.debug("File size : "+data.length());
			logger.debug(data);
			data = data.replace("&quot;", "\"");
			logger.debug(data);
			session.setAttribute("data", data);
		}
		
		res.setStatus( HttpServletResponse.SC_OK );
	}
	
	@RequestMapping(
			value = "/app/load", 
			method = RequestMethod.GET)
	public String runner(HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session) throws ParseException, IOException
	{
		String body = (String) session.getAttribute("startup");
		String filename = (String) session.getAttribute("filename");
		
		logger.debug("API /app/load");
		
		if( body != null && filename == null )
		{
			// 페이지는 보이나 한글이 깨짐
			// 바로앱에서 전송시에는 어떤 처리를 하든 관계 없이 한글은 다 깨져서 보임
			//body = java.net.URLDecoder.decode(body, StandardCharsets.UTF_8.name());
			// 페이지가 안보임
			//body = java.net.URLEncoder.encode(body, "UTF-8");
			// 위 처리는 필요없음. JSP 에서 ContentType을 설정해서 한글깨지는 처리 해결. <%@ page contentType="text/xml; charset=utf-8" %> 
			
			logger.debug(body);
			model.addAttribute("baroapp", body);
		}
		else if( filename != null )
		{
			this.writeFile( res, filename, (String) session.getAttribute("data") );
			model.addAttribute("baroapp", "<script>window.close();</script>");
		}
		
		res.setStatus(HttpServletResponse.SC_OK);
		
		//return "baroapp";
		return null;
	}

	protected JSONObject parse(String src) throws ParseException
	{
		JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
		JSONObject obj = (JSONObject) parser.parse( src );

		return obj;
	}
	
	protected Model initBaroappParams(Model model, JSONObject params)
	{
		model.addAttribute( "debugMode", params.get("debugMode"));
		model.addAttribute( "uiBaseVer", params.get("uiBaseVer"));

		model.addAttribute( "cookerAppVer", params.get("cookerAppVer"));
		model.addAttribute( "baangappAppVer", params.get("baangappAppVer"));
		model.addAttribute( "baroappAppVer", params.get("baroappAppVer"));
		model.addAttribute( "signerAppVer", params.get("signerAppVer"));
				
		model.addAttribute( "resource ", params.get("resource"));
		model.addAttribute( "file ", params.get("file"));
		model.addAttribute( "app ", params.get("app"));
		model.addAttribute( "api ", params.get("api"));
		model.addAttribute( "oauth ", params.get("oauth"));
		model.addAttribute( "unime ", params.get("unime"));
		model.addAttribute( "launcher ", params.get("launcher"));
		model.addAttribute( "baang ", params.get("baang"));
				
		model.addAttribute( "svc_id", params.get("svc_id"));
		model.addAttribute( "con_id", params.get("con_id"));
				
		model.addAttribute( "appId", params.get("appId"));
				
		model.addAttribute( "id", params.get("id"));
		model.addAttribute( "state", params.get("state"));
		model.addAttribute( "name", params.get("name"));
		model.addAttribute( "icon", params.get("icon"));
		model.addAttribute( "desc", params.get("desc"));
				
		model.addAttribute( "skeleton", params.get("skeleton"));
		
		return model;
	}
	
	protected void writeFile(HttpServletResponse res, String filename, String data) throws IOException
	{
		String contentType = new MimetypesFileTypeMap().getContentType(filename);
		byte filedata[] = data.getBytes();
		
		logger.debug("writeFile() - Filename : "+filename);
		logger.debug("writeFile() - Content type : "+contentType);
		logger.debug("writeFile() - File size : "+filedata.length);
		logger.debug("writeFile() - File data : "+data);
		
		res.reset();
		res.setContentType(contentType);
		res.setHeader("Content-Description", "T-Platform App Resource Builder");
		res.setHeader("Content-Type", contentType+"; charset=utf-8");
		res.setHeader("Content-Length", ""+filedata);
		res.setHeader("Content-Disposition", "attachment; filename="+filename);
		
		ServletOutputStream os = res.getOutputStream();
		os.write( data.getBytes() );
		os.close();
	}
}
