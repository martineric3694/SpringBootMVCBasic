package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ParamModel;

@Controller
@RequestMapping("hello")
public class HelloWorldController {

	@RequestMapping(path = "",method = RequestMethod.GET)
	public String getModel() {
		return "hello";
	}
	
	@RequestMapping(path = "model",method = RequestMethod.GET)
	public String passModel(Model model) {
		model.addAttribute("message", "Hello Model");
		return "model";
	}
	
	@RequestMapping(path = "param/{param}",method = RequestMethod.GET)
	public String passModel(@PathVariable(name = "param")String param,Model model) {
		model.addAttribute("message", param);
		return "model";
	}
	
	@RequestMapping(path = "form",method = RequestMethod.GET)
	public String showForm() {
		return "form";
	}
	
	@RequestMapping(path = "form",method = RequestMethod.POST)
	public String showResult(@RequestParam(name = "nama")String nama, @RequestParam(name = "kelas")String kelas,Model model) {
		Map<String,String> result = new HashMap<String, String>();
		result.put("nama", nama);
		result.put("kelas", kelas);
		model.addAttribute("result", result);
		
//		int umurInt = Integer.parseInt(umur);
//		model.addAttribute("nama", nama);
//		model.addAttribute("umur", umurInt);
		return "result";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++) {
			result.add(i);
		}
		model.addAttribute("list", result);
		return "collection/list";
	}
	
	@RequestMapping("/listModel")
	public String listModel(Model model) {
		List<ParamModel> result = new ArrayList<ParamModel>();
		for(int i = 0 ; i < 10 ; i++) {
			ParamModel mdl = new ParamModel();
			mdl.setNama("nama "+i);
			mdl.setKelas(i);
			result.add(mdl);
		}
		model.addAttribute("list", result);
		return "collection/listModel";
	}
	
	@RequestMapping("/parammodel")
	public String model(Model model) {
		ParamModel pm = new ParamModel();
		pm.setNama("AdIns");
		pm.setKelas(100);
		model.addAttribute("message", "Param Model");
		model.addAttribute("object", pm);
		return "parammodel";
	}
	
	@RequestMapping("formmodel")
	public String formModel(Model model) {
		model.addAttribute("paramModel", new ParamModel());
		return "formModel";
	}
	
	@RequestMapping(path = "formmodel",method = RequestMethod.POST)
	public String postModel(@ModelAttribute ParamModel paramModel,Model model) {
		System.out.println(paramModel.getNama());
		model.addAttribute("result", paramModel);
		return "result";
	}
}
