/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum.database.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MSI 65 SERIES
 */
@Controller
@ResponseBody
public class MyController {
    
    C2022ws data = new C2022ws ();
    C2022wsJpaController dctrl = new C2022wsJpaController();
    @RequestMapping("/getNama")
    
    public String getNameByid(){
        try {data = dctrl.findC2022ws(1);}
            catch (Exception error){}
                 
            return data.getName();
                    
    } 
    @RequestMapping("/getBorn")
    
    public Date getBornByid(){
        try {data = dctrl.findC2022ws(1);}
            catch (Exception error){}
                 
            return data.getBirthdate();
                    
    }
    @RequestMapping("/GET")
    
    public String getDataByid(){
        List<C2022ws> datas = new ArrayList();
        try {datas = dctrl.findC2022wsEntities();}
            catch (Exception error){}
            return datas.toString();
                    
    }
    @RequestMapping("/getData/{id}")
    
    public String getData(@PathVariable("id")int id){
        try {data = dctrl.findC2022ws(id);}
            catch (Exception error){}
                 
            String result = data.getName() + "<br>" + data.getBirthdate().toString();
            return result;
                    
    }
    
    @RequestMapping(value="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
       String message="no action";
       String json_receive = kiriman.getBody();
       ObjectMapper mapper = new ObjectMapper();
       C2022ws data = new C2022ws();
       
       data = mapper.readValue(json_receive, C2022ws.class);
       dctrl.create(data);
       message = data.getName()+"Saved";
       return message;
    }
    
    @RequestMapping(value="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
       String message="no action";
       String json_receive = kiriman.getBody();
       ObjectMapper mapper = new ObjectMapper();
       C2022ws data = new C2022ws();
       
       data = mapper.readValue(json_receive, C2022ws.class);
       dctrl.edit(data);
       message = data.getName()+"Saved";
       return message;
    }
    
    @RequestMapping(value="/DELETE", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpServletRequest request) throws Exception{
       String message="no action";
       String id = request.getParameter("id");
       int idDelete= Integer.parseInt(id);
       dctrl.destroy(idDelete);
       return request.getParameter("id");
    }
}