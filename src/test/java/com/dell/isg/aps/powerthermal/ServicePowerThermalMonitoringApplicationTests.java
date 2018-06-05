/**
 * Copyright � 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.aps.powerthermal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dell.isg.aps.powerthermal.controller.model.DCIM_SystemView;
import com.dell.isg.smi.adapter.server.inventory.IInventoryAdapter;
import com.dell.isg.smi.adapter.server.inventory.InventoryAdapterImpl;
import com.dell.isg.smi.adapter.server.model.WsmanCredentials;
import com.dell.isg.smi.adapter.server.powerthermal.IPowerThermalAdapter2_0;
import com.dell.isg.smi.adapter.server.powerthermal.PowerThermalAdapterImpl2_0;
import com.dell.isg.smi.wsman.command.entity.DCIMSystemViewType;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.TypeRef;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;



//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ServicePowerThermalMonitoringApplicationTests {

     @Test
    public void contextLoads() {

        String address = "100.68.123.32";
        String username = "root";
        String password = "calvin";   	
        
        try {
        	com.jayway.jsonpath.Configuration configuration = com.jayway.jsonpath.Configuration.builder()
    				.mappingProvider(new JacksonMappingProvider()).jsonProvider(new JacksonJsonProvider()).build();
        	
        	WsmanCredentials wsmanCredentials = new WsmanCredentials(address, username, password);
        	IPowerThermalAdapter2_0 adapter = new PowerThermalAdapterImpl2_0();
        	
        	//IInventoryAdapter inventoryAdapter = new InventoryAdapterImpl();
        	
        	//DCIMSystemViewType
        	
        	/*Map<String, Object> result = (Map<String, Object>) adapter.collectPowerMonitoring(wsmanCredentials);       	
        	TypeRef<DCIM_SystemView> typeRef = new TypeRef<DCIM_SystemView>() {};       	
        	DCIM_SystemView dcimSystemView = JsonPath.using(configuration).parse(result.get("DCIM_SystemView")).read("$", typeRef);       	
        	System.out.println("DCIM_SystemView PowerCAP: \n" + dcimSystemView.getPowerCap());*/
        	
        	Map<String, Object> result = (Map<String, Object>) adapter.collectPowerMonitoring(wsmanCredentials);
        	//DCIM_SystemView
        	//DCIM_BaseMetricValue
        	//DCIM_AggregationMetricValue
        	//DCIM_BIOSEnumeration
        	
        	ObjectMapper mapperObj = new ObjectMapper();
        	String jsonString = mapperObj.writeValueAsString(result.get("DCIM_BIOSEnumeration"));
        	
        	System.out.println("DCIM_BIOSEnumeration: \n" + jsonString);
        	
        	//JsonPath.using(configuration).parse(jsonString).read("$.PowerCap");      	
        	
        	
        	/*System.out.println("PowerCap: \n" + JsonPath.using(configuration).parse(jsonString).read("$..*[?(@.InstanceID =='DCIM:System:Point:PowerHdrm:Cont')]"));
        	
        	List<Map<String, Object>> t = JsonPath.using(configuration).parse(jsonString).read("$..*[?(@.InstanceID =='DCIM:System:Point:PowerHdrm:Cont')]");
        	
        	Map<String, Object> map = t.get(0);
        	
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        	}*/
        	
        	//System.out.println("PowerCap: \n" + JsonPath.using(configuration).parse(jsonString).read("$..*[?(@.InstanceID =='DCIM:System:Min:Power:1H'||'DCIM:System:Max:Power:Cont')]"));
        	
        	//List<Map<String, Object>> t = JsonPath.using(configuration).parse(jsonString).read("$..*[?(@.InstanceID =='DCIM:System:Max:Power:Cont'|| @.InstanceID =='DCIM:System:Max:PowerHdrm:Cont')]");
        	
        	/*List<Map<String, Object>> t = JsonPath.parse(jsonString).read("$..*[?(@.InstanceID =='DCIM:System:Max:Power:Cont'|| @.InstanceID =='DCIM:System:Max:PowerHdrm:Cont')]");
        	
        	for (Map<String, Object> map : t) {
        	    for (Map.Entry<String, Object> entry : map.entrySet()) {
        	    	System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        	    }
        	}*/
        	
        	System.out.println("PowerCap: \n" + JsonPath.parse(jsonString).read("$..*[?(@.AttributeName =='SysProfile')]"));
        	
        	


        	
        	
            
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

}
