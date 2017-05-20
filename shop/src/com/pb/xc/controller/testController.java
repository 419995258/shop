package com.pb.xc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.entity.Student;


@RestController
@RequestMapping("/testC")
@Scope("prototype")
public class testController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Message deleteGroup(@RequestBody Student student) throws Exception {
        Message message = new Message();
        message.setSuccess(true);
        Map<String, Object> result =new HashMap<>();
        result.put("first", 555);
        result.put("seco", 132);
        message.setResult(result);
        return message;
    }
    
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public JSONObject deleteGroup2() throws Exception {
    	JSONArray array = new JSONArray();
    	JSONObject json = null;
    	json = new JSONObject();
    	json.put("name", "a");
    	json.put("age", 15);
    	json.put("sex", "男");
    	array.add(json);
    	json = new JSONObject();
    	json.put("name", "b");
    	json.put("age", 14);
    	json.put("sex", "女");
    	array.add(json);
    	JSONObject json2 = new JSONObject();
    	json2.put("students", array);
        return json2;
    }
}
