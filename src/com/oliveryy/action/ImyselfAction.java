package com.oliveryy.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;
@Component
public class ImyselfAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IDiscussionService discusstionService;
	public String excute(){
		String id=getSession().getAttribute("id").toString();
		Map user=userService.getUserInfo(id);
		Map[] tasks=taskService.getUTasks(id);
		for(int i=0;i<tasks.length;i++){
			String dl=tasks[i].get("task_deadline").toString();
			DateFormat df=new SimpleDateFormat();
			try {
				Date dld=df.parse(dl);
				Date now=new Date();
				if(dld.after(now)){
					tasks[i].put("delayed", false);
				}else{
					tasks[i].put("delayed", true);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map[] reports=discusstionService.getDiscussions(id, "r");
		JSONObject juser=JSONObject.fromObject(user);
		JSONArray jtask=JSONArray.fromObject(tasks);
		JSONArray jreports=JSONArray.fromObject(reports);
		String result="{id:"+id+",userinfo:"+juser.toString()+",tasks:"+jtask.toString()+",reports:"+jreports.toString()+"}";
		getWriter().write(result);
		return null;
	}
}
